package vooga.sprites.spritebuilder.components.basic;

import java.awt.Point;
import vooga.physics.interfaces.IPointField;
import vooga.physics.util.Velocity;
import vooga.sprites.improvedsprites.Sprite;
import vooga.util.buildable.components.BasicComponent;

public class PointFieldC extends BasicComponent implements IPointField{
    
    private Sprite mySprite;
    private double myMagnitude;
    private boolean immovable;
    private boolean isOn;
    
    @Override
    public double getPointMagnitude() {
        return myMagnitude;
    }

    @Override
    public boolean isOn() {
        return isOn;
    }

    @Override
    protected int compareTo(BasicComponent o) {
        // TODO what to do here?
        return 0;
    }

    @Override
    protected Object[] getFieldValues() {
        return new Object[]{mySprite, myMagnitude, immovable, isOn};
    }

    @Override
    protected void setFieldValues(Object... fields) {
        mySprite = (Sprite) fields[0];
        myMagnitude = (Double) fields[1];
        if (fields.length > 2)
            immovable = (Boolean) fields[2];
        else
            immovable = false;
        if (fields.length > 3)
            isOn = (Boolean) fields[3];
        else
            isOn = true;
        
    }

    @Override
    public void turnPhysicsOnOff(boolean isOn) {
        this.isOn = isOn;
        
    }

    @Override
    public Point getCenter() {
        return new Point((int)mySprite.getCenterX(), (int)mySprite.getCenterY());
    }

    @Override
    public Velocity getVelocity() {
        return new Velocity(mySprite.getHorizontalSpeed(), mySprite.getVerticalSpeed());
    }

    @Override
    public void setVelocity(Velocity spriteVelocity) {
        if (!immovable){
            mySprite.setHorizontalSpeed(spriteVelocity.getXComponent());
            mySprite.setVerticalSpeed(spriteVelocity.getYComponent());
        }
    }

}
