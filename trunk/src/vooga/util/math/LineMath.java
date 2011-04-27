package vooga.util.math;

import java.awt.geom.Line2D;
import java.awt.geom.Point2D;


public class LineMath
{

    public static final int P1 = 1;
    public static final int P2 = 2;


	static double findSlope (Line2D line)
    {
        return (line.getY2() - line.getY1()) / (line.getX2() - line.getX1());
    }


    public static Point2D findIntersection (Line2D line1, Line2D line2)
    {
        double m2 = findSlope(line2);
        double m1 = findSlope(line2);
        double x;
        if (m1 == m2) return null;
        else if (!line1.intersectsLine(line2)) return null;

        else if (line2.getX1() == line2.getX2())
        {
            x = line2.getX1();
            return new Point2D.Double(x, m1 * x - (m1 * line1.getX1()) +
                                         line1.getY1());
        }
        else if (line1.getX1() == line1.getX2())
        {
            x = line1.getX2();
            return new Point2D.Double(x, m2 * x - (m2 * line2.getX1()) +
                                         line2.getY1());

        }
        else
        {
            x =
                ((-1 * m2 * line2.getX1()) + line2.getY1() +
                 (m1 * line1.getX1()) - line1.getY1()) /
                        (m1 - m2);
            return new Point2D.Double(x, m1 * x - (m1 * line1.getX1()) +
                                         line1.getY1());
        }

    }


    
    /**
     * finds the direction of this change in x,y distance
     * @param line
     * @return the direction in degrees of this line if it were a vector in
     *         caresian system
     */
    public static Double findDirection (double dx, double dy)
    {
    	if (dx > 0) return Math.toDegrees(Math.atan(dy / dx));
        else if (dx < 0) return (Math.toDegrees(Math.atan(dy / dx)) + 180) % 360;
        else if (dy < 0) return 270.0;
        else if (dy > 0) return 90.0;
        return null; //when line has mag = 0
    }
    
    /**
     * @param line
     * @return the direction in degrees of this line if it were a vector in
     *         caresian system
     */
    public static Double findDirection (Line2D line)
    {
    	return findDirection(findDX(line),findDY(line));
        
    }


    public static double length (Line2D line)
    {
        return line.getP1().distance(line.getP2());

    }


    public static Point2D findMidpoint (Line2D line)
    {
        return new Point2D.Double(line.getX1() + findDX(line) / 2,
                                  line.getY1() + findDY(line) / 2);
    }


    public static double findDY (Line2D line)
    {
        return line.getY2() - line.getY1();
    }


    public static double findDX (Line2D line)
    {
        return line.getX2() - line.getX1();
    }


    /**
     * projects the first line onto the second as if they were vectors with
     * start at b.p1 and returns the result.
     * 
     * @param a,b
     * @return projection of a onto b
     */
    public static Line2D findProjection (Line2D a, Line2D b)
    {
        if (length(b) == 0) return b;
        double mag = dotProduct(a, b) / length(b), dir = findDirection(b);

        return new Line2D.Double(b.getX1(),
                                 b.getY1(),
                                 b.getX1() +
                                         (mag * Math.cos(Math.toRadians(dir))),
                                 b.getY1() +
                                         (mag * Math.sin(Math.toRadians(dir))));
    }


    public static double dotProduct (Line2D a, Line2D b)
    {
        return findDX(a) * findDX(b) + findDY(a) * findDY(b);
    }


    public static Line2D[] split (Line2D line, Point2D splitPoint)
    {
        return new Line2D[] {
                new Line2D.Double(line.getP1().getX(),
                                  line.getP1().getY(),
                                  splitPoint.getX(),
                                  splitPoint.getY()),
                new Line2D.Double(splitPoint.getX(),
                                  splitPoint.getY(),
                                  line.getP2().getX(),
                                  line.getP2().getY()) };
    }

    /**
     * @param line
     * @return the direction in degrees of this line if it were a vector in
     *         caresian system
     */
    public static double findDirection (double x, double y, double xs, double ys)
    {
        return findDirection(new Line2D.Double(x,y,xs,ys));
    }


	public static double calcMagnitude(double dx, double dy) {
		return Math.sqrt(dx*dx + dy*dy);
	}



	private static Line2D rotateAround(Line2D line, Point2D p, double dAngle) {
		Line2D radius = new Line2D.Double(LineMath.findMidpoint(line), p);
		return LineMath.moveCenterTo(line, LineMath.rotate(radius,dAngle).getP2());
	}


	private static Line2D moveCenterTo(Line2D line, Point2D nC) {
		double dx = nC.getX() - LineMath.findMidpoint(line).getX();
		double dy = nC.getY() - LineMath.findMidpoint(line).getY();
		line.setLine(line.getP1().getX()+dx, 
				line.getP1().getY()+dy,
				line.getP2().getX()+dx, 
				line.getP2().getY()+dy);
		return line;
	}


	public static Line2D rotate(Line2D radius, double dAngle) {
		double mag = LineMath.length(radius);
		double nA = LineMath.findDirection(radius)+dAngle;
		
		return new Line2D.Double(radius.getX1(), 
				radius.getY1(), 
				radius.getX1()+mag*Math.cos(Math.toRadians(nA)),
				radius.getY1()+mag*Math.sin(Math.toRadians(nA)));
	}


	private static double findDirection(Point2D p1, Point2D p2) {
		return findDirection(new Line2D.Double(p1, p2));
	}


	public static double length(Point2D p1, Point2D p2) {
		return LineMath.length(new Line2D.Double(p1,p2));
	}


	public static Line2D rotate(double x1, double y1, double x2,
			double y2, Double angle) {
		return rotate(new Line2D.Double(x1,y1,x2,y2), angle);
	}
    

}
