package vooga.sprites.improvedsprites.interfaces;

/**
 * Adds various methods which attribute elements of mobility when implemented
 * 
 * @author Julian Genkins
 *
 */
public interface IMobility
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
     *   0�   : moving to top (12 o'clock)
     *   90�  : moving to right (3 o'clock)
     *   180� : moving to bottom (6 o'clock)
     *   270� : moving to left (9 o'clock)
     * </pre>
     */
    public abstract void setMovement (double speed, double angleDir);


    /**
     * Accelerates sprite horizontal speed by <code>accel</code> and limit the
     * speed to <code>maxSpeed</code>.
     * <p>
     * 
     * This is used to create momentum speed (slowly increase/decrease the
     * sprite speed).
     * <p>
     * 
     * For example :
     * 
     * <pre>
     * Sprite s;
     * 
     * public void update(long elapsedTime) {
     *  // accelerate sprite speed by 0.002
     *  // and limit the maximum speed to 4
     *  // moving right
     *  s.addHorizontalSpeed(elapsedTime, 0.002, 4);
     *  // moving left
     *  s.addHorizontalSpeed(elapsedTime, -0.002, -4);
     *  // momentum stop
     *  s.addHorizontalSpeed(elapsedTime, (s.getHorizontalSpeed() &gt; 0) ? -0.002
     *          : 0.002, 0);
     * }
     * </pre>
     */
    public abstract void addHorizontalSpeed (long elapsedTime,
                                             double accel,
                                             double maxSpeed);


    /**
     * Accelerates sprite vertical speed by <code>accel</code> and limit the
     * speed to <code>maxSpeed</code>.
     * <p>
     * 
     * This is used to create momentum speed (slowly increase/decrease the
     * sprite speed).
     * <p>
     * 
     * For example :
     * 
     * <pre>
     * Sprite s;
     * 
     * public void update(long elapsedTime) {
     *  // accelerate sprite speed by 0.002
     *  // and limit the maximum speed to 4
     *  // moving down
     *  s.addVerticalSpeed(elapsedTime, 0.002, 4);
     *  // moving up
     *  s.addVerticalSpeed(elapsedTime, -0.002, -4);
     *  // momentum stop
     *  s.addVerticalSpeed(elapsedTime,
     *          (s.getVerticalSpeed() &gt; 0) ? -0.002 : 0.002, 0);
     * }
     * </pre>
     */
    public abstract void addVerticalSpeed (long elapsedTime,
                                           double accel,
                                           double maxSpeed);


    void addSpeed (long elapsedTime, double accel, double direction, double max);


    /**
     * Updates sprite movement.
     */
    public abstract void updateMovement (long elapsedTime);


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





    
    
}
