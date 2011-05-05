package games.nathanAsteroids.ship;

import java.awt.image.BufferedImage;
import java.util.List;
import vooga.physics.util.Velocity;
import vooga.util.math.Angle;

public class EnginePiece extends ShipPiece{

    private static final double DEFAULT_ENGINE_SPEED = 0.001;

    public EnginePiece(BufferedImage image, int x, int y, int mass, int hp, int hardness, 
            Ship ship, Angle angle) {
        super(image, x, y, mass, hp, hardness, ship, angle);
    }

    @Override
    public void eventOccurred() {
        getShip().increaseVelocityByVector(new Velocity(DEFAULT_ENGINE_SPEED, new Angle(Angle.degreesToRadians(this.getAngle()))));
    }


}
