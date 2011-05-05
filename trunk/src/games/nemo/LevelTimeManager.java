package games.nemo;

import com.golden.gamedev.engine.timer.SystemTimer;

import vooga.core.VoogaGame;
import vooga.levels.LevelManager;
import vooga.stats.NumStat;

public class LevelTimeManager extends LevelManager {

	private SystemTimer myTimer;
	private long myStartTime;
	
	private final int FRAMES_PER_SECOND = 50;
	
	public LevelTimeManager(VoogaGame game) {
		super(game);
		myTimer = new SystemTimer();
		myTimer.setFPS(FRAMES_PER_SECOND);
		myTimer.startTimer();
		myStartTime = myTimer.getTime();
	}
	
	public long getPlayTime() {
		return myTimer.getTime()-myStartTime;
	}

}
