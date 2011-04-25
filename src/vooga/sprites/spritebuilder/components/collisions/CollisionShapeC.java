package vooga.sprites.spritebuilder.components.collisions;


import vooga.collisions.shapes.collisionShapes.ICollisionShape;
import vooga.util.buildable.components.BasicComponent;

public class CollisionShapeC<T extends ICollisionShape> extends BasicComponent {

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

}
