package vooga.levels;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Collection;
import vooga.collisions.collisionManager.CollisionManager;
import vooga.sprites.improvedsprites.Sprite;
import vooga.sprites.spritegroups.SpriteGroup;
import com.golden.gamedev.object.PlayField;


/**
 * Serves as a container for sprites, sprite groups and collision managers in
 * which objects gets updated and renders if needed.
 * 
 * @author Andrew Patterson
 */
public class VoogaPlayField extends PlayField
{
    /** All of the sprite groups that are currently on the playingfield */
    protected Collection<SpriteGroup<Sprite>> mySpriteGroups;

    /** All of the collision managers that are part of the playingfield */
    protected Collection<CollisionManager> myCollisionManagers;

    public VoogaPlayField()
    {
        super();
        mySpriteGroups = new ArrayList<SpriteGroup<Sprite>>();
        myCollisionManagers = new ArrayList<CollisionManager>();
    }

    /**
     * If it exists, returns the sprite group of the specified name. If it
     * doesn't exist, a new sprite group of the specified name is created, added
     * to the playingfield and returned
     * 
     * @return a sprite group of the specified name
     */
    public SpriteGroup<Sprite> getSpriteGroup (String groupName)
    {
        for (SpriteGroup<Sprite> currentGroup : mySpriteGroups)
        {
            if (currentGroup.getName().equals(groupName))
            {
                return currentGroup;
            }
        }
        SpriteGroup<Sprite> newGroup = new SpriteGroup<Sprite>(groupName);
        addGroup(newGroup);
        return newGroup;
    } 


    /**
     * Adds a sprite group to this playfield. If the new sprite group and an
     * existing sprite group have the same names, then the exisiting sprite
     * group is overriden.
     * 
     * @param SpriteGroup to add
     */
    public void addGroup(SpriteGroup<Sprite> group)
    {
        for(SpriteGroup<Sprite> currentGroup : mySpriteGroups)
        {
            if(currentGroup.getName().equals(group.getName()))
            {
                mySpriteGroups.remove(currentGroup);
                break;
            }
        }
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
        for(SpriteGroup<Sprite> currentGroup : mySpriteGroups)
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
        for(SpriteGroup<Sprite> currentGroup : mySpriteGroups)
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
        for(SpriteGroup<Sprite> currentGroup : mySpriteGroups)
        {
            currentGroup.render(g);
        }  
    }
}
