package vooga.physics.tests;


import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import vooga.physics.newtonianInterfaces.INewtonianPhysical;
import vooga.physics.util.Force;
import vooga.physics.util.Velocity;
import vooga.util.math.Angle;

public class TestNewtonianEngine {
    private static INewtonianPhysical testObject;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        testObject = new TestingNewtonianObject();

        //TestObject at (0,0) with mass 100 and velocity 0
    }
    
    @Test
    public void testApplyForce() {
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
        // The X component of the resulting velocity is 0 while the Y component is 10^-17
    }
}
