package vooga.levels.example.main;

import java.awt.Graphics2D;
import vooga.levels.example.collisions.BulletCollision;
import vooga.levels.example.collisions.WallStopCollision;
import vooga.levels.example.levels.LevelManager;
import vooga.levels.example.resourceManager.ResourceManager;
import vooga.levels.example.sprites.Ship;
import com.golden.gamedev.Game;
import com.golden.gamedev.object.Background;
import com.golden.gamedev.object.PlayField;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;


/**
 * A playing field which manages the connections between aliens, ships, bullets
 * and infoboard functions
 */
public class CustomPlayField extends PlayField
{
    private static final ResourceManager playfieldResources = new ResourceManager("playfield");
    private Game myGame;

    // Sprite Groups
    private SpriteGroup myShipGroup;
    private SpriteGroup myAlienGroup;
    private SpriteGroup myBulletGroup;
    private SpriteGroup myAlienBulletGroup;


    public CustomPlayField (Background background, Game game)
    {
        super(background);
        myGame = game;

        // Initialize Sprite Groups
        myBulletGroup = addGroup(new SpriteGroup(playfieldResources.getString("bullet_group_name")));
        myAlienBulletGroup = addGroup(new SpriteGroup(playfieldResources.getString("alien_bullet_group_name")));
        myAlienGroup = addGroup(new SpriteGroup(playfieldResources.getString("alien_group_name")));
        myShipGroup = addGroup(new SpriteGroup(playfieldResources.getString("ship_group_name")));
        myShipGroup.add(new Ship(myGame, this));

        setCollisionGroups();
        LevelManager.getInstance().setPlayField(this);
        LevelManager.getInstance().loadLevel(0);

    }

    
    @Override
    public void update (long elapsedTime)
    {
        super.update(elapsedTime);
        LevelManager.getInstance().checkLevelCompletion();
    }

    
    /**
     * Sets the collision groups
     */
    private void setCollisionGroups ()
    {
          addCollisionGroup(myShipGroup, null, new WallStopCollision(getBackground()));
          addCollisionGroup(myBulletGroup, myAlienGroup, new BulletCollision());
          addCollisionGroup(myAlienBulletGroup, myShipGroup, new BulletCollision());
    }
    
    
    @Override
    public void render (Graphics2D pen)
    {
        super.render(pen);
    }

    
    /**
     * Add a bullet to the playing field
     */
    public void addBullet (Sprite bullet)
    {
        myBulletGroup.add(bullet);
    }


    /**
     * Add an alien bullet to the playing field
     */
    public void addAlienBullet (Sprite bullet)
    {
        myAlienBulletGroup.add(bullet);
    }


    /**
     * Remove a bullet from the playing field
     */
    public void removeBullet (Sprite bullet)
    {
        myBulletGroup.remove(bullet);
    }


    /**
     * Remove a bullet from the playing field
     */
    public void removeAlienBullet (Sprite bullet)
    {
        myAlienBulletGroup.remove(bullet);
    }


    /**
     * Remove an alien from the playing field
     */
    public void removeAlien (Sprite alien)
    {
        myAlienGroup.remove(alien);
    }
    
    /**
     * Adds the aliens (bad guys) to the playing field
     */
    public void addAlien (Sprite alien)
    {
        myAlienGroup.add(alien);
    }


    /**
     * Update the score board by points
     */
    public void updateScoreBoard (int points)
    {
    }


    public SpriteGroup getAlienGroup ()
    {
        return myAlienGroup;
    }
}
