package vooga.stats.operators;

public class Max extends BinaryOperator
{
	public Max(String token) {
		super(token);
	}

	@Override
	protected Double evaluate(Double x, Double y) {
		return Math.max(x, y);
	}
	
}