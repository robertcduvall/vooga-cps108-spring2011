package games.tetris.src.TetrisComponents;


import games.tetris.src.Helper.ImageProcessor;
import games.tetris.src.Helper.ResourceManager;
import games.tetris.src.Minos.AbstractTetroMino;
import games.tetris.src.Minos.TetrominoFactory;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;



import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;

public class LookAheadManager
{
	private static ResourceManager resources = new ResourceManager(
			"games.tetris.TetrisResource");
	private static ResourceManager tetrominoResources = new ResourceManager(
			"games.tetris.TetrominoResource");
	private int numLookAheads;
	private int lookAheadSpawnX;
	private int lookAheadSpawnY;
	private int tetrisBlockSize;
	private int lookAheadBlockSpacing;

	private SpriteGroup sprites;

	private List<BufferedImage> imageList;
	private LinkedList<AbstractTetroMino> lookAheadMinos;

	public LookAheadManager(SpriteGroup sg)
	{
		imageList = new ArrayList<BufferedImage>();
		lookAheadMinos = new LinkedList<AbstractTetroMino>();
		numLookAheads = resources.getInteger("NumberOfLookAheads");
		lookAheadSpawnX = resources.getInteger("LookAheadSpawnPositionWidth");
		lookAheadSpawnY = resources.getInteger("LookAheadSpawnPositionHeight");
		lookAheadBlockSpacing = resources.getInteger("LookAheadBlockSpacing");
		tetrisBlockSize = resources.getInteger("MatrixCellSize");
		sprites = sg;
		
		loadImages();
		this.initialize();
		this.updateSprites();
	}

	private void loadImages()
	{
		// F you golden T and having a Non-static getImage method.

		for (int i = 0; i < resources.getInteger("NumberOfBlocks"); i++)
		{
			String imagePath = tetrominoResources
					.getString(Integer.toString(i));
			BufferedImage bi = ImageProcessor.loadImage(imagePath);
			bi = ImageProcessor.scaleImageWithSetY(bi, 2*tetrisBlockSize);
			imageList.add(bi);
		}
	}

	private void initialize()
	{
		for (int i = 0; i < numLookAheads; i++)
		{
			this.add(TetrominoFactory.generateTetromino());
		}
	}

	private void updateSprites()
	{
		sprites.reset();
		for (int i = 0; i < lookAheadMinos.size(); i++)
		{
			int type = lookAheadMinos.get(i).getTypeNumber();
			Sprite s = new Sprite(imageList.get(type), lookAheadSpawnX,
					lookAheadSpawnY + i*lookAheadBlockSpacing*tetrisBlockSize);
			s.setLayer(numLookAheads-i);
			sprites.add(s);
		}
	}

	private void add(AbstractTetroMino atm)
	{
		lookAheadMinos.add(atm);
	}

	public AbstractTetroMino getNextBlock()
	{
		AbstractTetroMino toReturn = lookAheadMinos.removeFirst();
		this.add(TetrominoFactory.generateTetromino());
		this.updateSprites();
		return toReturn;
	}
}
