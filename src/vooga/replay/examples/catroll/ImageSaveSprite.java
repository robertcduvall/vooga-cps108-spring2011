package vooga.replay.examples.catroll;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import vooga.replay.ISaveImage;

import com.golden.gamedev.object.Sprite;



@SuppressWarnings("serial")
public class ImageSaveSprite extends Sprite implements ISaveImage{
	private String imageFile;
	
	public ImageSaveSprite(String fileName){
		super();
		this.imageFile = fileName;
	}
	public ImageSaveSprite(BufferedImage image, String fileName){
		super(image);
		this.imageFile = fileName;
		saveImage();
	}
	public ImageSaveSprite(BufferedImage image, double x, double y, String fileName){
		super(image,x,y);
		this.imageFile = fileName;
		saveImage();
	}
	public ImageSaveSprite(double x, double y, String fileName){
		super(x,y);
		this.imageFile = fileName;
	}
	@Override
	public void setImage(BufferedImage image){
		super.setImage(image);
		saveImage();
	}
	@Override
	public void saveImage() {
		File outputFile = new File(this.imageFile);
		System.out.println(imageFile);
		try {
			ImageIO.write(this.getImage(), "png", outputFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public String getImageFileName() {
		return imageFile;
	}
	
}
