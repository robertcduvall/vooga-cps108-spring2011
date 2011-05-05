package games.pacman;

import games.pacman.sprites.players.Players;

import java.util.Collection;
import vooga.core.VoogaGame;
import vooga.core.event.IEventHandler;
import vooga.sprites.improvedsprites.Sprite;
import vooga.sprites.spritegroups.SpriteGroup;
import vooga.levels.AbstractLevel;

/**
 * A level of Pacman.
 * 
 * @author DJ Sharkey
 *
 */
public class Level extends AbstractLevel
{
	PacManGame game;
	
    public Level (Collection<SpriteGroup<Sprite>> players, VoogaGame game)
    {
        super(players, game);
        this.game=(PacManGame) game;
    }

    /**
     * Load the level by loading all the available blocks.
     */
    @Override
    public void loadLevel ()
    {
        addAllSpritesFromPool();
        addBackground();
        placeDots();
        respawnEnemies();
      /*  game.getLevelManager().addArchetypeSprite(
    			"chaser",0,0,game.getLevelManager().getCurrentLevel().getSpriteGroup("pacman"));*/
    }
    
    private void respawnEnemies() {    	
    	this.game.registerEventHandler("SpawnEnemies", new IEventHandler()
        {
            @Override
            public void handleEvent (Object o)
            {
            	for(Sprite sp: getSpriteGroup("enemies").getSprites()){
            		((Players) sp).respawn();
            		
            	}
            	
            	for(Sprite sp: getSpriteGroup("chaser").getSprites()){
            		((Players) sp).respawn();
            	}
            }            
        });
	}

	private void placeDots() {
    	for(int i=0;i<15;i++){
    		for(int j=0;j<10;j++){
    			 game.getLevelManager().addArchetypeSprite(
    	    			"dot",i*48+24,j*48+24);
    		}
    	}		
	}
}
