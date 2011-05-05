package games.patterson_game.refactoredVooga.levelsRefactored.util;

/**
 * Abstract class that converts one type to another type.
 * @author Sterling Dorminey
 *
 */
public abstract class Converter<T> {
	public abstract T convert(String input);
}
