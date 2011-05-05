package vooga.network;

//import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import vooga.network.tcpEngine.ConnectInfo;
import vooga.network.tcpEngine.Connection;

public interface INetworkEngine {
	/**
	 * connects to a server with ip IP d
	 * 
	 * @param IP
	 * @return false if successfully created
	 */
	boolean connect(String IP);

	/**
	 * Create a host, if the argument is true, then there will be connections
	 * between each clients connecting to this host. The default argument should
	 * be false.
	 * @param visiable shows whether the client would be visiable to others
	 * @param fullyConnect shows whether to setup connection between clients
	 * 
	 * @return false if successfully created
	 */
	boolean createHost(boolean fullyConnect, boolean visiable);

	/**
	 * 
	 * @return the list of object received from the network since last update
	 */
	List<Object> update();

	/**
	 * Search for the hosts on the network
	 * 
	 * @return a list of connection information of the available server
	 */
	List<ConnectInfo> searchHost();

	/**
	 * Disconnect from all connections.
	 */
	void disconnect();

	/**
	 * Send an serializable obj to all its connections
	 * 
	 * @param obj
	 * @return
	 */
	boolean send(Serializable obj);

	/**
	 * check whether currently is in connection or disconnected
	 * 
	 * @return true if currently connecting to other computers
	 */
	boolean isConnected();

	/**
	 * Return the List of connection information this computer are connecting to
	 * 
	 * @return
	 */
	List<ConnectInfo> getConnectionInfo();

	/**
	 * Return information of this computer.
	 * 
	 */
	ConnectInfo getMyInfo();
}