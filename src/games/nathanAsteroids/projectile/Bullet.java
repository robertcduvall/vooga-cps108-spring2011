package games.nathanAsteroids.projectile;

import java.awt.image.BufferedImage;
import vooga.physics.util.Velocity;
import vooga.sprites.improvedsprites.Sprite;

public class Bullet extends Sprite{

    private static final int DEFAULT_HARDNESS = 20;
    private static final int DEFAULT_HP = 1;
    private static final int DEFAULT_MASS = 1;
    private static final int bulletDamage = 5;

    public Bullet(double x, double y, Velocity velocity) {
        super(x,y);
    }

}
