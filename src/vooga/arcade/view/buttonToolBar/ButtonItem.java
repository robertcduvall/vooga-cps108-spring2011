package vooga.arcade.view.buttonToolBar;

import java.awt.Insets;
import java.awt.event.ActionListener;
import javax.swing.Icon;
import javax.swing.JButton;
import vooga.arcade.controller.ArcadeController;
import vooga.arcade.view.actions.ActionFactory;


/**
 * This class represents a Button for the Button Toolbar that grabs its actions
 * with the action factory.
 */
public class ButtonItem extends JButton
{
    private static final long serialVersionUID = 1L;
    private static final Insets margins = new Insets(0, 0, 0, 0);

    private static String className = "ButtonBarItem";


    public ButtonItem (Icon icon, String buttonName, ArcadeController p)
    {
        super(icon);
        ActionListener aa =
            ActionFactory.createAction(className + buttonName, p);
        this.addActionListener(aa);

        setMargin(margins);
        setVerticalTextPosition(BOTTOM);
        setHorizontalTextPosition(CENTER);
    }

    public ButtonItem (String buttonName, String buttonText, ArcadeController p)
    {
        super(buttonText);
        ActionListener aa =
            ActionFactory.createAction(className + buttonName, p);
        this.addActionListener(aa);

        setMargin(margins);
        setVerticalTextPosition(BOTTOM);
        setHorizontalTextPosition(CENTER);
    }
}
