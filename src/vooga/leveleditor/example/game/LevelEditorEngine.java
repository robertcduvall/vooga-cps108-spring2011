package vooga.leveleditor.example.game;

import java.awt.Dimension;

import com.golden.gamedev.GameEngine;
import com.golden.gamedev.GameLoader;
import com.golden.gamedev.GameObject;

public class LevelEditorEngine extends GameEngine{

	@Override
	public GameObject getGame(int gameID) {
		
		switch(gameID){
		case 0: return new FirstGUIUse(this);

		}
		return null;
	}
	
	public static void main(String[] args){
		GameLoader loader = new GameLoader();
		loader.setup(new LevelEditorEngine(), new Dimension(600,600), false);
		loader.start();
	}

}
