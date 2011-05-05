import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Random;

import sprite.BlockEditSprite;
import gui.util.ScrollingSpriteDisplay;

import com.golden.gamedev.GameEngine;
import com.golden.gamedev.GameObject;
import com.golden.gamedev.gui.TLabel;
import com.golden.gamedev.gui.toolkit.FrameWork;
import com.golden.gamedev.object.Background;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;
import com.golden.gamedev.object.background.ColorBackground;
import com.golden.gamedev.util.FileUtil;

/**
 * 
 */

/**
 * @author Dave
 *
 */
public class LevelEditor extends GameObject {
	FrameWork frame;
	Background background;
	SpriteGroup  BLOCK_GROUP;
	Random randomNumberGenerator;
    Sprite createButton, levelEditorInfo, acceptButton;
    TLabel acceptPopup;
    BlockEditSprite[][] editBlockArray;
    int myCols, myRows;
    boolean created=false;
    ScrollingSpriteDisplay<BlockEditSprite> myPallate;
    Sprite pallateInfo;
    
    int myHealthPaint=0;
	
	/**
	 * Screen that allows a user to create and save custom levels.
	 * @param parent - this object's parental gameEngine.
	 * @param columns - the # of blocks high you want your edit square to be.
	 * @param rows - the # of blocks long you want your edit square to be.
	 */
	public LevelEditor(GameEngine parent, int columns, int rows) {
		super(parent);
		editBlockArray = new BlockEditSprite[columns][rows];
		myCols=columns;
		myRows=rows;
		randomNumberGenerator=new Random();
	}

	@Override
	public void initResources() {

		showCursor();

        frame = new FrameWork(bsInput, getWidth(), getHeight());
        background = new ColorBackground(Color.BLUE, 1024, 768);
    	BLOCK_GROUP=new SpriteGroup("Block Group");
    	BLOCK_GROUP.setBackground(background);
		
		
		
        BufferedImage image = getImage("/resources/images/new/create_my_level_button.gif");
		createButton = new Sprite(image, getWidth()-image.getWidth()-10,getHeight()-image.getHeight()-10);
		
		image = getImage("/resources/images/block_0.png");
		for (int i=0;i<myCols;i++){
			for (int j=0;j<myRows;j++){
				BlockEditSprite s = new BlockEditSprite(image, image.getWidth()*j+3*j+30,
    					(image.getHeight()*i+3*i)+50,this);
				editBlockArray[i][j]=s;
				BLOCK_GROUP.add(s);
			}
		}

		myPallate = new ScrollingSpriteDisplay<BlockEditSprite>(30, getHeight()-40, 200, 30, 5, this);
		
		for(int i=0;i<13;i++){
			image = getImage("/resources/images/block_0.png");
			BlockEditSprite block = new BlockEditSprite(image, this);
			block.setHealth(i);
			myPallate.addSprite(block);
		}
		image = getImage("/resources/images/pick_a_paint_color.png");
		pallateInfo = new Sprite(image, 33,getHeight()-65);
		
		
	}

	@Override
	public void render(Graphics2D g) {
        // Graphics Engine
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(0, 0, getWidth(), getHeight());
		createButton.render(g);
		BLOCK_GROUP.render(g);
		if(acceptButton!=null){
			acceptButton.render(g);
		}
		frame.render(g);
		
		pallateInfo.render(g);
		myPallate.render(g);

	}

	@Override
	public void update(long elapsedTime) {
		BlockEditSprite b=myPallate.getActiveSprite();
		if(b!=null){
			myHealthPaint=b.getHealth();
		}
		
		
    	if(click()){
    		if(checkPosMouse(createButton, true)&&created==false){
    			//TODO: Create file.
    			int randomIndex = randomNumberGenerator.nextInt( 10000 );
    			randomIndex+=100;
    			File f=new File("./bin/resources/levels/level"+randomIndex+".txt");
    			while (f.exists()){
    				randomIndex = randomNumberGenerator.nextInt( 10000 );
        			randomIndex+=100;
        			f=new File("./bin/resources/levels/level"+randomIndex+".txt");
    			}
    			String[] myLevelAsText=getLevelAsText();
    			
			  FileUtil.fileWrite(myLevelAsText, f);
			  created=true;
				frame.add(new TLabel("Your level has been saved with the code: " +
						randomIndex, 10,10,300,30));
				 BufferedImage image = getImage("/resources/images/new/menu_button.gif");
				acceptButton=new Sprite(image, 350, 0 );
      		}
    		
    		
    		if (acceptButton!=null){
    		if(checkPosMouse(acceptButton, true)){
                parent.nextGameID = 0;
                finish();
      		}
    		}
    		
    		for (int i=0;i<myCols;i++){
    			for (int j=0;j<myRows;j++){
    				if(checkPosMouse(editBlockArray[i][j], true)){
    					editBlockArray[i][j].setHealth(myHealthPaint);
    				}
    			}
    		}
    		
    		myPallate.sendClick(getMouseX(), getMouseY());
    	}
	}
	
	private String[] getLevelAsText(){
		String s = "";
		for (int i=0;i<myCols;i++){
			for (int j=0;j<myRows;j++){
				BlockEditSprite me=editBlockArray[i][j];
					if (j==0) s=s+("[ "+me.getHealth()+" | ");
					else if (j<18) s=s+(me.getHealth()+" | ");
					if (j==18) s=s+(me.getHealth()+" ]\n");
			}
		}
		String[] level=new String[1];
		level[0]=s;
		return level;
	}

}
