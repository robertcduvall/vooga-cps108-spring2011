package games.nathanAsteroids.ship;

import java.awt.image.BufferedImage;
import java.util.List;
import vooga.core.VoogaGame;
import vooga.util.math.Angle;

public class CommandCenterPiece extends ShipPiece {

    public CommandCenterPiece(BufferedImage image, int x, int y, int mass, int hp, int hardness,
            Ship ship, Angle angle) {
        super(image, x, y, mass, hp, hardness, ship, angle);
    }
    
    @Override
    public void update(long elapsedTime){
        super.update(elapsedTime);
        getShip().setShipRelativeTo(this);
    }
}
