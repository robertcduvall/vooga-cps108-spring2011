package vooga.network.tcpEngine;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.List;

import vooga.network.INetworkEngine;

public class ReceiveRunnable implements Runnable
{
	Connection client;
	List<Object> receivedList;
	List<Connection> connectionList;
	AbstractNetworkEngine engine;

	public ReceiveRunnable(Connection client, AbstractNetworkEngine e)
	{
		this.client = client;
		this.engine = e;
		this.receivedList = e.getReceivedList();
		this.connectionList = e.getConnectionList();
	}

	@Override
	public void run()
	{
		while (true) {
			try {
				Object obj = client.input.readObject();
				if (obj instanceof ConnectionControl) {
					if (obj == ConnectionControl.STOP) {
						break;
					} else if (obj == ConnectionControl.CONNECT) {
						System.out.println("received build connection command");
						String str = (String) client.input.readObject();
						engine.connect(str);
					} else if (obj == ConnectionControl.CREATEHOST) {
						System.out.println("received build host command");
						engine.createHost(false);
					} else if (obj == ConnectionControl.INFORMATION) {
						client.info = (ConnectInfo) client.input.readObject();
					} else
						continue;
				} else {
					receivedList.add(obj);
				}
			} catch (SocketTimeoutException e) {

			} catch (IOException e) {
				break;
			} catch (ClassNotFoundException e) {
				break;
			}
		}
		try {
			connectionList.remove(client);
			client.input.close();
			client.output.close();
			client.socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
