package games.lolcats.src.Helper;

import java.awt.Color;
import java.awt.image.BufferedImage;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.BasicCollisionGroup;
import com.golden.gamedev.object.collision.CollisionShape;

// http://www.goldenstudios.or.id/forum/archive/index.php/thread-2190.html
public abstract class PixelPerfectCollision extends BasicCollisionGroup {
	{
		pixelPerfectCollision = true;
	}

	@Override
	public boolean isCollide(Sprite s1, Sprite s2, CollisionShape shape1,
			CollisionShape shape2) {
		if (!pixelPerfectCollision)
			return shape1.intersects(shape2);
		if (shape1.intersects(shape2))
			return isPixelCollide(s1.getX(), s1.getY(), s1.getImage(), s2
					.getX(), s2.getY(), s2.getImage());
		return false;
	}

	public static boolean isPixelCollide(double x1, double y1,
			BufferedImage img1, double x2, double y2, BufferedImage img2) {
		int offX1 = x1 > x2 ? 0 : (int) x1 - (int) x2;
		int offX2 = x2 > x1 ? 0 : (int) x2 - (int) x1;
		int offY1 = y1 > y2 ? 0 : (int) y1 - (int) y2;
		int offY2 = y2 > y1 ? 0 : (int) y2 - (int) y1;
		for (int i = 0; i - offX1 < img1.getWidth()
				&& i - offX2 < img2.getWidth(); i++) {
			for (int j = 0; j - offY1 < img1.getHeight()
					&& j - offY2 < img2.getHeight(); j++) {
				Color imgColor1 = new Color(img1.getRGB(i - offX1, j - offY1));
				Color imgColor2 = new Color(img2.getRGB(i - offX2, j - offY2));
				if (!Color.BLACK.equals(imgColor1)
						&& !Color.BLACK.equals(imgColor2)) {
					return true;
				}
			}
		}
		return false;
	}
}
