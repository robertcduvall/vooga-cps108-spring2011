package vooga.arcade.view.actions;

import java.awt.event.ActionEvent;
import java.util.Arrays;

import javax.swing.JTextField;

import vooga.arcade.controller.ArcadeController;

public class Search extends AbstractViewAction
{

    public Search (ArcadeController c)
    {
        super(c);
    }

    @Override
    public void actionPerformed (ActionEvent e)
    {
        String searchQuery = ((JTextField)(e.getSource())).getText();
        controller.updateGameObjects(controller.queryModel("title",new String[]{searchQuery}), searchQuery);
        
    }
}
