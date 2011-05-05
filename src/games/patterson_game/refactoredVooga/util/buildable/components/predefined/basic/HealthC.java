package games.patterson_game.refactoredVooga.util.buildable.components.predefined.basic;

import games.patterson_game.refactoredVooga.util.buildable.components.BasicComponent;


public class HealthC extends BasicComponent 
{

    Double myMax;
    Double myCurrent;
    
    public Double getMax() {
		return myMax;
	}

	public void setMax(Double max) {
		this.myMax = max;
	}

	public Double getCurrent() {
		return myCurrent;
	}

	public void setCurrent(Double current) {
		this.myCurrent = current;
	}
	
	public double increase(double add){
		return myCurrent += add;
	}
	
	public double decrease(double sub){
		return myCurrent -= sub;
	}


	public HealthC(Double max){
    	myMax = max;
    	setToMaxHealth();
    }
   
    public void setToMaxHealth() {
    	myCurrent = myMax;
	}

    /**
     * Compares remaining health fraction
     */
	@Override
    protected int compareTo (BasicComponent o)
    {
        return ((Double)this.getHealthFraction()).compareTo((Double)((HealthC) o).getHealthFraction());
    }

    private double getHealthFraction() {
    	
		return myCurrent/myMax;
	}

	@Override
    protected Object[] getFieldValues ()
    {
        return new Object[]{myMax, myCurrent};
    }

    @Override
    protected void setFieldValues (Object ... fields)
    {
        myMax = (Double) fields[0];
        if (fields.length > 1) myCurrent = (Double) fields[1];
        else setToMaxHealth();
        
    }

	public boolean isDead() {
		return myCurrent <= 0;
	}
}
