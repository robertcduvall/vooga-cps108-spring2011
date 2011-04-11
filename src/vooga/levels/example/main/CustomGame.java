package vooga.levels.example.main;
import java.awt.Color;
import java.awt.Graphics2D;
import vooga.levels.example.resourceManager.ResourceManager;
import com.golden.gamedev.Game;
import com.golden.gamedev.GameLoader;
import com.golden.gamedev.object.PlayField;
import com.golden.gamedev.object.background.ColorBackground;
import com.golden.gamedev.object.background.ImageBackground;

/** 
 * @author Andrew Patterson
 */
public class CustomGame extends Game
{
    private static final ResourceManager gameResources = new ResourceManager("game");    
    private PlayField myPlayfield;
    private static CustomGame myInstance;


    /**
     * Starts the game
     */
    public static void main (String[] args)
    {
        GameLoader game = new GameLoader();
        game.setup(new CustomGame(), gameResources.getDimension("screen_size"), false);
        game.start();
    }

    /**
     * Initialization of game resources
     * Put all that usually belongs to the constructor
     */
    public void initResources ()
    {
        myInstance = this;
        myPlayfield = new CustomPlayField(new ImageBackground(getImage(gameResources.getString("background_image")), getWidth(), getHeight()),this);
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
        myPlayfield = new PlayField(new ImageBackground(getImage(gameResources.getString("winning_image"))));
    }

    { distribute = false; }

    public static CustomGame getInstance()
    {
        if (myInstance == null) myInstance = new CustomGame();
        return myInstance;
    }

}
