package vooga.core.event.examples.bubblefishbob.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.io.StringReader;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;


public class XMLReader
{
    private Document myDoc;
    private XPath myXPath;


    public XMLReader (File file) throws FileNotFoundException
    {
        this(new FileReader(file));
    }


    public XMLReader (InputSource inputSource)
    {
        try
        {
            DocumentBuilderFactory docBuilderFactory =
                DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
            myDoc = docBuilder.parse(inputSource);

            // normalize text representation
            myDoc.getDocumentElement().normalize();

            XPathFactory factory = XPathFactory.newInstance();
            myXPath = factory.newXPath();
        }
        catch (SAXParseException err)
        {
            System.out.println("** Parsing error" + ", line " +
                               err.getLineNumber() + ", uri " +
                               err.getSystemId());
            System.out.println(" " + err.getMessage());
        }
        catch (SAXException e)
        {
            Exception x = e.getException();
            ((x == null) ? e : x).printStackTrace();
        }
        catch (Throwable t)
        {
            t.printStackTrace();
        }
    }


    public XMLReader (Reader reader)
    {
        this(new InputSource(reader));
    }


    public XMLReader (String data)
    {
        this(new InputSource(new StringReader(data)));
    }


    public Double getDoubleValue (String expr)
    {
        Node node = getNode(expr);
        if (node == null) throw new RuntimeException("Node not found: " + expr);
        String value = node.getNodeValue();
        if (value == null) throw new RuntimeException("Node has no value: " +
                                                      expr);
        return Double.parseDouble(value);
    }


    public Double getDoubleValue (XPathExpression expr)
    {
        Node node = getNode(expr);
        if (node == null) throw new RuntimeException("Node not found: " + expr);
        String value = node.getNodeValue();
        if (value == null) throw new RuntimeException("Node has no value: " +
                                                      expr);
        return Double.parseDouble(value);
    }


    public Integer getIntegerValue (String expr)
    {
        Node node = getNode(expr);
        if (node == null) throw new RuntimeException("Node not found: " + expr);
        String value = node.getNodeValue();
        if (value == null) throw new RuntimeException("Node has no value: " +
                                                      expr);
        return Integer.parseInt(value);
    }


    public Integer getIntegerValue (XPathExpression expr)
    {
        Node node = getNode(expr);
        if (node == null) throw new RuntimeException("Node not found: " + expr);
        String value = node.getNodeValue();
        if (value == null) throw new RuntimeException("Node has no value: " +
                                                      expr);
        return Integer.parseInt(value);
    }


    public Node getNode (String xpathExpression)
    {
        try
        {
            return (Node) myXPath.evaluate(xpathExpression,
                                           myDoc,
                                           XPathConstants.NODE);
        }
        catch (XPathExpressionException e)
        {
            e.printStackTrace();
        }
        return null;
    }


    public Node getNode (XPathExpression expr)
    {
        try
        {
            return (Node) expr.evaluate(myDoc, XPathConstants.NODE);
        }
        catch (XPathExpressionException e)
        {
            e.printStackTrace();
        }
        return null;
    }


    public NodeList getNodes (String xpathExpression)
    {
        try
        {
            return (NodeList) myXPath.evaluate(xpathExpression,
                                               myDoc,
                                               XPathConstants.NODESET);
        }
        catch (XPathExpressionException e)
        {
            e.printStackTrace();
        }
        return null;
    }


    public NodeList getNodes (XPathExpression expr)
    {
        try
        {
            return (NodeList) expr.evaluate(myDoc, XPathConstants.NODESET);
        }
        catch (XPathExpressionException e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
