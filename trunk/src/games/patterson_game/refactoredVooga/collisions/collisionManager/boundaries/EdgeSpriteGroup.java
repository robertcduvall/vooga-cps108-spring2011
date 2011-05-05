package games.patterson_game.refactoredVooga.collisions.collisionManager.boundaries;

import games.patterson_game.refactoredVooga.collisions.shapes.Vertex;
import games.patterson_game.refactoredVooga.collisions.shapes.regularShapes.Polygon;
import games.patterson_game.refactoredVooga.core.VoogaGame;
import games.patterson_game.refactoredVooga.sprites.improvedsprites.Sprite;
import games.patterson_game.refactoredVooga.sprites.spritebuilder.components.collisions.CollisionPolygonC;
import games.patterson_game.refactoredVooga.sprites.spritebuilder.components.collisions.CollisionShapeC;
import games.patterson_game.refactoredVooga.sprites.spritegroups.SpriteGroup;
import com.golden.gamedev.object.Background;



public class EdgeSpriteGroup extends SpriteGroup<EdgeSprite> {

	public static final int RECTANGLE_LEFT = 0;
	public static final int RECTANGLE_BOTTOM = 1;
	public static final int RECTANGLE_RIGHT= 2;
	public static final int RECTANGLE_TOP = 3;
	
	public EdgeSpriteGroup(String name, Background b) {
		super(name, createEdgeSprites(b));
	}

	private static EdgeSprite[] createEdgeSprites(Background b) {
		
		
		
		return new EdgeSprite[]{new EdgeSprite(b.getX(), b.getY(), b.getX(), b.getY()+b.getHeight(),RECTANGLE_LEFT),
							new EdgeSprite(b.getX(), b.getY()+b.getHeight(), b.getX()+b.getWidth(), b.getY()+b.getHeight(), RECTANGLE_BOTTOM),
							new EdgeSprite(b.getX()+b.getWidth(), b.getY(), b.getX()+b.getWidth(), b.getY()+b.getHeight(), RECTANGLE_RIGHT),
							new EdgeSprite(b.getX(), b.getY(), b.getX()+b.getWidth(), b.getY(),RECTANGLE_TOP)};
		
	}



	
}
