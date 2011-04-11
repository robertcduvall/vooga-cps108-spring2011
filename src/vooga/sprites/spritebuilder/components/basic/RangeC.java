package vooga.sprites.spritebuilder.components.basic;

import vooga.util.buildable.components.predefined.basic.DoubleC;
import vooga.util.buildable.components.predefined.basic.IntegerC;

public class RangeC extends DoubleC
{

    public RangeC ()
    {
        super(0.0);
    }
    
    public RangeC (Double d){
        super(d);
    }
    
    public double getRange(){
        return this.myDouble;
    }

}
