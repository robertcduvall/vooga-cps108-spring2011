package vooga.levels.example.levels;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.TreeMap;
import vooga.levels.example.main.CustomGame;
import vooga.levels.example.main.CustomPlayField;
import vooga.levels.example.sprites.BasicAlien;
import vooga.levels.example.sprites.HorizontalBounceAlien;
import com.golden.gamedev.object.Background;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.background.ImageBackground;


public class ExampleLevel1 extends AbstractLevel
{

    public ExampleLevel1 (String filePath, int id, CustomPlayField pf)
    {
        super(filePath, id, pf);
        ignoreThis();

    }


    @Override
    public void loadLevel ()
    {
        addBackground();
        addAllSprites();
        addMusic();
    }

    private void ignoreThis ()
    {

        mySprites = new TreeMap<Class<?>, ArrayList<Sprite>>();
        ArrayList<Sprite> aliens = new ArrayList<Sprite>();
        aliens.add(new HorizontalBounceAlien(new BasicAlien(100,200,1,CustomGame.getInstance(),myPlayField)));
        aliens.add(new HorizontalBounceAlien(new BasicAlien(300,200,1,CustomGame.getInstance(),myPlayField)));
        aliens.add(new HorizontalBounceAlien(new BasicAlien(200,500,1,CustomGame.getInstance(),myPlayField)));
        mySprites.put(aliens.get(0).getClass(), aliens);
        myBackgrounds = new LinkedList<Background>();
        myBackgrounds.add(new ImageBackground(CustomGame.getInstance().getImage("src/images/background1.jpg")));
        myMusic = new LinkedList<String>();
        myMusic.add("src/sounds/soundtrack.mid");
        myGoal = new ExampleGoal(myPlayField);
    }
}
