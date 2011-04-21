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
    
    public void login()
    {
        // TODO: Call Conrad's login thingy.  Replace login button with user button.
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
