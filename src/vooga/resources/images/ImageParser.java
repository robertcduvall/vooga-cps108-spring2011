package vooga.resources.images;

import java.awt.image.BufferedImage;
import org.w3c.dom.Element;
import vooga.resources.Direction;
import vooga.resources.ResourceException;
import vooga.resources.xmlparser.Parser;
import vooga.resources.xmlparser.XMLTag;
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
public class ImageParser extends Parser
{
    private static final String
    	RESOURCES = "images",
        IMAGE = "image",
        NAME = "name",
        PATH = "path",
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
        OFFSET = "offset",
        ROW_OFFSET = "row",
        COL_OFFSET = "column";
    
    /**
     * Root tag.
     */
    private class ResourcesTag extends XMLTag {
    	public String getTagName() { return RESOURCES; }
    	public void parse(Parser context, Element xmlElement) {
    		parseChildren(context, xmlElement);
    	}
    	
    }
    private class ImageTag extends XMLTag {
        public String getTagName() {return IMAGE;}
        public void parse(Parser context, Element xmlElement) {
            imageName = xmlElement.getAttribute(NAME);
            
            imageAdded = false;
            parseChildren(context, xmlElement);
            if (!imageAdded) addImage(0);
        }
    }
    private class SourceTag extends XMLTag {
        public String getTagName() {return PATH;}
        public void parse(Parser context, Element xmlElement) {
            sourceImage = bsLoader.getImage(getValue(xmlElement));
            tileCols = 1;
            tileRows = 1;
            tileIndex = 0;
            updateTiles();
        }
    }
    private class ColumnsTag extends XMLTag {
        public String getTagName() {return COLUMNS;}
        public void parse(Parser context, Element xmlElement) {
            if (sourceImage == null)
                throw ResourceException.syntaxException();
            tileCols = Integer.parseInt(getValue(xmlElement));
            tileIndex = 0;
            updateTiles();
        }
    }
    private class RowsTag extends XMLTag {
        public String getTagName() {return ROWS;}
        public void parse(Parser context, Element xmlElement) {
            if (sourceImage == null)
                throw ResourceException.syntaxException();
            tileRows = Integer.parseInt(getValue(xmlElement));
            tileIndex = 0;
            updateTiles();
        }
    }    
    private class WidthTag extends XMLTag {
        public String getTagName() {return WIDTH;}
        public void parse(Parser context, Element xmlElement) {
            int width = Integer.parseInt(getValue(xmlElement));
            if (sourceImage == null)
                throw ResourceException.syntaxException();
            tileCols = sourceImage.getWidth() / width;
            tileIndex = 0;
            updateTiles();
        }
    }
    private class HeightTag extends XMLTag {
        public String getTagName() {return HEIGHT;}
        public void parse(Parser context, Element xmlElement) {
            int height = Integer.parseInt(getValue(xmlElement));
            if (sourceImage == null)
                throw ResourceException.syntaxException();
            tileRows = sourceImage.getHeight() / height;
            tileIndex = 0;
            updateTiles();
        }
    }
    private abstract class RepeatableTag extends XMLTag {
        public void parse(Parser context, Element xmlElement) {
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
        
        public void nestedParse(Parser context, Element xmlElement) {
            parseChildren(context, xmlElement);
        }                                                                  
    }
    private class StateTag extends RepeatableTag {
        public String getTagName() {return STATE;}
        public void nestedParse(Parser context, Element xmlElement) {
            imageAdded = false;
            parseChildren(context, xmlElement);
            if (!imageAdded) addImage(0);
            state++;
        }
    }
    private class DirectionsTag extends XMLTag {
        public String getTagName() {return DIRECTIONS;}
        public void parse(Parser context, Element xmlElement) {
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
    private class FrameTag extends RepeatableTag {
        public String getTagName() {return FRAME;}
        public void nestedParse(Parser context, Element xmlElement) {
            parseChildren(context, xmlElement);
            int duration = 0;
            if (xmlElement.hasAttribute(DELAY))
                duration = Integer.parseInt(xmlElement.getAttribute(DELAY));
            
            addImage(duration);
        }
    }
    private class OffsetTag extends XMLTag {
        public String getTagName() {return OFFSET;}
        public void parse(Parser context, Element xmlElement) {
            tileIndex = Integer.parseInt(getValue(xmlElement));
        }
    }
    private class RowOffsetTag extends XMLTag {
        public String getTagName() {return ROW_OFFSET;}
        public void parse(Parser context, Element xmlElement) {
            int row = Integer.parseInt(getValue(xmlElement));
            int col = tileIndex % tileCols;
            tileIndex = row * tileCols + col;
        }
    }
    private class ColumnOffsetTag extends XMLTag {
        public String getTagName() {return COL_OFFSET;}
        public void parse(Parser context, Element xmlElement) {
            int row = tileIndex / tileCols;
            int col = Integer.parseInt(getValue(xmlElement));
            tileIndex = row * tileCols + col;
        }
    }
    
    private ImageLoader parent;
    private BaseLoader bsLoader;
    
    private String imageName = null;
    private int state = 0;
    private Direction dir = Direction.NORTH;
    private BufferedImage sourceImage = null;
    private BufferedImage[] tiles = null;
    private int tileCols = 0;
    private int tileRows = 0;
    private int tileIndex = 0;
    private boolean imageAdded = false;
    
    /**
     * Creates a new ImageParser that will load images from a
     * BaseLoader and save them in ImageLoader.
     * 
     * @param parent The ImageLoader using this parser.
     * @param bsLoader The BaseLoader to use to actually load images.
     */
    public ImageParser(ImageLoader parent, BaseLoader bsLoader)
    {
        this.parent = parent;
        this.bsLoader = bsLoader;
        
        addDefinitions(new ResourcesTag(),
        			   new ImageTag(), new SourceTag(),
                       new WidthTag(), new HeightTag(),
                       new RowsTag(), new ColumnsTag(),
                       new StateTag(), new DirectionsTag(),
                       new FrameTag(), new OffsetTag(),
                       new RowOffsetTag(), new ColumnOffsetTag());
    }

    /**
     * Updates the tileset retrieved from the image -- this is called
     * whenever the source image or the number of tiles horizontally or
     * vertically changes.
     */
    private void updateTiles()
    {
        tiles = ImageUtil.splitImages(sourceImage, tileCols, tileRows);
    }
    
    /**
     * Converts a character (lowercase or uppercase N, S, E, or W)
     * into a direction.
     * 
     * @param c The character to convert.
     * @return The corresponding direction
     */
    private static Direction getDirection(char c)
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
    
    /**
     * Add an image with the current image data. If there is already
     * an image there, add an image to it as a frame.
     * 
     * @param duration The duration of the frame added (use 0 by default, 
     * and for static images).
     */
    private void addImage(int duration)
    {
        ImageKey key = new ImageKey(imageName, state, dir);
        
        if (!parent.images.containsKey(key))
        {
            parent.images.put(key, new AnimatedImage());
        }
        
        AnimatedImage image = parent.images.get(key);
        image.addFrame(tiles[tileIndex++], duration);
        
        imageAdded = true;
    }

}
    
