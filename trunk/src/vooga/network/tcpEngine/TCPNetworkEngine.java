package vooga.network.tcpEngine;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import vooga.network.NetworkEngine;


public class TCPNetworkEngine implements NetworkEngine
{
	private int port = Constants.HostPort;
	private ConnectInfo myInfo;
	private List<Connection> connectionList;
	private List<Object> receivedList;
	private boolean isInternetHost;
	private boolean startGame;

	private boolean closed;
	private ServerSocket serverSocket;
	private int gameSize;

	public TCPNetworkEngine(int port)
	{
		this.port = port;
		connectionList = Collections
				.synchronizedList(new LinkedList<Connection>());
		receivedList = Collections.synchronizedList(new LinkedList<Object>());
		try {
			myInfo = new ConnectInfo(InetAddress.getLocalHost()
					.getHostAddress(), "userName");
		} catch (Exception e) {
			e.printStackTrace();
		}
		closed = false;
		isInternetHost = false;
		startGame = false;
		gameSize = 20;
	}

	/**
	 * return value false indicates successful connection
	 */
	@Override
	public boolean connect(String IP)
	{
		Connection c = new Connection();
		try {
			c.socket = new Socket(IP, port);
			connectionList.add(c);
		} catch (Exception e) {
			return true;
		}
		try {
			c.output = new ObjectOutputStream(c.socket.getOutputStream());
			c.output.flush();
			c.output.writeObject(ConnectionControl.INFORMATION);
			c.output.writeObject(myInfo);
			c.output.flush();
			c.input = new ObjectInputStream(c.socket.getInputStream());
			(new Thread(new ReceiveRunnable(c, this))).start();
			closed = false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean createLocalHost(boolean fullyConnect)
	{
		try {
			serverSocket = new ServerSocket(port, 10);
			// start to listen to the UDP broadcast
			BroadcastListener listener = new BroadcastListener();
			listener.start();

			ServerListener listenerThread = new ServerListener(serverSocket,
					this, fullyConnect);
			(new Thread(listenerThread)).start();
			closed = false;
		} catch (IOException e) {
			e.printStackTrace();
			return true;
		}
		return false;
	}

	@Override
	public List<Object> update()
	{
		List<Object> rtn;
		synchronized (receivedList) {
			rtn = new LinkedList<Object>(receivedList);
			receivedList.clear();
		}

		return rtn;
	}

	@Override
	public void disconnect()
	{
		send(ConnectionControl.STOP);
		isInternetHost = false;

		// if internet connection type, tell the server to eliminate itself
		if (isInternetHost) {
			Socket connection = null;
			ObjectOutputStream output = null;
			String ControlMsg = Constants.DisconnectHostPrefix + ",";
			try {
				// connect to the server, sent local info to the server
				connection = new Socket(Constants.ServerIP,
						Constants.ServerPort);
				output = new ObjectOutputStream(connection.getOutputStream());

				InetAddress clientAddress = InetAddress.getLocalHost();
				ControlMsg = ControlMsg + clientAddress.getHostAddress();

				output.writeObject(ControlMsg);

			} catch (Exception e) {

			}
		}

		synchronized (connectionList) {
			for (Connection c : connectionList) {
				try {
					c.input.close();
					c.output.close();
					c.socket.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			connectionList.clear();
		}

		try {
			if (serverSocket != null)
				serverSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		closed = true;
	}

	@Override
	public boolean send(Serializable obj)
	{
		synchronized (connectionList) {
			for (Connection c : connectionList) {
				try {
					c.output.writeObject(obj);
					c.output.flush();
				} catch (Exception e) {
					e.printStackTrace();
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public boolean isClosed()
	{
		return closed;
	}


	// for creation of Internet Host, add itself to the List of the server
	public boolean createInternetHost()
	{
		Socket connection = null;
		ObjectOutputStream output = null;
		String ControlMsg = Constants.CreateHostPrefix + ",";
		try {
			// connect to the server, sent local info to the server
			connection = new Socket(Constants.ServerIP, Constants.ServerPort);
			try {
				output = new ObjectOutputStream(connection.getOutputStream());

				InetAddress clientAddress = InetAddress.getLocalHost();
				ControlMsg = ControlMsg + clientAddress.getHostAddress();

				output.writeObject(ControlMsg);

				// start the host thread
				serverSocket = new ServerSocket(port, 10);
				ServerListener listenerThread = new ServerListener(
						serverSocket, this, false);
				(new Thread(listenerThread)).start();

				isInternetHost = true;
				closed = false;
			} finally {
				// TODO: no need to disconnect here
				// connection.close();
			}
		} catch (Exception e) {
			return true;
		}
		return false;
	}

	@Override
	// ask the server for the list and return it
	public List<ConnectInfo> searchInternetHost()
	{
		Socket connection = null;
		ObjectOutputStream output = null;
		ObjectInputStream input = null;
		String ControlMsg = Constants.RequestAvailableHostPrefix;
		try {
			connection = new Socket(Constants.ServerIP, Constants.ServerPort);
			try {
				output = new ObjectOutputStream(connection.getOutputStream());
				output.flush();
				input = new ObjectInputStream(connection.getInputStream());
				output.writeObject(ControlMsg);
				List<ConnectInfo> result = new ArrayList<ConnectInfo>();
				while (true) {
					Object received = input.readObject();
					if (received.equals(ConnectionControl.STOP))
						break;
					String ip = (String) received;
					result.add(new ConnectInfo(ip));
				}
				return result;
			} catch (ClassNotFoundException e) {

			} finally {
				connection.close();
			}
		} catch (Exception e) {
			// throw NetworkException.SERVER_NOT_EXIST;
			return null;
		}
		return null;
	}

	@Override
	public List<ConnectInfo> searchLocalHost()
	{
		// first boardcast UDP to all nearby computers
		Broadcast broadcastUDP = new Broadcast();
		broadcastUDP.start();
		DatagramSocket server = null;

		Set<String> availableServer = new HashSet<String>();
		List<ConnectInfo> availableServers = new ArrayList<ConnectInfo>();
		byte[] buffer = new byte[65507];

		try {
			server = new DatagramSocket(Constants.BroadcastResponsePort);
			DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
			server.setSoTimeout(Constants.SearchServerTimeOut);

			while (true) {
				server.receive(packet);
				String message = new String(packet.getData(), 0,
						packet.getLength());
				if (message.split(",").length < 2)
					continue;
				String token = message.split(",")[0];
				String serverIP = message.split(",")[1];
				if (token.equals(Constants.BroadcastResponsePrefix)
						&& !availableServer.contains(serverIP)) {
					System.out.println("returned from sever" + serverIP);
					availableServer.add(serverIP);
					availableServers.add(new ConnectInfo(serverIP, myInfo
							.getName()));
				}
			}
		} catch (Exception e) {
			System.out.println("exception occur");
		} finally {
			server.close();
		}
		return availableServers;
	}

	@Override
	public List<ConnectInfo> getConnectionInfo()
	{
		List<ConnectInfo> rtn = new LinkedList<ConnectInfo>();
		synchronized (connectionList) {
			for (Connection c : connectionList) {
				rtn.add(c.info);
			}
		}
		return rtn;
	}

	@Override
	public ConnectInfo getMyConnectInfo()
	{

		return myInfo;
	}

	@Override
	public int getConnectionSize()
	{
		return connectionList.size();
	}

	@Override
	public List<Object> getReceivedList()
	{
		return receivedList;
	}

	@Override
	public List<Connection> getConnectionList()
	{
		return connectionList;
	}

	@Override
	public void setStartGame(boolean v)
	{
		startGame = v;
	}

	@Override
	public boolean isStartGame()
	{
		return startGame;
	}

	public void fullyConnect()
	{
		synchronized (connectionList) {
			for (Connection c : connectionList) {
				try {
					c.output.writeObject(ConnectionControl.CONNECT);
					c.output.flush();
					c.output.writeObject(c.info);
					c.output.flush();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public void setGameSize(int size)
	{
		gameSize = size;
	}

	@Override
	public int getGameSize()
	{
		return gameSize;
	}

	public boolean spaceToJoin()
	{
		return gameSize > connectionList.size();
	}

	@Override
	public void setUserName(String userName)
	{
		myInfo.setName(userName);
	}
}
