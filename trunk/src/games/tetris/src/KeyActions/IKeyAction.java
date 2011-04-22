package games.tetris.src.KeyActions;

import games.tetris.src.Context.ITetrisContext;

public interface IKeyAction
{
	void performKeyPressed(ITetrisContext tc, long elapsedTime);

	void performKeyDown(ITetrisContext tc, long elapsedTime);
}
