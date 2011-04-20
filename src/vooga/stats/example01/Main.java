package vooga.stats.example01;

import java.awt.Dimension;

import com.golden.gamedev.GameLoader;

public class Main {
	public static void main(String[] args)
	{		
		GameLoader myGameLoader = new GameLoader();
		myGameLoader.setup(new TrickyGame(), new Dimension(800, 600), false);
		myGameLoader.start();
	}
}
