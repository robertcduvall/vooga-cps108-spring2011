package vooga.sprites.spritebuilder.components.collisions;


import vooga.collisions.shapes.collisionShapes.ICollisionShape;
import vooga.util.buildable.components.BasicComponent;

public class CollisionShapeC extends BasicComponent {

	private ICollisionShape myCollisionShape;

	public ICollisionShape getCollisionShape() {
		return myCollisionShape;
	}


	public void setCollisionShape(ICollisionShape cs) {
		this.myCollisionShape = cs;
	}


	public CollisionShapeC(ICollisionShape cs){
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
		myCollisionShape = (ICollisionShape) fields[0];
	}

}
