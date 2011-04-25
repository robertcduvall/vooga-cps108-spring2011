package vooga.debugger.model;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
/**
 * 
 * @author Austin Benesh
 *
 * The XML Parser Class reads an XML file, builds a TreeMap of Classname Strings to corresponding XML nodes,
 * and allows the user to view either all Fields in a certain class or fields specified in the XML file.
 */
public class XMLParser extends DebuggerParser
{
	private final String FILE_PATH = "src/vooga/debugger/resources/GameFields.xml";
	
	/**
	 * Default Constructor. Parses document and builds Field Map.
	 */
	public XMLParser()
	{
		fieldMap = new TreeMap<String,Node>();
		try {
			 File file = new File(FILE_PATH);
			 DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			 DocumentBuilder db = dbf.newDocumentBuilder();
			 Document doc = db.parse(file);
			 doc.getDocumentElement().normalize();
			  
			 buildFieldMap(doc);
			  
		 } catch(Exception e)
		 {
			 System.err.println("XML File not found at: "+FILE_PATH+"\nShowing all variables by default");
		 }
	}
	
	/**
	 * Builds TreeMap with Class name Strings mapped to Nodes.
	 * @param doc XML Document being read
	 */
	private void buildFieldMap(Document doc)
	{
		NodeList allLevels = doc.getElementsByTagName("Object");
		for(int cur = 0; cur < allLevels.getLength(); cur++)
		{
			Node curLevel = allLevels.item(cur);
			String className = ((Element)curLevel).getAttribute("name");
			fieldMap.put(className, curLevel);
		}
	}
	
	/**
	 * @param className Name of class
	 * @return List of Fields in a specified class with visibility according to XML
	 */
	private List<Field> getFields(String className)
	{
		List<Field> list = new ArrayList<Field>();
		Node nNode = fieldMap.get(className);
		Element eField = (Element) nNode; 
		if(eField == null)
			return list;
		NodeList nl = eField.getElementsByTagName("FieldName");
		
		List<Field> allFields = loadAvailableFields(className, eField, false);
					
		//Allows show="all" in xml
		String showPref = eField.getAttribute("show");
		if(showPref.equals("all") || showPref.equals("declared"))
			for(Field f : allFields)
				list.add(f);
		else for(int i=0; i<nl.getLength(); i++)
		{
			Node curNode = nl.item(i);
			if(curNode.hasChildNodes())
				list.addAll(populateChildren(allFields,curNode));
			else
				list.addAll(populateAllOfType(allFields,curNode));
		}
		return list;

	}
	/**
	 * Populates a list of all available fields, allows for show all override
	 * @param className
	 * @param eField
	 * @param overrideShowAll
	 * @return
	 */
	private List<Field> loadAvailableFields(String className, Element eField, boolean overrideShowAll)
	{
		List<Field> toRet = new ArrayList<Field>();
		try{
			if(overrideShowAll || !eField.getAttribute("show").equals("declared"))
				for(Field f : Class.forName(className).getFields())
					if(!toRet.contains(f))	
						toRet.add(f);
			for(Field f : Class.forName(className).getDeclaredFields())
				if(!toRet.contains(f))	
					toRet.add(f);
		}
		catch(Exception e)
		{
			System.err.println(e);
		}
		return toRet;
	}
	/**
	 * @param allFields Array of all Fields to be checked
	 * @param curNode Current object node being read
	 * @return List of Fields for Objects when Field display is limited by name
	 */
	private List<Field> populateChildren(List<Field> allFields, Node curNode)
	{
		ArrayList<Field> list = new ArrayList<Field>();
		
		//Populates list of acceptable variable names
		Node nValue= (Node)curNode.getChildNodes().item(0);
		String[] splitVals = nValue.getNodeValue().split(",");
	    ArrayList<String> acceptedNames = new ArrayList<String>();
	    for(int j=0; j<splitVals.length; j++)
	    	acceptedNames.add(splitVals[j].trim());
		
		//adds only fields of specified name and type
	    for(Field f : allFields)
			if(acceptedNames.contains(f.getName()))
				list.add(f);
	    return list;
	}
	
	/**
	 * @param allFields Array of all Fields to be checked
	 * @param curNode Current object node being read
	 * @return List of Fields with all Objects of type curNode
	 */
	private List<Field> populateAllOfType(List<Field> allFields, Node curNode)
	{
		ArrayList<Field> list = new ArrayList<Field>();
		String type = ((Element)curNode).getAttribute("name");
		for(Field f : allFields)
			if(f.getType().getName().equals(type))
				list.add(f);
		return list;
	}
	
	/**
	 * @param list List<Field> to be converted to an Array
	 * @return Field Array for List<Field>
	 */
	private Field[] asArray(List<Field> list)
	{
		Field[] ret = new Field[list.size()];
		for(int i=0; i<list.size(); i++)
			ret[i] = list.get(i);
		return ret;
	}
	
	/**
	 * @param testClass Class to get valid fields for
	 * @param showAllVariables If true, returns all Fields; if false (default), parses XML for Fields to display
	 * @return Field array of all valid Fields for a corresponding class
	 */
	public Field[] getValidFieldsFor(Class<?> testClass, boolean showAllVars)
	{		
		if(fieldMap.containsKey(testClass.getName()) && !showAllVars)
			return asArray(getFields(testClass.getName()));
		else if(!testClass.isPrimitive())
			return asArray(loadAvailableFields(testClass.getName(),null,true));
		return new Field[]{};
	}
	
	/**
	 * Deprecated method
	 */
	public GameField createGameField(Object gameObj)
	{
		// TODO Auto-generated method stub
		return null;
	}
}
