package vooga.arcade.parser;

import java.awt.Dimension;
import java.io.IOException;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

import vooga.core.event.examples.bubblefishbob.util.resources.ResourceManager;

import com.golden.gamedev.Game;

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

        Document file = null;
        try {
            file = builder.build(path);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        Element root = file.getRootElement();

        Game game = createGame(root);

        String defaultName = resourceManager.getString("defaultName");
        String[] dataArray = new String[resourceManager.getInteger(defaultName
                + ".arraySize")];

        for (int i = 0; i < dataArray.length; i++) {
            dataArray[i] = root.getChildText(resourceManager
                    .getString(defaultName + '.' + i));
        }

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

        return new ArcadeGameObject(game, dataArray, results[0], results[1]);
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
