package games.tetris.src.Context;

import games.tetris.src.Main.TetrisMatrix;
import games.tetris.src.TetrisComponents.ActiveMino;

import com.golden.gamedev.object.Timer;

public interface ITetrisContext
{
	ActiveMino getActiveMino();

	TetrisMatrix getTetrisMatrix();

	Timer getDasTimer();

	Timer getArrTimer();

	Timer getSoftDropTimer();

	void generateNextBlock();

	void adjustDasBy(int i);

	void reset();

	boolean holdMino();

	void setHoldUsed(boolean b);
}
