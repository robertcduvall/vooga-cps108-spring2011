package vooga.arcade.controller;

import java.awt.Dimension;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import vooga.arcade.model.ArcadeModel;
import vooga.arcade.parser.ArcadeGameObject;
import vooga.arcade.parser.ArcadeUserObject;
import vooga.arcade.parser.gameObject.ArcadeObject;
import vooga.arcade.view.gui.VoogaViewer;
import vooga.user.controller.LoginController;


/**
 * Controller for the arcade
 * 
 * @author Andrea Scripa
 */

public class ArcadeController
{
    private VoogaViewer view;
    private ArcadeModel model;


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
    
//    public List<ArcadeUserObject> queryModel (String[] query)
//    {
//    	model.f
//    }
    
    public void sortInModel()
    {
        // TODO: Get the text from the search panel and call queryModel??
    }
    
    public void login()
    {
        // Give Conrad a list of game titles
        //LoginController start = new LoginController("VOOGA GAME LOGIN", "", 640, 480);  
        //start.toString();
        
     // TODO: Replace login button with user button.
    }
    
    public void userProperties()
    {
        // TODO: Create the actionPerformed for the user button that pops up in login's place.
    }

    /**
     * Uses reflection to call the correct sorting algorithm in the model based on what was selected in 
     * the drop-down menu.
     */
    @SuppressWarnings("unchecked")
    public void sortInModel (String sort)
        throws SecurityException,
            NoSuchMethodException,
            ClassNotFoundException,
            IllegalArgumentException, IllegalAccessException, InvocationTargetException
    {

        Class<?> thisClass = Class.forName("ArcadeController");
        Class[] parameters = null;
        Method sortBy = thisClass.getMethod(sort, parameters);
        sortBy.invoke(new ArcadeController(), parameters);
    }


    public void sortByTitle ()
    {

    }


    public void sortByType ()
    {

    }


    public void sortByAuthor ()
    {

    }


    public void sortByPopularity ()
    {

    }


    public void sortByRating ()
    {

    }


    public void sortByDateAdded ()
    {

    }
}
