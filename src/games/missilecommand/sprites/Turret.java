package games.missilecommand.sprites;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.golden.gamedev.util.ImageUtil;

import vooga.collisions.shapes.ShapeFactory;
import vooga.collisions.shapes.collisionShapes.CollisionPolygon;
import vooga.sprites.improvedsprites.Sprite;
import vooga.sprites.spritebuilder.components.collisions.CollisionPolygonC;

/**
 * A sprite that represents the player-controlled turret. Fires bullets and
 * doesn't afraid of anything.
 * @author Alex Lee (hl69)
 */
public class Turret extends Sprite
{

    public Turret(BufferedImage image, int x, int y)
    {
        super(image, x, y);
        this.addComponent(new CollisionPolygonC(new CollisionPolygon(ShapeFactory.makePolygonFromImage(ImageUtil.resize(image, width,height), 4))));;
    }

    public void render(Graphics2D g, int x, int y)
    {
        super.render(g, x, y);
    }
}
