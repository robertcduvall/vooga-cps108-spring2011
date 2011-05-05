package games.tetris.src.Minos;

import games.tetris.src.Helper.ResourceManager;
import games.tetris.src.Main.TetrisMatrix;

public abstract class AbstractTetroMino extends AbstractMino
{
	private static ResourceManager rotationResources = new ResourceManager(
			"games.tetris.TetrominoRotationResource");
	protected static ResourceManager tetrominoResources = new ResourceManager(
			"games.tetris.TetrominoResource");

	private int rotationPosition = 0;

	public boolean rotate(boolean clockwise, TetrisMatrix tetrisMatrix,
			String tetrominoTypeName)
	{
		int rotationDirection = clockwise ? 1 : -1;
		int nextRotationPosition = rotationPosition + rotationDirection;
		nextRotationPosition = nextRotationPosition < 0 ? nextRotationPosition += 4
				: nextRotationPosition;
		nextRotationPosition %= 4;

		// Special Wall Kick rules for I tetromino, no way around this.
		String tetrominoWallKickTypeName = tetrominoTypeName;
		if (tetrominoTypeName.charAt(tetrominoTypeName.length() - 1) != 'I')
		{
			tetrominoWallKickTypeName = "TetrominoX";
		}

		int[] rotations = null;
		int[] translations = null;
		if (clockwise)
		{
			rotations = rotationResources.getIntegerArray(tetrominoTypeName
					+ "Rotate" + rotationPosition + "" + nextRotationPosition);
			translations = rotationResources
					.getIntegerArray(tetrominoWallKickTypeName
							+ rotationPosition + "" + nextRotationPosition);

		}
		else
		{
			rotations = rotationResources.getIntegerArray(tetrominoTypeName
					+ "Rotate" + nextRotationPosition + "" + rotationPosition);
			translations = rotationResources
			.getIntegerArray(tetrominoWallKickTypeName
					+ nextRotationPosition + "" + rotationPosition);
		}

		// Super Rotation System - Wall Kicks
		for (int i = 0; i < translations.length; i += 2)
		{
			for (int j = 0; j < minos.size(); j++)
			{
				Monomino m = minos.get(j);
				m.moveX(rotationDirection
						* (rotations[2 * j] + translations[i]));
				m.moveY(rotationDirection
						* (rotations[2 * j + 1] + translations[i + 1]));
				// System.out.println(rotationDirection
				// * (rotations[2 * j] + translations[i]) + " "
				// + rotations[2 * j] + " " + translations[i]);
				// System.out.println(rotationDirection
				// * (rotations[2 * j + 1] + translations[i + 1]) + " "
				// + rotations[2 * j + 1] + " " + translations[i + 1]);
			}

			// Check Legal Position, reverse if illegal
			if (!tetrisMatrix.isLegalPosition(this))
			{
				for (int j = 0; j < minos.size(); j++)
				{
					Monomino m = minos.get(j);
					m.moveX(-rotationDirection
							* (rotations[2 * j] + translations[i]));
					m.moveY(-rotationDirection
							* (rotations[2 * j + 1] + translations[i + 1]));
				}

				continue;
			}
			rotationPosition += rotationDirection;
			rotationPosition = rotationPosition < 0 ? rotationPosition += 4
					: rotationPosition;
			rotationPosition %= 4;

			return true;
		}
		return false;
	}
}
