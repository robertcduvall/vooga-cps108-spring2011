package vooga.arcade.view.actions;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import vooga.arcade.controller.ArcadeController;


public abstract class AbstractViewAction implements ActionListener
{
    protected ArcadeController controller;


    public AbstractViewAction (ArcadeController c)
    {
        controller = c;
    }


    @SuppressWarnings("unchecked")
    public void promptController (String methodName, Object ... objList)
    {
        ArrayList<Class> classList = new ArrayList<Class>();

        for (Object o : objList)
        {
            classList.add(o.getClass());
        }

        try
        {
            Method m =
                controller.getClass()
                          .getDeclaredMethod(methodName,
                                             classList.toArray(new Class[classList.size()]));
            m.invoke(controller, objList);
        }
        catch (SecurityException e)
        {
            e.printStackTrace();
        }
        catch (NoSuchMethodException e)
        {
            e.printStackTrace();
        }
        catch (IllegalArgumentException e)
        {
            e.printStackTrace();
        }
        catch (IllegalAccessException e)
        {

            e.printStackTrace();
        }
        catch (InvocationTargetException e)
        {
            e.printStackTrace();
        }
    }

}
