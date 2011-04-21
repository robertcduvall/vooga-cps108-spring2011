package vooga.arcade.parser;

import javax.swing.JFrame;

import vooga.arcade.view.gamePanel.ArcadeGamePanel;


public class Main {
    public static void main(String[] args) {
        ArcadeGameObject ob = GameXMLParser.getGameData("./src/vooga/arcade/parser/game.xml");
        System.out.println(ob.getData("rating"));
        ob.putData("rating", "yhg is wtf");
        //ob.start();
        //System.out.println("lololol");
        
        JFrame frame = new JFrame();
        frame.getContentPane().add(new ArcadeGamePanel(ob));
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
