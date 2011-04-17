package vooga.resources.xmlparser;

import java.io.File;
import java.util.HashMap;
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
public class Parser
{
    protected Map<String, XMLTag> xmlDefinitions;
 
    /**
     * Source tag. Allows inclusion of other XML files.
     * @author Sterling Dorminey
     *
     */
    private static final class SourceTag extends XMLTag {
    	private static final String TAG_NAME = "source";
    	
		@Override
		public String getTagName() {
			return TAG_NAME;
		}
		
		@Override
		public void parse(Parser context, Element xmlElement) {
			String filename = getValue(xmlElement);
			
			File xmlFile = new File(filename);
			Parser subParser = new Parser(super);
		}
    	
    }
    /**
     * Creates a new parser with no syntax definitions.
     */
    public Parser() {
    	xmlDefinitions = new HashMap<String, XMLTag>();
    }

    /**
     * Creates a parser from an existing parser.
     */
    public Parser(Parser oldParser) {
    	xmlDefinitions = new HashMap<String, XMLTag>(oldParser.xmlDefinitions);
    }
    
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
    	String tagName = xmlElement.getTagName();
    	XMLTag tag = xmlDefinitions.get(tagName);
        tag.parse(this, xmlElement);
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
