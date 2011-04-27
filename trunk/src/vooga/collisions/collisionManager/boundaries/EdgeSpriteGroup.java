package vooga.collisions.collisionManager.boundaries;

import com.golden.gamedev.object.Background;

import vooga.collisions.shapes.Vertex;
import vooga.collisions.shapes.regularShapes.Polygon;
import vooga.core.VoogaGame;
import vooga.sprites.improvedsprites.Sprite;
import vooga.sprites.spritebuilder.components.collisions.CollisionPolygonC;
import vooga.sprites.spritebuilder.components.collisions.CollisionShapeC;
import vooga.sprites.spritegroups.SpriteGroup;


public class EdgeSpriteGroup extends SpriteGroup<EdgeSprite> {

	public EdgeSpriteGroup(String name, Background b) {
		super(name, createEdgeSprites(b));
	}

	private static EdgeSprite[] createEdgeSprites(Background b) {
		
		
		
		return new EdgeSprite[]{new EdgeSprite(b.getX(), b.getY(), b.getX(), b.getY()+b.getHeight()),
							new EdgeSprite(b.getX(), b.getY()+b.getHeight(), b.getX()+b.getWidth(), b.getY()+b.getHeight()),
							new EdgeSprite(b.getX()+b.getWidth(), b.getY(), b.getX()+b.getWidth(), b.getY()+b.getHeight()),
							new EdgeSprite(b.getX(), b.getY(), b.getX()+b.getWidth(), b.getY())};
		
	}



	
}
