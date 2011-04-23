package vooga.sprites.spritebuilder.components;

import vooga.sprites.improvedsprites.Sprite;
import vooga.sprites.improvedsprites.interfaces.ISpriteUpdater;
import vooga.sprites.spritebuilder.components.basic.TargetC;

public class PongTargetC extends TargetC implements ISpriteUpdater
{

    @Override
    public void update (Sprite s, long elapsedTime)
    {
        s.setLocation(s.getX(), myTarget.getY());
    }

}
