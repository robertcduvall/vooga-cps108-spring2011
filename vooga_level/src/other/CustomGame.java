package other;

import java.awt.Graphics2D;
import levels.LevelManager;
import com.golden.gamedev.Game;
import com.golden.gamedev.GameLoader;
import com.golden.gamedev.object.PlayField;
import com.golden.gamedev.object.background.ImageBackground;

/** 
 * @author Andrew Patterson
 */
public class CustomGame extends Game
{
    private static CustomGame myInstance;

    public static CustomGame getInstance ()
    {
        if (myInstance == null) myInstance = new CustomGame();
        return myInstance;
    }

    @Override
    public void initResources ()
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void update (long elapsedTime)
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void render (Graphics2D g)
    {
        // TODO Auto-generated method stub
        
    }
}
