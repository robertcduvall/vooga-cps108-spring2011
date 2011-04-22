package games.tetris.src.TetrisComponents;

import games.tetris.src.Helper.ResourceManager;
import games.tetris.src.Main.TetrisMatrix;
import games.tetris.src.Minos.AbstractTetroMino;
import games.tetris.src.Minos.TetrominoFactory;

import java.awt.Color;
import java.util.Observable;



import com.golden.gamedev.object.SpriteGroup;

public class ActiveMino extends Observable
{
	private static ResourceManager resources = new ResourceManager(
			"TetrisResource");

	private AbstractTetroMino currentMino;
	private AbstractTetroMino ghostMino;
	private SpriteGroup matrixGroup;
	private TetrisMatrix tetrisMatrix;
	private int yDelta;
	private int matrixSpawnHeight;

	public ActiveMino(SpriteGroup sg, TetrisMatrix tm)
	{
		tetrisMatrix = tm;
		matrixGroup = sg;
		matrixSpawnHeight = resources.getIntegerArray("MinoSpawnLocation")[1];
	}

	public void setMino(AbstractTetroMino atm)
	{
		currentMino = atm;
		ghostMino = TetrominoFactory.generateTetromino(atm.getTypeNumber(),
				new Color(0, 0, 0, 0));
		resetGhostMino(0, tetrisMatrix);
		yDelta = matrixSpawnHeight;

		currentMino.addMinosToSpriteGroup(matrixGroup);
		ghostMino.addMinosToSpriteGroup(matrixGroup);
	}

	public void hardDrop(TetrisMatrix tetrisMatrix)
	{
		currentMino.hardDrop(tetrisMatrix);
		ghostMino.removeMinos(matrixGroup);
	}

	private void resetGhostMino(int dx, TetrisMatrix tetrisMatrix)
	{
		ghostMino.setY(yDelta, tetrisMatrix);
		ghostMino.moveX(dx, tetrisMatrix);
		while (ghostMino.moveY(-1, tetrisMatrix))
			;
	}

	public boolean moveX(int dx, TetrisMatrix tetrisMatrix)
	{
		resetGhostMino(dx, tetrisMatrix);
		return currentMino.moveX(dx, tetrisMatrix);
	}

	public boolean moveY(int dy, TetrisMatrix tetrisMatrix)
	{
		boolean b = currentMino.moveY(dy, tetrisMatrix);
		if (b)
			yDelta--;
		return b;
	}

	public boolean rotate(boolean clockwise, TetrisMatrix tetrisMatrix)
	{
		boolean b = currentMino.rotate(clockwise, tetrisMatrix);
		if (b)
		{
			ghostMino.setY(yDelta, tetrisMatrix);
			ghostMino.rotate(clockwise, tetrisMatrix);
			while (ghostMino.moveY(-1, tetrisMatrix))
				;
		}
		return b;
	}

	public AbstractTetroMino getCurrentMino()
	{
		return currentMino;
	}

	public void setActive(boolean b)
	{
		ghostMino.removeMinos(matrixGroup);
	}

	public void removeMinos()
	{
		currentMino.removeMinos(matrixGroup);
		ghostMino.removeMinos(matrixGroup);
	}

	public void addMinos()
	{
		currentMino.addMinosToSpriteGroup(matrixGroup);
		ghostMino.addMinosToSpriteGroup(matrixGroup);
	}

}
