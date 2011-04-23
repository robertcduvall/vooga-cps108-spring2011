package vooga.physics.tests;


import static org.junit.Assert.*;

import java.awt.Point;


import org.junit.BeforeClass;
import org.junit.Test;

import vooga.physics.NewtonianPhysicsEngine;
import vooga.physics.newtonianProperties.INewtonianPhysical;
import vooga.physics.util.Force;
import vooga.physics.util.Velocity;
import vooga.util.math.Angle;

/**
 * JUnit test class for testing Newtonian physics.
 * 
 * @author Anne Weng
 *
 */
public class TestNewtonianEngine {
    private static NewtonianPhysicsEngine engine;
    
    @BeforeClass
    public static void setUpBeforeClass(){
        engine = new NewtonianPhysicsEngine();
    }
    
    @Test
    public void testApplyForce() {
        INewtonianPhysical testObject = new TestingNewtonianObject(new Point(0,0), new Velocity(0, new Angle(0)), 100);
        //TestObject at (0,0) with mass 100 and velocity 0
        Force eastForce = new Force(10, new Angle(0));
        eastForce.applyForce(testObject, 1);
        Velocity objectVelocity = testObject.getVelocity();
        assertEquals("Magnitude after first force", 0.1, objectVelocity.getMagnitude(), 0);
        assertEquals("Angle after first force", 0, objectVelocity.getAngle().getRadians(), 0);
        
        Force northForce =  new Force(10, new Angle(Math.PI/2));
        northForce.applyForce(testObject, 1);
        objectVelocity = testObject.getVelocity();
        assertEquals("Magnitude after second force", 0.1 * Math.sqrt(2), objectVelocity.getMagnitude(), 0);
        assertEquals("Angle after second force", Math.PI/4, objectVelocity.getAngle().getRadians(), 0);
        
        Force cancelForce = new Force(10*Math.sqrt(2), new Angle(5*Math.PI/4));
        cancelForce.applyForce(testObject, 1);
        objectVelocity = testObject.getVelocity();
        assertEquals("Magnitude after third force", 0, objectVelocity.getMagnitude(), Math.pow(10, -16));
        assertEquals("Angle after third force", 0, objectVelocity.getAngle().getRadians(), 2);
        //In this case, Angle is not quite accurate but this is only because of roundoff errors.
        //The X component of the resulting velocity is 0 while the Y component is 10^-17 so it gets an "angle"
    }
    
    @Test
    public void testCollision(){
        INewtonianPhysical eastTrain50MPH = new TestingNewtonianObject(new Point(0, 0), new Velocity(50, new Angle(0)), 100);
        INewtonianPhysical westTrain70MPH = new TestingNewtonianObject(new Point(0, 0), new Velocity(70, new Angle(Math.PI)), 100);
        engine.elasticCollision(eastTrain50MPH, westTrain70MPH, new Angle(0), new Point(0,0));
        Velocity trainA = eastTrain50MPH.getVelocity();
        Velocity trainB = westTrain70MPH.getVelocity();
        assertEquals("Elastic - Train A magnitude", 50, trainA.getMagnitude(), 0);
        assertEquals("Elastic - Train B magnitude", 70, trainB.getMagnitude(), 0);
        
    }
}
