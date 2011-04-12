package vooga.levels.example.main;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import vooga.levels.example.resourceManager.ResourceManager;
import vooga.core.VoogaGame;
import com.golden.gamedev.Game;
import com.golden.gamedev.GameLoader;
import com.golden.gamedev.object.PlayField;
import com.golden.gamedev.object.background.ColorBackground;
import com.golden.gamedev.object.background.ImageBackground;

/** 
 * @author Andrew Patterson
 */
public class CustomGame extends VoogaGame
{
    private PlayField myPlayfield;
    private static CustomGame myInstance;


    /**
     * Starts the game
     */
    public static void main (String[] args)
    {
        GameLoader game = new GameLoader();
        game.setup(new CustomGame(), new Dimension(1250,700), false);
        game.start();
    }

    /**
     * Initialization of game resources
     * Put all that usually belongs to the constructor
     */
    public void initResources ()
    {
        myInstance = this;
        myPlayfield = new CustomPlayField(new ColorBackground(Color.RED, getWidth(), getHeight()),this);
    }


    /**
     * Updating the game variables
     * Animate the game
     */
    public void update (long elapsedTime)
    {
        myPlayfield.update(elapsedTime);
    }


    /**
     * Rendering to the screen
     */
    public void render (Graphics2D pen)
    {
        myPlayfield.render(pen);
    }

    /**
     * Called when the player loses the game
     */
    public void gameOver()
    {
        myPlayfield = new PlayField(new ColorBackground(Color.BLACK));
    }

    /**
     * Called when the player wins the game
     */
    public void youWin()
    {
        //myPlayfield = new PlayField(new ImageBackground(getImage(gameResources.getString("winning_image"))));
    }

    { distribute = false; }

    @Override
    public void updatePlayField (long elapsedTime)
    {
        // TODO Auto-generated method stub
        
    }
}
