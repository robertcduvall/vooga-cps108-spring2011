package games.nathanAsteroids;

import games.nathanAsteroids.ship.Ship;
import java.util.Collection;
import vooga.core.VoogaGame;
import vooga.sprites.improvedsprites.Sprite;
import vooga.sprites.spritegroups.SpriteGroup;
import vooga.levels.AbstractLevel;

/**
 * A level of Asteroids
 * 
 * @author Nathan
 *
 */
public class Level extends AbstractLevel
{
    
    VoogaGame myGame;
    
    public Level (Collection<SpriteGroup<Sprite>> players, VoogaGame game)
    {
        super(players, game);
        myGame = game;
    }

    /**
     * Load the level
     */
    @Override
    public void loadLevel ()
    {
        addAllSpritesFromPool();
        addBackground();
        addShip();
    }
    
    public void addShip(){
        Ship ship = new Ship();
        ship.createShipFromXML(myGame);
        this.getAllSpriteGroups().add(ship);
        myGame.getLevelManager().addPlayer(ship);
    }

}
