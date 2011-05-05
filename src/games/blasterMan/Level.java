package games.blasterMan;

import games.blasterMan.sprites.Monster;
import games.blasterMan.sprites.PlayerType;

import java.util.Collection;
import java.util.Random;

import com.golden.gamedev.util.ImageUtil;

import vooga.core.VoogaGame;
import vooga.core.event.IEventHandler;
import vooga.levels.AbstractLevel;
import vooga.resources.Direction;
import vooga.sprites.improvedsprites.Sprite;
import vooga.sprites.spritegroups.SpriteGroup;
/**
 * Level class for blasterman.
 * 
 * @author Josue
 *
 */
public class Level extends AbstractLevel{
	private PlayerType player;
	private VoogaGame game;
	public Level(Collection<SpriteGroup<Sprite>> players, VoogaGame game) {
		super(players, game);
		this.game = game;
		initEvents();
	}
	@Override
    public void loadLevel ()
    {
		player = new PlayerType(game, game.getImageLoader().getImage("blasterman", Direction.WEST));
		player.setLocation(300, 200);
        this.getSpriteGroup("blasterman").addSprites(player);
        addBackground();
    }
	public void initEvents(){
		game.addPeriodicTimer("spawn", 2000 + (this.getId()*500), "Method.Spawn", game);
		game.registerEventHandler("Method.Spawn", new IEventHandler(){
			@Override
			public void handleEvent(Object o){
				spawn((VoogaGame)o);
			}
		});
	}
	public void spawn (VoogaGame game){
		Random gen = new Random();
		boolean direction = (gen.nextBoolean());
		int x = direction ? 0 : game.getWidth();
		int y = gen.nextInt(399);
		Sprite slime = new Monster(
				game, game.getImageLoader().getImage("slime", Direction.WEST), x, y);
		slime.setSpeed(direction ? 0.2 : -0.2, 0);
		if (!direction){
			slime.setImage(ImageUtil.flip(ImageUtil.rotate(game.getImageLoader().getImage("slime"), -90)));
		}
		this.getSpriteGroup("slime").addSprites(slime);
		slime.addComponent(new Targetting(player));
	}
}