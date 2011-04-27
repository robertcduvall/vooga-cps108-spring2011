package vooga.collisions.shapes.regularShapes;

import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Point2D.Double;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import vooga.collisions.shapes.Vertex;
import vooga.util.math.LineMath;


public class Circle extends Shape implements java.awt.Shape
{

    protected double radius;

    public Circle (Vertex center, double radius)
    {
        super.center = center;
        this.radius = radius;
    }
    
    public Circle (Point2D center, double radius)
    {
    	this(new Vertex(center), radius);
    }


    public Circle (Circle c)
    {
        super.center = new Vertex(c.getCenter());
        this.radius = c.getRadius();
    }


    public Circle (double x, double y, double r)
    {
        this(new Point2D.Double(x, y), r);
    }


    public Circle ()
    {}


    public double area ()
    {
        return Math.PI * radius * radius;
    }


    public double perimeter ()
    {
        return 2 * Math.PI * radius;
    }


    public boolean intersects (Circle c)
    {
        return center.distance(c.center) <= radius + c.radius;
    }


    @Override
    public boolean contains (Point2D p)
    {
        return p.distance(center) <= radius;
    }


    @Override
    public boolean contains (Rectangle2D r)
    {
        Point2D[] p = getRectCorners(r);

        return contains(p[0]) && contains(p[1]) && contains(p[2]) &&
               contains(p[3]);
    }


    public double getRadius ()
    {
        return radius;
    }


    public void setRadius (double radius)
    {
        this.radius = radius;
    }


    @Override
    public Rectangle getBounds ()
    {
        return new Rectangle((int) (center.getX() - radius),
                             (int) (center.getY() - radius),
                             (int) (2 * radius),
                             (int) (2 * radius));
    }


    @Override
    public Rectangle2D getBounds2D ()
    {
        return new Rectangle2D.Double((center.getX() - radius),
                                      (center.getY() - radius),
                                      (2 * radius),
                                      (2 * radius));
    }


    @Override
    public PathIterator getPathIterator (AffineTransform arg0)
    {
        return asEllipse2D().getPathIterator(arg0);
    }


    @Override
    public PathIterator getPathIterator (AffineTransform arg0, double arg1)
    {
        return asEllipse2D().getPathIterator(arg0, arg1);
    }


    /**
     * @return
     */
    public Ellipse2D asEllipse2D ()
    {
        return (new Ellipse2D.Double((center.getX() - radius),
                                     (center.getY() - radius),
                                     (2 * radius),
                                     (2 * radius)));
    }


    @Override
    public boolean intersects (Rectangle2D r)
    {
        for (Point2D p : getRectCorners(r))
            if (this.contains(p)) return true;
        
        return false;
    }


    /**
     * @param r
     * @return
     */
    protected Line2D[] splitRectangleToEdges (Rectangle2D r)
    {
        Point2D[] p = getRectCorners(r);
        Line2D[] edges =
            new Line2D[] {
                    new Line2D.Double(p[0], p[1]),
                    new Line2D.Double(p[0], p[2]),
                    new Line2D.Double(p[1], p[3]),
                    new Line2D.Double(p[2], p[3]) };
        return edges;
    }


    /**
     * @param r
     * @return
     */
    protected Point2D[] getRectCorners (Rectangle2D r)
    {

        return getRectCorners(r.getX(),
                              r.getY(),
                              (int) r.getWidth(),
                              (int) r.getHeight());
    }


    public boolean intersects (Line2D l)
    {
        Point2D p =
            LineMath.findProjection(new Line2D.Double(l.getP1(), center), l)
                    .getP2();

        return this.contains(p);
    }


    public Point2D[] findIntersections (Line2D line)
    {
        if (this.contains(line)) return null;
        Point2D p =
            LineMath.findProjection(new Line2D.Double(line.getP1(), center),
                                    line).getP2();
        if (!this.contains(p)) return null;
        double mag =
            Math.sqrt(radius * radius - Math.pow(p.distance(center), 2));
        double dir = LineMath.findDirection(line);
        if (dir == radius) return new Point2D[] { p };
        else return new Point2D[] {
                new Point2D.Double(p.getX() + mag *
                                           Math.cos(Math.toRadians(dir)),
                                   p.getY() + mag *
                                           Math.sin(Math.toRadians(dir))),
                new Point2D.Double(p.getX() - mag *
                                           Math.cos(Math.toRadians(dir)),
                                   p.getY() - mag *
                                           Math.sin(Math.toRadians(dir))) };

    }


    public List<Point2D> findIntersections (Rectangle2D rect)
    {
        List<Point2D> ins = new ArrayList<Point2D>();

        for (Line2D l : splitRectangleToEdges(rect))
        {
            ins.addAll(Arrays.asList(this.findIntersections(l)));
        }
        return ins;

    }


    public boolean contains (Line2D l)
    {
        return this.contains(l.getP1()) && this.contains(l.getP2());
    }


    @Override
    public boolean intersects (double x, double y, double w, double h)
    {
        for (Point2D p : getRectCorners(x, y, w, h))
            if (this.contains(p)) return true;

        return false;
    }


    public Rectangle2D inscribedSquare ()
    {
        double e = 2 * radius * Math.sin(Math.PI / 4);
        return new Rectangle2D.Double(center.getX() - e / 2, center.getY() - e /
                                                             2, e, e);
    }


    public Rectangle2D inscribedInSquare ()
    {
        return new Rectangle2D.Double(center.getX() - radius,
                                      center.getY() - radius,
                                      radius,
                                      radius);
    }


    public Point2D[] getRectCorners (double x, double y, double w, double h)
    {

        return new Point2D[] {
                new Point2D.Double(x, y),
                new Point2D.Double(x + w, y),
                new Point2D.Double(x, y + h),
                new Point2D.Double(x + w, y + h) };
    }

	@Override
	public void move(double dx, double dy) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setLocation(double x, double y) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public java.lang.Double setAngle(double angle) {
		
		return angle;
		
	}

	@Override
	public double getMaxDistanceFromCenter() {
		return getRadius();
	}

	@Override
	public Point2D getTopLeftCorner(Point2D[] vertices) 
	{
		return new Point2D.Double(this.center.x - this.radius, this.center.y - radius);
	}

	@Override
	public boolean contains(double arg0, double arg1) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public java.lang.Double getAngle() {
		// TODO Auto-generated method stub
		return 0.0;
	}

	@Override
	public java.lang.Double rotate(double dAngle) {
		// TODO Auto-generated method stub
		return 0.0;
	}
}
