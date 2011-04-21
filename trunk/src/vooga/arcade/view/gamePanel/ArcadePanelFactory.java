package vooga.arcade.view.gamePanel;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import vooga.arcade.parser.ArcadeGameObject;
import vooga.arcade.parser.ArcadeUserObject;
import vooga.arcade.parser.gameObject.ArcadeObject;

/**
 * Generates Panels for the Arcade Frame based on Raw Data obtained from the model.
 * TODO: Use Reflection (ugh) to generalize this method. Its gonna be ugly.
 * @author Ethan Goh
 *
 */
public class ArcadePanelFactory
{
	public static List<JPanel> generateArcadeGamePanels(List<ArcadeGameObject> aoList)
	{
		List<JPanel> toReturn = new ArrayList<JPanel>();
		for (ArcadeGameObject ao : aoList)
		{
			toReturn.add(new ArcadeGamePanel(ao));
		}
		return toReturn;
	}
//	
//	public static List<JPanel> generateAracdeUserPanels(List<ArcadeUserObject> aoList)
//	{
//		List<JPanel> toReturn = new ArrayList<JPanel>();
//		for (ArcadeUserObject ao : aoList)
//		{
//			toReturn.add(new ArcadeUserPanel(ao));
//		}
//		return toReturn;
//	}
}
