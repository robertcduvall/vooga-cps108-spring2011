package vooga.replay;

/**
 * An interface that a sprite will implement to save the file name of its image.
 * 
 * @author Josue
 *
 */
public interface ISaveImage {
	/**
	 * Called when constructing a sprite with an image or when setting the sprites image to another image.
	 * 
	 * @param fileName
	 */
	public abstract void saveImage();
	
	/**
	 * Returns the name of the sprite's image
	 * 
	 * @return String
	 */
	public abstract String getImageFileName();
}
