package games.blasterMan;

import vooga.util.buildable.components.BasicComponent;

public class HealthPoints extends BasicComponent {
	private double maxHP;
	private double currentHP;
	public HealthPoints(double HP){
		maxHP = HP;
		currentHP = HP;
	}
	
	public void decrease(double damage){
		currentHP -= damage;
	}
	public void increase(double heal){
		currentHP = Math.min(maxHP, currentHP + heal);
	}
	public boolean isDead(){
		return currentHP == 0.0;
	}
	@Override
	protected int compareTo(BasicComponent o) {
		 return ((Double)this.getHealthFraction()).compareTo((Double)((HealthPoints) o).getHealthFraction());
    }

    public double getHealthFraction() {
    	
		return currentHP/maxHP;
	}
	@Override
	protected Object[] getFieldValues() {
		return null;
	}

	@Override
	protected void setFieldValues(Object... fields) {

	}

}
