package games.tetris.src.KeyActions;

import games.tetris.src.Context.ITetrisContext;

public class SoftDropPiece implements IKeyAction
{
	@Override
	public void performKeyPressed(ITetrisContext tc, long elapsedTime)
	{
		return;
	}

	@Override
	public void performKeyDown(ITetrisContext tc, long elapsedTime)
	{
		if (tc.getSoftDropTimer().action(elapsedTime))
		{
			tc.getSoftDropTimer().refresh();
			tc.getActiveMino().moveY(-1, tc.getTetrisMatrix());
		}
	}
}
