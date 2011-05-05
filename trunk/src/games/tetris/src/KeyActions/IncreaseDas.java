package games.tetris.src.KeyActions;

import games.tetris.src.Context.ITetrisContext;

public class IncreaseDas implements IKeyAction
{
	@Override
	public void performKeyPressed(ITetrisContext tc, long elapsedTime)
	{
		return;
	}

	@Override
	public void performKeyDown(ITetrisContext tc, long elapsedTime)
	{
		tc.adjustDasBy(5);
	}
}
