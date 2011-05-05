package games.nathanAsteroids.gameObjects;

import games.nathanAsteroids.sprites.components.DefenseC;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.Random;
import vooga.collisions.shapes.ShapeFactory;
import vooga.collisions.shapes.collisionShapes.CollisionPolygon;
import vooga.sprites.improvedsprites.Sprite;
import vooga.sprites.spritebuilder.components.collisions.CollisionPolygonC;
import vooga.sprites.spritebuilder.components.physics.BasicPhysicsC;
import vooga.util.buildable.components.predefined.basic.HealthC;
import com.golden.gamedev.object.background.ImageBackground;
import com.golden.gamedev.util.ImageUtil;

public class Asteroid extends Sprite {
    private static final int LARGE_ASTEROID_SPEED_DIV = 8;
    private static final int MEDIUM_ASTEROID_SPEED_DIV = 6;
    private static final int SMALL_ASTEROID_SPEED_DIV = 4;
    private static final int DEFAULT_HARDNESS = 5;
    private static final int DEFAULT_MASS = 10;
    private static final int DEFAULT_HP = 200;
    private static Random random;

    public Asteroid(BufferedImage image, int x, int y, int mass, int hp, int def) {
        super(image, x, y);
        addComponents(new BasicPhysicsC(mass), new HealthC((double) hp), new DefenseC((double) def));
        this.addComponent(new CollisionPolygonC(new CollisionPolygon(ShapeFactory.makePolygonFromImage(ImageUtil.resize(image, width,height), 4))));
    }

//    // TODO: write resize image method to refactor this method
//    public static Asteroid getAsteroid(Dimension bounds, int size) {
//        if (size == 0)
//            return getSmallAsteroid(bounds);
//        else if (size == 1)
//            return getMediumAsteroid(bounds);
//        else
//            return getLargeAsteroid(bounds);
//
//    }

//    public static Asteroid getSmallAsteroid(Dimension bounds) {
//        random = new Random();
//        Asteroid asteroid = new Asteroid(res.getBufferedImage("Asteroid.small.image"), random.nextInt(bounds.width),
//                random.nextInt(bounds.height), DEFAULT_MASS, DEFAULT_HP, DEFAULT_HARDNESS);
//        asteroid.setDefaultPhysicsNature();
//        asteroid.setMovement(random.nextDouble() / SMALL_ASTEROID_SPEED_DIV, (double) random.nextInt(360));
//        
//        return asteroid;
//    }
//
//    public static Asteroid getMediumAsteroid(Dimension bounds) {
//        random = new Random();
//        Asteroid asteroid = new Asteroid(res.getBufferedImage("Asteroid.medium.image"), random.nextInt(bounds.width),
//                random.nextInt(bounds.height),  DEFAULT_MASS, DEFAULT_HP,DEFAULT_HARDNESS);
//        asteroid.setDefaultPhysicsNature();
//        asteroid.setMovement(random.nextDouble() / MEDIUM_ASTEROID_SPEED_DIV, (double) random.nextInt(360));
//        return asteroid;
//    }
//
//    public static Asteroid getLargeAsteroid(Dimension bounds) {
//        random = new Random();
//        Asteroid asteroid = new Asteroid(res.getBufferedImage("Asteroid.large.image"), random.nextInt(bounds.width),
//                random.nextInt(bounds.height), DEFAULT_MASS, DEFAULT_HP, DEFAULT_HARDNESS);
//        asteroid.setDefaultPhysicsNature();
//        asteroid.setMovement(random.nextDouble() / LARGE_ASTEROID_SPEED_DIV, (double) random.nextInt(360));
//        return asteroid;
//    }

}
