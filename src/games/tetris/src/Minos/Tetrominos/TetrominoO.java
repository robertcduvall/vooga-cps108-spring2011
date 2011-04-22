package games.tetris.src.Minos.Tetrominos;

import games.tetris.src.Main.TetrisMatrix;
import games.tetris.src.Minos.AbstractTetroMino;
import games.tetris.src.Minos.Monomino;

import java.awt.Color;


public class TetrominoO extends AbstractTetroMino
{
	public TetrominoO(Color c)
	{
		super();
		typeNum = gameResources.getInteger("TetrominoO");

		for (int i = spawnLocation[0] + 1; i < spawnLocation[0] + 3; i++)
			minos.add(new Monomino(c, i, spawnLocation[1]));
		for (int i = spawnLocation[0] + 1; i < spawnLocation[0] + 3; i++)
			minos.add(new Monomino(c, i, spawnLocation[1] + 1));
	}

	public TetrominoO()
	{
		this(new Color(
				tetrominoResources.getIntegerArray("TetrominoOColor")[0],
				tetrominoResources.getIntegerArray("TetrominoOColor")[1],
				tetrominoResources.getIntegerArray("TetrominoOColor")[2],
				tetrominoResources.getIntegerArray("TetrominoOColor")[3]));
	}

	@Override
	public boolean rotate(boolean clockwise, TetrisMatrix matrix)
	{
		return true;
	}
}
