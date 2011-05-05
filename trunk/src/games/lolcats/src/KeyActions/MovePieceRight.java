package games.lolcats.src.KeyActions;

import games.lolcats.src.Context.ITetrisContext;
import games.lolcats.src.Minos.Tetromino;

public class MovePieceRight implements IKeyAction {
	@Override
	public void performKeyPressed(ITetrisContext tc, long elapsedTime) {
		// tc.getArrTimer().setActive(false);
		// tc.getDasTimer().refresh();
		// tc.getActiveMino().moveX(-1, tc.getTetrisMatrix());
	}

	@Override
	public void performKeyDown(ITetrisContext tc, long elapsedTime) {
		// if (tc.getDasTimer().action(elapsedTime)) {
		// tc.getArrTimer().setActive(true);
		// }
		// if (tc.getArrTimer().action(elapsedTime)) {
		// tc.getActiveMino().moveX(-1, tc.getTetrisMatrix());
		// }
		tc.getActiveMino().setDirection(Tetromino.RIGHT);
	}
}
