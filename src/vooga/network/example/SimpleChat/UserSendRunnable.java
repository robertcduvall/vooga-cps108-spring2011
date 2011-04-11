package vooga.network.example.SimpleChat;

import java.util.Scanner;

import vooga.network.NetworkEngine;


public class UserSendRunnable implements Runnable
{
	NetworkEngine e;
	public UserSendRunnable(NetworkEngine e){
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
