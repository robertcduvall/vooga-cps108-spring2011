package vooga.core.event.examples.duelingdan;

import vooga.core.VoogaGame;
import com.golden.gamedev.object.Sprite;


/**
 * @author Michael Ansel
 */
public class Bubble
{
    private static final int MOVE_DOWN = 1;
    private static final int MOVE_LEFT = -1;
    private static final int MOVE_RIGHT = 1;
    private static final int MOVE_UP = -1;

    private static final double SPEED = 0.05;

    private VoogaGame myGame;
    private Sprite mySprite;


    public Bubble (VoogaGame game)
    {
        myGame = game;

        mySprite = new Sprite(myGame.getImage("resources/enemy.png"));
        mySprite.setDataID(this);

        // start in top-left, moving down
        mySprite.setX(myGame.getWidth() / 3);
        mySprite.setY(myGame.getHeight() / 3);
        this.moveDown();
    }


    public void destroy ()
    {
        mySprite.setActive(false);
    }


    public Sprite getSprite ()
    {
        return mySprite;
    }


    private boolean isAtBottomLeft ()
    {
        double dx = Math.abs(mySprite.getX() - (myGame.getWidth() / 3));
        double dy = Math.abs(mySprite.getY() - (2.0 * myGame.getHeight() / 3));
        return dx < 1.0 && dy < 1.0;
    }


    private boolean isAtBottomRight ()
    {
        double dx = Math.abs(mySprite.getX() - (2.0 * myGame.getWidth() / 3));
        double dy = Math.abs(mySprite.getY() - (2.0 * myGame.getHeight() / 3));
        return dx < 1.0 && dy < 1.0;
    }


    private boolean isAtTopLeft ()
    {
        double dx = Math.abs(mySprite.getX() - (myGame.getWidth() / 3));
        double dy = Math.abs(mySprite.getY() - (myGame.getHeight() / 3));
        return dx < 1.0 && dy < 1.0;
    }


    private boolean isAtTopRight ()
    {
        double dx = Math.abs(mySprite.getX() - (2.0 * myGame.getWidth() / 3));
        double dy = Math.abs(mySprite.getY() - (myGame.getHeight() / 3));
        return dx < 1.0 && dy < 1.0;
    }


    private boolean isMovingDown ()
    {
        return Math.signum(mySprite.getVerticalSpeed()) == MOVE_DOWN;
    }


    private boolean isMovingLeft ()
    {
        return Math.signum(mySprite.getHorizontalSpeed()) == MOVE_LEFT;
    }


    private boolean isMovingRight ()
    {
        return Math.signum(mySprite.getHorizontalSpeed()) == MOVE_RIGHT;
    }


    private boolean isMovingUp ()
    {
        return Math.signum(mySprite.getVerticalSpeed()) == MOVE_UP;
    }


    private void moveDown ()
    {
        mySprite.setHorizontalSpeed(0.0);
        mySprite.setVerticalSpeed(MOVE_DOWN * SPEED);
    }


    private void moveLeft ()
    {
        mySprite.setHorizontalSpeed(MOVE_LEFT * SPEED);
        mySprite.setVerticalSpeed(0.0);
    }


    private void moveRight ()
    {
        mySprite.setHorizontalSpeed(MOVE_RIGHT * SPEED);
        mySprite.setVerticalSpeed(0.0);
    }


    private void moveUp ()
    {
        mySprite.setHorizontalSpeed(0.0);
        mySprite.setVerticalSpeed(MOVE_UP * SPEED);
    }


    public void turnLeft ()
    {
        double dx = mySprite.getHorizontalSpeed();
        double dy = mySprite.getVerticalSpeed();
        if (dy != 0.0) // moving down/up
        { // go right/left
            dx = dy;
            dy = 0;
        }
        else if (dx != 0.0) // moving right/left
        { // go up/down
            dy = -dx;
            dx = 0;
        }
        else throw new IllegalStateException("Ummm....Why aren't we moving???");
        mySprite.setHorizontalSpeed(dx);
        mySprite.setVerticalSpeed(dy);
    }


    public void updateMovement ()
    {
        if (isMovingLeft() && isAtTopLeft()) moveDown();
        if (isMovingUp() && isAtTopRight()) moveLeft();
        if (isMovingDown() && isAtBottomLeft()) moveRight();
        if (isMovingRight() && isAtBottomRight()) moveUp();
    }
}
