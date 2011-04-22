package games.tetris.src.Minos.Tetrominos;

import games.tetris.src.Main.TetrisMatrix;
import games.tetris.src.Minos.AbstractTetroMino;
import games.tetris.src.Minos.Monomino;

import java.awt.Color;


public class TetrominoS extends AbstractTetroMino
{
	public TetrominoS(Color c)
	{
		super();
		typeNum = gameResources.getInteger("TetrominoS");

		for (int i = spawnLocation[0]; i < spawnLocation[0] + 2; i++)
			minos.add(new Monomino(c, i, spawnLocation[1]));
		for (int i = spawnLocation[0] + 1; i < spawnLocation[0] + 3; i++)
			minos.add(new Monomino(c, i, spawnLocation[1] + 1));
	}

	public TetrominoS()
	{
		this(new Color(
				tetrominoResources.getIntegerArray("TetrominoSColor")[0],
				tetrominoResources.getIntegerArray("TetrominoSColor")[1],
				tetrominoResources.getIntegerArray("TetrominoSColor")[2],
				tetrominoResources.getIntegerArray("TetrominoSColor")[3]));
	}
	@Override
	public boolean rotate(boolean clockwise, TetrisMatrix matrix)
	{
		return super.rotate(clockwise, matrix, "TetrominoS");
	}
}
