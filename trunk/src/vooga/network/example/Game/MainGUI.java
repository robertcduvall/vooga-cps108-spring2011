package vooga.network.example.Game;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import vooga.network.INetworkEngine;
import vooga.network.tcpEngine.ConnectInfo;
import vooga.network.tcpEngine.InternetNetworkEngine;
import vooga.network.tcpEngine.LocalNetworkEngine;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class MainGUI extends JFrame implements ActionListener
{

	INetworkEngine networkEngine;

	static int port = 8999;
	String userName;

	public JTextArea messageShow;
	public JList gameList;
	JScrollPane messageScrollPane;
	JScrollPane memberScrollPane;

	JLabel messageLabel;
	JLabel nameLabel;

	JTextField clientMessage;
	JButton clientMessageButton;
	JTextField showStatus;

	JMenuBar jMenuBar = new JMenuBar();
	JMenu operateMenu = new JMenu("Operation(O)");

	JMenuItem userNameItem = new JMenuItem("UserName(I)");
	JMenuItem startgameItem = new JMenuItem("StartGame(L)");
	JMenuItem exitItem = new JMenuItem("Exit(X)");

	JMenu conMenu = new JMenu("Option(C)");
	JMenuItem userItem = new JMenuItem("Users(U)");
	JMenuItem joinItem = new JMenuItem("Join(C)");

	JMenu helpMenu = new JMenu("Help(H)");
	JMenuItem helpItem = new JMenuItem("Help(H)");

	JToolBar toolBar = new JToolBar();

	JButton usernameButton;
	JButton startgameButton;
	JButton createServerButton;
	JButton joinButton;
	JButton exitButton;

	Dimension faceSize = new Dimension(400, 600);

	JPanel downPanel;
	JPanel midPanel;
	GridBagLayout girdBag;
	GridBagConstraints girdBagCon;
	
	private DefaultListModel listModel;

	public MainGUI()
	{
		init();

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(faceSize);

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((int) (screenSize.width - faceSize.getWidth()) / 2,
				(int) (screenSize.height - faceSize.getHeight()) / 2);
		this.setResizable(false);
		this.setTitle("Network Game Starter");

		this.setVisible(true);
	}

	public void init()
	{

		Container contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());

		operateMenu.add(userNameItem);
		operateMenu.add(startgameItem);
		operateMenu.add(exitItem);
		jMenuBar.add(operateMenu);
		conMenu.add(userItem);
		conMenu.add(joinItem);
		jMenuBar.add(conMenu);
		helpMenu.add(helpItem);
		jMenuBar.add(helpMenu);
		setJMenuBar(jMenuBar);

		usernameButton = new JButton("Username");
		startgameButton = new JButton("Start Game");
		createServerButton = new JButton("Create");
		joinButton = new JButton("Join");
		exitButton = new JButton("Disconnect");
		exitButton.setEnabled(false);

		usernameButton.setToolTipText("change username");
		startgameButton.setToolTipText("start the game");
		createServerButton.setToolTipText("create server");
		joinButton.setToolTipText("join the server");

		toolBar.add(createServerButton);
		toolBar.add(joinButton);
		toolBar.add(exitButton);
		toolBar.addSeparator();
		toolBar.add(usernameButton);
		toolBar.addSeparator();
		toolBar.add(startgameButton);
		toolBar.addSeparator();
		contentPane.add(toolBar, BorderLayout.NORTH);

		usernameButton.setEnabled(true);
		startgameButton.setEnabled(true);

		userNameItem.addActionListener(this);
		startgameItem.addActionListener(this);
		exitItem.addActionListener(this);
		userItem.addActionListener(this);
		joinItem.addActionListener(this);
		helpItem.addActionListener(this);

		usernameButton.addActionListener(this);
		startgameButton.addActionListener(this);
		createServerButton.addActionListener(this);
		joinButton.addActionListener(this);
		exitButton.addActionListener(this);

		messageShow = new JTextArea();
		messageShow.setEditable(false);
		
        listModel = new DefaultListModel();
        listModel.addElement("Sustain30s");
        listModel.addElement("PlantsVsZombies");
        
        gameList = new JList(listModel);
        gameList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        gameList.setSelectedIndex(0);
        gameList.addListSelectionListener(new ListListener());
        

		messageScrollPane = new JScrollPane(messageShow,
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		messageScrollPane.setPreferredSize(new Dimension(300, 400));
		messageScrollPane.revalidate();

		memberScrollPane = new JScrollPane(gameList,
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		memberScrollPane.setPreferredSize(new Dimension(150, 400));
		memberScrollPane.revalidate();

		clientMessage = new JTextField(23);
		clientMessage.setEnabled(true);
		clientMessageButton = new JButton();
		clientMessageButton.setText("Send");

		clientMessage.addActionListener(this);
		clientMessageButton.addActionListener(this);

		messageLabel = new JLabel("Msg sent:");
		downPanel = new JPanel();
		girdBag = new GridBagLayout();
		downPanel.setLayout(girdBag);

		girdBagCon = new GridBagConstraints();
		girdBagCon.gridx = 0;
		girdBagCon.gridy = 0;
		girdBagCon.gridwidth = 5;
		girdBagCon.gridheight = 2;
		girdBagCon.ipadx = 5;
		girdBagCon.ipady = 5;

		userName = "user" + String.valueOf(System.currentTimeMillis() % 1000);
		nameLabel = new JLabel("User Name: " + userName);
		girdBag.setConstraints(nameLabel, girdBagCon);
		downPanel.add(nameLabel);

		girdBagCon = new GridBagConstraints();
		girdBagCon.gridx = 0;
		girdBagCon.gridy = 3;
		girdBag.setConstraints(messageLabel, girdBagCon);
		downPanel.add(messageLabel);

		girdBagCon = new GridBagConstraints();
		girdBagCon.gridx = 1;
		girdBagCon.gridy = 3;
		girdBagCon.gridwidth = 3;
		girdBagCon.gridheight = 1;
		girdBag.setConstraints(clientMessage, girdBagCon);
		downPanel.add(clientMessage);

		girdBagCon = new GridBagConstraints();
		girdBagCon.gridx = 4;
		girdBagCon.gridy = 3;
		girdBag.setConstraints(clientMessageButton, girdBagCon);
		downPanel.add(clientMessageButton);

		showStatus = new JTextField(35);
		showStatus.setEditable(false);
		girdBagCon = new GridBagConstraints();
		girdBagCon.gridx = 0;
		girdBagCon.gridy = 5;
		girdBagCon.gridwidth = 5;
		girdBag.setConstraints(showStatus, girdBagCon);
		downPanel.add(showStatus);

		midPanel = new JPanel();
		midPanel.setLayout(new javax.swing.BoxLayout(midPanel,
				javax.swing.BoxLayout.LINE_AXIS));
		midPanel.add(memberScrollPane);
		midPanel.add(Box.createRigidArea(new Dimension(10, 0)));

		midPanel.add(messageScrollPane);

		contentPane.add(midPanel, BorderLayout.CENTER);
		contentPane.add(downPanel, BorderLayout.SOUTH);

		this.addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent e)
			{
				System.exit(0);
			}
		});
	}

	public void actionPerformed(ActionEvent e)
	{
		Object obj = e.getSource();

		if (obj == userItem || obj == createServerButton) {
			if (networkEngine == null) {
				// networkEngine = new InternetNetworkEngine(port);
				networkEngine = new LocalNetworkEngine(port);
				networkEngine.getMyInfo().setName(userName);
			}
			if (networkEngine.createHost(false)) {
				messageShow.append("fail to build the server\n");
			} else {
				joinButton.setEnabled(false);
				createServerButton.setEnabled(false);
				exitButton.setEnabled(true);

			}

		} else if (obj == joinItem || obj == joinButton) {
			if (networkEngine == null) {
				// networkEngine = new InternetNetworkEngine(port);
				networkEngine = new LocalNetworkEngine(port);
				networkEngine.getMyInfo().setName(userName);
			}
			List<ConnectInfo> result = networkEngine.searchHost();
			if (result != null) {
				if (result.size() == 0)
					JOptionPane.showMessageDialog(null, "Host Not Found",
							"Alert", JOptionPane.ERROR_MESSAGE);
				else {
					String[] foundServer = new String[result.size()];
					for (int i = 0; i < result.size(); i++)
						foundServer[i] = result.get(i).getIPaddress();
					Object selectedValue = JOptionPane.showInputDialog(null,
							"Choose one Server", "Input",
							JOptionPane.INFORMATION_MESSAGE, null, foundServer,
							foundServer[0]);
					// System.out.println((String) selectedValue);
					if ((String) selectedValue != null) {
						if (networkEngine.connect((String) selectedValue)) {
							messageShow
									.append("fail to build the connection\n");
						} else {
							// set buttons
							joinButton.setEnabled(false);
							createServerButton.setEnabled(false);
							exitButton.setEnabled(true);

							// start thread
//							(new Thread(new UserReceiveRunnable(this,
//									networkEngine))).start();
//							(new Thread(new UpdateConnectionInfo(this,
//									networkEngine))).start();
							messageShow
									.append("build the connection successfully\n");
						}
					}
				}
			} else {
				JOptionPane.showMessageDialog(null,
						"Server not found, check server connectivity", "Alert",
						JOptionPane.ERROR_MESSAGE);
			}

		} else if (obj == userNameItem || obj == usernameButton) {
//			UserConf userConf = new UserConf(this, userName);
//			userConf.setVisible(true);
//			userName = userConf.userInputName;
			nameLabel.setText("User Name: " + userName);
			networkEngine.getMyInfo().setName(userName);
			networkEngine.getMyInfo().setName(userName);

		} else if (obj == startgameItem || obj == startgameButton) {
			int index = gameList.getSelectedIndex();
			messageShow.append("selected game "+(String)listModel.get(index)+"\n");
			
			
		} else if (obj == clientMessage || obj == clientMessageButton) {
			String message = clientMessage.getText();
			networkEngine.send(userName + " : " + message);
			clientMessage.setText("");

		} else if (obj == exitButton || obj == exitItem) {
			int j = JOptionPane.showConfirmDialog(this, "Confirm disconnect?",
					"exit", JOptionPane.YES_OPTION,
					JOptionPane.QUESTION_MESSAGE);

			if (j == JOptionPane.YES_OPTION) {
				networkEngine.disconnect();
				// set the buttons
				joinButton.setEnabled(true);
				createServerButton.setEnabled(true);
				exitButton.setEnabled(false);
			}

		} else if (obj == helpItem) {

		}
	}
	
	private class ListListener implements ListSelectionListener {

		@Override
		public void valueChanged(ListSelectionEvent e) {
			

		}

	}

	public static void main(String[] args)
	{
		MainGUI app = new MainGUI();
	}
}