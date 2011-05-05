package games.nathanAsteroids.ship;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.EventObject;
import java.util.List;
import vooga.util.math.Angle;

public class GunPiece extends ShipPiece{

    private static final double DEFAULT_BULLET_SPEED = 1;
    private Point endOfGun;

    public GunPiece(BufferedImage image, int x, int y, int mass, int hp, int hardness,
            Ship ship, Angle angle) {
        super(image, x, y, mass, hp, hardness, ship, angle);

    }


//    @Override
//    public void eventOccurred(EventObject object) {
//        System.out.println(getDirectionFacing());
//        if ((getGameDegrees(getDirectionFacing()) > 315) || (getGameDegrees(getDirectionFacing()) <= 45)) {
//            endOfGun = new Point((int) getCenterX(), (int) getY());
//        }
//        if ((getGameDegrees(getDirectionFacing()) > 45) && (getGameDegrees(getDirectionFacing()) <= 135)) {
//            endOfGun = new Point((int) getX(), (int) getCenterY());
//        }
//        if ((getGameDegrees(getDirectionFacing()) > 135) && (getGameDegrees(getDirectionFacing()) <= 225)) {
//            endOfGun = new Point((int) getCenterX(), (int) (getY() + getHeight()));
//        }
//        if ((getGameDegrees(getDirectionFacing()) > 225) && (getGameDegrees(getDirectionFacing()) <= 315)) {
//            endOfGun = new Point((int) (getX() + getWidth()), (int) getCenterY());
//        }
//        Velocity bulletVelocity = new Velocity(DEFAULT_BULLET_SPEED, getDirectionFacing());
//        Bullet bullet = new Bullet(endOfGun.x, endOfGun.y, bulletVelocity);
//        getShip().getShipSprites().add(bullet);
//
//    }

}
