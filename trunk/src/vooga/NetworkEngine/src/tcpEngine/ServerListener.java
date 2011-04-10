package tcpEngine;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.util.List;


public class ServerListener implements Runnable
{
	ServerSocket server;
	Connection client;
	List<Connection> connection;
	ReceiveRunnable receiveRunnable;
	List<Object> receivedList;
	TCPNetworkEngine engine;
	boolean fullyConnect;

	public ServerListener(ServerSocket server, TCPNetworkEngine e, boolean fullyConnect){
		this.server = server;
		this.engine = e;
		this.connection = e.getConnectionList();
		this.receivedList = e.getReceivedList();
		this.fullyConnect = fullyConnect;
	}
	
	@Override
	public void run()
	{
		while(!server.isClosed() && !engine.isStartGame() && engine.spaceToJoin()){
			try {
				client = new Connection();
				client.socket = server.accept();
				client.socket.setSoTimeout(1500);
				client.output = new ObjectOutputStream(client.socket.getOutputStream());
				
				client.output.flush();
				client.output.writeObject(ConnectionControl.INFORMATION);
				client.output.writeObject(engine.getMyConnectInfo());
				client.output.flush();
				
				client.input  = new ObjectInputStream(client.socket.getInputStream());
				connection.add(client);
				if(fullyConnect){
					client.output.writeObject(ConnectionControl.CREATEHOST);
					client.output.flush();
				}
//				System.out.println("request form: "+client.userName+". number of connections: "+connection.size());
				receiveRunnable = new ReceiveRunnable(client, engine);
				(new Thread(receiveRunnable)).start();
			} catch (IOException e) {
				break;
			}
		}
		if(fullyConnect){
			engine.fullyConnect();
		}
	}
}
