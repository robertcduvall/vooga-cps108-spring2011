package vooga.util.images;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;


/**
 * Implements scaling for BufferedImages.
 * 
 * @author Julian
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


    public static BufferedImage getCenterSection (BufferedImage buf,
                                                  int width,
                                                  int height)
    {

        if (buf.getWidth() >= width && buf.getHeight() >= height) return buf.getSubimage((buf.getWidth() - width) / 2,
                                                                                         (buf.getHeight() - height) / 2,
                                                                                         width,
                                                                                         height);
        return buf;
    }

}
