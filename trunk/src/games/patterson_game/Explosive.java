package games.patterson_game;

import games.patterson_game.refactoredVooga.levelsRefactored.LevelManager;
import games.patterson_game.refactoredVooga.resources.bundle.Bundle;
import vooga.sprites.improvedsprites.Sprite;
import java.awt.image.BufferedImage;
import vooga.sprites.spritebuilder.components.collisions.CollisionCircleC;
import vooga.util.buildable.components.predefined.basic.HealthC;

/**
 * An explosive floating object that explodes and sends shrapnel flying when it is destroyed
 * 
 * @author Andrew Patterson
 */
public class Explosive extends AbstractFloatingObject
{
    private static final long serialVersionUID = 1314L;
    private LevelManager myLevelManager;
    private Bundle myBundle;
    
    public Explosive(BufferedImage image, int x, int y)
    {
        super(image, x, y);
        myBundle = AvoiderGame.getBundle();
        myLevelManager = AvoiderGame.getInstance().getLevelManager();
        addComponents(new HealthC(myBundle.getDouble("explosive_strength")));
        addComponent(new CollisionCircleC(getCenterPoint(), getWidth()/3));
    }

    @Override
    public void takeDamage(double damage)
    {
        getComponent(HealthC.class).decrease(damage);
        if (getComponent(HealthC.class).isDead())
        {
            setActive(false);
            explode();
        }   
    }
    
    
    /**
     * Explodes this explosive, sending damaging shrapnel pieces everywhere
     */
    private void explode ()
    {
        int numOfShrapnelPieces = myBundle.getInteger("num_of_shrapnel_pieces");
        for(int i = 0; i < numOfShrapnelPieces; i ++)
        {
            Sprite shrapnel = myLevelManager.addArchetypeSprite("shrapnel", (int) getX(), (int) getY());
            shrapnel.setAngle(i * (360 / numOfShrapnelPieces));
            shrapnel.accelerate(myBundle.getDouble("shrapnel_speed")); 
        }
        setActive(false);
    }

}
