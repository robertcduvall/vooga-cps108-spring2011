package vooga.gui.examplegame;

import java.awt.Dimension;

import com.golden.gamedev.GameEngine;
import com.golden.gamedev.GameLoader;
import com.golden.gamedev.GameObject;

/**
 * 
 * 
 * @author Dave
 *
 */
 public class MenuGameEngine extends GameEngine {

    public GameObject getGame(int GameID) {
       switch (GameID) {
       }

       return null;
    }

    public static void main(String[] args) {
       // GameEngine class creation is equal with Game class creation
       GameLoader game = new GameLoader();
       game.setup(new ExampleGame(), new Dimension(680,500), false);
       game.start();
    }

 }