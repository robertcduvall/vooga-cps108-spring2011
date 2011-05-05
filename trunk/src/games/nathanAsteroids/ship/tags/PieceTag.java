package games.nathanAsteroids.ship.tags;

import games.nathanAsteroids.ship.ShipParser;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import vooga.core.event.EventManager;
import vooga.levels.util.LevelParser;
import vooga.levels.util.SpriteConstructor;
import vooga.resources.xmlparser.Parser;
import vooga.resources.xmlparser.XMLTag;
import vooga.util.math.Angle;

public class PieceTag extends XMLTag {
    private static final String PIECE = "piece";
    private static final String TYPE = "type";
    private static final String ROTATION = "rotation";

    private static final String X = "x";
    private static final String Y = "y";

    private ShipParser parser;
    private EventManager eventManager;

    @Override
    public String getTagName() {
        return PIECE;
    }

    public PieceTag(ShipParser parser, EventManager eventManager) {
        this.parser = parser;
        this.eventManager = eventManager;
    }

    @Override
    public void parse(Parser context, Element xmlElement) {
        String name = xmlElement.getAttribute(TYPE) + "Piece";

        

        int degrees = Integer.parseInt(xmlElement.getAttribute(ROTATION));
        Angle angle = new Angle(Angle.degreesToRadians(degrees));

        int x = Integer.parseInt(xmlElement.getAttribute(X));
        int y = Integer.parseInt(xmlElement.getAttribute(Y));

        Collection<String> events = new HashSet<String>();

        NodeList children = xmlElement.getChildNodes();
        for (int i = 0; i < children.getLength(); i++) {
            if (children.item(i).getNodeName().equals("event"))
                events.add(getValue((Element) children.item(i)));
        }

        parser.buildPiece(name, x, y, angle, events, eventManager);
    }
}
