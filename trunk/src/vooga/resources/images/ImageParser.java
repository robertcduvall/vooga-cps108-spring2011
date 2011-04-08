package vooga.resources.images;

import java.awt.image.BufferedImage;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import vooga.resources.Direction;
import vooga.resources.ResourceException;
import vooga.resources.xmlparser.Context;
import vooga.resources.xmlparser.Expression;
import com.golden.gamedev.engine.BaseLoader;
import com.golden.gamedev.util.ImageUtil;

/**
 * Internal class to parse an XML file containing image data.
 * 
 * Not really meant to be used by anyone but ImageLoader.
 * 
 * @author Misha
 *
 */
public class ImageParser extends Context
{
    private static final String
        IMAGE = "image",
        NAME = "name",
        SOURCE = "source",
        WIDTH = "width",
        HEIGHT = "height",
        ROWS = "rows",
        COLUMNS = "columns",
        STATE = "state",
        COUNT = "count",
        DIRECTIONS = "directions",
        ORDER = "order",
        FRAME = "frame",
        DELAY = "t",
        OFFSET = "offset";
    
    private class ImageTag extends Expression {
        public String getTagName() {return IMAGE;}
        public void parse(Context context, Element xmlElement) {
            imageName = xmlElement.getAttribute(NAME);
            
            imageAdded = false;
            parseChildren(context, xmlElement);
            if (!imageAdded) addImage(0);
        }
    }
    private class SourceTag extends Expression {
        public String getTagName() {return SOURCE;}
        public void parse(Context context, Element xmlElement) {
            sourceImage = bsLoader.getImage(getValue(xmlElement));
            tileCols = 1;
            tileRows = 1;
            tileIndex = 0;
            updateTiles();
        }
    }
    private class ColumnsTag extends Expression {
        public String getTagName() {return COLUMNS;}
        public void parse(Context context, Element xmlElement) {
            if (sourceImage == null)
                throw ResourceException.syntaxException();
            tileCols = Integer.parseInt(getValue(xmlElement));
            tileIndex = 0;
            updateTiles();
        }
    }
    private class RowsTag extends Expression {
        public String getTagName() {return ROWS;}
        public void parse(Context context, Element xmlElement) {
            if (sourceImage == null)
                throw ResourceException.syntaxException();
            tileRows = Integer.parseInt(getValue(xmlElement));
            tileIndex = 0;
            updateTiles();
        }
    }    
    private class WidthTag extends Expression {
        public String getTagName() {return WIDTH;}
        public void parse(Context context, Element xmlElement) {
            int width = Integer.parseInt(getValue(xmlElement));
            if (sourceImage == null)
                throw ResourceException.syntaxException();
            tileCols = sourceImage.getWidth() / width;
            tileIndex = 0;
            updateTiles();
        }
    }
    private class HeightTag extends Expression {
        public String getTagName() {return HEIGHT;}
        public void parse(Context context, Element xmlElement) {
            int height = Integer.parseInt(getValue(xmlElement));
            if (sourceImage == null)
                throw ResourceException.syntaxException();
            tileRows = sourceImage.getHeight() / height;
            tileIndex = 0;
            updateTiles();
        }
    }
    private abstract class RepeatableExpression extends Expression {
        public void parse(Context context, Element xmlElement) {
            String countStr = xmlElement.getAttribute(COUNT);
            if (countStr.isEmpty())
                nestedParse(context, xmlElement);
            else if (countStr.equals("*"))
                while (tileIndex < tiles.length)
                    nestedParse(context, xmlElement);
            else 
                for(int i=0; i < Integer.parseInt(countStr); i++)
                    nestedParse(context, xmlElement);
                
        }
        
        public void nestedParse(Context context, Element xmlElement) {
            parseChildren(context, xmlElement);
        }                                                                  
    }
    private class StateTag extends RepeatableExpression {
        public String getTagName() {return STATE;}
        public void nestedParse(Context context, Element xmlElement) {
            imageAdded = false;
            parseChildren(context, xmlElement);
            if (!imageAdded) addImage(0);
            state++;
        }
    }
    private class DirectionsTag extends Expression {
        public String getTagName() {return DIRECTIONS;}
        public void parse(Context context, Element xmlElement) {
            String order = xmlElement.getAttribute(ORDER);
            if (order.isEmpty()) order = "NESW";
            
            for(char c : order.toCharArray()) {
                dir = getDirection(c);

                imageAdded = false;
                parseChildren(context, xmlElement);
                if (!imageAdded) addImage(0);
            }
        }
    }
    private class FrameTag extends RepeatableExpression {
        public String getTagName() {return FRAME;}
        public void nestedParse(Context context, Element xmlElement) {
            parseChildren(context, xmlElement);
            int duration = 0;
            if (xmlElement.hasAttribute(DELAY))
                duration = Integer.parseInt(xmlElement.getAttribute(DELAY));
            
            addImage(duration);
        }
    }
    private class OffsetTag extends Expression {
        public String getTagName() {return OFFSET;}
        public void parse(Context context, Element xmlElement) {
            tileIndex = Integer.parseInt(getValue(xmlElement));
        }
    }
    
    
    private ImageLoader parent;
    private BaseLoader bsLoader;
    
    private String imageName = null;
    private int state = 0;
    private Direction dir = null;
    private BufferedImage sourceImage = null;
    private BufferedImage[] tiles = null;
    private int tileCols = 0;
    private int tileRows = 0;
    private int tileIndex = 0;
    private boolean imageAdded = false;
    
    public ImageParser(ImageLoader parent, BaseLoader bsLoader)
    {
        this.parent = parent;
        this.bsLoader = bsLoader;
        
        addDefinitions(new ImageTag(), new SourceTag(),
                       new WidthTag(), new HeightTag(),
                       new RowsTag(), new ColumnsTag(),
                       new StateTag(), new DirectionsTag(),
                       new FrameTag(), new OffsetTag());
    }

    public void updateTiles()
    {
        tiles = ImageUtil.splitImages(sourceImage, tileCols, tileRows);
    }
    
    private Direction getDirection(char c)
    {
        switch(c)
        {
            case 'N': case 'n': return Direction.NORTH;
            case 'S': case 's': return Direction.SOUTH;
            case 'E': case 'e': return Direction.EAST;
            case 'W': case 'w': return Direction.WEST;
            default: return Direction.NORTH;
        }
    }
    
    public void addImage(int duration)
    {
        ImageKey key = new ImageKey(imageName, state, dir);
        
        if (!parent.images.containsKey(key))
        {
            parent.images.put(key, new AnimatedImage());
        }
        
        parent.images.get(key).addFrame(tiles[tileIndex++], duration);
        
        imageAdded = true;
    }
        
    public void parse(Document doc)
    {
        NodeList nodeList = doc.getChildNodes();
        
        for(int i=0; i<nodeList.getLength(); i++)
        {
            parseElement((Element) nodeList.item(i));
        }
    }
}
    
