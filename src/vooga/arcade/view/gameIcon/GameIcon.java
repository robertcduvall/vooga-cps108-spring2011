/**
 * 
 */
package vooga.arcade.view.gameIcon;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import vooga.arcade.parser.ArcadeGameObject;

/**
 * @author KevinWang
 *
 */
public class GameIcon extends JPanel {
    
    private static final long serialVersionUID = 1L;
    private ArcadeGameObject gameObject;
    
    public GameIcon (ArcadeGameObject gameObject){
        super();
        this.gameObject = gameObject;
        this.setLayout(new BorderLayout(10,10));
        addIcon(BorderLayout.NORTH);
        addPlayButton(BorderLayout.WEST);
        addTitle(BorderLayout.EAST);
        this.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
    }

    private void addTitle(String layout) {
        this.add(new JLabel(gameObject.getData("title")), layout);
        
    }

    private void addPlayButton(String layout) {
        JButton playButton = new JButton("Play");
        playButton.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                gameObject.start();
                
            }
        });
        this.add(playButton,layout);
        
    }

    private void addIcon(String layout) {
        this.add(new JLabel(new ImageIcon(gameObject.getImage())), layout);
    }
}
