package vooga.collisions.collisionManager;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;

import com.golden.gamedev.object.SpriteGroup;
import com.golden.gamedev.object.collision.CollisionRect;

import vooga.sprites.spritegroups.BasicSpriteGroup;

public abstract class CollisionManager 
{

	private ArrayList<SpriteGroup> groups;
	private boolean active = true;

	public CollisionManager()
	{
		//empty
	}

	public void setCollisionGroup(SpriteGroup ... spriteGroups)
	{
		groups.clear();
		groups.addAll(Arrays.asList(spriteGroups));
	}

	public SpriteGroup getGroup(int index)
	{
		return groups.get(index);
	}

	public abstract void checkCollision();

	public boolean isActive()
	{
		return this.active;
	}

	public void setActive(boolean b)
	{
		this.active = b;
	}

	// //////////////optimization///////////
	private final static CollisionRect iRect = new CollisionRect();

	/**
	 * Returns true whether <code>image1</code> at <code>x1</code>,
	 * <code>y1</code> collided with <code>image2</code> at <code>x2</code>,
	 * <code>y2</code>.
	 */
	public static boolean isPixelCollide(double x1, double y1, BufferedImage image1, double x2, double y2, BufferedImage image2) {
		// initialization
		double width1 = x1 + image1.getWidth() - 1, height1 = y1
		+ image1.getHeight() - 1, width2 = x2 + image2.getWidth() - 1, height2 = y2
		+ image2.getHeight() - 1;

		int xstart = (int) Math.max(x1, x2), ystart = (int) Math.max(y1, y2), xend = (int) Math
		.min(width1, width2), yend = (int) Math.min(height1, height2);

		// intersection rect
		int toty = Math.abs(yend - ystart);
		int totx = Math.abs(xend - xstart);

		for (int y = 1; y < toty - 1; y++) {
			int ny = Math.abs(ystart - (int) y1) + y;
			int ny1 = Math.abs(ystart - (int) y2) + y;

			for (int x = 1; x < totx - 1; x++) {
				int nx = Math.abs(xstart - (int) x1) + x;
				int nx1 = Math.abs(xstart - (int) x2) + x;
				try {
					if (((image1.getRGB(nx, ny) & 0xFF000000) != 0x00)
							&& ((image2.getRGB(nx1, ny1) & 0xFF000000) != 0x00)) {
						// collide!!
						return true;
					}
				}
				catch (Exception e) {
					// System.out.println("s1 = "+nx+","+ny+" - s2 =
					// "+nx1+","+ny1);
				}
			}
		}

		return false;
	}
	
	
}
