package vooga.arcade.parser;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

import vooga.arcade.gui.helper.AffineImage;
import vooga.arcade.gui.helper.DrawableData;
import vooga.core.event.examples.bubblefishbob.util.resources.ResourceManager;

import com.golden.gamedev.Game;
/**
 * Contains static methods that given path to xml file will create the game object
 * specified in the xml and also extract associated game data.
 * 
 * @author KevinWang
 *
 */
public final class GameXMLParser {
    private static SAXBuilder builder = new SAXBuilder();
    private static ResourceManager resourceManager = ResourceManager
            .getInstance();

    /**
     * Give file path obtain game meta-data and return an ArcadeGameObject given
     * those data
     * 
     * @param path
     * @return ArcadeGameObject
     */
    public static ArcadeGameObject getGameData(String path) {

        resourceManager.addResourcesFromFile("GameInfo",
                "vooga.arcade.resources");

        Element root = getRoot(path);

        Game game = createGame(root);

        String[] dataArray = getItemData(root);

        String[][] results = getListData(root);     
        
        DrawableData drawableData = createDarableData(root);

        return new ArcadeGameObject(game, dataArray, results[0], results[1], drawableData);
    }

    /**
     * 
     * Return the root element of the xml file. See JDOM documentation
     * for more information
     * @param path
     * @return
     */
    protected static Element getRoot(String path) {
        Document file = null;
        try {
            file = builder.build(path);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        Element root = file.getRootElement();
        return root;
    }

    /**
     * 
     * Sometimes items in the xml file are lists in nature. This method
     * Extract the list data and put all of them into a double string array.
     * @param root
     * @return String[][] where each array is a list of data
     */
    protected static String[][] getListData(Element root) {
        String[] specialArrays = resourceManager
                .getStringArray("specialArrays");

        String[][] results = new String[specialArrays.length][];
        for (int i = 0; i < specialArrays.length; i++) {
            String arrayName = specialArrays[i];
            int arraySize = resourceManager
                    .getInteger(arrayName + ".arraySize");
            results[i] = new String[arraySize];

            Element currentElement = root.getChild(arrayName);
            for (int j = 0; j < arraySize; j++) {
                results[i][j] = currentElement.getChildText(resourceManager
                        .getString(arrayName + '.' + i));
            }
        }
        return results;
    }

    /**
     * Get item data about the game from the xml file.   
     * 
     * @param root
     * @return String[] where each element in the array is 
     * a descriptor of the game
     */
    protected static String[] getItemData(Element root) {
        String defaultName = resourceManager.getString("defaultName");
        String[] dataArray = new String[resourceManager.getInteger(defaultName
                + ".arraySize")];

        for (int i = 0; i < dataArray.length; i++) {
            dataArray[i] = root.getChildText(resourceManager
                    .getString(defaultName + '.' + i));
        }
        return dataArray;
    }

    /**
     * 
     * Create drawable data representing icon of the game
     * 
     * @param root
     * @return
     */
    protected static DrawableData createDarableData(Element root) {
        Image image = null;
        try {
            image = ImageIO.read(new File(root.getChildText(resourceManager.getString("image"))));
        }
        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        DrawableData drawableData = new DrawableData();
        drawableData.addImage(new AffineImage(image, new AffineTransform()));
        return drawableData;
    }

    /**
     * 
     * Method to create a game given its path.
     * 
     * @param root
     * @return game object from the specified path
     */
    private static Game createGame(Element root) {
        Game game = null;
        Class cls = null;

        try {
            cls = Class.forName(root.getChildText("path"));
            game = (Game) (cls.newInstance());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return game;
    }

}
