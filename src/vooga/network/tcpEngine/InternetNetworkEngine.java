package vooga.network.tcpEngine;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.List;

public class InternetNetworkEngine extends AbstractNetworkEngine {

	ServerSocket HostRemainConnectivity;
	
	public InternetNetworkEngine(int port) {
		super(port);
	}

	public InternetNetworkEngine() {
		super(Constants.HostPort);
	}

	@Override
	public boolean createHost(boolean fullyConnect, boolean visiable) {
		if (!isConnected) {
			Socket connection = null;
			ObjectOutputStream output = null;
			String ControlMsg = Constants.CreateHostPrefix + ",";
			try {
				// connect to the server, sent local info to the server
				SocketAddress Server = new InetSocketAddress(Constants.ServerIP,
						Constants.ServerPort);
				connection = new Socket();
				connection.connect(Server, Constants.SearchServerTimeOut);

				try {
					//tell the server already online
					output = new ObjectOutputStream(
							connection.getOutputStream());

					InetAddress clientAddress = InetAddress.getLocalHost();
					ControlMsg = ControlMsg + clientAddress.getHostAddress();
					output.writeObject(ControlMsg);

					// start the host thread
					serverSocket = new ServerSocket(port, 10);
					ServerListener listenerThread = new ServerListener(
							serverSocket, this, false);
					(new Thread(listenerThread)).start();
					
					// start to listen to the check from server
					//HostRemainConnectivity = new ServerSocket(Constants.HostRemainConnectivityPort, 10);
					
					
					isConnected = true;
				} finally {
					// TODO: no need to disconnect here
					// connection.close();
				}
			} catch (Exception e) {
				return true;
			}
			return false;
		} else
			return true;
	}

	@Override
	public List<ConnectInfo> searchHost() {
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

	public void disconnect() {
		send(ConnectionControl.STOP);

		// if internet connection type, tell the server to eliminate itself
		Socket connection = null;
		ObjectOutputStream output = null;
		String ControlMsg = Constants.DisconnectHostPrefix + ",";
		try {
			// connect to the server, sent local info to the server
			connection = new Socket(Constants.ServerIP, Constants.ServerPort);
			output = new ObjectOutputStream(connection.getOutputStream());

			InetAddress clientAddress = InetAddress.getLocalHost();
			ControlMsg = ControlMsg + clientAddress.getHostAddress();

			output.writeObject(ControlMsg);

		} catch (Exception e) {

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
			if (HostRemainConnectivity != null)
				HostRemainConnectivity.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		isConnected = false;
	}
}
