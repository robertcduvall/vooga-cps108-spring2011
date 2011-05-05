package games.patterson_game.refactoredVooga.sprites.spritebuilder.components.basic;

import games.patterson_game.refactoredVooga.util.buildable.components.predefined.basic.DoubleC;

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
