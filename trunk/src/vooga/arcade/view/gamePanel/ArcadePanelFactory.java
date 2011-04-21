package vooga.arcade.view.gamePanel;

import vooga.arcade.parser.ArcadeGameObject;
import vooga.arcade.parser.ArcadeUserObject;
import vooga.arcade.parser.gameObject.ArcadeObject;

public class ArcadePanelFactory
{
	public static ArcadePanel generateAracdePanel(ArcadeObject ao)
	{
		/**
		 * REFLECTION IS TOO SLOW. I CODED THIS IN 30 SECONDS.
		 */
		if (ao instanceof ArcadeGameObject)
		{
			return new ArcadeGamePanel((ArcadeGameObject) ao);
		}
		else if (ao instanceof ArcadeUserObject)
		{
			return new ArcadeUserPanel((ArcadeUserObject) ao);
		}
		return null;
	}
}
