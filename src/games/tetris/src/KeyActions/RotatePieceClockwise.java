package games.tetris.src.KeyActions;

import games.tetris.src.Context.ITetrisContext;

public class RotatePieceClockwise implements IKeyAction
{
	@Override
	public void performKeyPressed(ITetrisContext tc, long elapsedTime)
	{
		tc.getActiveMino().rotate(true, tc.getTetrisMatrix());
	}

	@Override
	public void performKeyDown(ITetrisContext tc, long elapsedTime)
	{
		return;
	}
}
