package games.leapingSquirrel.sprites.components;

import vooga.sprites.improvedsprites.Sprite;
import vooga.sprites.improvedsprites.interfaces.ISpriteUpdater;
import vooga.util.buildable.components.BasicComponent;
import vooga.util.buildable.components.predefined.movement.HeadingC;
import vooga.util.math.LineMath;

/**
 * 
 * @author Julian Genkins
 *
 */

public class SquirrelVelocity extends HeadingC implements ISpriteUpdater
{
    protected Double myMagnitude;
    protected Double oldAngle;

    public SquirrelVelocity(Double mag, Double dir){
        super(dir);
        myMagnitude = mag;
        oldAngle = myAngle;
    }
    
    @Override
    public Double setAngle(double angle) {
        super.setAngle(angle);
        return oldAngle = myAngle;
    }

    @Override
    public Double rotate(double dAngle) {
         super.rotate(dAngle);
         return oldAngle = myAngle;
    }
    
    public SquirrelVelocity(){
        this(0.0,0.0);
    }
    
    @Override
    protected int compareTo(BasicComponent o) {
        return ((Double)this.getMagnitude()).compareTo((Double)((SquirrelVelocity) o).getMagnitude());
    }

    private double getMagnitude() {
        return myMagnitude;
    }

    @Override
    protected Object[] getFieldValues() {
        return new Object[]{myMagnitude, myAngle};
    }

    @Override
    protected void setFieldValues(Object... fields) {
        myMagnitude = (Double) fields[0];
        oldAngle = myAngle = (Double) fields[1];
    }

    /**
     * adjusts to screen x/y
     */
    @Override
    public void update(Sprite s, long elapsedTime) {
        s.move(this.getXComponent()*elapsedTime, this.getYComponent()*elapsedTime);
    }

    public double getXComponent() {
        return myMagnitude*Math.cos(-Math.toRadians(myAngle));
    }

    public double getYComponent() {
        return myMagnitude*Math.sin(Math.toRadians(myAngle));
    }

    public double setByComponents(double vx, double vy) {
        if ((myAngle =LineMath.findDirection(vx, vy)) == null)
            myAngle = oldAngle;
        else setAngle(LineMath.findDirection(vx, vy));
        return myMagnitude = LineMath.calcMagnitude(vx,vy);
    }

    public double addByComponents(double dVx, double dVy) {
        return this.setByComponents(getXComponent()+dVx, getYComponent()+dVy);
    }
}
