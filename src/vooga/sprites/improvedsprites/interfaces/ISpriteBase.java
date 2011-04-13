package vooga.sprites.improvedsprites.interfaces;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import com.golden.gamedev.object.Background;


public interface ISpriteBase extends Cloneable

{

    /**
     * Associates specified background with this sprite.
     */
    public abstract void setBackground (Background backgr);


    /**
     * Returns the background where this sprite lived.
     */
    public abstract Background getBackground ();


    /**
     * Returns the image of this sprite.
     */
    public abstract BufferedImage getImage ();


    /**
     * Sets specified image as this sprite image.
     */
    public abstract void setImage (BufferedImage image);


    /**
     * Returns the width of this sprite.
     */
    public abstract int getWidth ();


    /**
     * Returns the height of this sprite.
     */
    public abstract int getHeight ();

    /**
     * Sets this sprite coordinate to specified location on the background.
     */
    public abstract void setLocation (double xs, double ys);


    /**
     * Moves this sprite as far as delta x (dx) and delta y (dy).
     */
    public abstract void move (double dx, double dy);


    /**
     * Moves sprite <code>x</code> coordinate as far as delta x (dx).
     */
    public abstract void moveX (double dx);


    /**
     * Moves sprite <code>y</code> coordinate as far as delta y (dy).
     */
    public abstract void moveY (double dy);


    /**
     * Sets sprite <code>x</code> coordinate.
     */
    public abstract void setX (double xs);


    /**
     * Sets sprite <code>y</code> coordinate.
     */
    public abstract void setY (double ys);


    /**
     * Forces sprite <code>x</code> position to specified coordinate.
     * <p>
     * 
     * The difference between {@link #setX(double)} with this method : <br>
     * <code>setX(double)</code> changes the sprite old position (oldX = xs),
     * while using <code>forceX(double)</code> <b>the old position is n ot
     * changed</b>.
     * <p>
     * 
     * This method is used on collision check to move the sprite, but still keep
     * the sprite old position value.
     */
    public abstract void forceX (double xs);


    /**
     * Forces sprite <code>y</code> position to specified coordinate.
     * <p>
     * 
     * The difference between {@link #setY(double)} with this method : <br>
     * <code>setY(double)</code> changes the sprite old position (oldY = ys),
     * while using <code>forceY(double)</code> <b>the old position is n ot
     * changed</b>.
     * <p>
     * 
     * This method is used on collision check to move the sprite, but still keep
     * the sprite old position value.
     */
    public abstract void forceY (double ys);


    /**
     * Returns sprite <code>x</code> coordinate.
     */
    public abstract double getX ();


    /**
     * Returns sprite <code>y</code> coordinate.
     */
    public abstract double getY ();


    /**
     * Returns sprite <code>x</code> coordinate before the sprite moving to
     * the current position.
     */
    public abstract double getOldX ();


    /**
     * Returns sprite <code>y</code> coordinate before the sprite moving to
     * the current position.
     */
    public abstract double getOldY ();




    /**
     * Returns sprite <code>x</code> coordinate relative to screen area.
     */
    public abstract double getScreenX ();


    /**
     * Returns sprite <code>y</code> coordinate relative to screen area.
     */
    public abstract double getScreenY ();


    /**
     * Returns the center of the sprite in <code>x</code> coordinate (x +
     * (width/2)).
     */
    public abstract double getCenterX ();


    /**
     * Returns the center of the sprite in <code>y</code> coordinate (y +
     * (height/2)).
     */
    public abstract double getCenterY ();


    /**
     * Returns whether the screen is still on background screen area in
     * specified offset.
     */
    public abstract boolean isOnScreen (int leftOffset,
                                        int topOffset,
                                        int rightOffset,
                                        int bottomOffset);


    /**
     * Returns whether the screen is still on background screen area.
     */
    public abstract boolean isOnScreen ();


    /**
     * Returns the layer of this sprite.
     * 
     * @see #setLayer(int)
     */
    public abstract int getLayer ();


    /**
     * Sets the layer of this sprite.
     * <p>
     * 
     * Layer is used for z-order rendering. Use this along with
     * {@link PlayField#setComparator(Comparator)} or
     * {@link SpriteGroup#setComparator(Comparator)} for that purpose.
     * 
     * @see #getLayer()
     */
    public abstract void setLayer (int i);


    /**
     * Returns active state of this sprite.
     */
    public abstract boolean isActive ();


    /**
     * Sets active state of this sprite, only active sprite will be updated and
     * rendered and check for collision.
     * <p>
     * 
     * Inactive sprite is same as dead sprite, it won't be updated nor rendered,
     * and only wait to be disposed (if the sprite is not immutable).
     * <p>
     * 
     * The proper way to remove a sprite from the game, is by setting sprite
     * active state to false (Sprite.setActive(false)).
     * 
     * @see #setImmutable(boolean)
     */
    public abstract void setActive (boolean b);


    /**
     * Returns whether this sprite is immutable or not.
     */
    public abstract boolean isImmutable ();


    /**
     * Sets immutable state of this sprite, immutable sprite means the sprite
     * won't be removed from its group even though the sprite is not active.
     * <p>
     * 
     * This state is used for optimization by reusing inactive sprite rather
     * than making new sprite each time.
     * <p>
     * 
     * Usually used for many, small, short live, and frequently used sprites
     * such as projectile in shooter game. Thus rather than making a new sprite
     * for every projectile that can cause performance degrade, the inactive
     * projectiles can be reuse again and again.
     * <p>
     * 
     * <b>WARNING:</b> Immutable sprite won't be disposed by Java garbage
     * collector until the sprite is manually removed from its group using
     * {@link com.golden.gamedev.object.SpriteGroup#removeImmutableSprites()}.
     * 
     * @see com.golden.gamedev.object.SpriteGroup#getInactiveSprite()
     * @see com.golden.gamedev.object.SpriteGroup#removeImmutableSprites()
     * @see #setActive(boolean)
     */
    public abstract void setImmutable (boolean b);


    /**
     * Returns the distance of this sprite from the specified sprite.
     * <p>
     * 
     * Used this method to check whether the specified sprite is in this sprite
     * range area or not.
     * <p>
     * 
     * This method can be used for :
     * <ul>
     * <li>Determining sprite attack range.</li>
     * <li>Sprite aura that affecting surrounding unit.</li>
     * <li>Activating this sprite to chase player whenever the player come
     * closer to certain distance of this sprite.</li>
     * </ul>
     */
    public abstract double getDistance (ISpriteBase other);

}
