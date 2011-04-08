package vooga.resources.xmlparser;

import java.util.Map;
import org.w3c.dom.Element;

public class Context
{
    private Map<String, Expression> xmlDefinitions;
    
    public Context() {}
    
    public void addDefinitions(Expression... expressions)
    {
        for(Expression expr : expressions)
            addDefinition(expr);
    }
    
    public void addDefinition(Expression expr)
    {
        xmlDefinitions.put(expr.getTagName(), expr);
    }
    
    public void parseElement(Element xmlElement)
    {
        xmlDefinitions.get(xmlElement.getTagName()).parse(this, xmlElement);
    }
}
