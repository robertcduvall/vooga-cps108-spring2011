package vooga.network;

//import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import vooga.network.tcpEngine.ConnectInfo;
import vooga.network.tcpEngine.Connection;

public interface INetworkEngine
{
	/**
	 * connects to a server with ip IP
	 * d
	 * @param IP
	 * @return false if successfully created
	 */
	boolean connect(String IP);

	/**
	 * Create a local host, if the argument is true, then there will be
	 * connections between clients. The default argument should be false.
	 * 
	 * @param name
	 * @return false if successfully created
	 */
	boolean createHost(boolean fullyConnect);

//	/**
//	 * create a host which is visible from a global server
//	 * 
//	 * @return false if successfully created
//	 */
//	boolean createInternetHost();

	/**
	 * 
	 * @return the list of object received since last update
	 */
	List<Object> update();

	/**
	 * Search for the hosts on local area network
	 * 
	 * @return
	 */
	List<ConnectInfo> searchHost();

	/**
//	 * Search for the hosts registered on a global server
//	 * 
//	 * @return
//	 */
//	List<ConnectInfo> searchInternetHost();

	/**
	 * Disconnect from all connections.
	 */
	void disconnect();

	/**
	 * Send an serializable obj after the connection is established
	 * 
	 * @param obj
	 * @return
	 */
	boolean send(Serializable obj);

	/**
	 * check whether currently is in connection or disconnected
	 * 
	 * @return
	 */
	boolean isConnected();

//	
//	/**
//	 * The number of current connections.
//	 * 
//	 * @return
//	 */
//	int getConnectionSize();
//
	/**
	 * Return the List of connection information this computer are connecting to
	 * 
	 * @return
	 */
	List<ConnectInfo> getConnectionInfo();
//
//	/**
//	 * 
//	 * @return
//	 */
//	ConnectInfo getMyConnectInfo();
//
//	/**
//	 * 
//	 * @return a synchronized list holds the received objects temporarily.
//	 */
//	List<Object> getReceivedList();

//	/**
//	 * 
//	 * @return a synchronized list holds the connections.
//	 */
//	List<Connection> getConnectionList();
//
//	/**
//	 * Start the game, then the server will not accept more connections.
//	 * 
//	 * @param v
//	 */
//	void setGameStarted(boolean v);		//receive no more connections
//
//	/**
//	 * 
//	 * @return
//	 */
//	boolean isGameStarted();
//
//	/**
//	 * Set the upper limit for connection.
//	 * 
//	 * @param size
//	 *            the upper limit for connections;
//	 */
//	void setGameSize(int size);
//
//	/**
//	 * Get the upper limit for connections.
//	 * 
//	 * @return
//	 */
//	int getGameSize();
//
//	/**
//	 * Set user name.
//	 * 
//	 * @param userName
//	 */
//	void setUserName(String userName);
//	
//	boolean spaceToJoin();
}