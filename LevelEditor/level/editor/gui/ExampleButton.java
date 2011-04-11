package level.editor.gui;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.Transparency;
import java.awt.event.MouseEvent;
import java.awt.geom.GeneralPath;

import level.editor.example.game.FirstGUIUse;

import com.golden.gamedev.GameObject;
import com.golden.gamedev.gui.TButton;
import com.golden.gamedev.gui.toolkit.GraphicsUtil;
import com.golden.gamedev.object.Sprite;


public class ExampleButton extends TButton {
	private Sprite mySprite;
	private boolean isselected = false;
	private GameObject myGame;
	public ExampleButton(GameObject game, String arg0, int arg1, int arg2, int arg3, int arg4) {
		super(arg0, arg1, arg2, arg3, arg4);
		customRendering = true;
		myGame = game;
		this.myGame=game;
		mySprite = new Sprite(game.getImage("Images/space_ship.png"));

	}
	
	public void update(){
		mySprite.update(10);
		if(isMousePressed()){
			Sprite add = new Sprite(mySprite.getImage());
			add.setBackground(((FirstGUIUse) myGame).getBackground());
			((FirstGUIUse) myGame).getSpriteGroup().add(mySprite);
			isselected = true;
		}
		if (isselected){
			mySprite.setLocation(myGame.getMouseX(), myGame.getMouseY());
			if(myGame.bsInput.isMousePressed(MouseEvent.BUTTON1)){
				isselected = false;
			}
		}
	}
	protected void createCustomUI(int w, int h) {
		// creates 2 ui images -> for normal and pressed state
		ui = GraphicsUtil.createImage(2, w, h, Transparency.BITMASK);

		// draw the button, hexagonal shape
		GeneralPath path = new GeneralPath();
		path.moveTo(1, 1);
		path.lineTo(w, 1);
		path.lineTo(w,h);
		path.lineTo(1, h);
		path.closePath();

		// fill with gradient color
		Color[] col1 = new Color[] { new Color(204,204,255), Color.LIGHT_GRAY },
				col2 = new Color[] { new Color(102,102,153), Color.GRAY };

		for (int i=0;i < ui.length;i++) {
			Graphics2D g = ui[i].createGraphics();
			g.setPaint(new GradientPaint(0, 0, col1[i],
										 w, h, col2[i], false));
			g.fill(path);
			g.dispose();
		}
	}
	
	@Override
	protected void renderCustomUI(Graphics2D g, int x, int y,
			int w, int h) {
		g.drawImage((isMousePressed()) ? ui[1] : ui[0], x, y, null);
		mySprite.render(g, x + (w/2)-(mySprite.getWidth()/2),
				   y + (h/3)-(mySprite.getHeight()/2));
		
	}

}
