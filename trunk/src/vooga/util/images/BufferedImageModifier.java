package vooga.util.images;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;


/**
 * Implements scaling for BufferedImages. Can choose to scale via absolute or to keep the ratio.
 * 
 * @author Julian @author Dave
 */
public class BufferedImageModifier
{

    /**
     * Returns a new scaled version of this image. The original image is NOT
     * affected
     * 
     * @param width
     * @param height
     * @return
     */
    public static BufferedImage getScaledInstance (BufferedImage img,
                                                   int width,
                                                   int height)
    {
        // Create new (blank) image of required (scaled) size

        BufferedImage scaledImage =
            new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        // Paint scaled version of image to new image

        Graphics2D graphics2D = scaledImage.createGraphics();
        graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                                    RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        graphics2D.drawImage(img, 0, 0, width, height, null);

        // clean up

        graphics2D.dispose();
        return scaledImage;
    }
    
    
	/**
	 * Resizes a BufferedImage to fit the given Width and Height.
	 * Keeps the ratio of width/height the same so there's no
	 * change in shape.
	 * 
	 * @param maxWidth the maximum width you will allow the image to be
	 * @param maxHeight the maximum height you will allow your image to be
	 */
	public static BufferedImage getRatioScaledInstance(double maxWidth, 
					double maxHeight, BufferedImage img){
		int imgHeight=img.getHeight();
		int imgWidth=img.getWidth();
		double dFactor;

		//Width relatively greater than height, Width=maxWidth, height adjusts
		//by the same factor as width to keep our image looking good.
		if ((imgWidth/maxWidth)>(imgHeight/maxHeight)){
			dFactor=maxWidth/imgWidth;
			imgWidth=(int) maxWidth;
			imgHeight=(int) (imgHeight*dFactor);
		}
		//Height relatively greater than width, Height=maxHeight, width adjusts
		//by the same factor as width to keep our image looking good.
		else if ((imgHeight/maxHeight)>(imgWidth/maxWidth)){
			dFactor=maxHeight/imgHeight;
			imgWidth=(int) (imgWidth*dFactor);
			imgHeight=(int) maxHeight;
		}
		//There is no difference. Set both to max.
		else {
			imgWidth= (int) maxHeight;
			imgHeight= (int) maxWidth;
		}
		//Makes new BufferedImage, of type "ARGB" where A is alpha, so list
		//displays transparency correctly
		BufferedImage resizedImage = new BufferedImage(imgWidth, imgHeight, 
				BufferedImage.TYPE_INT_ARGB);
		Graphics2D graphics = resizedImage.createGraphics();
		graphics.drawImage(img, 0, 0, imgWidth, imgHeight, null);
		graphics.dispose();
		return resizedImage;
	}


    public static BufferedImage getCenterSection (BufferedImage buf,
                                                  int width,
                                                  int height)
    {

        if (buf.getWidth() >= width && buf.getHeight() >= height) 
        	return buf.getSubimage((buf.getWidth() - width) / 2,
                                   (buf.getHeight() - height) / 2,
                                   width,
                                   height);
        return buf;
    }

}
