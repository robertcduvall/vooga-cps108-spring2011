package games.patterson_game.refactoredVooga.resources.xmlparser;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * An abstract class for XML tags a parser can know how to parse.
 * 
 * @author Misha
 *
 */
public abstract class XMLTag
{
    /**
     * @return The name of the tag, as it will be used in the XML document.
     */
    public abstract String getTagName();
    
    
    /**
     * Creates a new instance of this XML tag. The tag doesn't actually store
     * any data of its own; an instance is just needed to pass this class around.
     */
    public XMLTag() {}
    
    /**
     * Internal method for extracting a string enclosed inside
     * a tag.
     * 
     * @param element The XML element shell.
     * @return The string inside.
     */
    protected String getValue(Element element)
    {
        return element.getFirstChild().getNodeValue();
    }
    
    /**
     * Parses a particular instance of this XML tag. By default,
     * just parses all the children.
     * 
     * @param context The parser parsing this tag -- this should also
     * provide the context in which the document is being parsed.
     * @param xmlElement The element to parse.
     */
    public void parse(Parser context, Element xmlElement)
    {
        parseChildren(context, xmlElement);
    }
    
    /**
     * Parses all the children of an XML tag.
     * 
     * @param context The parser parsing this tag.
     * @param xmlElement The element whose children to parse.
     */
    protected void parseChildren(Parser context, Element xmlElement)
    {
        NodeList children = xmlElement.getChildNodes();
        
        for(int i=0; i<children.getLength(); i++)
        {
        	Node child = children.item(i);
        	if(child instanceof Element)
        		context.parseElement((Element) child);
        }
    }

}
