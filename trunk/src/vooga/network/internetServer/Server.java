package vooga.network.internetServer;
import javax.swing.*;

import vooga.network.exceptions.NetworkException;

import vooga.network.tcpEngine.ConnectInfo;
import vooga.network.tcpEngine.Constants;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class Server implements ActionListener{
	private JFrame frame;
	private JButton startServer;
	private JButton stopServer;
	private JLabel status;
	
	private Set<String> availableServers;
	ServerSocket serverSocket;
	boolean threadStop = false;
	
	Server(){
		init();
		
	}
	
	public static void main(String[] args){
		try {
			Server server = new Server();
		}catch(Exception e){
			JOptionPane.showMessageDialog(null,
                    e.getMessage(),
                    "Input Error",
                    JOptionPane.ERROR_MESSAGE);
		}
		
	}
	
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if (obj == startServer){
			startService();
		}
		else if (obj == stopServer){
			int j=JOptionPane.showConfirmDialog( 
				null,"Stop Server?","Confirmation", 
				JOptionPane.YES_OPTION,JOptionPane.QUESTION_MESSAGE); 
 
			if (j == JOptionPane.YES_OPTION){ 
				stopService(); 
			} 
		}
	}
	private void startService(){
		startServer.setEnabled(false);
		stopServer.setEnabled(true);
		status.setText("Server started!");
		
		availableServers = Collections.synchronizedSet(new HashSet<String>());
		threadStop = false;
		try {
			
			
			//server listen to the port, if host connect, add IP to the list, if client query, return the list
			serverSocket = new ServerSocket(Constants.ServerPort, 10);
			ServerListener requestListenerThread = new ServerListener(serverSocket, availableServers,this);
			requestListenerThread.start();
			
			CheckConnectivity checkConnectivityThread = new CheckConnectivity(availableServers,this);
			checkConnectivityThread.start();
			
		} catch (IOException e) {
			throw NetworkException.FAIL_TO_CREATE_HOST;
		}
	}
	
	private void stopService() {
		startServer.setEnabled(true);
		stopServer.setEnabled(false);
		status.setText("Server stopped!");
		threadStop = true;
		try{
			serverSocket.close();
		}catch (Exception e){
			
		}
	}

	private void init(){
		//Create and set up the window.
        frame = new JFrame("Game Server");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Set up the content pane.
        startServer = new JButton("Start Server");
        startServer.setPreferredSize(new Dimension(150, 80));
        frame.add(startServer, BorderLayout.LINE_START);
        startServer.addActionListener(this);
        
        stopServer = new JButton("Stop Server");
        stopServer.setPreferredSize(new Dimension(150, 80));
        frame.add(stopServer, BorderLayout.LINE_END);
        stopServer.setEnabled(false);
        stopServer.addActionListener(this);
        
        status = new JLabel();
        status.setText("Server not started !");
        frame.add(status, BorderLayout.PAGE_END);

        frame.pack();
        frame.setVisible(true);
	}
}
