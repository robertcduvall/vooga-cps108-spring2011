package games.tetris.src.Minos.Tetrominos;

import games.tetris.src.Main.TetrisMatrix;
import games.tetris.src.Minos.AbstractTetroMino;
import games.tetris.src.Minos.Monomino;

import java.awt.Color;


public class TetrominoL extends AbstractTetroMino
{
	public TetrominoL(Color c)
	{
		super();
		typeNum = gameResources.getInteger("TetrominoL");
		for (int i = spawnLocation[0]; i < spawnLocation[0] + 3; i++)
			minos.add(new Monomino(c, i, spawnLocation[1]));
		minos.add(new Monomino(c, spawnLocation[0] + 2, spawnLocation[1] + 1));
	}

	public TetrominoL()
	{
		this(new Color(
				tetrominoResources.getIntegerArray("TetrominoLColor")[0],
				tetrominoResources.getIntegerArray("TetrominoLColor")[1],
				tetrominoResources.getIntegerArray("TetrominoLColor")[2],
				tetrominoResources.getIntegerArray("TetrominoLColor")[3]));
	}
	
	@Override
	public boolean rotate(boolean clockwise, TetrisMatrix matrix)
	{
		return super.rotate(clockwise, matrix, "TetrominoL");
	}
}
