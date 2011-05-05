package games.blasterMan.sprites;

import games.blasterMan.Skill;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import com.golden.gamedev.util.ImageUtil;

import vooga.core.VoogaGame;
import vooga.core.event.IEventHandler;
import vooga.resources.Direction;
import vooga.sprites.improvedsprites.Sprite;
import vooga.util.buildable.components.IComponent;
import vooga.util.buildable.components.predefined.basic.HealthC;

@SuppressWarnings("serial")
public class PlayerType extends Sprite {
	/*
	 * this represents the side the playerType is facing. 1 for east, -1 for
	 * west.
	 */
	protected int face;
	protected List<Skill> mySkills;
	protected VoogaGame game;
	protected BufferedImage originalImage;
	public PlayerType(VoogaGame game) {
		super();
		this.game = game;
		this.initEvents();
		mySkills = new ArrayList<Skill>();
		face = 1;
		this.addComponents(new HealthC(42.0));
	}

	public PlayerType(VoogaGame game, BufferedImage image, double x, double y,
			IComponent... comps) {
		super(image, x, y, comps);
		this.game = game;
		this.initEvents();
		mySkills = new ArrayList<Skill>();
		originalImage = image;
		face = 1;
	}

	public PlayerType(VoogaGame game, BufferedImage image, double x, double y) {
		super(image, x, y);
		this.game = game;
		this.initEvents();
		mySkills = new ArrayList<Skill>();
		originalImage = image;
		face = 1;
		this.addComponents(new HealthC(42.0));
	}

	public PlayerType(VoogaGame game, BufferedImage image) {
		super(image);
		this.game = game;
		this.initEvents();
		mySkills = new ArrayList<Skill>();
		originalImage = image;
		face = 1;
		this.addComponents(new HealthC(42.0));
	}

	public PlayerType(VoogaGame game, double x, double y) {
		super(x, y);
		this.game = game;
		this.initEvents();
		mySkills = new ArrayList<Skill>();
		face = 1;
		this.addComponents(new HealthC(42.0));
	}

	public void toggleDirection() {
		face *= -1;
		if(face == -1)
			setImage(ImageUtil.rotate(ImageUtil.flip(originalImage), 180));
		else
			setImage(originalImage);
	}

	public int getDirection() {
		return face;
	}

	public void useSkill(Skill skill) {
		for (Skill sk : mySkills) {
			if (sk.equals(skill)) {
				sk.invoke(this);
				break;
			}
		}
	}

	public void damage() {
		getComponent(HealthC.class).decrease(3.0);
		if (getComponent(HealthC.class).isDead()) {
			game.fireEvent(this, "Game.PlayerDied");
		}
	}

	/*public void updateSkill(Skill skill) {
		for (Skill sk : mySkills) {
			if (sk.equals(skill)) {
				sk.upgrade();
				break;
			}
		}
	}*/

	public void learnSkill(Skill skill) {
		mySkills.add(skill);
	}

	public void initEvents() {
		this.game.registerEventHandler("Game.PlayerDied", new IEventHandler() {
			@Override
			public void handleEvent(Object o) {
				System.out.println("You're such a noob, loser");
				game.getLevelManager().getCurrentLevel().clearPlayField();
				game.getLevelManager().loadLevel(game.getLevelManager().getCurrentLevel().getId());
			}
		});
		
		this.game.registerEventHandler("Input.MoveUp", new IEventHandler(){
			@Override
			public void handleEvent(Object o){
				moveVertical(-3.0);
			}
		});
		
		this.game.registerEventHandler("Input.MoveDown", new IEventHandler(){
			@Override
			public void handleEvent(Object o){
				moveVertical(3.0);
			}
		});
		
		this.game.registerEventHandler("Input.MoveLeft", new IEventHandler(){
			@Override
			public void handleEvent(Object o){
				moveHorizontal(-3.0);
				if(face == -1)
					toggleDirection();
			}
		});
		this.game.registerEventHandler("Input.MoveRight", new IEventHandler(){
			@Override
			public void handleEvent(Object o){
				moveHorizontal(3.0);
				if(face == 1)
					toggleDirection();
			}
		});
		this.game.registerEventHandler("Input.Shoot", new IEventHandler(){
			@Override
			public void handleEvent(Object o){
				shoot();
			}
		});
	}
	public void shoot(){
		Sprite fireball = new SkillSprite(game.getImageLoader().getImage("fireball", Direction.WEST),
				this.getX(), this.getY());
		game.getLevelManager().getCurrentLevel().getSpriteGroup("skill").addSprites(fireball);
		fireball.setSpeed(0.5*-face, 0);
	}
	public int getFace(){
		return face;
	}
	public void moveHorizontal(double dx){
		double newX = getX() + dx;
        
        if (0 < newX && newX + getWidth() < game.getWidth())
            moveX(dx);
	}
	public void moveVertical(double dy){
		double newY = getY() + dy;
        
        if (0 < newY && newY + getHeight() < game.getHeight())
            moveY(dy);
	}
}
