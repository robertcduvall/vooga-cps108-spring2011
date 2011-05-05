package vooga.viewTeam.refactored;


public class HorizWrapColissionGroup extends NewColGroup{

	@Override
	public void collidedNew(NewSprite inner, NewSprite outer) {
		checkOutsideLeft(inner,outer);
		checkOutsideRight(inner,outer);
	}

	private void checkOutsideRight(NewSprite inner, NewSprite outer) {
		if (inner.getLeftCoordinate() 
				< outer.getLeftCoordinate()) {
			
			inner.setX(
					outer.getRightCoordinate()
					    - inner.getWidth());
		} 		
	}

	private void checkOutsideLeft(NewSprite inner, NewSprite outer) {
		if (inner.getRightCoordinate()
				> outer.getRightCoordinate()) {
			
			inner.setX(
					outer.getLeftCoordinate());
		}		
	}		



}
