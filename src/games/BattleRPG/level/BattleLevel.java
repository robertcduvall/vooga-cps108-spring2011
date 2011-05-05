package games.BattleRPG.level;

import games.BattleRPG.sprite.StatSprite;
import games.BattleRPG.util.BattleGameHandler;
import games.BattleRPG.util.ResourceManager;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

import vooga.core.VoogaGame;
import vooga.core.event.IEventHandler;
import vooga.levels.AbstractLevel;
import vooga.resources.Direction;
import vooga.sprites.improvedsprites.Sprite;
import vooga.sprites.spritegroups.SpriteGroup;

public class BattleLevel extends AbstractLevel
{
	private VoogaGame myGame;
	private ResourceManager resources;
	private Random rand;
	
	private int selectedEnemy;
	private int selectedMenuOption;
	private Sprite cursorSprite;
	private List<StatSprite> enemySprites;
	private StatSprite myPlayer;
	public boolean isDead;
	boolean cursorInMenu;
	
	public BattleLevel(Collection<SpriteGroup<Sprite>> players, VoogaGame game)
	{
		super(players, game);
		myGame = game;
		rand = new Random();
		enemySprites = new ArrayList<StatSprite>();
	}

	@Override
	public void loadLevel()
	{
		this.addAllSpritesFromPool();
		resources = ResourceManager.getInstance();
		resources.addResourcesFromFile("battle","games.BattleRPG.resources");
		myPlayer = BattleGameHandler.myPlayer;
		
		addBackground();
		addRandomEnemies();
		addCharacters();
		addSpriteHitPoints();
		addCursor();
		
		loadEventHandlers();
	}
	private void addRandomEnemies()
	{	
		int enemiesToSpawn = 3 - (int)Math.floor(Math.sqrt(rand.nextInt(9)));
		int[] enemyRoll = resources.getIntegerArray("EnemiesLevel"+getId());
		for(int i=0; i<enemiesToSpawn; i++)
		{
			int enemyNumber = enemyRoll[rand.nextInt(enemyRoll.length)];
			BufferedImage enemyImage = myGame.getImageLoader().getImage("enemy"+enemyNumber,Direction.WEST);
			StatSprite randomEnemy = new StatSprite(myGame,enemyImage, 300 + i*40, 180 - i * 80,enemyNumber,(rand.nextInt(enemyNumber+2)+1) * (this.getId()));
			enemySprites.add(randomEnemy);
			this.getSpriteGroup("randomEnemy").addSprites(randomEnemy);
		}
	}
	private void addCharacters()
	{
		this.getSpriteGroup("playerSprite").addSprites(myPlayer);
		myPlayer.setX(40);
		myPlayer.setY(130);
	}
	private void addCursor()
	{
		cursorInMenu = true;
		selectedMenuOption = 0;
		selectedEnemy = 0;
		BufferedImage cursorImage = myGame.getImageLoader().getImage("cursor",Direction.WEST);
		cursorSprite = new Sprite(cursorImage, 30, 310);
		this.getSpriteGroup("cursorSprite").addSprites(cursorSprite);
	}
	
	private void moveArrow(int right, int down)
	{
		//Menu is hard-coded for simplicity
		if(cursorInMenu)	//if it is in menu
		{
			selectedMenuOption = (selectedMenuOption + right + 2*down) %4;
			switch(selectedMenuOption){
			case 0: cursorSprite.setX(30); cursorSprite.setY(310); break;
			case 1: cursorSprite.setX(280); cursorSprite.setY(310); break;
			case 2: cursorSprite.setX(30); cursorSprite.setY(360); break;
			case 3: cursorSprite.setX(280); cursorSprite.setY(360);
			}
		}
		else	//cursor not in menu
		{
			selectedEnemy = (selectedEnemy - down+enemySprites.size()) % enemySprites.size();
			double enemyX = enemySprites.get(selectedEnemy).getX();
			double enemyY = enemySprites.get(selectedEnemy).getY();
			cursorSprite.setX(enemyX-40); cursorSprite.setY(enemyY+20);
		}
	}
	private void enterPressed()
	{
		if(!cursorInMenu)
			executeMove();
		if(selectedMenuOption==3)
			escape();
		selectedEnemy = 0;
		cursorInMenu = !cursorInMenu;	//toggle cursor option
		moveArrow(0,0);	//no arrow movement by default
	}
	private void executeMove()
	{
		if(selectedMenuOption == 0)
			engageCombat();
	}

	private void loadEventHandlers()
	{
		myGame.registerEventHandler("arrowRight", new IEventHandler(){
			@Override
			public void handleEvent(Object o) {
				moveArrow(1,0);
			}
        });
		myGame.registerEventHandler("arrowDown",new IEventHandler(){

			@Override
			public void handleEvent(Object o) {
				moveArrow(0,1);
			}
		});
		myGame.registerEventHandler("arrowLeft",new IEventHandler(){

			@Override
			public void handleEvent(Object o) {
				moveArrow(-1,0);
			}
		});
		myGame.registerEventHandler("arrowUp",new IEventHandler(){

			@Override
			public void handleEvent(Object o) {
				moveArrow(0,-1);
			}
		});
		myGame.registerEventHandler("enterButton",new IEventHandler(){

			@Override
			public void handleEvent(Object o) {
				enterPressed();
			}
		});
	}
	private void engageCombat()
	{
		myPlayer.engageSprite(enemySprites.get(selectedEnemy));
		for(int i=0; i<enemySprites.size(); i++)
			if(i != selectedEnemy)
				enemySprites.get(i).attackSprite(myPlayer);
		if(!enemySprites.get(selectedEnemy).alive)
		{
			enemySprites.get(selectedEnemy).setActive(false);
			this.getSpriteGroup("randomEnemy").removeInactiveSprites();
			enemySprites.remove(selectedEnemy);
		}
		selectedEnemy = 0;
		if(enemySprites.size() == 0)
		{
			System.out.println(resources.getString("VictoryMessage"));
			escape();
		}
		if(!myPlayer.alive)
		{
			System.out.println(resources.getString("DeathMessage"));
			//exit to arcade
			escape();
		}
		removeSpriteGroup(getSpriteGroup("numberSprite"));
		addSpriteHitPoints();
	}
	public void addSpriteHitPoints()
	{	
		for(StatSprite enemy : enemySprites)
			addHitPointsForSprite(enemy);
		addHitPointsForSprite(myPlayer);
	}
	public void addHitPointsForSprite(StatSprite enemy)
	{
		String hpString = (enemy.curHP+"");
		
		for(int i=hpString.length()-1; i>=0; i--)
		{
			String digit = hpString.charAt(i)+"";
			BufferedImage numberImage = myGame.getImageLoader().getImage(digit,Direction.WEST);
			Sprite numberSprite = new Sprite(numberImage, enemy.getX()-15*(-i), enemy.getY()-15);
			this.getSpriteGroup("numberSprite").addSprites(numberSprite);
		}
	}
	private void escape()
	{
		myGame.getLevelManager().loadLevel(0);	//return to default
	}

}
