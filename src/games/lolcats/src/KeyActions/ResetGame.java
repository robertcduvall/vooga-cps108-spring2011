package games.lolcats.src.KeyActions;

import games.lolcats.src.Context.ITetrisContext;

public class ResetGame implements IKeyAction {
	@Override
	public void performKeyPressed(ITetrisContext tc, long elapsedTime) {
		tc.reset();
	}

	@Override
	public void performKeyDown(ITetrisContext tc, long elapsedTime) {
	}
}
