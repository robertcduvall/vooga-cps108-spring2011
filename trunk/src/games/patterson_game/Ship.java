package games.patterson_game;

import games.patterson_game.refactoredVooga.core.VoogaGame;
import games.patterson_game.refactoredVooga.core.event.IEventHandler;
import games.patterson_game.refactoredVooga.resources.bundle.Bundle;
import games.patterson_game.refactoredVooga.sprites.improvedsprites.Sprite;
import games.patterson_game.refactoredVooga.sprites.spritebuilder.components.collisions.CollisionCircleC;
import games.patterson_game.refactoredVooga.util.buildable.components.predefined.basic.HealthC;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import com.golden.gamedev.object.GameFont;


/**
 * The player ship that the user drives around the screen, shoots floating
 * objects with and tries to stay alive for as long as possible
 * 
 * @author Andrew
 */
public class Ship extends Sprite
{
    private static final long serialVersionUID = 1L;
    private GameFont myFont;
    private VoogaGame myGame;
    private boolean gunCharged;
    private String myWeapon;
    private double myMaxSpeed;
    private String myDisplayText;
    private Bundle myBundle;


    public Ship (VoogaGame game)
    {
        super(game.getImageLoader().getImage("Ship"));
        myGame = game;
        myBundle = AvoiderGame.getBundle();
        registerEventHandlers();
        setX(myBundle.getDouble("ship_initial_x"));
        setY(myBundle.getDouble("ship_initial_y"));
        addComponents(new HealthC(myBundle.getDouble("ship_initial_health")), new CollisionCircleC(getCenterPoint(), getWidth()/2));
        gunCharged = true;
        myWeapon = myBundle.getString("ship_initial_weapon");
        myMaxSpeed = myBundle.getDouble("ship_initial_max_speed");
        myFont = myGame.fontManager.getFont(myGame.getImageLoader().getImage("Font"));
    }


    /**
     * Register all of the event handlers for the ship
     */
    private void registerEventHandlers ()
    {
        myGame.registerEventHandler("Input.User.Up", new IEventHandler()
        {
            @Override
            public void handleEvent (Object o)
            {
                setVerticalSpeed(-myMaxSpeed);
            }
        });
        myGame.registerEventHandler("Input.User.Down", new IEventHandler()
        {
            @Override
            public void handleEvent (Object o)
            {
                setVerticalSpeed(myMaxSpeed);
            }
        });
        myGame.registerEventHandler("Input.User.Left", new IEventHandler()
        {
            @Override
            public void handleEvent (Object o)
            {
                setHorizontalSpeed(-myMaxSpeed);   
            }
        });
        myGame.registerEventHandler("Input.User.Right", new IEventHandler()
        {
            @Override
            public void handleEvent (Object o)
            {
                setHorizontalSpeed(myMaxSpeed);   
            }
        });

        myGame.registerEventHandler("Input.User.Shoot", new IEventHandler()
        {
            @Override
            public void handleEvent (Object o)
            {
                shoot(getAngle());
            }
        });
        myGame.registerEventHandler("ChargeGun", new IEventHandler()
        {
            @Override
            public void handleEvent (Object o)
            {
                gunCharged = true;
            }
        });
        myGame.registerEventHandler("DisplayThenResetText", new IEventHandler()
        {
            @Override
            public void handleEvent (Object o)
            {
                setDisplayText((String) o);
                myGame.addTimer("ResetDisplayText", myBundle.getInteger("ship_message_display_time"), "ResetDisplayText");
            }
        });
        myGame.registerEventHandler("ResetDisplayText", new IEventHandler()
        {
            @Override
            public void handleEvent (Object o)
            {
                setDisplayText("");
            }
        });
    }
    

    /**
     * Inflict damage to the ship.  The game is over if the ship has less than 0 health
     * @param damage
     */
    public void takeDamage (double damage)
    {
        myGame.fireEvent("DisplayThenResetText", "DisplayThenResetText", myBundle.getString("ship_taking_damage_message"));
        getComponent(HealthC.class).decrease(damage);
        if (getComponent(HealthC.class).isDead())
        {
            ((AvoiderGame) myGame).lose();
        }
    }


    /**
     * Shoots the ships current weapon if the gun is charged
     * @param angle
     */
    public void shoot (double angle)
    {
        if(gunCharged)
        {
            Sprite bullet = myGame.getLevelManager().addArchetypeSprite(myWeapon,  (int) getCenterX(), (int) getCenterY());
            if(bullet == null) return;
            bullet.setSpeed(Math.abs(getHorizontalSpeed()), 0);
            gunCharged = false;
            myGame.addTimer("ChargeGun",myBundle.getInteger("ship_gun_recharge_delay"), "ChargeGun");
        }
    }

    /**
     * Sets the ship's max speed
     * 
     * @param maxSpeed
     */
    public void setMaxSpeed(double maxSpeed)
    {
        myMaxSpeed = maxSpeed;
    }
    
    /**
     * Returns the ship's max speed
     * @return
     */
    public double getMaxSpeed()
    {
        return myMaxSpeed;
    }
    
    /**
     * Sets which weapon the ship can fire
     * 
     * @param weapon
     */
    public void setWeapon(String weapon)
    {
        myWeapon = weapon;
    }
    
    /**
     * Revert's the ship's weapon back to default
     */
    public void revertToDefaultWeapon()
    {
        myWeapon = myBundle.getString("ship_initial_weapon");
    }
    
    /**
     * Sets the message that the ship displays
     * @param text
     */
    public void setDisplayText(String text)
    {
        myDisplayText = text.toUpperCase();
    }
    

    /**
     * Normal rendering except no rotations of image
     */
    @Override
    public void render (Graphics2D g, int x, int y)
    {
        AffineTransform aTransform = new AffineTransform();
        aTransform.translate((int) this.getX(), (int) this.getY());
        g.drawImage(image.getScaledInstance(width, height, 0),aTransform,null);
        renderComponents(g, x, y);
        myFont.drawString(g,
                          myBundle.getString("health_label") + getComponent(HealthC.class).getCurrent(),
                          myBundle.getInteger("health_label_x"), myBundle.getInteger("health_label_y"));
        myFont.drawString(g, myDisplayText, (int) getX() - getWidth(),(int) getY() - getHeight()/4);
    }
}
