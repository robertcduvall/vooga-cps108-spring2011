package games.tetris.src.KeyActions;

import games.tetris.src.Context.ITetrisContext;

public class RotatePieceCounterClockwise implements IKeyAction
{
	@Override
	public void performKeyPressed(ITetrisContext tc, long elapsedTime)
	{
		tc.subtractBlockLockDelayTotal(-100);
		tc.getActiveMino().rotate(false, tc.getTetrisMatrix());
	}

	@Override
	public void performKeyDown(ITetrisContext tc, long elapsedTime)
	{
		return;
	}
}
