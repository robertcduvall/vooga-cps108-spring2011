package games.mariobros.collisions;

import games.mariobros.sprites.Block;
import games.mariobros.sprites.Lander;
import vooga.collisions.collisionManager.BasicCollisionGroup;

public class LanderHitsFloor extends BasicCollisionGroup<Lander, Block>
{
    
    @Override
    public boolean areCollide(Lander lander, Block block)
    {
    	double landerBottomEdge = lander.getCenterY() + lander.getHeight()/2;
    	double blockTopEdge = block.getCenterY() - lander.getHeight()/2;
    	
    	boolean check1 = blockTopEdge < landerBottomEdge;
    	
    	double landerRightEdge = lander.getCenterX() + lander.getWidth()/2;
    	double blockLeftEdge = block.getCenterX() - lander.getWidth()/2;
    	
    	boolean check2 = blockLeftEdge < landerRightEdge;
    	
    	double landerLeftEdge = lander.getCenterX() - lander.getWidth()/2;
    	double blockRightEdge = block.getCenterX() + lander.getWidth()/2;
    	
    	boolean check3 = blockRightEdge > landerLeftEdge;
    	
    	return check1 && check2 && check3;
    }
    
    @Override
    public void collided (Lander lander, Block block)
    {
    	lander.setVerticalSpeed(0);
    	lander.setHorizontalSpeed(0);
    	
        System.out.println("HAHAHA");
        
    }

}
