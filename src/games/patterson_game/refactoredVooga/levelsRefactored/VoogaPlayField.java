package games.patterson_game.refactoredVooga.levelsRefactored;

import games.patterson_game.refactoredVooga.collisions.collisionManager.CollisionManager;
import games.patterson_game.refactoredVooga.sprites.improvedsprites.Sprite;
import games.patterson_game.refactoredVooga.sprites.spritegroups.SpriteGroup;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Collection;
import com.golden.gamedev.object.Background;


/**
 * Serves as a container for <code>Sprite</code>, <code>SpriteGroups</code>,
 * <code>CollisionManagers</code>, updatables and renderables in which objects
 * gets updated and renders if needed. Note that this playingfield does not need
 * to be used in conjuction with the level API. 
 * 
 * @author Andrew Patterson
 * @version 1.7
 * @since 1.6
 */
public class VoogaPlayField
{
    /** The background for this playingfield that will be rendered each time the playingfield is rendered */
    private Background myBackground;

    /** All of the sprite groups that are currently on the playingfield */
    private Collection<SpriteGroup<Sprite>> mySpriteGroups;
    
    /** All of the collision managers that are part of the playingfield */
    private Collection<CollisionManager<Sprite,Sprite>> myCollisionManagers;

    /** All other objects which are updatable */
    private Collection<IUpdatable> myUpdatables;

    /** All other objects which are renderable */
    private Collection<IRenderable> myRenderables;

    /**
     * Creates an empty playfield with a default background
     */
    public VoogaPlayField()
    {
        mySpriteGroups = new ArrayList<SpriteGroup<Sprite>>();
        myCollisionManagers = new ArrayList<CollisionManager<Sprite,Sprite>>();
        myUpdatables = new ArrayList<IUpdatable>();
        myRenderables = new ArrayList<IRenderable>();
        myBackground = Background.getDefaultBackground();
    }


    /**
     * If it exists, returns the sprite group of the specified name; if it
     * doesn't exist, a new <code>SpriteGroup</code> of the specified name is
     * created, added to the playingfield and returned
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
     * Adds a <code>SpriteGroup</code> to this playfield. If the new
     * <code>SpriteGroup</code> and an existing <code>SpriteGroup</code> have
     * the same names, then the exisiting <code>SpriteGroup</code> is overriden.
     * 
     * @param group the <code>SpriteGroup</code> to add
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
     * Returns all of the sprite groups for this playingfield
     * 
     * @return a collection of all the current sprite groups
     */
    public Collection<SpriteGroup<Sprite>> getAllSpriteGroups()
    {
        return mySpriteGroups;
    }


    /**
     * Removes a specified <code>SpriteGroup</code> from the playingfield (and if necessary
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
        for(CollisionManager<Sprite,Sprite> currentManager : myCollisionManagers)
        {
            currentManager.clearGroupByName(group.getName());
        }
    }


    /**
     * Adds a collision manager to this playfield
     * 
     * @param collision manager to add
     */
    public void addCollisionManager(CollisionManager<Sprite,Sprite> manager)
    {
        myCollisionManagers.add(manager);
    }


    /**
     * Removes the specified <code>CollisionManager</code> from the playingfield
     * (if it exists)
     * 
     * @param managerToRemove the <code>CollisionManager</code> that you wish to remove
     */
    public void removeCollisionManager (CollisionManager<Sprite,Sprite> managerToRemove)
    {
        for (CollisionManager<Sprite,Sprite> currentManager : myCollisionManagers)
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
     * @param updatable the IUpdatable to add
     */
    public void addIUpdatable(IUpdatable updatable)
    {
        myUpdatables.add(updatable);
    }


    /**
     * Adds an IRenderable to this playingfield
     * 
     * @param renderable the IRenderable to add
     */
    public void addIRenderable(IRenderable renderable)
    {
        myRenderables.add(renderable);
    }
    
    
    /**
     * Adds an object to this playingfield by placing it into the appropriate
     * updatable or renderable (or both) collection.
     */
    public void add (Object o)
    {
        if (o instanceof IUpdatable) addIUpdatable((IUpdatable) o);
        if (o instanceof IRenderable) addIRenderable((IRenderable) o);
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
     * Sets the <code>Background</code> for this playingfield
     * 
     * @param b the <code>Background</code> to use
     */
    public void setBackground(Background b)
    {
        if(b == null) myBackground = Background.getDefaultBackground();
        else myBackground = b;
    }


    /**
     * Checks collisions for all <code>CollisionManager</code> on this playingfield
     */
    public void checkCollisions()
    {
        for(CollisionManager<Sprite,Sprite> currentManager : myCollisionManagers)
            currentManager.checkCollision();
    }


    /**
     * Inserts a <code>Sprite</code> directly into the playingfield without
     * explicitly giving it a <code>SpriteGroup</code>.
     * 
     * @param extra the <code>Sprite</code> to add
     */
    public void add(Sprite extra)
    {
        getSpriteGroup("").addSprites(extra);
    }


    /**
     * Gets the <code>Background</code> for this playingfield
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
    public Collection<CollisionManager<Sprite,Sprite>> getCollisionManagers()
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
        for(CollisionManager<Sprite,Sprite> currentManager : myCollisionManagers)
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
     * @param g the Graphics2D used to render
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
