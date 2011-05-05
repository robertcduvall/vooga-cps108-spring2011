package games.arrow;

import games.arrow.sprites.Arrow;
import games.arrow.sprites.Enemy;

import java.util.Collection;

import com.golden.gamedev.util.ImageUtil;

import vooga.collisions.shapes.ShapeFactory;
import vooga.collisions.shapes.regularShapes.Polygon;
import vooga.core.VoogaGame;
import vooga.core.event.IEventHandler;
import vooga.levels.AbstractLevel;
import vooga.physics.forceGenerator.MassProportionalForceGenerator;
import vooga.physics.util.Force;
import vooga.sprites.improvedsprites.Sprite;
import vooga.sprites.spritebuilder.builder.SpriteBuilder;
import vooga.sprites.spritebuilder.components.basic.PermAccelerationC;
import vooga.sprites.spritebuilder.components.collisions.CollisionPolygonC;
import vooga.sprites.spritegroups.SpriteGroup;

/**
 * @author Michael Ansel
 * @author Wesley Brown
 *
 */
public class Level extends AbstractLevel
{
	
	public VoogaGame myGame;
	private SpriteBuilder<Enemy> EnemyBuilder;
    public Level (Collection<SpriteGroup<Sprite>> players, VoogaGame game)
    {
        super(players, game);
        EnemyBuilder = new SpriteBuilder<Enemy>(CollisionPolygonC.class);
        this.getPhysics().addGlobalForce(new MassProportionalForceGenerator(new Force(0, -.0005)),true);
        myGame = game;
        game.registerEventHandler("EnemySpawn", new IEventHandler()
        {
                @Override
                public void handleEvent(Object o)
                {
                        spawnEnemy();
                }
        });
        
        game.addPeriodicTimer("OneTime", 1500, "EnemySpawn");
    }

    @Override
    public void loadLevel ()
    {
        addAllSpritesFromPool();
        addBackground();
    }
    
    protected void spawnEnemy() {
    	Enemy enemy = (Enemy) myGame.getLevelManager().addArchetypeSprite("enemy" + (int)Math.floor((Math.random()*5)), (int)(getBackground().getWidth()*Math.random()),(int)((getBackground().getHeight()/2)*Math.random())); 
    	int n =  (int) Math.ceil((Math.random()*20)+1);
    	enemy = EnemyBuilder.buildSprite(enemy, new Polygon(enemy.getCenterX(), enemy.getCenterY(),n, enemy.getWidth()/2));
    	enemy.setBackground(getBackground());
		getSpriteGroup("enemy").addSprites(enemy);
	}
    
   
}
