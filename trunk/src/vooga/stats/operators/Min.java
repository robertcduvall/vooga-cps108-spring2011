package vooga.stats.operators;

public class Min extends BinaryOperator
{

	public Min(String token) {
		super(token);
	}

	@Override
	protected Double evaluate(Double x, Double y) {
		return Math.min(x, y);
	}
	
}
