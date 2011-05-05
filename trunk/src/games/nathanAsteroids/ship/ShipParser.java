package games.nathanAsteroids.ship;

import games.nathanAsteroids.ship.tags.PieceSpriteTag;
import games.nathanAsteroids.ship.tags.PieceTag;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import vooga.core.VoogaGame;
import vooga.core.event.EventManager;
import vooga.levels.util.ConverterRack;
import vooga.levels.util.SpriteConstructor;
import vooga.reflection.Reflection;
import vooga.resources.xmlparser.Parser;
import vooga.resources.xmlparser.XMLTag;
import vooga.util.math.Angle;

/**
 * XML Level Parser
 * 
 * @author Sterling Dorminey
 * 
 */
public class ShipParser extends Parser {
    private HashMap<String, ShipSpriteConstructor> spriteFactoryMap;
    private ConverterRack converterRack;
    private Ship myShip;
    private int centerX;
    private int centerY;

    private static final class ShipTag extends XMLTag {
        public static final String SHIP = "ship";

        @Override
        public String getTagName() {
            return SHIP;
        }

    }

    public ShipParser(Ship ship, VoogaGame game) {
        super();

        spriteFactoryMap = new HashMap<String, ShipSpriteConstructor>();

        converterRack = new ConverterRack(game);
        myShip = ship;

        super.addDefinitions(new ShipTag(), new PieceSpriteTag(this), new PieceTag(this, game.getEventManager()));
        centerX = 0;
        centerY = 0;
    }

    public void setLocation(int x, int y) {
        centerX = x;
        centerY = y;
    }

    public void addSpriteArchetype(String name, ShipSpriteConstructor factory) {
        spriteFactoryMap.put(name, factory);
    }

    public void buildPiece(String type, int x, int y, Angle angle, Collection<String> events, EventManager eventManager) {


        ShipPiece piece = (ShipPiece) spriteFactoryMap.get(type).construct(centerX + x, centerY + y, myShip, angle);

        for (String event : events) {
            piece.registerEvent(event, eventManager);
        }
        myShip.addSprites(piece);

        if (type.equals("commandCenterPiece")) {
            myShip.setCommandCenter((CommandCenterPiece) piece);
        }
    }

    public ConverterRack getConverterRack() {
        return converterRack;
    }

    /**
     * Returns the map of archetype names to sprite constructors.
     */
    public Map<String, ShipSpriteConstructor> getSpriteConstructorMap() {
        return spriteFactoryMap;
    }

}
