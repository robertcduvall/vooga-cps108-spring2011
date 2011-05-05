package vooga.stats.operators;

public class Replace extends BinaryOperator
{
	public Replace(String token) {
		super(token);
	}

	@Override
	protected Double evaluate(Double x, Double y) {
		x = y;
		return y;
	}
	
}
