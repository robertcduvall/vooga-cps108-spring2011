package vooga.arcade.gui.drawTools;
/** @author Conrad Haynes
*/
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import vooga.arcade.gui.helper.AffineImage;
import vooga.arcade.gui.helper.AffineShape;

public class Screen extends Display implements IDrawable{

	public static final String DEFAULT_NAME = "Screen";
	
	public Screen() {
		super();
	}

	/**
	 * This class paint the background, shapes, and images for the given display
	 */
	public void painter(Graphics2D pen, Display display) {
		pen.drawImage(display.getBackgroundImage(), 0, 0, display.mySize.width, display.mySize.height,null);

		pen.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

		for(AffineShape shape : display.myShapes){
	
			AffineTransform tempTransform = mappingToScreen(shape.getTransform());
			Shape tempShape = shape.getShape();
			Shape finalShape = tempTransform.createTransformedShape(tempShape);
			pen.setPaint(shape.getColor());
			pen.draw(finalShape);
			
		}
		
		for (AffineImage image : display.myImages){
			AffineTransform tempTransform2 = mappingToScreen(image.getTransform());
			Image tempImage = image.getImage();
			tempTransform2.scale(.5, .5);
			tempTransform2.rotate(-1*Math.PI/2); //To Straighten Image Accordingly
			pen.drawImage(tempImage, tempTransform2, null);
		}
	}
}