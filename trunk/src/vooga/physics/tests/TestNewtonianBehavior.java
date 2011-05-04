package vooga.physics.tests;

import static org.junit.Assert.*;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collection;

import org.junit.Test;

import vooga.physics.collisionBehavior.EmptyCollisionBehavior;
import vooga.physics.forceGenerator.AbstractForceGenerator;
import vooga.physics.forceGenerator.BasicForceGenerator;
import vooga.physics.util.Force;
import vooga.physics.util.Velocity;
import vooga.sprites.spritebuilder.components.physics.AbstractPhysicsC;
import vooga.sprites.spritebuilder.components.physics.BasicPhysicsC;
import vooga.util.math.Angle;

/**
 * Test class for Newtonian behavior.
 * 
 * @author Anne Weng
 *
 */
public class TestNewtonianBehavior {

    @Test
    public void testNewtonianForce(){
        AbstractPhysicsC basic = new BasicPhysicsC(new Velocity(0,new Angle(0)), 1);
        //Velocity of 0, mass of 1
        AbstractForceGenerator force = new BasicForceGenerator(new Force(10,new Angle(Math.PI)));
        basic.applyForce(force, 1);
        //Applies force with magnitude 10 pointing west
        Velocity delta = basic.getDeltaVelocity();
        assertEquals("Delta magnitude single application", 10, delta.getMagnitude(), 0);
        assertEquals("Delta angle single application", Math.PI, delta.getAngle().getRadians(),0);

        Collection<AbstractForceGenerator> forces = new ArrayList<AbstractForceGenerator>();
        forces.add(new BasicForceGenerator(new Force(10,new Angle(Math.PI/2))));
        forces.add(new BasicForceGenerator(new Force(10,new Angle(0))));
        forces.add(new BasicForceGenerator(new Force(10,new Angle(Math.PI*1.5))));
        basic.applyForces(forces, 1);
        delta = basic.getDeltaVelocity();
        assertEquals("Delta magnitude multiple applications", 0, delta.getMagnitude(),Math.pow(10,-14));
    }

    @Test
    /*
     * So applyCollision isn't getting overridden...
     */
    public void testPhysicalCollision(){
        BasicPhysicsC basic1 = new BasicPhysicsC(new Velocity(10,new Angle(0)), 1);
        BasicPhysicsC basic2 = new BasicPhysicsC(new Velocity(5,new Angle(Math.PI)), 1);
        Angle collisionAngle = new Angle(Math.PI/2);
        EmptyCollisionBehavior behave2 = basic2.getCollisionBehavior();
        basic1.applyCollision(behave2, collisionAngle, new Point(0,0), 1);
        basic2.applyCollision(basic1.getCollisionBehavior(), collisionAngle, new Point(0,0), 1);
        Velocity delta1 = basic1.getDeltaVelocity();
        assertEquals("Delta1 magnitude", 5, delta1.getMagnitude(),0);
        assertEquals("Delta1 angle", Math.PI, delta1.getAngle().getRadians(),0);
    }
}
