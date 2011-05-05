package vooga.network.tcpEngine;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import vooga.network.INetworkEngine;

public abstract class AbstractNetworkEngine implements INetworkEngine {
	protected int port;
	protected ConnectInfo myInfo;
	protected List<Connection> connectionList;
	protected List<Object> receivedList;
	protected boolean startGame;

	protected boolean isConnected;
	protected ServerSocket serverSocket;
	protected int gameSize;

	protected void finalize() {
		disconnect();
	}

	protected AbstractNetworkEngine(int port) {
		this.port = port;
		connectionList = Collections
				.synchronizedList(new LinkedList<Connection>());
		receivedList = Collections.synchronizedList(new LinkedList<Object>());
		try {
			myInfo = new ConnectInfo(InetAddress.getLocalHost()
					.getHostAddress(), "userName", port);
		} catch (Exception e) {
			e.printStackTrace();
		}
		isConnected = false;
		startGame = false;
		gameSize = 20;
	}

	@Override
	public boolean connect(String IP) {
		// only do the connection if is not connected yet
		if (!isConnected) {
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
				isConnected = true;
			} catch (Exception e) {
				e.printStackTrace();
			}
			return false;
		} else
			return true;
	}

	@Override
	public List<Object> update() {
		List<Object> rtn;
		synchronized (receivedList) {
			rtn = new LinkedList<Object>(receivedList);
			receivedList.clear();
		}

		return rtn;
	}

	@Override
	public void disconnect() {
		send(ConnectionControl.STOP);

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
		isConnected = false;
	}

	@Override
	public boolean send(Serializable obj) {
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
	public boolean isConnected() {
		return isConnected;
	}

	public List<ConnectInfo> getConnectionInfo() {
		List<ConnectInfo> rtn = new LinkedList<ConnectInfo>();
		synchronized (connectionList) {
			for (Connection c : connectionList) {
				rtn.add(c.info);
			}
		}
		return rtn;
	}

	public ConnectInfo getMyInfo() {

		return myInfo;
	}

	public int getConnectionSize() {
		return connectionList.size();
	}

	public List<Object> getReceivedList() {
		return receivedList;
	}

	public List<Connection> getConnectionList() {
		return connectionList;
	}

	public void setStartGame(boolean v) {
		startGame = v;
	}

	public boolean isStartGame() {
		return startGame;
	}

	public void fullyConnect() {
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

	public void setGameSize(int size) {
		gameSize = size;
	}

	public int getGameSize() {
		return gameSize;
	}

	public void setUserName(String userName) {
		myInfo.setName(userName);
	}

	public boolean spaceToJoin() {
		return gameSize > connectionList.size();
	}

	public abstract boolean createHost(boolean fullyConnect, boolean visiable);

	public abstract List<ConnectInfo> searchHost();
}
