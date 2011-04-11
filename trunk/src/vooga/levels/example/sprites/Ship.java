package vooga.levels.example.sprites;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import vooga.levels.example.main.CustomGame;
import vooga.levels.example.main.CustomPlayField;
import vooga.levels.example.resourceManager.ResourceManager;
import vooga.levels.example.weapons.IWeaponLauncher;
import vooga.levels.example.weapons.MissileLauncher;
import com.golden.gamedev.Game;
import com.golden.gamedev.object.Sprite;


/**
 * The ship (and hero of the game) that the user controls. A ship can use a
 * variety of weapons to destroy aliens
 */
public class Ship extends Sprite
{
    private static final ResourceManager shipResources =  new ResourceManager("ship");
    private static final long serialVersionUID = 1L;

    private double mySpeed;
    private int myHealth;
    private Game myGame;
    private IWeaponLauncher myCurrentLauncher;
    private ArrayList<IWeaponLauncher> myLaunchers;


    public Ship (Game game, CustomPlayField playfield)
    {
        super(game.getImage(shipResources.getString("image_file")),
              shipResources.getDouble("starting_x"),
              shipResources.getDouble("starting_y"));
        myGame = game;
        myHealth = shipResources.getInteger("default_health");
        mySpeed = shipResources.getDouble("default_ship_speed");
        myLaunchers = new ArrayList<IWeaponLauncher>();
        myCurrentLauncher = new MissileLauncher(this, myGame, playfield);
        myLaunchers.add(myCurrentLauncher);
    }

    /**
     * Checks for user input and performs the associated action
     */
    @Override
    public void update (long elapsedTime)
    {
        super.update(elapsedTime);
        for (IWeaponLauncher weaponLauncher : myLaunchers)
        {
            weaponLauncher.update(elapsedTime);
        }
        
        //Move the ship
        if (myGame.keyDown(KeyEvent.VK_DOWN))
            move(0, mySpeed * elapsedTime);
        if (myGame.keyDown(KeyEvent.VK_UP))
            move(0, -mySpeed * elapsedTime);
        if (myGame.keyDown(KeyEvent.VK_RIGHT))
            move(mySpeed * elapsedTime, 0);
        if (myGame.keyDown(KeyEvent.VK_LEFT))
            move(-mySpeed * elapsedTime, 0);

        //Switch weapons
        if (myGame.keyPressed(KeyEvent.VK_0))
            myCurrentLauncher = myLaunchers.get(0);

        if (myGame.keyPressed(KeyEvent.VK_1))
            myCurrentLauncher = myLaunchers.get(1);

        // Use the weapon if possible
        myCurrentLauncher.useWeapon();
    }

    /**
     * Draws the ship and all weapons that have been fired
     */
    @Override
    public void render (Graphics2D pen)
    {
        super.render(pen);
        for (IWeaponLauncher weaponLauncher : myLaunchers)
        {
            weaponLauncher.render(pen);
        }
    }

    /**
     * Take damage to this ship.  
     * If health is gone, game is over.
     */
    public void takeDamage (int damage)
    {
        myHealth -= damage;
        if (myHealth < 0) 
            if(myGame instanceof CustomGame)
            {
                CustomGame game = (CustomGame) myGame;
                game.gameOver();
            }
            else
                System.exit(0); //This should never happen
    }
    
    /**
     * Adds health to this ship
     */
    public void addHealth(int health)
    {
        myHealth += health;
    }

    /**
     * Returns this ship's health
     */
    public int getHealth ()
    {
        return myHealth;
    }
}
