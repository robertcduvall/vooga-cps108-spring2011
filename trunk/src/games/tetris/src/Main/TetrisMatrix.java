package games.tetris.src.Main;

import games.tetris.src.Context.ITetrisContext;
import games.tetris.src.Helper.ResourceManager;
import games.tetris.src.Minos.AbstractMino;
import games.tetris.src.Minos.Monomino;
import games.tetris.src.TetrisComponents.ActiveMino;

import java.util.List;




public class TetrisMatrix
{
	private Monomino[][] filledCells;
	private int matrixWidth;
	private int matrixMaxHeight;
	private int matrixValidHeight;

	private ResourceManager resources = new ResourceManager("TetrisResource");

	public TetrisMatrix()
	{
		matrixWidth = resources.getInteger("MatrixWidth");
		matrixMaxHeight = resources.getInteger("MatrixMaxHeight");
		matrixValidHeight = resources.getInteger("MatrixHeight");
		filledCells = new Monomino[matrixWidth][matrixMaxHeight];
	}

	public void lockMino(ActiveMino lastBlockUsed, ITetrisContext tc)
	{
		List<Monomino> list = lastBlockUsed.getCurrentMino().getMinos();
		for (Monomino m : list)
		{
			int x = m.getX();
			int y = m.getY();
			filledCells[x][y] = m;
		}
		
		lastBlockUsed.setActive(false);
		tc.setHoldUsed(false);

		checkLineClears(lastBlockUsed, tc);
	}

	// TO DO: return a type encasing a scoring system. -> tspin/tetris
	public void checkLineClears(ActiveMino lastBlockUsed, ITetrisContext tc)
	{
		int numClears = 0;
		for (int j = 0; j <= matrixValidHeight; j++)
		{
			boolean isFull = true;
			for (int i = 0; i < filledCells.length; i++)
			{
				if (filledCells[i][j] == null)
				{
					isFull = false;
					break;
				}
			}

			if (isFull)
			{
				shiftRowsDown(j);
				j--;
				numClears++;
			}
		}

	}

	private void shiftRowsDown(int clearedRowNum)
	{
		// Clear the Row
		for (int i = 0; i < filledCells.length; i++)
		{
			filledCells[i][clearedRowNum].getSprite().setActive(false);
			filledCells[i][clearedRowNum] = null;
		}

		for (int j = clearedRowNum + 1; j <= matrixValidHeight + 3; j++)
		{
			for (int i = 0; i < filledCells.length; i++)
			{
				if (filledCells[i][j] != null)
				{
					filledCells[i][j].moveY(-1);
					filledCells[i][j - 1] = filledCells[i][j];
					filledCells[i][j] = null;
				}
			}
		}
	}

	public boolean isLegalPosition(AbstractMino am)
	{
		List<Monomino> minoList = am.getMinos();
		for (Monomino m : minoList)
		{
			if (m.getX() >= matrixWidth || m.getX() < 0)
				return false;
			if (m.getY() >= matrixMaxHeight || m.getY() < 1)
				return false;
			if (filledCells[m.getX()][m.getY()] != null)
				return false;
		}
		return true;
	}
}
