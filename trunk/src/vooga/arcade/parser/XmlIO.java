package vooga.arcade.parser;

import java.io.File;
import java.io.FileOutputStream;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

/**
 * Static class used to read and write XML
 * @author KevinWang
 *
 */
public class XmlIO{

    private static XMLOutputter writer;
    private static SAXBuilder builder;

    
    /**
     * create arcadeGameObject from the given path.
     * 
     * @param path
     * @return
     */
    public static ArcadeGameObject getArcadeGameObject(String path){
        initiatebuilder();
        
        Document file = null;
        try {
            file = builder.build(path);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        Element root = file.getRootElement();
        
        return new ArcadeGameObject(root, path);
    }
    
    /**
     * Initialize the SAXBuilder.
     * 
     */
    private static void initiatebuilder() {
        if(builder == null){
            builder = new SAXBuilder(); 
        }
    }

    /**
     * Write current xml contained in root to the specified path location
     * 
     * @param root
     * @param path
     */
    public static void writeToXml(Element root, String path){
        
        initiateWriter();
        
        try {
            writer.output(root, new FileOutputStream(new File(path)));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * initialize XMLOutputter
     */
    private static void initiateWriter() {
        if(writer == null){
            writer = new XMLOutputter();
            writer.setFormat(Format.getPrettyFormat());
        }
    }
}
