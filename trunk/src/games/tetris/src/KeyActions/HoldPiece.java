package games.tetris.src.KeyActions;

import games.tetris.src.Context.ITetrisContext;

public class HoldPiece implements IKeyAction
{
	@Override
	public void performKeyPressed(ITetrisContext tc, long elapsedTime)
	{
		tc.holdMino();
		tc.setHoldUsed(true);
	}

	@Override
	public void performKeyDown(ITetrisContext tc, long elapsedTime)
	{
	}
}
