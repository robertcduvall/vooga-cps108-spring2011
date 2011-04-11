package vooga.core.event.examples.bubblefishbob.bonuses;

import java.awt.Color;

import java.lang.reflect.Constructor;
import java.util.Random;


import vooga.core.event.examples.bubblefishbob.bubbleFish.ProgBob;
import vooga.core.event.examples.bubblefishbob.util.resources.ResourceManager;

public class BonusFactory
{
	private static ResourceManager resources = ResourceManager.getInstance();

	private static final String packagePath = "vooga.core.event.examples.bubblefishbob.bonuses.";
	private static Random rand = new Random(123);

	static
	{
		resources.addResourcesFromFile("BonusFactory");
	}
	
	public static Bonus generateBonus(ProgBob pb)
	{
		return generateBonus(rand.nextInt(resources.getInteger("numBonuses")),
				pb);
	}

	public static Bonus generateBonus(int type, ProgBob pb)
	{
		String bonusClassName = resources.getString(Integer.toString(type));
		Class<?> c = null;
		try
		{
			c = Class.forName(packagePath + bonusClassName);
			Constructor<?> ctr = c.getConstructor(ProgBob.class);
			Bonus am = (Bonus) ctr.newInstance(pb);
			return am;
		}
		catch (Exception e)
		{
		}
		return null;
	}
}
