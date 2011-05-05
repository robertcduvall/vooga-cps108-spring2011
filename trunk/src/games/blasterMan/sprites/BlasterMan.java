package games.blasterMan.sprites;

import games.blasterMan.Skill;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import vooga.core.VoogaGame;
import vooga.sprites.improvedsprites.Sprite;
import vooga.util.buildable.components.IComponent;

@SuppressWarnings("serial")
public class BlasterMan extends PlayerType{

	public BlasterMan(VoogaGame game) {
		super(game);
		mySkills = new ArrayList<Skill>();
	}

	public BlasterMan(VoogaGame game, BufferedImage image, double x, double y, IComponent... comps) {
		super(game, image, x, y, comps);
		mySkills = new ArrayList<Skill>();
	}

	public BlasterMan(VoogaGame game,BufferedImage image, double x, double y) {
		super(game, image, x, y);
		mySkills = new ArrayList<Skill>();
	}

	public BlasterMan(VoogaGame game, BufferedImage image) {
		super(game, image);
		mySkills = new ArrayList<Skill>();
	}

	public BlasterMan(VoogaGame game, double x, double y) {
		super(game, x, y);
		mySkills = new ArrayList<Skill>();
	}
	
}
