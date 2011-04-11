package vooga.network.tcpEngine;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;


public class Connection
{
	public ConnectInfo info;
	public Socket socket;
	public ObjectInputStream input;
	public ObjectOutputStream output;

	public Connection(){
		
	}
	
	public Connection(ConnectInfo info){
		this.info = info;
	}
}
