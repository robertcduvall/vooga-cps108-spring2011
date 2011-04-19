package vooga.leveleditor.gui;

import javax.swing.JLabel;
import javax.swing.BorderFactory;
import javax.swing.border.EtchedBorder;

/**
 * A simple status bar. Used to display helpful messages to the user.
 */
public class StatusBar extends JLabel
{

    public StatusBar()
    {
        super();
        this.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        this.setText("Ready");
    }

}
