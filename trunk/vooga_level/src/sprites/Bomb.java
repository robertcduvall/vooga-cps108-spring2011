package sprites;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.*;

import com.golden.gamedev.object.*;

public class Bomb extends Sprite {

	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 150;
	public static final int VALUE = 10;
	
	private String question, answer;
	private GameFont font;
	private BufferedImage[] animation;

	public Bomb() {
		
	}
	
	public Bomb(GameFont font, BufferedImage image, BufferedImage[] animation,
			double x, double y, String[] QA) {
		super(image, x, y);
		this.question = QA[0];
		this.answer = QA[1];
		this.font = font;
		this.animation = animation;
	}

	public void update(long elapsedTime) {
		super.update(elapsedTime);
	}

	public void render(Graphics2D g, int x, int y) {
		super.render(g, x, y);
		showQuestion(g, x, y);
	}

	private void showQuestion(Graphics2D g, int x, int y) {
		g.setColor(Color.WHITE);
		font.drawString(g, question, GameFont.CENTER, (int) getX() - 20,
				(int) getY() + 60, 128);
	}
	
	public BufferedImage[] getAnimation() {
		return animation;
	}
	
	public String getAnswer() {
		return answer;
	}
	
	public int getValue() {
		return Bomb.VALUE;
	}
}
