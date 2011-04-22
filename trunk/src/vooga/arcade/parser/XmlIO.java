package vooga.arcade.parser;

import java.io.File;
import java.io.FileOutputStream;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;


public class XmlIO{

    private static XMLOutputter writer;
    private static SAXBuilder builder;

    
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
    
    private static void initiatebuilder() {
        if(builder == null){
            builder = new SAXBuilder(); 
        }
    }

    public static void writeToXml(Element root, String path){
        initiateWriter();
        
        try {
            writer.output(root, new FileOutputStream(new File(path)));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void initiateWriter() {
        if(writer == null){
            writer = new XMLOutputter();
            writer.setFormat(Format.getPrettyFormat());
        }
        
    }
}
