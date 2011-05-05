package games.patterson_game.refactoredVooga.resources.xmlparser;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * Abstract class for parsing an XML document. To parse XML documents with
 * a specific syntax, create a subclass of Parser, which will also act as
 * the context in which the tags are parsed, and add XMLTag instances for
 * the class to recognize.
 * 
 * @author Misha and Sterling
 *
 */
public class Parser
{
    protected Map<String, XMLTag> xmlDefinitions;
 
    /**
     * Source tag. Allows inclusion of other XML files.
     *
     */
    private class SourceTag extends XMLTag {
    	private static final String TAG_NAME = "source";
    	
    	private Parser parser;
    	
		@Override
		public String getTagName() {
			return TAG_NAME;
		}
		
		public SourceTag(Parser parser) {
			this.parser = parser;
		}
		
		@Override
		public void parse(Parser context, Element xmlElement) {
			String filename = getValue(xmlElement);
			
			Parser subParser = new Parser(parser);
			subParser.parse(filename);
		}
    	
    }
    /**
     * Creates a new parser with no syntax definitions.
     */
    public Parser() {
    	xmlDefinitions = new HashMap<String, XMLTag>();
    	
    	addDefinition(new SourceTag(this));
    }

    /**
     * Creates a parser from an existing parser.
     */
    public Parser(Parser oldParser) {
    	this();
    	xmlDefinitions.putAll(oldParser.xmlDefinitions);
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
    
    /**
     * Parses an XML document.
     * @param filename the filename of the XML file.
     * @throws ParserException an exception that can occur during parsing.
     */
	public void parse(String filename) throws ParserException {
		File xmlFile = new File(filename);
		try {
			DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
	        Document doc = db.parse(xmlFile);
	        parse(doc);
		} catch(IOException e) {
			throw ParserException.IO_ERROR;
		} catch (SAXException e) {
			throw ParserException.SYNTAX_ERROR;
		} catch (ParserConfigurationException e) {
			throw ParserException.SYSTEM_ERROR;
		}

	}
}
