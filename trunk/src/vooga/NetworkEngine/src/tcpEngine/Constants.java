package tcpEngine;

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
	static int SearchServerTimeOut = 5000;
	
	public static int ServerPort = 5140;
	static String ServerIP = "10.180.122.138";
	
	static int HostPort = 5150;
	
	public static int getSearchServerTimeOut(){
		return SearchServerTimeOut;
	}
}
