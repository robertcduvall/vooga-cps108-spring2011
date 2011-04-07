package vooga.resources.images;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.SortedMap;
import java.util.TreeMap;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import com.golden.gamedev.engine.BaseLoader;
import com.golden.gamedev.util.ImageUtil;
import vooga.resources.Direction;

/**
 * Loads images from sources specified in an XML resource file.
 * 
 * <br /> <br />
 * 
 * The program need not know the actual name and location of the image file.
 * After the image file is parsed, the image files are indexed by a name
 * supplied in the XML resource file. When retrieving an image, you can
 * also request:
 * <ul>
 * <li>a Direction: the XML file can specify that an alternate image
 * can be used for rotation, or the image will be rotated.</li>
 * <li>An index: this can be used for many purposes, from indicating sprite
 * status to choosing an image from a tile set</li>
 * <li>A time value: this will be used for choosing one of several images in
 * an animation.
 * </ul>
 * As an alternative, the getAnimation() methods can be used, which 
 * return an array of images to time the animation elsewhere.
 * 
 * @author Misha
 *
 */
public class ImageLoader
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
        DELAY = "t";

    
    private SortedMap<ImageKey, AnimatedImage> images;
    private BaseLoader bsLoader;
    private String currentName;
    private int currentState;
    private Direction currentDir;
    private BufferedImage[] currentImages;
    private int rowLength;
    private int currentImage;
    

    private Direction getDirection(char c)
    {
        switch(c)
        {
            case 'N': case 'n': return Direction.NORTH;
            case 'S': case 's': return Direction.SOUTH;
            case 'E': case 'e': return Direction.EAST;
            case 'W': case 'w': return Direction.WEST;
            default: return null;
        }
    }
    
    private boolean hasChild(Element element, String name)
    {
        return element.getElementsByTagName(name).getLength() > 0;
    }
    
    private String getValue(Element element, String name)
    {
        Node node = element.getElementsByTagName(name).item(0);
        
        return node.getFirstChild().getNodeValue();
    }

    private void updateImages(Element element)
    {
        if (!hasChild(element, SOURCE)) return;
        
        BufferedImage source = bsLoader.getImage(getValue(element, SOURCE));

        int rows = 0;
        int cols = 0;
        
        if (hasChild(element, ROWS))
            rows = Integer.parseInt(getValue(element, ROWS));
        else if (hasChild(element, WIDTH))
            rows = source.getWidth() / Integer.parseInt(getValue(element, WIDTH));
        else
            rows = 1;
        
        if (hasChild(element, COLUMNS))
            cols = Integer.parseInt(getValue(element, COLUMNS));
        else if (hasChild(element, HEIGHT))
            rows = source.getHeight() / Integer.parseInt(getValue(element, HEIGHT));
        else
            cols = 1;
        
        currentImages = ImageUtil.splitImages(source, cols, rows);
        currentImage = 0;
        rowLength = cols;
    }
    
    private void parseImageData(Element imageElement)
    {
        updateImages(imageElement);
     
        currentState = 0;
        
        NodeList states = imageElement.getElementsByTagName(STATE);
        
        if (states.getLength() > 0)
        {
            for(int i=0; i < states.getLength(); i++)
            {
                Element stateElement = (Element) states.item(i);
                
                String count = stateElement.getAttribute(COUNT);
                
                if (count.equals(""))
                {
                    parseStateData(stateElement);
                }
                else if (count.equals("*"))
                {
                    while (currentImage < currentImages.length)
                        parseStateData(stateElement);
                }
                else
                {
                    for(int j=0; j<Integer.parseInt(count); j++)
                        parseStateData(stateElement);
                }
            }
        }
        else
        {
            parseStateData(imageElement);
        }
    }
    
    private void parseStateData (Element stateElement)
    {
        updateImages(stateElement);
        
        if (hasChild(stateElement, DIRECTIONS))
        {
            Element dirElement = (Element) stateElement.getElementsByTagName(DIRECTIONS).item(0);
            String order = dirElement.getAttribute(ORDER);
            if (order.isEmpty()) order = "NESW";
            
            for(char c : order.toCharArray())
            {
                currentDir = getDirection(c);
                parseAnimation(dirElement);
            }
        }
        else
        {
            currentDir = null;
            parseAnimation(stateElement);
        }
        
        currentState++;
    }

    private void parseAnimation (Element dirElement)
    {
        updateImages(dirElement);
        
        NodeList frames = dirElement.getElementsByTagName(FRAME);
        
        AnimatedImage anim = new AnimatedImage(); 
        
        if (frames.getLength() == 0)
        {
            anim.addFrame(currentImages[currentImage++]);
        }
        else
        {
            for(int i=0; i < frames.getLength(); i++)
            {
                Element frame = (Element) frames.item(i);
                
                int delay = 0;
                if (!frame.getAttribute(DELAY).isEmpty())
                    delay = Integer.parseInt(frame.getAttribute(DELAY));
                
                String count = frame.getAttribute(COUNT);
                
                if (count.equals(""))
                {
                    anim.addFrame(currentImages[currentImage++], delay);
                }
                else if (count.equals("*"))
                {
                    while (currentImage < currentImages.length)
                        anim.addFrame(currentImages[currentImage++], delay);
                }
                else
                {
                    for(int j=0; j<Integer.parseInt(count); j++)
                        anim.addFrame(currentImages[currentImage++], delay);
                }
            }
        }
        
        images.put(new ImageKey(currentName, currentState, currentDir), anim);
    }

    /**
     * Initializes the ImageLoader from a resource file. 
     * 
     * @param resource The file to load the image specs from
     */
    public ImageLoader(File resource, BaseLoader bsLoader)
    {
        this.bsLoader = bsLoader;
        images = new TreeMap<ImageKey, AnimatedImage>();
        
        try
        {
            DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = db.parse(resource);
            NodeList nodeList = doc.getElementsByTagName(IMAGE);
            
            for(int i=0; i<nodeList.getLength(); i++)
            {
                Element imageXML = (Element) nodeList.item(i);
                currentName = imageXML.getAttribute(NAME);
                parseImageData(imageXML);
            }

        }
        catch (ParserConfigurationException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (SAXException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
    
    /** See getImage(name, imageIndex, direction, time). */
    public BufferedImage getImage(String name) {return getImage(name, 0, null, 0);}
    /** See getImage(name, imageIndex, direction, time). */
    public BufferedImage getImage(String name, int imageIndex) {return getImage(name, imageIndex, null, 0);}
    /** See getImage(name, imageIndex, direction, time). */
    public BufferedImage getImage(String name, Direction direction) {return getImage(name, 0, direction, 0);}
    /** See getImage(name, imageIndex, direction, time). */
    public BufferedImage getImage(String name, int imageIndex, Direction direction) {return getImage(name, imageIndex, direction, 0);}
    /** See getImage(name, imageIndex, direction, time). */
    public BufferedImage getImage(String name, int imageIndex, long time) {return getImage(name, imageIndex, null, time);}
    /** See getImage(name, imageIndex, direction, time). */
    public BufferedImage getImage(String name, Direction direction, long time) {return getImage(name, 0, direction, time);}

    /**
     * Returns an image specified in the resource file.
     * 
     * @param name The name this image is identified by in the resource file.
     * @param imageIndex Chooses which of several images corresponding to this name
     * to choose (defaults to the first (or only) image supplied)
     * @param direction Chooses which direction to use for the image (can either rotate
     * or use separate images). Defaults to first (or only) direction specified.
     * @param time Chooses which frame to use for animated images. Defaults to 
     * first (or only) frame.
     * @return The corresponding image.
     */
    public BufferedImage getImage(String name, int imageIndex, Direction direction, long time)
    {
        return images.get(new ImageKey(name, imageIndex, direction)).getFrame(time);
    }

    /** See getAnimation(name, imageIndex, direction) */    
    public BufferedImage[] getAnimation(String name) {return getAnimation(name, 0, null);}
    /** See getAnimation(name, imageIndex, direction) */    
    public BufferedImage[] getAnimation(String name, int imageIndex) {return getAnimation(name, imageIndex, null);}
    /** See getAnimation(name, imageIndex, direction) */
    public BufferedImage[] getAnimation(String name, Direction direction) {return getAnimation(name, 0, direction);}
    
    /**
     * Returns an animation specified in the resource file.
     * 
     * @param name The name this animation is identified by in the resource file.
     * @param imageIndex Chooses which of several animations corresponding to this name
     * to choose (defaults to the first (or only) animation supplied)
     * @param direction Chooses which direction to use for the animation (can either rotate
     * or use separate animations). Defaults to first (or only) direction specified.
     * @return An array of images corresponding to the animation.
     */
    public BufferedImage[] getAnimation(String name, int imageIndex, Direction direction)
    {
        return images.get(new ImageKey(name, imageIndex, direction)).getFrames();
    }

    /**
     * Returns the range of indices that are valid for the given image (or animation):
     * from 0 (inclusive) to the returned value (exclusive).
     * 
     * @param name The name this image is identified by in the resource file
     * @return The range of possible indices that can be used for the image.
     */
    public int getIndexRange(String name)
    {
        ImageKey last = images.headMap(new ImageKey(name+"/0", 0, null)).lastKey();
        
        return last.getIndex() + 1;
    }
}
