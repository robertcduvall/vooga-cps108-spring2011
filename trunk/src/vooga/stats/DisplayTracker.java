package vooga.stats;
import java.util.*;
import com.golden.gamedev.object.SpriteGroup;


/**
 * This class is used to keep track the stat and its display
 * 
 * @author Chao Chen
 *
 */

public class DisplayTracker
{
    private Map<String, SpriteGroup> myStatsGroup;
    private Map<String, Display> myDisplays;
    private Map<String, AbstractStat> myStats;
    
    public DisplayTracker() 
    {
        myStatsGroup = new HashMap<String, SpriteGroup>();
        myDisplays = new HashMap<String, Display>();
        myStats = new HashMap<String, AbstractStat>();
    }
    
    /**
     * get SpriteGroup
     */
    public SpriteGroup getStatsGroup(String name)
    {
        return myStatsGroup.get(name);
    }
    
    /**
     * get Display
     */
    public Display getDisplays(String name)
    {
        return myDisplays.get(name);
    }
    
    /**
     * get Stat
     */
    public AbstractStat getStat(String name)
    {
        return myStats.get(name);
    }
    
    /**
     * add SpriteGroup into game
     */
    public void putStatsGroup(String name, SpriteGroup sg)
    {
        myStatsGroup.put(name, sg);
    }
    
    /**
     * add Display into game
     */
    public void putDisplays(String name, Display d)
    {
        myDisplays.put(name, d);
    }
    
    /**
     * add Stat into game
     */
    public void putStat(String name, AbstractStat s)
    {
        myStats.put(name, s);
    }
}
