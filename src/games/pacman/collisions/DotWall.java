package games.pacman.collisions;

import games.pacman.sprites.Dot;
import games.pacman.sprites.Wall;
import vooga.collisions.collisionManager.BasicCollisionGroup;

public class DotWall  extends BasicCollisionGroup<Dot, Wall>{
		
    /**
     * Tests whether a ball and a block have collided, treating each
     * ball as a perfect circle and each block as a perfect rectangle.
     */
    @Override
    public boolean areCollide(Dot dot, Wall wall)
    {
    return	wall.getX()<dot.getX() &&
    	wall.getX()+wall.getWidth()>dot.getX() &&
    	wall.getY()<dot.getY() &&
    	wall.getY()+wall.getHeight()>dot.getY();

//      return CollisionManager.isPixelCollide(ball.getX(), ball.getY(), ball.getImage(), 
//                                             block.getX(), block.getY(), block.getImage());

    }
    
    
	@Override
	public void collided(Dot dot, Wall wall) {
		getGroup1().remove(dot);
	}

}
