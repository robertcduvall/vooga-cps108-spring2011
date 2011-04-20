package vooga.levels;

import java.awt.Graphics2D;
import java.util.Collection;
import vooga.sprites.spritegroups.SpriteGroup;
import com.golden.gamedev.object.PlayField;

public class VoogaPlayField extends PlayField
{
    /** All of the sprite groups that are currently on the playingfield */
    private Collection<SpriteGroup<?>> mySpriteGroups;
    
    
    /**
     * If it exists, returns the sprite group of the specified name. If it
     * doesn't exist, a new sprite group of the specified name is created, added
     * to the playingfield and returned
     * 
     * @return a sprite group of the specified name
     */
    public SpriteGroup<?> getSpriteGroup (String groupName)
    {
        for (SpriteGroup<?> currentGroup : mySpriteGroups)
        {
            if (currentGroup.getName().equals(groupName))
            {
                return currentGroup;
            }
        }
        SpriteGroup<?> newGroup = new SpriteGroup(groupName);
        addGroup(newGroup);
        return newGroup;
    } 
    
    public void addGroup(SpriteGroup<?> group)
    {
        mySpriteGroups.add(group);
    }
    
    @Override
    public void update(long elapsedTime)
    {
        for(SpriteGroup currentGroup : mySpriteGroups)
        {
            currentGroup.update(elapsedTime);
        }
    }
    
    @Override
    public void render(Graphics2D g)
    {
        for(SpriteGroup currentGroup : mySpriteGroups)
        {
            currentGroup.render(g);
        }  
    }
}
