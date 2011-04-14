package vooga.collisions.shapes;

public interface Shape {

	public abstract Vertex getCenter();

	public abstract void move(double dx, double dy);

	public abstract void setLocation(double x, double y);

	public abstract void rotate(double degrees);

}