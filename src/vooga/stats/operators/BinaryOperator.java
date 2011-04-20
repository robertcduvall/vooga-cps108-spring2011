package vooga.stats.operators;

public abstract class BinaryOperator extends AbstractArithmeticOperator 
{
	public BinaryOperator(String token) {
		super(token);
	}

	@Override
	public Double evaluateSafe(Double... variables)
	{
		if (variables.length==2)
			return evaluate(variables[0], variables[1]);
		return 0.0;
	}
	
	protected abstract Double evaluate(Double x, Double y);
}
