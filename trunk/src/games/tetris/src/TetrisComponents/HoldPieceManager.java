package games.tetris.src.TetrisComponents;

import games.tetris.src.Context.ITetrisContext;
import games.tetris.src.Helper.ImageProcessor;
import games.tetris.src.Helper.ResourceManager;
import games.tetris.src.Minos.AbstractTetroMino;
import games.tetris.src.Minos.TetrominoFactory;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;




import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;

public class HoldPieceManager
{
	private static ResourceManager resources = new ResourceManager(
			"TetrisResource");
	private static ResourceManager tetrominoResources = new ResourceManager(
			"TetrominoResource");
	private int holdSpawnX;
	private int holdSpawnY;
	private int tetrisBlockSize;

	private SpriteGroup sprites;

	private List<BufferedImage> imageList;

	private AbstractTetroMino heldMino;
	private boolean holdUsedThisTurn;

	public HoldPieceManager(SpriteGroup sg)
	{
		imageList = new ArrayList<BufferedImage>();
		holdSpawnX = resources.getInteger("HoldSpawnPositionWidth");
		holdSpawnY = resources.getInteger("HoldSpawnPositionHeight");
		tetrisBlockSize = resources.getInteger("MatrixCellSize");
		sprites = sg;

		this.loadImages();
	}

	private void loadImages()
	{
		for (int i = 0; i < resources.getInteger("NumberOfBlocks"); i++)
		{
			String imagePath = tetrominoResources
					.getString(Integer.toString(i));
			BufferedImage bi = ImageProcessor.loadImage(imagePath);
			bi = ImageProcessor.scaleImageWithSetY(bi, 2 * tetrisBlockSize);
			imageList.add(bi);
		}
	}

	private void updateSprites()
	{
		sprites.reset();
		int type = heldMino.getTypeNumber();
		Sprite s = new Sprite(imageList.get(type), holdSpawnX,
				holdSpawnY);
		sprites.add(s);
	}

	public void setHoldUsed(boolean b)
	{
		holdUsedThisTurn = b;
	}

	public boolean getHoldUsed()
	{
		return holdUsedThisTurn;
	}

	public boolean swapHeldMino(ActiveMino currentMino, ITetrisContext tc)
	{
		if (!holdUsedThisTurn)
		{
			currentMino.removeMinos();
			if (heldMino == null)
			{
				heldMino = currentMino.getCurrentMino();
				tc.generateNextBlock();
				this.updateSprites();
			}
			else
			{
				AbstractTetroMino tempMino = currentMino.getCurrentMino();
				currentMino.setMino(TetrominoFactory.generateTetromino(heldMino
						.getTypeNumber()));
				heldMino = tempMino;
			}

			this.updateSprites();
			return true;
		}
		return false;
	}
}
