package vooga.stats.operators;

public class Divide extends BinaryOperator 
{

	public Divide(String token) {
		super(token);
	}

	@Override
	protected Double evaluate(Double x, Double y) {
		if (y==0)
			return 0.0;
		return x/y;
	}
	
}
