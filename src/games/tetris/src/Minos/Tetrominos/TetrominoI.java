package games.tetris.src.Minos.Tetrominos;

import games.tetris.src.Main.TetrisMatrix;
import games.tetris.src.Minos.AbstractTetroMino;
import games.tetris.src.Minos.Monomino;

import java.awt.Color;


public class TetrominoI extends AbstractTetroMino
{
	public TetrominoI(Color c)
	{
		super();
		typeNum = gameResources.getInteger("TetrominoI");
		for (int i = spawnLocation[0]; i < spawnLocation[0] + 4; i++)
			minos.add(new Monomino(c, i, spawnLocation[1]));
	}

	public TetrominoI()
	{
		this(new Color(
				tetrominoResources.getIntegerArray("TetrominoIColor")[0],
				tetrominoResources.getIntegerArray("TetrominoIColor")[1],
				tetrominoResources.getIntegerArray("TetrominoIColor")[2],
				tetrominoResources.getIntegerArray("TetrominoIColor")[3]));
	}

	@Override
	public boolean rotate(boolean clockwise, TetrisMatrix matrix)
	{
		return super.rotate(clockwise, matrix, "TetrominoI");
	}

}
