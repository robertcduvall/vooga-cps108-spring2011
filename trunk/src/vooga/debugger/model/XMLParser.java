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
 */
public class XMLParser implements DebuggerParser
{
	private TreeMap<String, Node> fieldMap;
	
	private final String FILE_PATH = "src/resources/GameFields.xml";
	private Document doc;
	
	public XMLParser()
	{
		try {
			 File file = new File(FILE_PATH);
			 DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			 DocumentBuilder db = dbf.newDocumentBuilder();
			 doc = db.parse(file);
			 doc.getDocumentElement().normalize();
			  
			 buildFieldMap();
			  
		 } catch(Exception e)
		 {
			 e.printStackTrace();
			 //TODO: explain
		 }
	}
	private void buildFieldMap()
	{
		fieldMap = new TreeMap<String,Node>();
		NodeList allLevels = doc.getElementsByTagName("Object");
		for(int cur = 0; cur < allLevels.getLength(); cur++)
		{
			Node curLevel = allLevels.item(cur);
			String className = ((Element)curLevel).getAttribute("name");
			fieldMap.put(className, curLevel);
		}
	}
	private List<Field> getFields(String className)
	{
		List<Field> list = new ArrayList<Field>();
		try {
			Node nNode = fieldMap.get(className);
			Element eField = (Element) nNode; 
			if(eField == null)
				return list;
			NodeList nl = eField.getElementsByTagName("FieldName");
			Field[] allFields = Class.forName(className).getFields();
			
			//Allows show="all" in xml
			if(eField.hasAttribute("show"))
				for(Field f : allFields)
					list.add(f);
			
			else for(int i=0; i<nl.getLength(); i++)
			{
				Node curNode = nl.item(i);
				String type = ((Element)curNode).getAttribute("name");
				
				if(curNode.hasChildNodes())
				{	
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
				}
				else
				{					
					//adds all fields of specified type
					for(Field f : allFields)
						if(f.getType().getName().equals(type))
							list.add(f);
				}
			}
		}
		catch(Exception e)
		{
			System.err.println("error at "+className);
			e.printStackTrace();
			//TODO: put something here
		}
		return list;

	}
	private Field[] asArray(List<Field> list)
	{
		Field[] ret = new Field[list.size()];
		for(int i=0; i<list.size(); i++)
			ret[i] = list.get(i);
		return ret;
	}
	public Field[] getValidFieldsFor(Class<?> testClass)
	{
		List<Field> list = new ArrayList<Field>();
		if(!testClass.isPrimitive())
			list = getFields(testClass.getName());
		return asArray(list);
	}
	/**
	 * Deprecated
	 */
	public GameField createGameField(Object gameObj)
	{
		// TODO Auto-generated method stub
		return null;
	}
}
