package vooga.collisions.collisionManager;

import java.util.ArrayList;


import vooga.collisions.intersections.IntersectionFactory;
import vooga.collisions.shapes.ShapeFactory;
import vooga.collisions.shapes.collisionShapes.BoundingBox;
import vooga.collisions.shapes.collisionShapes.ICollisionShape;
import vooga.sprites.improvedsprites.Sprite;
import vooga.sprites.spritegroups.BasicSpriteGroup;
import vooga.sprites.spritegroups.SpriteGroup;

public abstract class BasicCollisionGroup extends CollisionManager
{
	
	private ShapeFactory shapeFactory = new ShapeFactory();
	
	public boolean pixelPerfectCollision;
	
	/** ************************************************************************* */
    /** ****************** MAIN-METHOD: CHECKING COLLISION ********************** */
    /** ************************************************************************* */
    
    public void checkCollision() {
        for(int i = 0; i < myGroups.size(); i++){
            for (int j = i; j < myGroups.size(); j++){
                this.checkCollisions(myGroups.get(i), myGroups.get(j));
            }
        }
    }
    
    private void checkCollisions (SpriteGroup sg1,
                                         SpriteGroup sg2)
    {
        if (!sg1.isActive() || !sg2.isActive())
            return;
        
        for(Sprite s1: sg1.getSprites()){
            for (Sprite s2: sg2.getSprites()){
                if(IntersectionFactory.areIntersecting(s1.getCollisionShape(), s2.getCollisionShape())){
                    this.collided(s1, s2);
                }
            }
        }
    }

    /**
     * Performs collision check between Sprite <code>s1</code> and Sprite
     * <code>s2</code>, and returns true if the sprites (<code>shape1</code>,
     * <code>shape2</code>) is collided.
     * <p>
     * 
     * Note: this method do not check active state of the sprites.
     * 
     * @param s1 sprite from group 1
     * @param s2 sprite from group 2
     * @param shape1 bounding box of sprite 1
     * @param shape2 bounding box of sprite 2
     * @return true, if the sprites is collided one another.
     */
    public boolean isCollide(Sprite s1, Sprite s2, CollisionShape shape1, CollisionShape shape2) {
        if (!this.pixelPerfectCollision) {
            return (shape1.intersects(shape2));
            
        }
        else {
            if (shape1.intersects(shape2)) {
                return CollisionManager.isPixelCollide(s1.getX(), s1.getY(), s1
                        .getImage(), s2.getX(), s2.getY(), s2.getImage());
            }
            
            return false;
        }
    }
    
    /**
     * Notified when <code>sprite1</code> from group 1 collided with
     * <code>sprite2</code> from group 2.
     * 
     * @param s1 sprite from group 1
     * @param s2 sprite from group 2
     */
    public abstract void collided(Sprite s1, Sprite s2);
}
