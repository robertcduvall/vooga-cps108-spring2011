package vooga.resources.images;

import java.awt.image.BufferedImage;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import vooga.resources.Direction;
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
public class ImageParser
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
        OFFSET = "offset",
        ROW_OFFSET = "row",
        COL_OFFSET = "column";
    
    private ImageLoader parent;
    private BaseLoader bsLoader;
    
    private String currentName = null;
    private int currentState = 0;
    private Direction currentDir = null;
    private BufferedImage[] currentImages = null;
    private int rowLength = 0;
    private int currentImage = 0;

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
    
    private void updateCurrentImage(Element element)
    {
        if (hasChild(element, OFFSET))
        {
            currentImage = Integer.parseInt(getValue(element, OFFSET));
        }
        else if (hasChild(element, ROW_OFFSET) && hasChild(element, COL_OFFSET))
        {
            currentImage = Integer.parseInt(getValue(element, COL_OFFSET)) +
                        rowLength * Integer.parseInt(getValue(element, ROW_OFFSET));
        }
    }
    
    private void parseImageData(Element imageElement)
    {
        updateImages(imageElement);
        
        if (currentImages != null)
            updateCurrentImage(imageElement);
     
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
        
        if (currentImages != null)
            updateCurrentImage(stateElement);
        
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
        
        if (currentImages != null)
            updateCurrentImage(dirElement);

        
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
        
        parent.addImage(new ImageKey(currentName, currentState, currentDir), anim);
    }
    
    public ImageParser(ImageLoader parent, BaseLoader bsLoader)
    {
        this.parent = parent;
        this.bsLoader = bsLoader;
    }
    
    public void parse(Document doc)
    {
        NodeList nodeList = doc.getElementsByTagName(IMAGE);
        
        for(int i=0; i<nodeList.getLength(); i++)
        {
            Element imageXML = (Element) nodeList.item(i);
            currentName = imageXML.getAttribute(NAME);
            parseImageData(imageXML);
        }
    }
}
    
