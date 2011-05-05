package vooga.viewTeam.refactored;


public class VertWrapColissionGroup extends NewColGroup{
	
	@Override
	public void collidedNew(NewSprite inner, NewSprite outer) {
		checkOutsideTop(inner,outer);
		checkOutsideBottom(inner,outer);
	}

	private void checkOutsideTop(NewSprite inner, NewSprite outer) {
		if (inner.getTopCoordinate() < outer.getTopCoordinate()) {
			inner.setY(outer.getBottomCoordinate()
					- inner.getHeight());
		} 		
	}

	private void checkOutsideBottom(NewSprite inner, NewSprite outer) {
		if (inner.getBottomCoordinate() > outer.getBottomCoordinate()) {
			inner.setY(outer.getTopCoordinate());
		}			
	}


}
