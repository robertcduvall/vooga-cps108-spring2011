package sprites;

import java.awt.Graphics2D;
import main.CustomPlayField;
import com.golden.gamedev.Game;
import com.golden.gamedev.object.Background;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;
import collisions.WallBounceCollision;


/**
 * An alien that moves and bounces vertically
 */
public class VerticalBounceAlien extends Sprite implements Alien
{
    private static final long serialVersionUID = 1L;
    private Alien myBaseAlien;
    private WallBounceCollision myCollision;


    public VerticalBounceAlien (Alien a)
    {
        myBaseAlien = a;
        SpriteGroup tempGroup =new SpriteGroup(alienResources.getString("temp_group_name"));
        if (myBaseAlien instanceof Sprite) tempGroup.add((Sprite) myBaseAlien);
        myCollision = new WallBounceCollision(getBackground());
        myCollision.setCollisionGroup(tempGroup, null);
        myBaseAlien.setVerticalSpeed(alienResources.getDouble("v_bounce_y_speed"));
        setSuperAlien(this);
    }


    public void update (long elapsedTime)
    {
        myBaseAlien.update(elapsedTime);
        setLocation(myBaseAlien.getX(), myBaseAlien.getY());
        move();
        fireBullet();
    }


    public int takeDamage (int damage)
    {
        return myBaseAlien.takeDamage(damage);
    }


    public void move ()
    {
        myCollision.checkCollision();
    }


    public Background getBackground ()
    {
        return myBaseAlien.getBackground();
    }


    public void render (Graphics2D pen)
    {
        myBaseAlien.render(pen);
    }


    public void setVerticalSpeed (double ySpeed)
    {
        myBaseAlien.setVerticalSpeed(ySpeed);
    }


    public void setSpeed (double dx, double dy)
    {
        myBaseAlien.setSpeed(dx, dy);
    }


    public void setHorizontalSpeed (double xSpeed)
    {
        myBaseAlien.setHorizontalSpeed(xSpeed);
    }


    public void fireBullet ()
    {
        myBaseAlien.fireBullet();
    }
    
    public void setSuperAlien (Sprite alien)
    {
        myBaseAlien.setSuperAlien(alien);
    }
    
    public Game getGame ()
    {
        return myBaseAlien.getGame();
    }


    public CustomPlayField getPlayField ()
    {
        return myBaseAlien.getPlayField();
    }
}
