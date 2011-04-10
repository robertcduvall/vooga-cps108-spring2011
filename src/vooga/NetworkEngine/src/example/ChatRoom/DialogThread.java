package example.ChatRoom;

import javax.swing.JOptionPane;

public class DialogThread extends Thread{
	public void run() {   
		
        try {   
        	
            Thread.sleep(1000);   
        }
        catch (InterruptedException e) {   
        }   
        JOptionPane.getRootFrame().dispose();                     
    }   
}
