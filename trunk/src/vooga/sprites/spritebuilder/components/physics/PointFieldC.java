package vooga.sprites.spritebuilder.components.physics;

import java.awt.Point;

import vooga.physics.IPointField;
import vooga.sprites.improvedsprites.Sprite;
import vooga.util.buildable.components.BasicComponent;

/**
 * Component used to specify if a sprite has a point field.
 * 
 * @author Nathan Klug
 * 
 */
public class PointFieldC extends VelocityC implements IPointField {

    private Sprite mySprite;
    private double myMagnitude;
    private boolean isOn;

    public PointFieldC(Sprite sprite, double magnitude) {
        this(sprite, magnitude, true);
    }

    public PointFieldC(Sprite sprite, double magnitude, boolean isOn) {
        super(sprite, isOn);
        myMagnitude = magnitude;
    }

    @Override
    public double getPointMagnitude() {
        return myMagnitude;
    }

    @Override
    protected int compareTo(BasicComponent o) {
        // TODO what to do here?
        return 0;
    }

    @Override
    protected Object[] getFieldValues() {
        return new Object[] { mySprite, myMagnitude, isOn };
    }

    @Override
    protected void setFieldValues(Object... fields) {
        mySprite = (Sprite) fields[0];
        myMagnitude = (Double) fields[1];
        if (fields.length > 2)
            isOn = (Boolean) fields[2];
        else
            isOn = true;

    }

    @Override
    public Point getCenter() {
        return new Point((int) mySprite.getCenterX(), (int) mySprite.getCenterY());
    }

}
