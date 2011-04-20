package vooga.network.example.SimpleChat;

import java.util.Scanner;

import vooga.network.INetworkEngine;


public class UserSendRunnable implements Runnable
{
	INetworkEngine e;
	public UserSendRunnable(INetworkEngine e){
		this.e = e;
	}

	@Override
	public void run()
	{
		Scanner sc = new Scanner(System.in);
		while(true){
			String toSend = sc.nextLine();
			if(toSend.startsWith("end")){
				e.disconnect();
				break;
			}
			else{
				e.send(toSend);
			}
		}

	}

}
