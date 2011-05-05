package games.pacman;

import java.util.Collection;
import vooga.core.VoogaGame;
import vooga.sprites.improvedsprites.Sprite;
import vooga.sprites.spritegroups.SpriteGroup;
import vooga.levels.AbstractLevel;

/**
 * A level of PacMan.
 * 
 * @author Misha
 *
 */
public class Level extends AbstractLevel
{
	VoogaGame myGame;
	
    public Level (Collection<SpriteGroup<Sprite>> players, VoogaGame game)
    {
        super(players, game);
        this.myGame=game;
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
    }
    protected void placeDots() {
    	for(int i=0;i<15;i++){
    		for(int j=0;j<10;j++){
    			 myGame.getLevelManager().addArchetypeSprite(
    	    			"dot",i*48+24,j*48+24);
    		}
    	}		
	}
}
