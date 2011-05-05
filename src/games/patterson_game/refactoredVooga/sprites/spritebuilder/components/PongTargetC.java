package games.patterson_game.refactoredVooga.sprites.spritebuilder.components;

import games.patterson_game.refactoredVooga.sprites.improvedsprites.Sprite;
import games.patterson_game.refactoredVooga.sprites.improvedsprites.interfaces.ISpriteUpdater;
import games.patterson_game.refactoredVooga.sprites.spritebuilder.components.basic.TargetC;

public class PongTargetC extends TargetC implements ISpriteUpdater
{

    @Override
    public void update (Sprite s, long elapsedTime)
    {
        s.setLocation(s.getX(), myTarget.getY());
    }

}
