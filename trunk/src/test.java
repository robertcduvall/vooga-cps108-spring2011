import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

import vooga.util.math.LineMath;


public class test {

	
	
	public static void main (String[] args){
		Line2D l = new Line2D.Double(-10546, -10454, 11549, 116);
		Line2D shift = 	LineMath.rotate(l, 50.0);
		System.out.println(shift.getP1()+","+ shift.getP2());
		System.out.println(LineMath.findDirection(shift)+ " " + LineMath.findDirection(l));
	}
}
