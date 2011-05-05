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

	Timer getBlockLockTimer();
	
	Timer getSoftDropTimer();

	void subtractBlockLockDelayTotal(int time);
	
	void resetBlockLockDelayTotal();
	
	void generateNextBlock();

	void adjustDasBy(int i);

	void reset();

	boolean holdMino();

	void setHoldUsed(boolean b);
}
