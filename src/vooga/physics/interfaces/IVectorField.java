package vooga.physics.interfaces;

import java.awt.Point;

/**
 * Interface for an object that acts as a point source for a vector field,
 * e.g. gravitational or magnetic attraction/repulsion.
 * 
 * @author Anne Weng
 *
 */
public interface IVectorField {

    /**
     * Gets the magnitude of an object with a field, e.g. mass for gravitation
     * or charge for magnetism.
     * @return
     */
    public double getMagnitude();
    
    /**
     * Gets the source of the vector field.
     * @return
     */
    public Point getSource();
    
    /**
     * Gets the attraction constant for a vector field, e.g. the gravitational constant.
     * @return
     */
    public double getAttractionConstant(); 
}
