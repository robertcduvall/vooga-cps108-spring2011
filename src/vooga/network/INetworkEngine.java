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

	/**
	 * Return the List of connection information this computer are connecting to
	 * 
	 * @return
	 */
	List<ConnectInfo> getConnectionInfo();
	
	/**
	 * Return the user information.
	 * 
	 * @param userName
	 */
	ConnectInfo getMyInfo();
}