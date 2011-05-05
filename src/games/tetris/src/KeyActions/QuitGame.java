package games.lolcats.src.KeyActions;

import games.lolcats.src.Context.ITetrisContext;

public class QuitGame implements IKeyAction {
	@Override
	public void performKeyPressed(ITetrisContext tc, long elapsedTime) {
		tc.finish();
	}

	@Override
	public void performKeyDown(ITetrisContext tc, long elapsedTime) {
	}
}
