package games.tetris.src.Minos.Tetrominos;

import games.tetris.src.Main.TetrisMatrix;
import games.tetris.src.Minos.AbstractTetroMino;
import games.tetris.src.Minos.Monomino;

import java.awt.Color;


public class TetrominoJ extends AbstractTetroMino
{
	public TetrominoJ(Color c)
	{
		super();
		typeNum = gameResources.getInteger("TetrominoJ");

		for (int i = spawnLocation[0]; i < spawnLocation[0] + 3; i++)
			minos.add(new Monomino(c, i, spawnLocation[1]));
		minos.add(new Monomino(c, spawnLocation[0], spawnLocation[1] + 1));
	}

	public TetrominoJ()
	{
		this(new Color(
				tetrominoResources.getIntegerArray("TetrominoJColor")[0],
				tetrominoResources.getIntegerArray("TetrominoJColor")[1],
				tetrominoResources.getIntegerArray("TetrominoJColor")[2],
				tetrominoResources.getIntegerArray("TetrominoJColor")[3]));
	}

	@Override
	public boolean rotate(boolean clockwise, TetrisMatrix matrix)
	{
		return super.rotate(clockwise, matrix, "TetrominoJ");
	}
}
