package vooga.viewTeam;

import com.golden.gamedev.object.Background;
import com.golden.gamedev.object.PlayField;

public class PlayFieldFrame extends BackgroundFrame {
	private final PlayField playField;

	public PlayFieldFrame(PlayField playField) {
		super(playField.getBackground());
		this.playField = playField;
	}

	@Override
	public Background getBackground() {
		return playField.getBackground();
	}
}
