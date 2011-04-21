package vooga.levels;

import java.awt.Graphics2D;
import java.util.Collection;
import vooga.collisions.collisionManager.CollisionManager;
import vooga.sprites.spritegroups.SpriteGroup;
import com.golden.gamedev.object.PlayField;

/**
 * Serve 
 * @author Andrew Patterson
 */
public class VoogaPlayField extends PlayField
{
    /** All of the sprite groups that are currently on the playingfield */
    protected Collection<SpriteGroup> mySpriteGroups;
    
    /** All of the collision managers that are part of the playingfield */
    protected Collection<CollisionManager> myCollisionManagers;
    
    
    /**
     * If it exists, returns the sprite group of the specified name. If it
     * doesn't exist, a new sprite group of the specified name is created, added
     * to the playingfield and returned
     * 
     * @return a sprite group of the specified name
     */
    public SpriteGroup getSpriteGroup (String groupName)
    {
        for (SpriteGroup<?> currentGroup : mySpriteGroups)
        {
            if (currentGroup.getName().equals(groupName))
            {
                return currentGroup;
            }
        }
        SpriteGroup newGroup = new SpriteGroup(groupName);
        addGroup(newGroup);
        return newGroup;
    } 
    
    
    /**
     * Adds a sprite group to this playfield
     * 
     * @param SpriteGroup to add
     */
    public void addGroup(SpriteGroup group)
    {
        mySpriteGroups.add(group);
    }
    
    
    /**
     * Adds a collision manager to this playfield
     * 
     * @param collision manager to add
     */
    public void addCollisionManager(CollisionManager manager)
    {
        myCollisionManagers.add(manager);
    }
    
    
    /**
     * Clears all sprites from the playingfield
     */
    public void clear()
    {
        for(SpriteGroup currentGroup : mySpriteGroups)
        {
            currentGroup.clear();
        }
    }
    
    
    /**
     * Updates all the sprite groups and checks all collision managers
     * 
     * @param elapsedTime
     */
    @Override
    public void update(long elapsedTime)
    {
        super.update(elapsedTime);
        for(SpriteGroup currentGroup : mySpriteGroups)
        {
            currentGroup.update(elapsedTime);
        }
        for(CollisionManager currentManager : myCollisionManagers)
        {
            currentManager.checkCollision();
        }
    }
    
    
    /**
     * Renders all sprite groups and the background
     * 
     * @param Graphics2D g
     */
    @Override
    public void render(Graphics2D g)
    {
        super.render(g);
        for(SpriteGroup currentGroup : mySpriteGroups)
        {
            currentGroup.render(g);
        }  
    }
}
