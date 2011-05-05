package vooga.network.example.plantvszombie;

import vooga.network.example.plantvszombie.game.PlantVsZombie;

import java.awt.Dimension;

import com.golden.gamedev.GameLoader;

/**
 * Why the reflection sometimes work but sometimes don't.
 * @author Roman
 *
 */
public class Main
{
    public static void main(String[] args) {
        GameLoader game = new GameLoader();
        Dimension gameDimension = new Dimension(800, 700);
        game.setup(new PlantVsZombie(), gameDimension, false);
        game.start();
    }
}
