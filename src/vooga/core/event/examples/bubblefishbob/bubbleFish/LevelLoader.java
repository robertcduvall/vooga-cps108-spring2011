package vooga.core.event.examples.bubblefishbob.bubbleFish;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import vooga.core.event.examples.bubblefishbob.util.XMLReader;
import vooga.core.event.examples.bubblefishbob.util.resources.ResourceManager;


/**
 * @author Michael Ansel
 */
public class LevelLoader
{
    private static final String DEFAULT_PATH = "src/vooga/core/event/examples/bubblefishbob/resources/levels";
    private static final String FILE_EXTENSION = "xml";

    private static final LevelLoader INSTANCE;
    private static final ResourceBundle RESOURCES;
    static
    {
        INSTANCE = new LevelLoader();
        RESOURCES = ResourceBundle.getBundle("vooga.core.event.examples.bubblefishbob.resources.LevelLoader");
    }


    public static LevelLoader getInstance ()
    {
        return INSTANCE;
    }


    private String getXPathExpression (String expressionName)
    {
        return RESOURCES.getString("XPath." + expressionName);
    }


    public Level load (String name)
    {
        return load(name, DEFAULT_PATH);
    }


    public Level load (String name, String path)
    {
        XMLReader xmlReader = null;
        File levelFile =
            new File(path + File.separator + name + "." + FILE_EXTENSION);
        try
        {
            xmlReader = new XMLReader(levelFile);
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
            System.err.println("Error loading level file: " + levelFile.getAbsolutePath());
            return null;
        }

        int startT = xmlReader.getIntegerValue(getXPathExpression("StartT"));
        int fishToSave =
            xmlReader.getIntegerValue(getXPathExpression("FishToSave"));

        double pathSpeed =
            xmlReader.getDoubleValue(getXPathExpression("PathSpeed"));

        int pathXOffset =
            xmlReader.getIntegerValue(getXPathExpression("PathXOffset"));
        int pathYOffset =
            xmlReader.getIntegerValue(getXPathExpression("PathYOffset"));
        
        ArrayList<PathPoint> pathPoints = new ArrayList<PathPoint>();
        NodeList pathPointNodes =
            xmlReader.getNodes(getXPathExpression("PathPoints"));
        for (int i = 0; i < pathPointNodes.getLength(); i++)
        {
            Element element = (Element) pathPointNodes.item(i);
            int x = Integer.parseInt(element.getAttribute("x"));
            int y = Integer.parseInt(element.getAttribute("y"));

            // TODO copied from ProgBob; not sure what is going on here...
            pathPoints.add(new PathPoint((x + pathXOffset) * 0.71,
                                         (y + pathYOffset - 10) * 0.71));
        }
        return new Level(startT, fishToSave, pathSpeed, pathPoints);
    }
}
