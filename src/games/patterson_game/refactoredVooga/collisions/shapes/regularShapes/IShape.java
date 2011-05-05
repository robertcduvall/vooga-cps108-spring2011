package games.patterson_game.refactoredVooga.collisions.shapes.regularShapes;

import games.patterson_game.refactoredVooga.levelsRefactored.IRenderable;
import games.patterson_game.refactoredVooga.sprites.improvedsprites.interfaces.IRotation;
import java.awt.geom.Point2D;



public interface IShape extends IRotation, IRenderable
{

    public abstract void move (double dx, double dy);


    public abstract void setLocation (double x, double y);


    public abstract double getMaxDistanceFromCenter ();


    public abstract Point2D getTopLeftCorner (Point2D[] vertices);


    public abstract Point2D getCenter ();


    public abstract boolean contains (double x, double y);


    public abstract boolean contains (double x1, double y1, double x2, double y2);


    public abstract void setCenter (Point2D.Double center);
    
    public abstract void setCenter (double x, double y);

}
