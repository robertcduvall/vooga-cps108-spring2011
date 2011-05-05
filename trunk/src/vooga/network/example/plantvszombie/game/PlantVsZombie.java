package vooga.network.example.plantvszombie.game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import vooga.reflection.Reflection;
import vooga.core.VoogaGame;
import vooga.network.example.plantvszombie.collisions.EnemyPlantCollision;
import vooga.network.example.plantvszombie.collisions.ProjectileEnemyCollision;

import com.golden.gamedev.Game;
import com.golden.gamedev.GameLoader;
import com.golden.gamedev.object.Background;
import com.golden.gamedev.object.GameFont;
import com.golden.gamedev.object.PlayField;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;
import com.golden.gamedev.object.background.ColorBackground;
import com.golden.gamedev.object.font.SystemFont;



/**
 * @author Roman
 * Trying reproduce the famous game Plant vs Zombies, all resources used here come from the game.
 */
public class PlantVsZombie extends VoogaGame {
	private final String resource = "vooga.network.example.plantvszombie.resources.sunCost";

	private final String loseScript = "Zombies ate your brain.";
	private final String endScript = "Congratulations! You win!";
	private final String transitionScript = "Press enter to start next level";
	private final String restartScript = "Press enter to restart";
	private final String startScript = "Press enter to start";
	private final String fontType = "Verdana";
	private final String nextLevel = "vooga/network/example/plantvszombie/resources/nextLevel.png";
	private final String infoString = "Sun: %d Level: %d Time remain: %d";
	
	private PlayField playfield;
	private Background background;
    public static Dimension gameDimension = new Dimension(800, 700);
    
    private static final int ROWS = 5;
    private static final int COLUMNS = 8;
    private static final int SIZE = 100;
    private static final int OFFSET = 50;
    private Point[][] grids = new Point[ROWS][COLUMNS];
    public boolean[][] occupiedGrid = new boolean[ROWS][COLUMNS];
    
    public SpriteGroup[] PLANT_GROUP;
    public SpriteGroup[] PROJECTILE_GROUP;
    public SpriteGroup[] ZOMBIE_GROUP;
    public SpriteGroup CONTROL_GROUP;
    
    private Map<Sprite, String> plantOptions;
    private Map<String, Integer> sunCost;
    private String selectedElement = "vooga.network.example.plantvszombie.plants.PeaShooter";
    
    private GameState gameState;
    
    private int SunRepository;
    private LevelSetup levelSetup;
    private GameFont onScreenOutput;
    private GameFont info;

    public void initResources() {
    	setFPS(50);
    	
    	levelSetup = new LevelSetup(this);
    	
        playfield = new PlayField();
        
        background = new ColorBackground(Color.gray,(int)gameDimension.getWidth(),(int)gameDimension.getHeight());
        playfield.setBackground(background);
        
        // the control buttons
        CONTROL_GROUP = new SpriteGroup("");
        playfield.addGroup(CONTROL_GROUP);
        
        PLANT_GROUP = new SpriteGroup[ROWS];
        PROJECTILE_GROUP = new SpriteGroup[ROWS];
        ZOMBIE_GROUP = new SpriteGroup[ROWS];
        for(int i = 0; i<ROWS; i++){
        	PLANT_GROUP[i] = new SpriteGroup("");
        	PROJECTILE_GROUP[i] = new SpriteGroup("");
        	ZOMBIE_GROUP[i] = new SpriteGroup("");
        	playfield.addGroup(PLANT_GROUP[i]);
        	playfield.addGroup(PROJECTILE_GROUP[i]);
        	playfield.addGroup(ZOMBIE_GROUP[i]);
            playfield.addCollisionGroup(PROJECTILE_GROUP[i], ZOMBIE_GROUP[i], new ProjectileEnemyCollision(this));
            playfield.addCollisionGroup(ZOMBIE_GROUP[i], PLANT_GROUP[i], new EnemyPlantCollision(this));
        }
        
        for(int j=0; j<ROWS; j++)
        	for(int i=0; i<COLUMNS; i++){
        		grids[j][i] = new Point(i*SIZE, j*SIZE+OFFSET);
        		occupiedGrid[j][i] = false;
        	}
        
        plantOptions = new HashMap<Sprite, String>();
        sunCost = new HashMap<String, Integer>();
        
        initializeControl();
    }


    public void update(long elapsedTime) {
    	playfield.update(elapsedTime);
    	switch(gameState){
    	case PLAYING:
    		levelSetup.update(elapsedTime);
			if (click()) {
		        for(int j=0; j<ROWS; j++){
		        	for(int i=0; i<COLUMNS; i++){
		        		Point s = grids[j][i];
						if (checkPosMouse((int) s.getX(), (int) s.getY(),
											  (int) s.getX() + SIZE,
											  (int) s.getY() + SIZE)) {
							if(costSun(selectedElement) && (occupiedGrid[j][i]==false)){
								SunRepository-=sunCost.get(selectedElement);
								Reflection.createInstance(selectedElement, (int)s.getX(), (int)s.getY(), j, i, this);
								occupiedGrid[j][i]=true;
							}
						}
					}
		        }
		        Sprite[] controls = CONTROL_GROUP.getSprites();
		        for(int i=0; i<CONTROL_GROUP.getSize(); i++){
		        	if(checkPosMouse((int)controls[i].getX(),(int)controls[i].getY(),(int)controls[i].getX()+controls[i].getWidth(),(int)controls[i].getY()+controls[i].getHeight())){
		        		selectedElement = plantOptions.get(controls[i]);
		        		System.out.println(selectedElement);
		        	}
		        }
		        if(checkPosMouse(500, 550, 600, 650)){
		        	gameState = GameState.TRANSITION;
		        }
			}
			if(keyPressed(KeyEvent.VK_ESCAPE))
				finish();
			break;
    	case LOSE:
    		if(keyPressed(KeyEvent.VK_ENTER))
    			levelSetup.restartLevel();
    		else if(keyPressed(KeyEvent.VK_ESCAPE))
				finish();
    		break;
    	case TRANSITION:
    		if(keyPressed(KeyEvent.VK_ENTER))
    			levelSetup.nextLevel();
    		break;
    	case END:
    		if(keyPressed(KeyEvent.VK_ENTER))
    			finish();
    		break;
    	case START:
    		if(keyPressed(KeyEvent.VK_ENTER))
    			levelSetup.initializeLevel(1);
    		break;
    	default:
    		break;
    	}
    }
    
    public void gameLose(){
    	gameState = GameState.LOSE;
    	clearStates();
    }

    public void render(Graphics2D g) {
    	playfield.render(g);
    	switch (gameState){
    	case PLAYING:
        	info.drawString(g, String.format(infoString, SunRepository, levelSetup.getLevel(), levelSetup.getRemainingTime()), 20, 20);
    		break;
    	case LOSE:
    		clearStates();
    		onScreenOutput.drawString(g, loseScript, getWidth()/4,  getHeight()/3);
    		onScreenOutput.drawString(g, restartScript, getWidth()/4,  getHeight()/3+60);
    		break;
    	case TRANSITION:
    		clearStates();
    		onScreenOutput.drawString(g, transitionScript, getWidth()/4,  getHeight()/3);
    		break;
    	case END:
    		clearStates();
    		onScreenOutput.drawString(g, endScript, getWidth()/4,  getHeight()/3);
    		break;
    	case START:
    		clearStates();
    		onScreenOutput.drawString(g, startScript, getWidth()/4,  getHeight()/3);
    	default:
    		clearStates();
    		break;
    	}
    }
    
    public static Dimension getGameDimension(){
    	return gameDimension;
    }
    
    public void initializeControl(){
    	ResourceBundle myResources = ResourceBundle.getBundle(resource);
    	for (String key : myResources.keySet()){
    		String[] tmpStr = myResources.getString(key).split(",");
    		sunCost.put(key, Integer.valueOf(tmpStr[0]));
    		Sprite tmpSprite = new Sprite(getImage(tmpStr[1]), Integer.valueOf(tmpStr[2]), Integer.valueOf(tmpStr[3]));
    		plantOptions.put(tmpSprite, key);
    		CONTROL_GROUP.add(tmpSprite);
    	}
    	
    	Sprite tmpSprite = new Sprite(getImage(nextLevel), 500, 550);
    	playfield.add(tmpSprite);
        
        onScreenOutput = new SystemFont(new Font(fontType, Font.BOLD, 26), Color.white);
        info = new SystemFont(new Font(fontType, Font.PLAIN, 14), Color.white);
        
        gameState = GameState.START;
    }
    
    public boolean costSun(String currentOption){
    	if(SunRepository>=sunCost.get(currentOption))	
    		return true;
    	else
    		return false;
    }
    
    public void addSun(int value){
    	SunRepository+=value;
    	if(SunRepository>9999){
    		SunRepository=9999;
    	}
    }
    
    public void setState(GameState state){
    	gameState = state;
    }
    
    public void clearStates(){
    	SunRepository = 0;
        for(int j=0; j<ROWS; j++)
        	for(int i=0; i<COLUMNS; i++)
        		occupiedGrid[j][i] = false;
        	
    	for(int i = 0; i<ROWS; i++){
	    	PLANT_GROUP[i].clear();
	    	ZOMBIE_GROUP[i].clear();
	    	PROJECTILE_GROUP[i].clear();
    	}
    }
    
    public void setSunRepository(int value){
    	SunRepository = value;
    }


	@Override
	public void updatePlayField(long elapsedTime) {
		
	}

    
}