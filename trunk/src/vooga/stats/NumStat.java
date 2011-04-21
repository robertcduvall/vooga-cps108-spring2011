package vooga.stats;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import vooga.stats.operators.AbstractArithmeticOperator;
import vooga.reflection.Reflection;

public class NumStat<T> extends AbstractStat {
	
	/**
	 * These are keywords that help define arithmetic operators.
	 */
	public final static String PLUS = "+";
	public final static String MINUS = "-";
	public final static String TIMES = "*";
	public final static String DIVIDE = "/";
	public final static String MAX = "max";
	public final static String MIN = "min";
	
	private T myStat;
	private T myStep;
	private AbstractArithmeticOperator myOperator;
	
	public NumStat(T iniVal) {
		this(iniVal, null, new String());
	}
	
	public NumStat(T iniVal, T step) {
		this(iniVal, step, new String());
	}
	
	public NumStat(T iniVal, T step, String token) {
		this(iniVal, step, token, new HashMap<String, String>());
	}
	
	/**
	 * Create instance of NumStat based on all given parameters
	 * @param iniVal
	 * @param step
	 * @param token
	 * @param operators
	 */
	public NumStat(T iniVal, T step, String token, Map<String, String> operators)
	{
		super(iniVal);
		myStat = iniVal;
		myStep = step;
		myOperator = setOperator(token, operators);
	}
	
	/**
	 * Create proper operator from given token
	 * @param operation
	 * @return
	 */
	public AbstractArithmeticOperator setOperator(String token, Map<String, String> operators) {
		if (token==null)
			return null;
		
		String className = operators.get(token);
		return (AbstractArithmeticOperator) Reflection.createInstance(
				className, token);
	}
	
	/**
	 * Set initial Value for statistical result
	 * @param initial
	 * @param eventName
	 */
	// TODO: is it necessary?
	public void setInitialValue(T initial) {
		super.cheat(initial);
	}
	
	/**
	 * Set step that is evaluated by operators
	 * @param step
	 * @param eventName
	 */
	public void setStep(T step) {
		myStep = step;
	}
	
	/**
	 * Manually change statistical information
	 * @param stat
	 */
	/*
	public void cheat(T stat) {
		myStat = stat;
	}
	*/
	
	/**
	 * Update stat by current step and operator
	 * @return
	 */
	@Override
	public T update() {
		cheat((T) myOperator.evaluateSafe(Double.valueOf(myStat.toString()), 
				Double.valueOf(myStep.toString())));
		return myStat;
	}
	
	/**
	 * Convert stat to a string
	 */
	public String toString() {
		return String.valueOf(myStat);
	}
}
