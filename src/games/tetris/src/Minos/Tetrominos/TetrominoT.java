package games.tetris.src.Minos.Tetrominos;

import games.tetris.src.Main.TetrisMatrix;
import games.tetris.src.Minos.AbstractTetroMino;
import games.tetris.src.Minos.Monomino;

import java.awt.Color;


public class TetrominoT extends AbstractTetroMino
{
	public TetrominoT(Color c)
	{
		super();
		typeNum = gameResources.getInteger("TetrominoT");

		int[] spawnLocation = gameResources
				.getIntegerArray("MinoSpawnLocation");
		for (int i = spawnLocation[0]; i < spawnLocation[0] + 3; i++)
			minos.add(new Monomino(c, i, spawnLocation[1]));
		minos.add(new Monomino(c, spawnLocation[0] + 1, spawnLocation[1] + 1));
	}

	public TetrominoT()
	{
		this(new Color(
				tetrominoResources.getIntegerArray("TetrominoTColor")[0],
				tetrominoResources.getIntegerArray("TetrominoTColor")[1],
				tetrominoResources.getIntegerArray("TetrominoTColor")[2],
				tetrominoResources.getIntegerArray("TetrominoTColor")[3]));
	}

	@Override
	public boolean rotate(boolean clockwise, TetrisMatrix matrix)
	{
		return super.rotate(clockwise, matrix, "TetrominoT");
	}
}
