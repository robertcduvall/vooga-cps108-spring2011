package games.tetris.src.KeyActions;

import games.tetris.src.Context.ITetrisContext;

public class MovePieceRight implements IKeyAction
{
	@Override
	public void performKeyPressed(ITetrisContext tc, long elapsedTime)
	{
		tc.getArrTimer().setActive(false);
		tc.getDasTimer().refresh();
		tc.getActiveMino().moveX(1, tc.getTetrisMatrix());
	}

	@Override
	public void performKeyDown(ITetrisContext tc, long elapsedTime)
	{
		if (tc.getDasTimer().action(elapsedTime))
		{
			tc.getArrTimer().setActive(true);
		}
		if (tc.getArrTimer().action(elapsedTime))
		{
			tc.getActiveMino().moveX(1, tc.getTetrisMatrix());
		}

	}
}
