package vooga.collisions.collisionManager;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;


import vooga.sprites.improvedsprites.Sprite;
import vooga.sprites.spritegroups.SpriteGroup;

public abstract class CollisionManager<T extends Sprite, S extends Sprite>
{

	protected SpriteGroup<T> Group1;
	protected SpriteGroup<S> Group2;
	private boolean active = true;

	public CollisionManager(){
		
	}
	
	
	public CollisionManager(SpriteGroup<T> s1, SpriteGroup<S> s2)
	{
		Group1 = s1;
		Group2 = s2;
	}

	public void setSpriteGroup(SpriteGroup<?> sg)
	{
		if (sg.getClass().isInstance(Group1.getClass()))
			Group1 = (SpriteGroup<T>) sg;
		else if (sg.getClass().isInstance(Group1.getClass()))
			Group2 = (SpriteGroup<S>) sg;
	}
	
	
	/**
	 * Associates specified sprite groups to this manager. The groups will be
	 * checked its collision one against another.
	 * 
	 * @see #checkCollision()
	 */
	public void setCollisionGroup(SpriteGroup<T> group1, SpriteGroup<S> group2) {
		this.Group1 = group1;
		this.Group2 = group2;
	}
	
	/**
	 * Returns the first group associated with this collision manager.
	 */
	public SpriteGroup<T> getGroup1() {
		return this.Group1;
	}
	
	/**
	 * Returns the second group associated with this collision manager.
	 */
	public SpriteGroup<S> getGroup2() {
		return this.Group2;
	}
	
	/** ************************************************************************* */
	/** ************************** COLLISION CHECK ****************************** */
	/** ************************************************************************* */
	
	/**
	 * Checks for collision between all members in
	 * {@linkplain #getGroup1() group 1} againts all members in
	 * {@linkplain #getGroup1() group 2}.
	 */
	public abstract void checkCollision();
	
	/** ************************************************************************* */
	/** ************************** ACTIVE STATE ********************************* */
	/** ************************************************************************* */
	
	/**
	 * Returns true, if this collision manager is active. Inactive collision
	 * manager won't perform any collision check.
	 * 
	 * @see #setActive(boolean)
	 */
	public boolean isActive() {
		return this.active;
	}
	
	/**
	 * Sets the active state of this collision manager. Inactive collision
	 * manager won't perform any collision check.
	 * 
	 * @see #isActive()
	 */
	public void setActive(boolean b) {
		this.active = b;
	}

	
	
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

	public void clearGroupByName(String name) {
		if (Group1.getName().equals(name)) Group1.clear();
		else if(Group2.getName().equals(name)) Group2.clear();
	}


	public void setSpriteGroup1(SpriteGroup<T> sg) {
		Group1 = sg;
		
	}
	
	public void setSpriteGroup2(SpriteGroup<S> sg) {
		Group2 = sg;
		
	}
}
