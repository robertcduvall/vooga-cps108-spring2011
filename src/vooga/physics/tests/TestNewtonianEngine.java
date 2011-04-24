package vooga.physics.tests;


import static org.junit.Assert.*;

import java.awt.Point;


import org.junit.BeforeClass;
import org.junit.Test;

import vooga.physics.NewtonianPhysicsEngine;
import vooga.physics.newtonianProperties.INewtonianPhysical;
import vooga.physics.newtonianProperties.INewtonianRotate;
import vooga.physics.util.Force;
import vooga.physics.util.IPointField;
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
    /**
     * Testing if applyForce functions correctly in setting an objects velocity using the
     * Impules Momentum Theorem.
     */
    public void testApplyForce() {
        INewtonianPhysical testObject = new NewtonianTestingObject(new Point(0,0), new Velocity(0, new Angle(0)), 100);
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
        assertEquals("Angle after third force", 0, objectVelocity.getAngle().getRadians(), Math.PI/2);
        //In this case, Angle is not quite accurate but this is only because of roundoff errors.
        //The X component of the resulting velocity is 0 while the Y component is 10^-17 so it gets an "angle"
    }
    
    @Test
    /**
     * Testing the applyForce method in Newtonian Engine.
     */
    public void testNewtonianApplyForce(){
        INewtonianPhysical testObject = new NewtonianTestingObject(new Point(0,0), new Velocity(0, new Angle(0)), 100);
        //TestObject at (0,0) with mass 100 and velocity 0
        Force eastForce = new Force(10, new Angle(0));
        engine.applyForce(testObject, eastForce, 1);
        Velocity objectVelocity = testObject.getVelocity();
        assertEquals("Magnitude after first force", 0.1, objectVelocity.getMagnitude(), 0);
        assertEquals("Angle after first force", 0, objectVelocity.getAngle().getRadians(), 0);
    }
    
    @Test
    /**
     * Testing if point fields function correctly.
     */
    public void testPointField(){
        IPointField object1 = new PointFieldTestingObject(1, new Point(1,0), new Velocity(0, new Angle(0)), 10);
        //Object1 at (1,0) with velocity 0 and magnitude 10
        IPointField object2 = new PointFieldTestingObject(1, new Point(-1,0), new Velocity(0, new Angle(0)), 10);
        //Object2 at (-1,0) with velocity 0 and magnitude 10
        engine.applyField(object1, object2, 1);
        //Applying object2's field to object1.
        Velocity velocity1 = object1.getVelocity();
        assertEquals("Object1 velocity magnitude", 5, velocity1.getMagnitude(), 0);
        assertEquals("Object1 velocity angle", Math.PI, velocity1.getAngle().getRadians(),0);
        
        engine.applyField(object2,object1,1);
        //Applying object1's field to object2.
        Velocity velocity2 = object2.getVelocity();
        assertEquals("Object2 velocity magnitude", 5, velocity2.getMagnitude(),0);
        assertEquals("Object2 velocity angle", 0, velocity2.getAngle().getRadians(), 0);
    }
    
    
    
    @Test
    /**
     * Testing rotational effects.
     */
    public void testRotational(){
        INewtonianRotate testObject = new RotationalTestingObject(new Point(0,0), 0, 100);
        //testObject at point (0,0) with 0 rotational velocity and mass of 100
        Force westForce = new Force(10, new Angle(Math.PI));
        Point topPoint = new Point(0, 1);
        engine.applyRotationalForce(testObject, westForce, topPoint, 1);
        double rotVelocity = testObject.getRotationalVelocity();
        assertEquals("Rotational velocity counterclockwise", 0.1, rotVelocity, 0);
        
        testObject.setRotationalVelocity(0);
        //reset testObject
        Force eastForce = new Force(10, new Angle(0));
        engine.applyRotationalForce(testObject, eastForce, topPoint, 1);
        rotVelocity = testObject.getRotationalVelocity();
        assertEquals("Rotational velocity clockwise", -0.1, rotVelocity, 0);
    }
    
    @Test
    /**
     * Testing if world forces correctly affect objects.
     * !!!!!!!!!!!!!! Running into the same problem with generics here
     */
    public void testWorldForce() {
        INewtonianPhysical testObject = new NewtonianTestingObject(new Point(0,0), new Velocity(0, new Angle(0)), 100);
        //TestObject at (0,0) with mass 100 and velocity 0
        Force gravity = new Force(10, new Angle(3*Math.PI/2));
        engine.addGlobalForce(gravity);
        engine.applyWorldForces(testObject, 1);
        Velocity objectVelocity = testObject.getVelocity();
        assertEquals("Magnitude after first application", 0.1, objectVelocity.getMagnitude(), 0);
        assertEquals("Angle after first application", 3*Math.PI/2, objectVelocity.getAngle().getRadians(), 0);
        
        testObject.setVelocity(new Velocity(0, new Angle(0))); 
        //Reset testObject velocity
        Force eastGravity = new Force(10, new Angle(0));
        engine.addGlobalForce(eastGravity);
        engine.applyWorldForces(testObject, 1);
        objectVelocity = testObject.getVelocity();
        assertEquals("Magnitude after second application", 0.1*Math.sqrt(2), objectVelocity.getMagnitude(),0);
        assertEquals("Angle after second application", 7*Math.PI/4, objectVelocity.getAngle().getRadians(), 0);
    }

    @Test
    /**
     * Testing if collisions work between two INewtonianPhysical objects.
     * Current fails because of generics.
     */
    public void testCollision(){
        INewtonianPhysical eastTrain50MPH = new NewtonianTestingObject(new Point(0, 0), new Velocity(50, new Angle(0)), 100);
        INewtonianPhysical westTrain70MPH = new NewtonianTestingObject(new Point(0, 0), new Velocity(70, new Angle(Math.PI)), 100);
        engine.elasticCollision(eastTrain50MPH, westTrain70MPH, new Angle(Math.PI/2), new Point(0,0));
        Velocity trainA = eastTrain50MPH.getVelocity();
        Velocity trainB = westTrain70MPH.getVelocity();
        assertEquals("Elastic - Train A magnitude", 50, trainA.getMagnitude(), 0);
        assertEquals("Elastic - Train B magnitude", 70, trainB.getMagnitude(), 0);
        assertEquals("Elastic - Train A angle", Math.PI, trainA.getAngle().getRadians(), 0);
        assertEquals("Elastic - Train B angle", 0, trainB.getAngle().getRadians(), 0);
    }
    
    
}
