package games.tetris.src.Minos.Tetrominos;

import games.tetris.src.Main.TetrisMatrix;
import games.tetris.src.Minos.AbstractTetroMino;
import games.tetris.src.Minos.Monomino;

import java.awt.Color;


public class TetrominoZ extends AbstractTetroMino
{
	public TetrominoZ(Color c)
	{
		super();
		typeNum = gameResources.getInteger("TetrominoZ");

		for (int i = spawnLocation[0]; i < spawnLocation[0] + 2; i++)
			minos.add(new Monomino(c, i, spawnLocation[1] + 1));
	
		for (int i = spawnLocation[0] + 1; i < spawnLocation[0] + 3; i++)
			minos.add(new Monomino(c, i, spawnLocation[1]));
	}

	public TetrominoZ()
	{
		this(new Color(
				tetrominoResources.getIntegerArray("TetrominoZColor")[0],
				tetrominoResources.getIntegerArray("TetrominoZColor")[1],
				tetrominoResources.getIntegerArray("TetrominoZColor")[2],
				tetrominoResources.getIntegerArray("TetrominoZColor")[3]));
	}
	@Override
	public boolean rotate(boolean clockwise, TetrisMatrix matrix)
	{
		return super.rotate(clockwise, matrix, "TetrominoZ");
	}
}
