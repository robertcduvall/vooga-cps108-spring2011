package vooga.network.tcpEngine;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.*;


public class ConnectInfo implements Serializable
{
	private String IP;
	private String userName;
	
	public ConnectInfo(String ip, String username){
		IP = ip;
		userName = username;
	}
	
	public ConnectInfo(String s){
		IP = s;
	}

	//You may also need a method to get the MAC address?  <3 Andrea
	public String getIPaddress(){
		return IP;
	}
	
	public String getName(){
		return userName;
	}
	
	public void setName(String name){
		userName = name;
	}
	
	@Override
	public String toString(){
		return userName;
	}
}
