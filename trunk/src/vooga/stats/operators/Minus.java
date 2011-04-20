package vooga.stats.operators;

public class Minus extends BinaryOperator 
{

	public Minus(String token) {
		super(token);
	}

	@Override
	protected Double evaluate(Double x, Double y) {
		return x - y;
	}
	
}
