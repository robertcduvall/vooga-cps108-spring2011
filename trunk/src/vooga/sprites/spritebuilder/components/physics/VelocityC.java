package vooga.sprites.spritebuilder.components.physics;

import vooga.physics.interfaces.IPhysicsToggle;
import vooga.physics.interfaces.newtonian.INewtonianMovable;
import vooga.physics.mediators.VoogaPhysicsMediator;
import vooga.physics.util.Velocity;
import vooga.sprites.improvedsprites.Sprite;
import vooga.sprites.spritebuilder.components.ISpriteUpdater;
import vooga.util.buildable.components.BasicComponent;

public class VelocityC extends BasicComponent implements INewtonianMovable, ISpriteUpdater, IPhysicsToggle
{

    private boolean isOn;
    protected Sprite mySprite;
    
    @Override
    public Velocity getVelocity() {
        return new Velocity(mySprite.getHorizontalSpeed(), -mySprite.getVerticalSpeed());
    }

    @Override
    public void setVelocity(Velocity newVelocity) {
        mySprite.setHorizontalSpeed(newVelocity.getXComponent());
        mySprite.setVerticalSpeed(-newVelocity.getYComponent());

    }
    
    @Override
    protected int compareTo (BasicComponent o)
    {
        //TODO: Need a comparable in velocity? should be there...
        //return this.getVelocity().compareTo(((INewtonianMovable) o).getVelocity());
        return 0;
    }


    @Override
    protected Object[] getFieldValues ()
    {
        return new Object[]{mySprite};
    }


    @Override
    protected void setFieldValues (Object ... fields)
    {
        mySprite = (Sprite) fields[0];
    }

    @Override
    public void update(Sprite s, long elapsedTime) {
        if (isOn())
            VoogaPhysicsMediator.getInstance().getEngine(null).applyWorldForces(this, elapsedTime);
        
    }

    @Override
    public void turnPhysicsOnOff(boolean isOn) {
        this.isOn = isOn;
        
    }

    @Override
    public boolean isOn() {
        return isOn;
    }


}
