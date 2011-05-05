package games.patterson_game.refactoredVooga.replay;

import java.awt.image.BufferedImage;
import java.io.Serializable;

@SuppressWarnings("serial")
public class BufferedImageSerialData implements Serializable {

	int width;
	int height;
	int[] pixels;

	public BufferedImageSerialData(BufferedImage bi) {
		width = bi.getWidth();
		height = bi.getHeight();
		pixels = new int[width * height];
		bi.getRGB(0, 0, width, height, pixels, 0, width);
	}

	public BufferedImage getImage() {
		BufferedImage bi = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_ARGB); 
		bi.setRGB(0, 0, width, height, pixels, 0, width);
		return bi;
	}
	
}

