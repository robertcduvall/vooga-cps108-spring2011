package games.tetris.src.Minos;

import games.tetris.src.Helper.ResourceManager;
import games.tetris.src.Main.TetrisMatrix;

import java.util.ArrayList;
import java.util.List;



import com.golden.gamedev.object.SpriteGroup;

public abstract class AbstractMino
{
	protected ResourceManager gameResources = new ResourceManager(
			"games.tetris.TetrisResource");
	protected List<Monomino> minos;
	protected int[] spawnLocation = gameResources
			.getIntegerArray("MinoSpawnLocation");
	protected int typeNum;

	public abstract boolean rotate(boolean clockwise, TetrisMatrix tetrisMatrix);

	public AbstractMino()
	{
		minos = new ArrayList<Monomino>();
	}

	public int getTypeNumber()
	{
		return typeNum;
	}

	public void addMinosToSpriteGroup(SpriteGroup sg)
	{
		for (Monomino m : minos)
		{
			sg.add(m.getSprite());
		}
	}

	public void removeMinos(SpriteGroup sg)
	{
		for (Monomino m : minos)
		{
			// m.getSprite().setActive(false);
			sg.remove(m.getSprite());
		}
	}

	public List<Monomino> getMinos()
	{
		return minos;
	}

	public boolean moveX(int dx, TetrisMatrix tetrisMatrix)
	{
		for (Monomino m : minos)
		{
			m.moveX(dx);
		}

		// Check Legal Position, reverse if illegal
		if (!tetrisMatrix.isLegalPosition(this))
		{
			for (Monomino m : minos)
			{
				m.moveX(-dx);
			}
			return false;
		}
		return true;
	}

	public boolean moveY(int dy, TetrisMatrix tetrisMatrix)
	{
		for (Monomino m : minos)
		{
			m.moveY(dy);
		}

		// Check Legal Position, reverse if illegal
		if (!tetrisMatrix.isLegalPosition(this))
		{
			for (Monomino m : minos)
			{
				m.moveY(-dy);
			}
			return false;
		}
		return true;
	}

	public boolean setY(int y, TetrisMatrix tetrisMatrix)
	{
		int dy = y - minos.get(0).getY();
		return moveY(dy, tetrisMatrix);
	}

	public boolean setX(int x, TetrisMatrix tetrisMatrix)
	{
		int dx = x - minos.get(0).getX();
		return moveX(dx, tetrisMatrix);
	}

	public void hardDrop(TetrisMatrix tetrisMatrix)
	{
		while (moveY(-1, tetrisMatrix))
		{
		}
	}

}
