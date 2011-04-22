package vooga.arcade.parser;

import java.awt.Dimension;
import java.awt.Image;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jdom.Element;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

import vooga.arcade.parser.gameObject.ArcadeObject;
import vooga.core.event.examples.bubblefishbob.util.resources.ResourceManager;

import com.golden.gamedev.Game;
import com.golden.gamedev.GameLoader;

/**
 * Object containing an instance of game and information associated with it.
 * This is used to transport game and its data around
 * 
 * @author KevinWang
 *
 */	
public class ArcadeGameObject extends ArcadeObject{
    
    protected Game game;
    protected Dimension dimension;
    public ArcadeGameObject(Element root, String path) {
    	
        super(root,path);
        this.dimension = getDimension();        
    }


    /**
     * start the game contained in the game object
     */
    public void start() {
        createGame();        
        GameLoader loader = new GameLoader();
        loader.setup(game, dimension, false);
        game.start();
    }
    
    /**
     * Creates the game instance
     */
    private void createGame() {
        Class cls = null;

        try {
            cls = Class.forName(root.getChildText("path"));
            game = (Game) (cls.newInstance());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public String getData(String name){
        return root.getChildText(name);
    }

    /**
     * @return the dimension
     */
    public Dimension getDimension() {
        List<String> dimensionList = getList("dimension");
        Dimension dimension = new Dimension(Integer.parseInt(dimensionList.get(0)),Integer.parseInt(dimensionList.get(1)));
        
        return dimension;
    }

    /**
     * @return data in a list
     */
    public List<String> getListData(String name) {
        return getList(name);
    }
    
    private List<String> getList(String name){
        ArrayList<String> result = new ArrayList<String>();
        List<Element> elementList = root.getChild(name).getChildren();
        for(Element e : elementList){
            result.add(e.getText());
        }
        
        return result;
    }
    
    /**
     * 
     * @param name
     * @param data
     */
    public void writeData(String name, String value){
        if(root.getChildText(name)==null){
            root.addContent(new Element(name));
        }
        root.getChild(name).setText(value);
        XmlIO.writeToXml(root, path);
        
    }

}
