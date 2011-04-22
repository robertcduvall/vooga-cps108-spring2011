package vooga.arcade.view.actions;


import java.awt.event.ActionListener;
import java.lang.reflect.Constructor;
import vooga.arcade.controller.ArcadeController;
import vooga.arcade.view.helper.ResourceManager;


/**
 * This class uses reflection to create the associated action with the
 * particular desired component, indicated by the string passed into the
 * factory.
 * 
 * @author Ethan Yong-Hui Goh
 * 
 */
public class ActionFactory {
	private static ResourceManager factoryResources = new ResourceManager(
			"vooga.arcade.resources.ActionFactoryResource");

	public static ActionListener createAction(String objName, ArcadeController p) {
		// reflect on method/class name
		String className = factoryResources.getString(objName);
	
		Class<?> cls = null;
		try {
			cls = Class.forName("view.actions." + className);
			// Method m = cls.getDeclaredMethod(methodName, null);

			Constructor<?> ctr = cls
					.getDeclaredConstructor(new Class[] { ArcadeController.class });
			// return new ActionMethod(obj,m);
			return (AbstractVoogaAction) ctr.newInstance(p);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
}
