package vooga.stats.operators;

import java.util.List;

public abstract class AbstractArithmeticOperator 
{	
	private String myToken;
	
	public AbstractArithmeticOperator(String token)
	{
		myToken = token;
		Integer i = 3;
	}
	
	public String toString()
	{
		return myToken;
	}
	
	public abstract Double evaluateSafe(Double... variables);
}
