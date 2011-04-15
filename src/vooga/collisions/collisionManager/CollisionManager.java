package vooga.collisions.collisionManager;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;


import vooga.sprites.spritegroups.SpriteGroup;

public abstract class CollisionManager 
{

	protected ArrayList<SpriteGroup> myGroups;
	private boolean active = true;

	public CollisionManager()
	{
	    myGroups = new ArrayList<SpriteGroup>();
	}

	public void setCollisionGroup(SpriteGroup ... spriteGroups)
	{
		myGroups = new ArrayList<SpriteGroup>(Arrays.asList(spriteGroups));
	}

	public void addGroups(SpriteGroup ... spriteGroups){
	    myGroups.addAll(Arrays.asList(spriteGroups));
	}
	
	
	public SpriteGroup getGroupByIndex(int index)
	{
		return myGroups.get(index);
	}
	
	public SpriteGroup getGroupByName(String name)
    {
	    for (SpriteGroup sg : myGroups){
	        if (sg.getName().equals(name))
	            return sg;
	    }
        return null;
    }
	
	public void removeGroupByIndex(int index)
    {
        myGroups.remove(index);
    }
    
    public void removeGroupByName(String name)
    {
        for (SpriteGroup sg : myGroups){
            if (sg.getName().equals(name))
                myGroups.remove(sg);
        }
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
