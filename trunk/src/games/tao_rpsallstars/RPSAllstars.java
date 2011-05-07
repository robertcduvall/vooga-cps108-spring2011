/**
 * 
 */
package games.tao_rpsallstars;

import games.tao_rpsallstars.sprites.User;

import java.awt.Dimension;

import vooga.core.VoogaGame;
import vooga.core.event.EventManager;
import vooga.levels.LevelManager;
import vooga.resources.images.ImageLoader;

/**
 * RPS Allstars. Rock Paper Scissors game with AI computer player.
 * 
 * @author Kevin Tao
 *
 */
public class RPSAllstars extends VoogaGame{
	
	public static EventManager eventManager;
	public static ImageLoader imageLoader;
	public static LevelManager levelManager;

	@Override
	public void updatePlayField(long elapsedTime) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void initResources() {
		
		eventManager = this.getEventManager();
        imageLoader = this.getImageLoader();
        levelManager = this.getLevelManager();
        
        User user = new User(eventManager);
        RPSReferee referee = new RPSReferee(eventManager, user);
        
	}
	
	public static void main(String[] args)
    {
        launchGame(new RPSAllstars(), new Dimension(640,480), false);
    }

}
