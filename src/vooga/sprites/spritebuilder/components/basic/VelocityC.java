package vooga.sprites.spritebuilder.components.basic;

import vooga.physics.interfaces.newtonian.INewtonianMovable;
import vooga.physics.util.Velocity;
import vooga.sprites.improvedsprites.Sprite;
import vooga.util.buildable.components.BasicComponent;

public class VelocityC extends BasicComponent implements INewtonianMovable
{

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
        return this.getVelocity().compareTo(((INewtonianMovable) o).getVelocity());
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


}
