package games.nemo.view.components;

import java.util.List;

import javax.swing.JOptionPane;

import games.nemo.util.reflection.Reflection;

/**
 * Show error message
 * @author Yin
 *
 */
public class ErrorCatcher extends JOptionPane {

    public ErrorCatcher(String noti) {
        setVisible(true);
        showMessageDialog(null, noti, "Error", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Show error message if exception is caught by running given method
     * @param errorName
     * @param object
     * @param methodName
     * @param list
     */
    public static void newError(String errorName, Object object,
            String methodName, List<Integer> list) {
        try 
        {
            Reflection.callMethod(object, methodName, list.get(0), list.get(1));
        }
        catch (Exception e) 
        {
            new ErrorCatcher(errorName);
        }
    }
    
    public static void newError(Object object, String methodName, Object[] objects)
    {
    	try
    	{
    		Reflection.callMethod(object, methodName, objects[0], objects[1]);
    	}
    	catch (Exception e)
    	{
    		new ErrorCatcher(e.getMessage());
    	}
    }
}