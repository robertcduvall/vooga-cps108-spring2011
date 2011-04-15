package vooga.arcade.gui.actions;

import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import controller.IController;

public abstract class AbstractSlogoAction implements ActionListener
{
	protected IController controller;

	public AbstractSlogoAction(IController p)
	{
		controller = p;
	}

	public void promptController(String methodName, Object... objList)
	{
		ArrayList<Class> classList = new ArrayList<Class>();

		for (Object o : objList)
		{
			classList.add(o.getClass());
		}

		try
		{
			Method m = controller.getClass().getDeclaredMethod(methodName,
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

	public BufferedImage createBufferedImage(String filename)
	{
		ImageIcon icon = new ImageIcon(filename);
		Image image = icon.getImage();

		// Create empty BufferedImage, sized to Image
		BufferedImage buffImage = new BufferedImage(image.getWidth(null),
				image.getHeight(null), BufferedImage.TYPE_INT_ARGB);

		// Draw Image into BufferedImage
		return buffImage;
	}

}
