package vooga.levels.util;

public class LevelParserException extends RuntimeException {

	private static final long serialVersionUID = -6622453484605655440L;

	public static LevelParserException SYNTAX_ERROR = new LevelParserException(
			"Syntax error in XML file");
	
	public static LevelParserException IO_ERROR = new LevelParserException(
			"IO error reading XML file");
	
	public static LevelParserException SYSTEM_ERROR = new LevelParserException(
			"System configuration error.");
	
	public LevelParserException(String text) {
		super(text);
	}
}
