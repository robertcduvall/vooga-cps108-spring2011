package games.lolcats.src.Context;

import games.lolcats.src.Minos.Tetromino;

public interface ITetrisContext {
	Tetromino getActiveMino();

	// TetrisMatrix getTetrisMatrix();
	// Timer getDasTimer();
	// Timer getArrTimer();
	// Timer getBlockLockTimer();
	// Timer getSoftDropTimer();
	// void subtractBlockLockDelayTotal(int time);
	// void resetBlockLockDelayTotal();
	// void generateNextBlock();
	// void adjustDasBy(int i);
	void finish();

	void reset();
	// boolean holdMino();
	// void setHoldUsed(boolean b);
}
