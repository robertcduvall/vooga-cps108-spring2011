package games.asteroids;

import java.awt.Dimension;

import com.golden.gamedev.object.PlayField;

import vooga.collisions.shapes.ShapeFactory;
import vooga.collisions.shapes.collisionShapes.CollisionCircle;
import vooga.collisions.shapes.collisionShapes.CollisionPolygon;
import vooga.collisions.shapes.regularShapes.Polygon;
import vooga.collisions.shapes.regularShapes.RegularPolygon;
import vooga.core.VoogaGame;
import vooga.sprites.improvedsprites.Sprite;
import vooga.sprites.spritebuilder.components.collisions.CollisionCircleC;
import vooga.sprites.spritebuilder.components.collisions.CollisionPolygonC;
import vooga.sprites.spritegroups.SpriteGroup;
import vooga.util.math.LineMath;


public class Asteroids extends VoogaGame
{
    public static void main (String[] args)
    {
        launchGame(new Asteroids(), new Dimension(640, 480), false);
    }

    private Ship myShip;


    @Override
    public void initResources ()
    {
        myShip = new Ship(this);
        myShip.setX(150);
        myShip.setY(150);
        myShip.addComponent(new CollisionPolygonC(ShapeFactory.makePolygonFromImage(myShip.getImage(),3)));
        getLevelManager().addPlayer(new SpriteGroup<Ship>("ship", myShip));
        
        // TODO getResourceManager().getKeyMap().registerEventHandler(this);

        getLevelManager().loadLevel(0);
    }


    @Override
    public void updatePlayField (long elapsedTime)
    {
        return;
    }
}
