package vooga.stats.operators;

public abstract class UnaryOperator extends AbstractArithmeticOperator
{
	
	public UnaryOperator(String token) {
		super(token);
	}

	@Override
	public Double evaluateSafe(Double... variables)
	{
		if (variables.length==1)
			return evaluate(variables[0]);
		return 0.0;
	}
	
	protected abstract Double evaluate(Double x);
}
