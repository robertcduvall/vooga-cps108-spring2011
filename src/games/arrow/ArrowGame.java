package games.arrow;

import games.arrow.sprites.Archer;
import games.breakout.Breakout;

import java.awt.Dimension;

import com.golden.gamedev.object.PlayField;

import vooga.collisions.shapes.ShapeFactory;
import vooga.collisions.shapes.collisionShapes.CollisionCircle;
import vooga.collisions.shapes.collisionShapes.CollisionPolygon;
import vooga.collisions.shapes.regularShapes.Polygon;
import vooga.collisions.shapes.regularShapes.RegularPolygon;
import vooga.core.VoogaGame;
import vooga.core.event.IEventHandler;
import vooga.sprites.improvedsprites.Sprite;
import vooga.sprites.spritebuilder.components.collisions.CollisionCircleC;
import vooga.sprites.spritebuilder.components.collisions.CollisionPolygonC;
import vooga.sprites.spritegroups.SpriteGroup;
import vooga.util.math.LineMath;


public class ArrowGame extends VoogaGame
{
    public static void main (String[] args)
    {
        launchGame(new ArrowGame(), new Dimension(800, 800), false);
    }

    private Archer myArcher;


    @Override
    public void initResources ()
    {
        myArcher = new Archer(this);
        myArcher.setLocation(this.getWidth()/2-myArcher.getWidth()/2, this.getHeight()-(myArcher.getHeight()+50));
        getLevelManager().addPlayer(new SpriteGroup<Archer>("archer", myArcher));
        
        // TODO getResourceManager().getKeyMap().registerEventHandler(this);

        getLevelManager().loadLevel(0);
        
        this.registerEventHandler("EnemySpawn", new IEventHandler()
        {
                @Override
                public void handleEvent(Object o)
                {
                        spawnEnemy();
                }
        });
        
        this.addPeriodicTimer("OneTime", 10000, "EnemySpawn");
    }


    protected void spawnEnemy() {
    	getLevelManager().addArchetypeSprite("enemy", (int)(this.getWidth()*Math.random()),(int)((this.getHeight()-100)*Math.random()));
		
	}


	@Override
    public void updatePlayField (long elapsedTime)
    {
        return;
    }
    
    
}
