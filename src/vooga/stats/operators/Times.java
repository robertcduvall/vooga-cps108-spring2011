package vooga.stats.operators;

public class Times extends BinaryOperator
{
	public Times(String token) {
		super(token);
	}

	@Override
	protected Double evaluate(Double x, Double y) {
		return x*y;
	}
	
}
