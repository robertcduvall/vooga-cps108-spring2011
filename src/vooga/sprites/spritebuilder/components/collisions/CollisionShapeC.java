package vooga.sprites.spritebuilder.components.collisions;


import vooga.collisions.shapes.collisionShapes.ICollisionShape;
import vooga.sprites.improvedsprites.Sprite;
import vooga.sprites.improvedsprites.interfaces.IRotation;
import vooga.sprites.improvedsprites.interfaces.ISpriteUpdater;
import vooga.util.buildable.components.BasicComponent;

public class CollisionShapeC<T extends ICollisionShape> extends BasicComponent implements IRotation, ISpriteUpdater{

	private T myCollisionShape;

	public  T getCollisionShape() {
		return myCollisionShape;
	}


	public void setCollisionShape(T cs) {
		this.myCollisionShape = cs;
	}


	public CollisionShapeC(T cs){
		myCollisionShape = cs;
	}
	
	
	@Override
	protected int compareTo(BasicComponent o) {
		//nobody cares
		return 0;
	}

	@Override
	protected Object[] getFieldValues() {
		return new Object[]{myCollisionShape};
	}

	@Override
	protected void setFieldValues(Object... fields) {
		myCollisionShape = (T) fields[0];
	}


	@Override
	public Double setAngle(double angle) {
		return myCollisionShape.setAngle(angle);
	}


	@Override
	public Double getAngle() {
		return myCollisionShape.getAngle();
	}


	@Override
	public Double rotate(double dAngle) {
		return myCollisionShape.rotate(dAngle);
	}


	@Override
	public void update(Sprite s, long elapsedTime) {
		myCollisionShape.move(s.getX()-s.getOldX(),s.getY()-s.getOldY());
	}

}
