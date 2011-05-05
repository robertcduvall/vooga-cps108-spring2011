package vooga.network.tcpEngine;

public class Constants {
	static int BroadcastPort = 5128;
	static int BroadcastResponsePort = 5129;
	static String BroadcastPrefix = "Request Server";
	static String BroadcastResponsePrefix = "Response from Server";
	public static String CreateHostPrefix = "Create Host";
	public static String DisconnectHostPrefix = "Disconnect Host";
	public static String RequestAvailableHostPrefix = "Request Available Hosts";
	public static String ResponseAvailableHostPrefix = "Available Hosts";
	static int BroadcastTimes = 3;
	static int SearchHostTimeOut = 3000;
	static int SearchServerTimeOut = 3000;
	static int TCPConnectionBacklogLimit = 20;
	
	public static int ServerPort = 5140;
	static String ServerIP = "10.180.100.144";
	
	public static int HostRemainConnectivityPort = 5141;
	
	static int HostPort = 5150;
	
	public static int getSearchServerTimeOut(){
		return SearchHostTimeOut;
	}
}
