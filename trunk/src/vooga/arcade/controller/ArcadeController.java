package vooga.arcade.controller;

import java.awt.Dimension;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
//import java.util.ArrayList;
import java.util.List;
//import javax.swing.JPanel;
import javax.swing.JToolBar;
import vooga.arcade.model.ArcadeModel;
import vooga.arcade.parser.ArcadeGameObject;
//import vooga.arcade.parser.ArcadeUserObject;
//import vooga.arcade.parser.gameObject.ArcadeObject;
import vooga.arcade.view.buttonToolBar.ButtonBar;
//import vooga.arcade.view.gui.ProductionState;
import vooga.arcade.view.gui.VoogaViewer;
//import vooga.user.controller.LoginController;
import vooga.user.voogauser.VoogaUser;


/**
 * Controller for the arcade
 * 
 * @author Andrea Scripa
 * @author Ethan Goh
 */

public class ArcadeController
{
    private VoogaViewer view;
    private ArcadeModel model;
    private VoogaUser currentUser;


    //private ProductionState prodSt;

    public ArcadeController (String string, String string2, int i, int j)
    {
        model = new ArcadeModel();
        view = new VoogaViewer(string, string2, new Dimension(i, j), this);
    }


    public ArcadeController ()
    {}


    public void displayError (String s)
    {
        view.showError(s);
    }


    public List<ArcadeGameObject> queryModel (String tag, String[] query)
    {
        model.filterArcadeGameList(tag, query);
        return model.getCurrentGameList();
    }


    /**
     * Uses reflection to call the correct sorting algorithm in the model based
     * on what was selected in the drop-down menu.
     */
    @SuppressWarnings("unchecked")
    public void sortInModel (String sort)
        throws SecurityException,
            NoSuchMethodException,
            ClassNotFoundException,
            IllegalArgumentException,
            IllegalAccessException,
            InvocationTargetException
    {

        Class<?> thisClass = Class.forName("ArcadeController");
        Class[] parameters = null;
        Method sortBy = thisClass.getMethod(sort, parameters);
        sortBy.invoke(new ArcadeController(), parameters);
    }

    public VoogaUser getUser ()
    {
        return currentUser;
    }


    public void updateGameObjects (List<ArcadeGameObject> arcadeGameList,
                                   String searchQuery)
    {
        view.updateGameObjectList(arcadeGameList, searchQuery);
    }


    public void updateOutlineText (String s, int index)
    {
        view.getMiddleFrame().getTextPanes().changePaneToURL(index, s);
    }


    public void updateButtonBar(VoogaUser newUser)
    {
        currentUser = newUser;
        JToolBar newBar =
            ButtonBar.createButtonToolBar(this,
                                          view.ps.getStateResources(),
                                          view.ps.currentState);
        view.switchToolBar(newBar);
    }
}
