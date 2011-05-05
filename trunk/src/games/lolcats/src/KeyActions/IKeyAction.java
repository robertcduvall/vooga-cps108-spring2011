package games.lolcats.src.KeyActions;

import games.lolcats.src.Context.ITetrisContext;

public interface IKeyAction {
	void performKeyPressed(ITetrisContext tc, long elapsedTime);

	void performKeyDown(ITetrisContext tc, long elapsedTime);
}
