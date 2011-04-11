package vooga.levels;

import com.golden.gamedev.object.PlayField;

public class ExampleLevel extends AbstractLevel
{

    public ExampleLevel (String filePath, int id, PlayField pf)
    {
        super(filePath, id, pf);
    }

    @Override
    public void loadLevel ()
    {
        addBackground();
        addAllSprites();
        addMusic();
    }

}
