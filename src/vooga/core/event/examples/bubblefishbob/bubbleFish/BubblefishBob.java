package vooga.core.event.examples.bubblefishbob.bubbleFish;
import java.awt.Dimension;
import java.awt.Graphics2D;
import vooga.core.event.EventManager;
import vooga.core.event.IEventHandler;
import com.golden.gamedev.Game;
import com.golden.gamedev.GameLoader;


public class BubblefishBob extends Game
{
	private static final long serialVersionUID = 1L;
	private ProgBob progbob = new ProgBob();
	private EventManager eventManager = new EventManager();


	public BubblefishBob ()
	{
	    distribute = true;
	}
	
	@Override
    public void initResources ()
    {
	    eventManager.registerEventHandler("EveryTurn.User.ProgBobUpdate", new IEventHandler()
        {
            @Override
            public void handleEvent (Object o)
            {
                long elapsedTime = (Long)o;
                progbob.update(elapsedTime / 1000.0);
            }
        });
	    progbob.init(this, eventManager);
    }


    @Override
    public void render (Graphics2D context)
    {
        progbob.draw(context);
    }


    @Override
    public void update (long elapsedTime)
    {
        progbob.handleInput();
        eventManager.update(elapsedTime);
    }


    public static void main (String[] args)
    {
        GameLoader loader = new GameLoader();
        loader.setup(new BubblefishBob(), new Dimension(480, 360), false);        
        loader.start();
    }
}
