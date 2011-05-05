package games.keen.sprites;

import games.keen.KeenGame;

import java.awt.image.BufferedImage;

import vooga.sprites.improvedsprites.Sprite;

@SuppressWarnings("serial")
public class StaticTile extends LevelSprite {
	public StaticTile(BufferedImage image, KeenGame game, int x, int y) {
		super(game, x, y);
		BufferedImage[] images = new BufferedImage[1];
		images[0] = image;
		super.setImages(images);
	}
}
