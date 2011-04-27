package vooga.sprites.improvedsprites.interfaces;

/**
 * Adds various methods which attribute elements of mobility when implemented
 * 
 * @author Julian Genkins
 *
 */
public interface IMobility extends IRotation
{

    /**
     * Sets the speed of this sprite, the speed is based on actual time in
     * milliseconds, 1 means the sprite is moving as far as 1000 (1x1000ms)
     * pixels in a second. Negative value (-1) means the sprite moving backward.
     */
    public abstract void setSpeed (double vx, double vy);

    /**
     * Attempts to move this sprite to specified <code>xs</code>,
     * <code>ys</code> location, if the sprite is right on specified location,
     * this method will return true.
     * <p>
     * 
     * For example :
     * 
     * <pre>
     *    Sprite s;
     *    public void update(long elapsedTime) {
     *       // move sprite to 100, 100 with speed 0.1 pixel in a millisecond
     *       if (s.moveTo(elapsedTime, 100, 100, 0.1) {
     *          // sprite has arrived to 100, 100
     *       
     *       }
     *    }
     * </pre>
     */
    public abstract boolean moveTo (long elapsedTime,
                                    double xs,
                                    double ys);

    /**
     * Sets horizontal speed of the sprite, the speed is based on actual time in
     * milliseconds, 1 means the sprite is moving as far as 1000 (1x1000ms)
     * pixels in a second to the right, while negative value (-1) means the
     * sprite is moving to the left.
     */
    public abstract void setHorizontalSpeed (double vx);


    /**
     * Sets vertical speed of the sprite, the speed is based on actual time in
     * milliseconds, 1 means the sprite is moving as far as 1000 (1x1000ms)
     * pixels in a second to the bottom, while negative value (-1) means the
     * sprite is moving to the top.
     */
    public abstract void setVerticalSpeed (double vy);


    /**
     * Moves sprite with specified angle, and speed.
     * <p>
     * 
     * The angle is as followed:
     * 
     * <pre>
     *   0�   : moving to right (12 o'clock)
     *   90�  : moving to top (3 o'clock)
     *   180� : moving to left (6 o'clock)
     *   270� : moving to bottom (9 o'clock)
     * </pre>
     */
    public abstract void setMovement (double speed, double angleDir);


    /**
     * liearly increases the horizontal speed of the sprite
     * @param dx
     */
    public abstract void addHorizontalSpeed (double dVx);


    /**
     * liearly increases the vertical speed of the sprite
     * @param dy
     */
    public abstract void addVerticalSpeed (double dVy);


    double addSpeed( double dVx, double dVy);


//    /**
//     * Updates sprite movement.
//     */
//    public abstract void updateMovement (long elapsedTime);


    /**
     * Returns horizontal speed of the sprite.
     * <p>
     * 
     * Positive means the sprite is moving to right, and negative means the
     * sprite is moving to left.
     */
    public abstract double getHorizontalSpeed ();


    /**
     * Returns vertical speed of the sprite.
     * <p>
     * 
     * Positive means the sprite is moving to bottom, and negative means the
     * sprite is moving to top.
     */
    public abstract double getVerticalSpeed ();

    
    /**
     * Calculates the absolute speed of this object based on the x and y speed components
     * @return
     */
    public abstract double getAbsoluteSpeed();
    
    /**
     * Accelerate this object by a given amount
     * @param i
     * @return
     */
	double accelerate(double mag, double dir);
    
}
