package vooga.arcade.parser.gameObject;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import org.jdom.Element;
/**
 * 
 * @author Yong-Hui Goh
 *
 */
public abstract class ArcadeObject
{
	protected Image image;
	protected Element root;
	protected String path;
	
	
	public ArcadeObject(Element root, String path)
	{
	    this.root = root;
	    this.path = path;
	}

	/**
	 * @return the drawableData
	 */
	public Image getImage() {
        if(image!= null) return image;
        
        try {
            image = ImageIO.read(new File(root.getChildText("image")));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }

	public String getName()
	{
		return root.getChildText("name");
	}
}
