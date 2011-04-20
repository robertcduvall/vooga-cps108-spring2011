package vooga.stats.operators;

public class Plus extends BinaryOperator 
{
	public Plus(String token) {
		super(token);
	}

	@Override
	protected Double evaluate(Double x, Double y) {
		return x+y;
	}
	
}
