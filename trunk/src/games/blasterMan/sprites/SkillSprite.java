package games.blasterMan.sprites;

import games.blasterMan.Skill;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import vooga.sprites.improvedsprites.Sprite;
import vooga.util.buildable.components.IComponent;

@SuppressWarnings("serial")
public class SkillSprite extends Sprite{
	public SkillSprite() {
		super();
	}

	public SkillSprite(BufferedImage image, double x, double y,
			IComponent... comps) {
		super(image, x, y, comps);
	}

	public SkillSprite(BufferedImage image, double x, double y) {
		super(image, x, y);
	}

	public SkillSprite(BufferedImage image) {
		super(image);
	}

	public SkillSprite(double x, double y) {
		super(x, y);
	}
}
