package vooga.network.example.gameGUI;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.swing.Box;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import vooga.network.INetworkEngine;
import vooga.network.tcpEngine.ConnectInfo;
import vooga.network.tcpEngine.InternetNetworkEngine;
import vooga.network.tcpEngine.LocalNetworkEngine;

/**
 * This GUI is designed as a platform for the network games. However, to do the book keeping it is necessary to
 * design another protocol, which may not be finished before the demo.
 * Should refactor this by the MVC pattern. In this program the GUI part make the program hard to read.
 * 
 * @author hz41
 *
 */
public class GameGUI extends JFrame implements ActionListener {

	private INetworkEngine networkEngine;

	private static int port = 8999;
	private String userName;
	
	private Map<String, ArcadeNetworkGame> gameMap;
	private Map<String, List<ConnectInfo>> connectMap;
	
	private static final String resources = "vooga.network.example.gameGUI.resources.games";

	public JTextArea messageShow;
	public JList gameList;
	private JScrollPane messageScrollPane;
	private JScrollPane memberScrollPane;

	private JLabel messageLabel;
	private JLabel nameLabel;

	private JTextField clientMessage;
	private JButton clientMessageButton;
	private JTextField showStatus;

	private JMenuBar jMenuBar = new JMenuBar();
	private JMenu operateMenu = new JMenu("Operation");

	private JMenuItem userNameItem = new JMenuItem("UserName");
	private JMenuItem startgameItem = new JMenuItem("StartGame");
	private JMenuItem exitItem = new JMenuItem("Exit");

	private JMenu conMenu = new JMenu("Option");
	private JMenuItem createItem = new JMenuItem("Create");
	private JMenuItem joinItem = new JMenuItem("Join");

	private JMenu helpMenu = new JMenu("Help");
	private JMenuItem helpItem = new JMenuItem("Help");

	private JToolBar toolBar = new JToolBar();

	private JButton usernameButton;
	private JButton startgameButton;
	private JButton createServerButton;
	private JButton joinButton;
	private JButton exitButton;

	private Dimension faceSize = new Dimension(400, 600);

	private JPanel downPanel;
	private JPanel midPanel;
	private GridBagLayout girdBag;
	private GridBagConstraints girdBagCon;

	private DefaultListModel listModel;

	public GameGUI() {
		this.gameMap = new HashMap<String, ArcadeNetworkGame>();
		
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

	public void init() {

		Container contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());

		operateMenu.add(userNameItem);
		operateMenu.add(startgameItem);
		operateMenu.add(exitItem);
		jMenuBar.add(operateMenu);
		conMenu.add(createItem);
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
		createItem.addActionListener(this);
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
    	ResourceBundle myResources = ResourceBundle.getBundle(resources);
    	for (String key : myResources.keySet()){
    		listModel.addElement(key);
    	}

		gameList = new JList(listModel);
		gameList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		gameList.setSelectedIndex(0);

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

		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		initGames();
	}
    private void initGames() {
        ResourceBundle myResources = ResourceBundle.getBundle(resources);
        for (String key : myResources.keySet()){
            gameMap.put(key, new ArcadeNetworkGame(myResources.getString(key)));
        }
    }
    
	public void actionPerformed(ActionEvent e)
	{
		Object obj = e.getSource();
        int gameIndex = gameList.getSelectedIndex();
        ArcadeNetworkGame game = gameMap.get((String) listModel.get(gameIndex));
		if (obj == createItem || obj == createServerButton) {
			if (networkEngine == null) {
				networkEngine = new InternetNetworkEngine(port);
			//	networkEngine = new LocalNetworkEngine(port);
				networkEngine.getMyInfo().setName(userName);
			}
			if (networkEngine.createHost(false, true)) {
				messageShow.append("fail to build the server\n");
			} else {
				joinButton.setEnabled(false);
				createServerButton.setEnabled(false);
				exitButton.setEnabled(true);

				(new Thread(new UserReceiveRunnable(this, networkEngine)))
						.start();
				
				game.setHost(true);
				int tmpPort = port+(int)Math.round(Math.random()*1000);
				game.setPort(tmpPort);
                messageShow.append("build the server successfully\n");
			}

		} else if (obj == joinItem || obj == joinButton) {
			if (networkEngine == null) {
				networkEngine = new InternetNetworkEngine(port);
			//	networkEngine = new LocalNetworkEngine(port);
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
							(new Thread(new UserReceiveRunnable(this,
									networkEngine))).start();
							
							game.setHost(false);
							game.setIP((String) selectedValue);
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
			UserConf userConf = new UserConf(this, userName);
			userConf.setVisible(true);
			userName = userConf.userInputName;
			nameLabel.setText("User Name: " + userName);
			networkEngine.getMyInfo().setName(userName);
			networkEngine.getMyInfo().setName(userName);

		} else if (obj == startgameItem || obj == startgameButton) {
			networkEngine.send(new StartGame(game.getPort()));
			game.start();
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
	
	public void startGame(int port){
        int gameIndex = gameList.getSelectedIndex();
        ArcadeNetworkGame game = gameMap.get((String) listModel.get(gameIndex));
        game.setPort(port);
        game.start();
	}

	public static void main(String[] args)
	{
		GameGUI app = new GameGUI();
	}
}