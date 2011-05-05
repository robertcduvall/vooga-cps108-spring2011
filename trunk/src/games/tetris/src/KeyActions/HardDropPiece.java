package games.tetris.src.KeyActions;

import games.tetris.src.Context.ITetrisContext;

public class HardDropPiece implements IKeyAction
{
        @Override
        public void performKeyPressed(ITetrisContext tc, long elapsedTime)
        {
                tc.getActiveMino().hardDrop(tc.getTetrisMatrix());
                tc.getTetrisMatrix().lockMino(tc.getActiveMino(),tc);
                tc.generateNextBlock();
                tc.resetBlockLockDelayTotal();
        }

        @Override
        public void performKeyDown(ITetrisContext tc, long elapsedTime)
        {
        }
}