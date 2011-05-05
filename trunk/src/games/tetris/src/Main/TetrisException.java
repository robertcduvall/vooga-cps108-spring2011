package games.lolcats.src.Main;

import java.util.ResourceBundle;

public class TetrisException extends Exception {
	private static final long serialVersionUID = 1L;

	private static ResourceBundle stringsResource = ResourceBundle
			.getBundle("games.lolcats.TetrisExceptionResource");

	public static TetrisException NONEXISTANT_MINO = new TetrisException(
			stringsResource.getString("NONEXISTANT_MINO"));

	public static TetrisException UNEXPECTED_ERROR = new TetrisException(
			stringsResource.getString("UNEXPECTED_ERROR"));

	public TetrisException(String string) {
		super(string);
	}
}
