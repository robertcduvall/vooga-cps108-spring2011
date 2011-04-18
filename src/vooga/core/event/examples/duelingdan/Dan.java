package vooga.core.event.examples.duelingdan;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import vooga.core.Game;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;


public class Dan
{
    interface Weapon
    {
        void shoot ();
    }

    private static final int MOVE_DOWN = 1;
    private static final int MOVE_LEFT = -1;
    private static final int MOVE_RIGHT = 1;
    private static final int MOVE_UP = -1;

    private BufferedImage ImageDanLeft;
    private BufferedImage ImageDanRight;
    private BufferedImage ImageShot;
    private BufferedImage ImageWhiteDanLeft;

    private BufferedImage ImageWhiteDanRight;
    private Weapon myCurrentWeapon;
    private int myDirection;
    private Game myGame;
    private SpriteGroup myShots;
    private Sprite mySprite;
    private List<Weapon> myWeapons;


    public Dan (Game game)
    {
        myGame = game;

        ImageDanLeft = myGame.getImage("resources/dan_left.png");
        ImageDanRight = myGame.getImage("resources/dan_right.png");
        ImageWhiteDanLeft = myGame.getImage("resources/dan_left_white.png");
        ImageWhiteDanRight = myGame.getImage("resources/dan_right_white.png");
        ImageShot = myGame.getImage("resources/shot.png");

        mySprite = new Sprite(ImageDanLeft);

        myDirection = MOVE_LEFT;

        myShots = new SpriteGroup("Dan's Shots");

        myWeapons = new ArrayList<Weapon>();
        myCurrentWeapon = new Weapon()
        {
            @Override
            public void shoot ()
            {
                Sprite newShot = new Sprite(ImageShot);
                newShot.setX(mySprite.getX());
                newShot.setY(mySprite.getY());
                newShot.setHorizontalSpeed(myDirection * 0.2);
                myShots.add(newShot);
            }
        };
        myWeapons.add(myCurrentWeapon);
        myWeapons.add(new Weapon()
        {
            @Override
            public void shoot ()
            {
                Sprite newShot =
                    new Sprite(myDirection == MOVE_LEFT ? ImageWhiteDanLeft
                                                       : ImageWhiteDanRight);
                newShot.setX(mySprite.getX());
                newShot.setY(mySprite.getY());
                newShot.setHorizontalSpeed(myDirection * 0.2);
                myShots.add(newShot);
            }
        });
    }


    public void changeWeapon (int weaponID)
    {
        myCurrentWeapon = myWeapons.get(weaponID);
    }


    public void changeWeaponNext ()
    {
        int newWeaponID = myWeapons.indexOf(myCurrentWeapon) + 1;
        newWeaponID = newWeaponID % myWeapons.size();
        changeWeapon(newWeaponID);
    }


    public void changeWeaponPrevious ()
    {
        int newWeaponID = myWeapons.indexOf(myCurrentWeapon) - 1;
        newWeaponID = (newWeaponID + myWeapons.size()) % myWeapons.size();
        changeWeapon(newWeaponID);
    }


    public void fireWeapon ()
    {
        myCurrentWeapon.shoot();
    }


    public SpriteGroup getShotsSpriteGroup ()
    {
        return myShots;
    }


    public Sprite getSprite ()
    {
        return mySprite;
    }


    public void moveDown ()
    {
        mySprite.moveY(MOVE_DOWN);
    }


    public void moveLeft ()
    {
        myDirection = MOVE_LEFT;
        mySprite.setImage(ImageDanLeft);
        mySprite.moveX(MOVE_LEFT);
    }


    public void moveRight ()
    {
        myDirection = MOVE_RIGHT;
        mySprite.setImage(ImageDanRight);
        mySprite.moveX(MOVE_RIGHT);
    }


    public void moveToCenter ()
    {
        int x = myGame.getWidth() / 2 - mySprite.getWidth() / 2;
        int y = myGame.getHeight() / 2 - mySprite.getHeight() / 2;
        mySprite.setX(x);
        mySprite.setY(y);
    }


    public void moveUp ()
    {
        mySprite.moveY(MOVE_UP);
    }
}
