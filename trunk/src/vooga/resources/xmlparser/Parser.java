package vooga.resources.xmlparser;

import java.util.Map;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 *
 * Abstract class for parsing an XML document. To parse XML documents with
 * a specific syntax, create a subclass of Parser, which will also act as
 * the context in which the tags are parsed, and add XMLTag instances for
 * the class to recognize.
 * 
 * @author Misha
 *
 */
public abstract class Parser
{
    protected Map<String, XMLTag> xmlDefinitions;
    
    /**
     * Creates a new parser with no syntax definitions.
     */
    public Parser() {}

    /**
     * Adds XML tags for the parser to recognize and interpret.
     */
    public void addDefinitions(XMLTag... tags)
    {
        for(XMLTag tag : tags)
            addDefinition(tag);
    }
    
    /**
     * Adds an XML tag for the parser to recognize and interpret.
     */
    public void addDefinition(XMLTag tag)
    {
        xmlDefinitions.put(tag.getTagName(), tag);
    }
    
    /**
     * Parses an XML element and (if applicable) its children.
     * 
     * @param xmlElement The XML element to parse.
     */
    public void parseElement(Element xmlElement)
    {
        xmlDefinitions.get(xmlElement.getTagName()).parse(this, xmlElement);
    }
    
    /**
     * Parses an entire XML document.
     * 
     * @param doc The document to parse.
     */
    public void parse(Document doc)
    {
        NodeList nodeList = doc.getChildNodes();
        
        for(int i=0; i<nodeList.getLength(); i++)
        {
            parseElement((Element) nodeList.item(i));
        }
    }
}
