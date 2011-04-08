package vooga.resources.xmlparser;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public abstract class Expression
{
    public abstract String getTagName();
    
    public Expression() {}
    
    protected String getValue(Element element)
    {
        return element.getFirstChild().getNodeValue();
    }
    
    public void parse(Context context, Element xmlElement)
    {
        parseChildren(context, xmlElement);
    }
    
    protected void parseChildren(Context context, Element xmlElement)
    {
        NodeList children = xmlElement.getChildNodes();
        
        for(int i=0; i<children.getLength(); i++)
        {
            context.parseElement((Element) children.item(i));
        }
    }

}
