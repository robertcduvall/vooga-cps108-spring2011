package vooga.network;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import vooga.network.tcpEngine.ConnectInfo;
import vooga.network.tcpEngine.Connection;

public interface NetworkEngine
{
	/**
	 * connects to a server with ip IP
	 * 
	 * @param IP
	 * @return
	 */
	boolean connect(String IP);

	/**
	 * Create a local host, if the argument is true, then there will be
	 * connections between clients. The default argument should be false.
	 * 
	 * @param name
	 * @return
	 */
	boolean createLocalHost(boolean fullyConnect);

	/**
	 * create a host which is visible from a global server
	 * 
	 * @return
	 */
	boolean createInternetHost();

	/**
	 * 
	 * @return the list of object received since last update
	 */
	List<Object> update();

	/**
	 * Serach for the hosts on local area network
	 * 
	 * @return
	 */
	List<ConnectInfo> searchLocalHost();

	/**
	 * Search for the hosts registered on a global server
	 * 
	 * @return
	 */
	List<ConnectInfo> searchInternetHost();

	/**
	 * Disconnect from all connections.
	 */
	void disconnect();

	/**
	 * Send an serializable obj
	 * 
	 * @param obj
	 * @return
	 */
	boolean send(Serializable obj);

	/**
	 * 
	 * @return
	 */
	boolean isClosed();

	/**
	 * The number of current connections.
	 * 
	 * @return
	 */
	int getConnectionSize();

	/**
	 * 
	 * @return
	 */
	List<ConnectInfo> getConnectionInfo();

	/**
	 * 
	 * @return
	 */
	ConnectInfo getMyConnectInfo();

	/**
	 * 
	 * @return a synchronized list holds the received objects temporarily.
	 */
	List<Object> getReceivedList();

	/**
	 * 
	 * @return a synchronized list holds the connections.
	 */
	List<Connection> getConnectionList();

	/**
	 * Start the game, then the server will not accept more connections.
	 * 
	 * @param v
	 */
	void setStartGame(boolean v);

	/**
	 * 
	 * @return
	 */
	boolean isStartGame();

	/**
	 * Set the upper limit for connection.
	 * 
	 * @param size
	 *            the upper limit for connections;
	 */
	void setGameSize(int size);

	/**
	 * Get the upper limit for connections.
	 * 
	 * @return
	 */
	int getGameSize();

	/**
	 * Set user name.
	 * 
	 * @param userName
	 */
	void setUserName(String userName);
}