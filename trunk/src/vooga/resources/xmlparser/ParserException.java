package vooga.resources.xmlparser;

public class ParserException extends RuntimeException {

	private static final long serialVersionUID = -6622453484605655440L;

	public static ParserException SYNTAX_ERROR = new ParserException(
			"Syntax error in XML file");
	
	public static ParserException IO_ERROR = new ParserException(
			"IO error reading XML file");
	
	public static ParserException SYSTEM_ERROR = new ParserException(
			"System configuration error.");
	
	public static ParserException CONSTRUCT_ERROR = new ParserException(
			"Unable to construct sprite from archetype!");
	
	public ParserException(String text) {
		super(text);
	}
}
