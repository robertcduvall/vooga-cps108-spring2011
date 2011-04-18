package vooga.leveleditor.xml;
/**
package xml;

import java.io.ByteArrayOutputStream;

import java.io.File;
import java.io.IOException;

import nu.xom.*;

/**
 * Deals with XML.
 * @author Alex Lee (hl69)
 *
public class XMLDealer
{

    private Builder parser;
    private Document doc;

    public XMLDealer(String filename)
    {
        this(new File(filename));
    }

    public XMLDealer(File f)
    {
        parser = new Builder();
        try
        {
            doc = parser.build(f);
        }
        catch (ValidityException e)
        {
            /*
             * The XML file is slightly malformed. (This error is not fatal.)
             *
            e.printStackTrace();
        }
        catch (ParsingException e)
        {
            /*
             * The XML file could not be parsed.
             *
            e.printStackTrace();
        }
        catch (IOException e)
        {
            /*
             * The file could not be found.
             *
            e.printStackTrace();
        }
    }

    public Document getDocument()
    {
        return doc;
    }

    public String toString()
    {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        Serializer serializer = new Serializer(out);
        serializer.setIndent(4);
        try
        {
            serializer.write(doc);
        }
        catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return out.toString();
    }

}
*/
