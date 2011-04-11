package vooga.util.math;

/**
 * Represents an angle. Stores useful information about handling angles. Also,
 * it is guaranteed to be wrapped to [0, 2pi].
 * 
 * @author Alex Lee (Reused from SLogo project)
 * @author Nathan Klug and Anne Weng (as implemented in the physics engine
 */
public class Angle implements Comparable<Angle> {

    /*
     * Define some convenient constant in radians for cardinal directions.
     */
    public static final double EAST = 0;
    public static final double WEST = Math.PI;
    public static final double SOUTH = 3 * Math.PI / 2;
    public static final double NORTH = Math.PI / 2;

    private double myRadians;

    /**
     * Constructor has default angle of pi/2, which is north.
     */
    public Angle() {
        this(NORTH);
    }

    /**
     * Creates an angle equal to the parameter
     * 
     * @param radians
     */
    public Angle(double radians) {
        myRadians = radians;

        /*
         * Wrap the given angle so that we don't have needlessly large numbers.
         */
        wrap();
    }

    /**
     * Gets the radian value of this angle.
     * 
     * @return angle in radians
     */
    public double getRadians() {
        return myRadians;
    }
    
    public void setRadians(double radians){
        myRadians = radians;
    }

    /**
     * Gets the radian value of this angle - Pi/2 for the Java bias.
     */
    public double getPictureRadians() {
        return myRadians - Math.PI / 2;
    }

    /**
     * Gets the degree value of this angle.
     * 
     * @return angle in degrees
     */
    public double getDegrees() {
        return (myRadians / Math.PI) * 180;
    }
    
    /**
     * Changes the angle its negative, 180 degrees away.
     * @return
     */
    public void setNegativeAngle() {
	myRadians = myRadians + Math.PI;
	wrap();
    }

    /**
     * Gets the cosine of this angle.
     * 
     * @return the cosine
     */
    public double cos() {
        return Math.cos(myRadians);
    }

    /**
     * Gets the sine of this angle.
     * 
     * @return the sine
     */
    public double sin() {
        return Math.sin(myRadians);
    }

    /**
     * Wraps this angle to [0, 2pi].
     */
    public void wrap() {
        myRadians = myRadians % (Math.PI * 2);
    }

    /**
     * Compares current angle to another angle and returns as an int.
     * 
     * @param other
     */
    public int compareTo(Angle other) {
        return (int) (getRadians() - other.getRadians());
    }

    /**
     * Converts degrees to radians.
     * 
     * @param degrees
     * @return the given degree in radians
     */
    public static double degreesToRadians(double degrees) {
        return (degrees / 180) * Math.PI;
    }

    /**
     * Converts radians to degrees.
     * 
     * @param radians
     * @return the given radian in degrees
     */
    public static double radiansToDegrees(double radians) {
        return (radians / Math.PI) * 180;
    }

    /**
     * Converts the radian value to a String.
     */
    public String toString() {
        return Double.toString(myRadians);
    }

    public static Angle getAngleFromDegrees(double degrees) {
        return new Angle(degreesToRadians(degrees));
    }

    public void setDegrees(double degrees) {
        myRadians = degreesToRadians(degrees);
        wrap();
        
    }

}
