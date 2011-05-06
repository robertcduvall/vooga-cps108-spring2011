package vooga.arcade.view.actions;


import java.awt.event.ActionListener;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import vooga.arcade.controller.ArcadeController;
import vooga.arcade.view.helper.ResourceManager;


/**
 * This class uses reflection to create the associated action with the
 * particular desired component, indicated by the string passed into the
 * factory.
 * 
 * @author Ethan Yong-Hui Goh
 * Revised by Andrea Scripa
 * 
 */
public class ActionFactory {
	private static ResourceManager factoryResources = new ResourceManager(
			"vooga.arcade.resources.ActionFactoryResource");

	public static ActionListener createAction(String objName, ArcadeController p) {
		//if (objName.equals(ButtonBarItemUser))
	    
	    // reflect on method/class name
		String className = factoryResources.getString(objName);
	
		Class<?> cls = null;
		try {
			cls = Class.forName("vooga.arcade.view.actions." + className);
			//Method m = cls.getDeclaredMethod(methodName, null);

			Constructor<?> ctr = cls
					.getDeclaredConstructor(new Class[] { ArcadeController.class });
			//return new ActionMethod(obj,m);
			return (AbstractViewAction) ctr.newInstance(p);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
}
