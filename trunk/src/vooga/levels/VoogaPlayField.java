package vooga.levels;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Collection;
import com.golden.gamedev.object.Background;
import vooga.collisions.collisionManager.CollisionManager;
import vooga.sprites.improvedsprites.Sprite;
import vooga.sprites.spritegroups.SpriteGroup;


/**
 * Serves as a container for sprites, sprite groups and collision managers in
 * which objects gets updated and renders if needed. Note that this playingfield does not
 * need to be used in conjuction with the level API.
 * 
 * @author Andrew Patterson
 */
public class VoogaPlayField
{
    /** The background for this playingfield that will be rendered each time the playingfield is rendered */
    private Background myBackground;

    /** All of the sprite groups that are currently on the playingfield */
    private Collection<SpriteGroup<Sprite>> mySpriteGroups;

    /** All of the collision managers that are part of the playingfield */
    private Collection<CollisionManager> myCollisionManagers;

    /** All other objects which are updatable */
    private Collection<IUpdatable> myUpdatables;

    /** All other objects which are renderable */
    private Collection<IRenderable> myRenderables;

    public VoogaPlayField()
    {
        mySpriteGroups = new ArrayList<SpriteGroup<Sprite>>();
        myCollisionManagers = new ArrayList<CollisionManager>();
        myUpdatables = new ArrayList<IUpdatable>();
        myRenderables = new ArrayList<IRenderable>();
        myBackground = Background.getDefaultBackground();
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
        addSpriteGroup(newGroup);
        return newGroup;
    } 


    /**
     * Adds a sprite group to this playfield. If the new sprite group and an
     * existing sprite group have the same names, then the exisiting sprite
     * group is overriden.
     * 
     * @param SpriteGroup to add
     */
    public void addSpriteGroup(SpriteGroup<Sprite> group)
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
     * Gets all of the sprite groups for this playingfield
     * 
     * @return a collection of all the current sprite groups
     */
    public Collection<SpriteGroup<Sprite>> getAllSpriteGroups()
    {
        return mySpriteGroups;
    }


    /**
     * Removes a specified sprite group from the playingfield (and if necessary
     * all of its associated collision managers)
     * 
     * @param Sprite group to remove
     */
    public void removeSpriteGroup(SpriteGroup<Sprite> group)
    {
        String groupName = group.getName();
        for(SpriteGroup<Sprite> currentGroup : mySpriteGroups)
        {
            if(groupName.equals(currentGroup.getName()))
            {
                mySpriteGroups.remove(currentGroup);
                break;
            }
        }
        for(CollisionManager currentManager : myCollisionManagers)
        {
            currentManager.removeGroupByName(group.getName());
        }
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
     * Removes the specified collision manager from the playingfield (if it
     * exists)
     * 
     * @param Collision manager that is to be removed
     */
    public void removeCollisionManager (CollisionManager managerToRemove)
    {
        for (CollisionManager currentManager : myCollisionManagers)
        {
            if (currentManager.equals(managerToRemove))
            {
                myCollisionManagers.remove(currentManager);
                break;
            }
        }
    }


    /**
     * Adds an IUpdatable to this playingfield
     * 
     * @param IUpdatable to add
     */
    public void addIUpdatable(IUpdatable updatable)
    {
        myUpdatables.add(updatable);
    }


    /**
     * Adds an IRenderable to this playingfield
     * 
     * @param IRenderable to add
     */
    public void addIUpdatable(IRenderable renderable)
    {
        myRenderables.add(renderable);
    }


    /**
     * Clears all sprites from the playingfield
     */
    public void clearPlayField()
    {
        for(SpriteGroup<Sprite> currentGroup : mySpriteGroups)
        {
            currentGroup.clear();
        }
    }


    /**
     * Sets the background for this playingfield
     * 
     * @param Background to use
     */
    public void setBackground(Background b)
    {
        if(b == null) myBackground = Background.getDefaultBackground();
        else myBackground = b;
    }


    /**
     * Checks collisions for all collision managers on this playingfield
     */
    public void checkCollisions()
    {
        for(CollisionManager currentManager : myCollisionManagers)
            currentManager.checkCollision();
    }


    /**
     * Inserts a sprite directly into the playingfield without explicitly giving
     * it a sprite group.
     * 
     * @param Sprite to add
     */
    public void add(Sprite extra)
    {
        getSpriteGroup("").add(extra);
    }


    /**
     * Gets the background for this playingfield
     * 
     * @return the current background
     */
    public Background getBackground()
    {
        return myBackground;
    }


    /**
     * Gets the collision managers for this playingfield
     * 
     * @return a collection of the currently defined collision managers
     */
    public Collection<CollisionManager> getCollisionManagers()
    {
        return myCollisionManagers;
    }


    /**
     * Updates all the sprite groups, collision managers and IUpdatables
     * 
     * @param elapsedTime
     */
    public void update(long elapsedTime)
    {
        // Sprite Groups
        for(SpriteGroup<Sprite> currentGroup : mySpriteGroups)
            if(currentGroup.isActive())
                currentGroup.update(elapsedTime);
        // Collision Managers
        for(CollisionManager currentManager : myCollisionManagers)
            if(currentManager.isActive())
                currentManager.checkCollision();
        // Updatables
        for(IUpdatable currentUpdatable : myUpdatables)
            currentUpdatable.update(elapsedTime);
        // Background
        myBackground.update(elapsedTime);
    }


    /**
     * Renders all sprite groups, the background and IRenderables
     * 
     * @param Graphics2D g
     */
    public void render(Graphics2D g)
    {
        // Background
        myBackground.render(g);
        // Sprite Groups
        for(SpriteGroup<Sprite> currentGroup : mySpriteGroups)
            if(currentGroup.isActive())
                currentGroup.render(g);
        // Renderables
        for(IRenderable currentRenderable : myRenderables)
            currentRenderable.render(g);
    }
}
