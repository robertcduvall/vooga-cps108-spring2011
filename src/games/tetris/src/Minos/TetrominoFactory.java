package games.tetris.src.Minos;

import games.tetris.src.Helper.ResourceManager;
import games.tetris.src.TetrisComponents.TetrominoRandomGenerator;

import java.awt.Color;
import java.lang.reflect.Constructor;
import java.util.Random;



public class TetrominoFactory
{
	private static ResourceManager resources = new ResourceManager(
			"TetrisResource");

	private static final String packagePath = "Minos.Tetrominos.";
	private static Random rand = new TetrominoRandomGenerator();

	public static AbstractTetroMino generateTetromino()
	{
		return generateTetromino(rand.nextInt(7));
	}

	public static AbstractTetroMino generateTetromino(int type)
	{
		String minoClassName = resources.getString(Integer.toString(type));
		Class<?> c = null;
		try
		{
			c = Class.forName(packagePath + minoClassName);
			Constructor<?> ctr = c.getConstructor();
			AbstractTetroMino am = (AbstractTetroMino) ctr.newInstance();
			return am;
		}
		catch (Exception e)
		{
		}
		return null;
	}

	public static AbstractTetroMino generateTetromino(int type, Color col)
	{
		String minoClassName = resources.getString(Integer.toString(type));
		Class<?> c = null;
		try
		{
			c = Class.forName(packagePath + minoClassName);
			Constructor<?> ctr = c.getConstructor(Color.class);
			AbstractTetroMino am = (AbstractTetroMino) ctr.newInstance(col);
			return am;
		}
		catch (Exception e)
		{
		}
		return null;
	}

	
	public static AbstractTetroMino copyTetromino(AbstractTetroMino atm)
	{
		AbstractTetroMino mino = generateTetromino(atm.getTypeNumber());
	
		return mino;
	}
}
