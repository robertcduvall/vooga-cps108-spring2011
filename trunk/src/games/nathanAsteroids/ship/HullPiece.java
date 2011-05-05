package games.nathanAsteroids.ship;

import java.awt.image.BufferedImage;
import java.util.List;
import vooga.util.math.Angle;

public class HullPiece extends ShipPiece {

    public HullPiece(BufferedImage image, int x, int y, int mass, int hp, int hardness, 
            Ship ship, Angle angle) {
        super(image, x, y, mass, hp, hardness, ship, angle);
    }


}
