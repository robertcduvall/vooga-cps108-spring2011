/**
 * 
 */
package games.tao_rpsallstars.cpu_player;

import vooga.core.event.EventManager;
import vooga.sprites.ai.AISprite;
import vooga.sprites.ai.FieldOfView;

/**
 * @author Kevin
 * 
 */
public class CpuPlayer extends AISprite {

	EventManager myManager;

	/**
	 * @param manager
	 * @param vision
	 */
	public CpuPlayer(EventManager manager, FieldOfView vision) {
		super(manager, vision);
		myManager = manager;

	}

	public void getResponse() {

		int random = (int) Math.floor(Math.random() * 3);

		switch (random) {
		case 1:
			myManager.fireEvent(this, "Input.CPU.Rock");
			rock();
			break;
		case 2:
			myManager.fireEvent(this, "Input.CPU.Paper");
			paper();
			break;
		default:
			myManager.fireEvent(this, "Input.CPU.Scissors");
			scissors();
			break;
		}
	}

	public void rock() {
		//Create RockThrow
	}

	public void paper() {
		//Create PaperThrow
	}

	public void scissors() {
		//Create ScissorsThrow
	}

}
