package games.keen.sprites;

import games.keen.KeenGame;

import java.awt.Point;
import java.awt.geom.Point2D;

import vooga.collisions.shapes.Vertex;
import vooga.collisions.shapes.collisionShapes.CollisionQuadrilateral;
import vooga.core.VoogaGame;
import vooga.core.event.IEventHandler;
import vooga.resources.Direction;
import vooga.sprites.spritebuilder.components.collisions.CollisionShapeC;
import vooga.sprites.spritebuilder.components.physics.MasslessPhysicsC;
import vooga.sprites.spritebuilder.components.physics.PhysicsVelocityC;

@SuppressWarnings("serial")
public class Keen extends MonsterSprite {
	private VoogaGame game;
	private boolean hasMoved;
	private Direction currentDirection;
	private boolean canJump;
	
	private static final double KEEN_SPEED = 1.5;
	private static final double KEEN_JUMP_VELOCITY = -0.3;
	private static final int ZAP_MARGIN = 15;
	
	public Keen(KeenGame game, int x, int y) {
		super(game, "keen", x, y);
		
		hasMoved = false;
		canJump = false;
		currentDirection = Direction.EAST;
		this.game = game;
		bindKeys();
		super.setAnimation(0, currentDirection);
		int height = super.getImages()[0].getHeight();
//		super.addComponent(new CollisionShapeC<CollisionQuadrilateral>(
//				new CollisionQuadrilateral(	new Vertex(0,0), new Vertex(0, 32),
//											new Vertex(height, 0), new Vertex(height, 32))));
	}
	
	private void bindKeys() {
		game.registerEventHandler("Input.User.MoveLeft", new IEventHandler() {
			@Override
			public void handleEvent(Object o) {
				move(Direction.WEST);
			}
		});
		game.registerEventHandler("Input.User.MoveRight", new IEventHandler() {
			@Override
			public void handleEvent(Object o) {
				move(Direction.EAST);
			}
		});
		game.registerEventHandler("Input.User.Jump", new IEventHandler() {
			@Override
			public void handleEvent(Object o) {
				jump();
			}
		});
		game.registerEventHandler("Input.User.Shoot", new IEventHandler() {
			@Override
			public void handleEvent(Object o) {
				shoot();
			}
		});
	}
	
	private void shoot() {
		super.setAnimation(3, currentDirection);
		int x = (int) (super.getCenterX()+
				(currentDirection == Direction.EAST ? 1 : -1)*
					(super.getWidth()/2+ZAP_MARGIN));
		int y = 4*(int)super.getCenterY()/3;
		Zap zap = (Zap) game.getLevelManager().addArchetypeSprite("zap", x, y);
		zap.setDirection(currentDirection);
	}
	
	private void jump() {
		if(!canJump) return;
		super.setAnimation(2, currentDirection);
		super.setSpeed(0, KEEN_JUMP_VELOCITY);
		canJump = false;
	}
	
	private void move(Direction d) {
		hasMoved = true;
		
		currentDirection = d;
		if(canJump) {
			super.setAnimation(1, currentDirection);
		}
		
		if(currentDirection.equals(Direction.EAST)) {
			super.moveX(KEEN_SPEED);
		}
		else if(currentDirection.equals(Direction.WEST)) {
			super.moveX(-KEEN_SPEED);
		}
	}
	
	@Override
	public void update(long elapsedTime) {
		if(!hasMoved && canJump) super.setAnimation(0, currentDirection);
		if(!canJump) super.setAnimation(2, currentDirection);
		hasMoved = false;
		super.update(elapsedTime);
	}
	
	@Override
	public void handleCollision() {
		if(!canJump) {
			super.setAnimation(0, currentDirection);
		}
		canJump = true;
		super.handleCollision();
	}
}
