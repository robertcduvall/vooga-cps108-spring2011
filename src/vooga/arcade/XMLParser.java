package vooga.arcade;

import java.awt.Dimension;
import java.io.IOException;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

import com.golden.gamedev.Game;

public class XMLParser {
    private SAXBuilder builder;

    public XMLParser() {
        builder = new SAXBuilder();
    }

    public ArcadeGameObject getGameData(String path) {
        Document file = null;
        try {
            file = builder.build(path);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        Element root = file.getRootElement();
        List<Element> attributes = root.getChildren();
        for (Element e : attributes) {
            System.out.println(root.getChildText(e.getName()));
        }

        Class cls = null;
        Game game = null;
        try {
            cls = Class.forName(root.getChildText("path"));
            game = (Game) (cls.newInstance());
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        String title = root.getChildText("title");
        String author = root.getChildText("author");
        String genre = root.getChildText("genre");
        String description = root.getChildText("description");
        String dateCreated = root.getChildText("datecreated");
        String version = root.getChildText("version");
        String price = root.getChildText("price");
        String language = root.getChildText("language");
        Element dimensionElement = (Element) root.getChildren("dimension").get(
                0);
        Dimension dimension = new Dimension(Integer.parseInt(dimensionElement
                .getChildText("width")), Integer.parseInt(dimensionElement
                .getChildText("height")));
        Element highscoreElement = (Element) root.getChildren("dimension").get(
                0);
        String[] highscores = {highscoreElement.getChildText("first"), highscoreElement.getChildText("second"), highscoreElement.getChildText("third")};

        return new ArcadeGameObject(game, title, author, genre, description, dateCreated, highscores, version,price, language, dimension);
    }

 
    /*
     * public arcadeGameObject(Game game, String title, String genre, String
     * description, String dateCreated, String[] highscores, String version,
     * String price, String language, Dimension dimension)
     */

    /*
     * Document books = builder.build("books.xml"); Document onebook =
     * builder.build("onebook.xml"); Element bookToAdd =
     * onebook.getRootElement().getChild("book");
     * 
     * books.getRootElement().addContent(bookToAdd);
     */

}
