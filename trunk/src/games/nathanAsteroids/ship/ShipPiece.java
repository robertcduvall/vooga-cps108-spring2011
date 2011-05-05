package games.nathanAsteroids.ship;

import games.nathanAsteroids.sprites.components.DefenseC;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import com.golden.gamedev.util.ImageUtil;
import vooga.collisions.shapes.ShapeFactory;
import vooga.collisions.shapes.collisionShapes.CollisionPolygon;
import vooga.core.VoogaGame;
import vooga.core.event.EventManager;
import vooga.core.event.IEventHandler;
import vooga.physics.util.Velocity;
import vooga.sprites.improvedsprites.Sprite;
import vooga.sprites.spritebuilder.components.collisions.CollisionPolygonC;
import vooga.sprites.spritebuilder.components.physics.BasicPhysicsC;
import vooga.util.buildable.components.predefined.basic.HealthC;
import vooga.util.math.Angle;

public abstract class ShipPiece extends Sprite {
    private boolean beenUpdated;
    private Ship ship;
    private int xOffset;
    private int yOffset;
    private Angle angleOffset;

    public ShipPiece(BufferedImage image, int x, int y, int mass, int hp, int defense, Ship ship) {
        this(image, x, y, mass, hp, defense, ship, new Angle());

    }

    public ShipPiece(BufferedImage image, int x, int y, int mass, int hp, int defense, Ship ship,
            Angle directionFacing) {
        super(image, x, y);
        this.addComponents(new HealthC((double) hp), new DefenseC((double) defense), new BasicPhysicsC((double) mass));
        this.addComponent(new CollisionPolygonC(new CollisionPolygon(ShapeFactory.makePolygonFromImage(ImageUtil.resize(image, width,height), 4))));
        beenUpdated = false;
        this.ship = ship;
        double directionInDegrees = directionFacing.getDegrees();
        this.rotate(directionInDegrees);
        if (getShip().getCommandCenter() != null) {
            xOffset = (int) (x - getShip().getCommandCenter().getX());
            yOffset = (int) (y - getShip().getCommandCenter().getY());
        }
        else {
            xOffset = 0;
            yOffset = 0;
        }
        angleOffset = directionFacing;
    }
    
    public Angle getAngleOffset(){
        return angleOffset;
    }

    // @Override
    // public void setHorizontalSpeed(double newX){
    // double speedChange = newX - getHorizontalSpeed();
    // ship.increaseVelocityByVector(new Velocity(speedChange, 0));
    // }
    //
    // @Override
    // public void setVerticalSpeed(double newY){
    // double speedChange = newY - getVerticalSpeed();
    // ship.increaseVelocityByVector(new Velocity(0,speedChange));
    // }

    @Override
    public void setX(double x) {
        super.setX(x + xOffset);
    }

    @Override
    public void setY(double y) {
        super.setY(y + yOffset);
    }

    protected Ship getShip() {
        return ship;
    }

//    public PieceSide getSide(int index) {
//        return sides.get(index);
//    }
//
//    public void setPieceInSides() {
//        for (PieceSide side : sides) {
//            side.setPiece(this);
//        }
//    }

    public void registerEvent(String eventName, EventManager manager) {
        manager.registerEventHandler(eventName, new IEventHandler() {
            @Override
            public void handleEvent(Object o) {
                eventOccurred();
            }
        });
    }

    public void eventOccurred() {

    }

    // public List<ShipPiece> updateList() {
    // List<ShipPiece> pieces = new ArrayList<ShipPiece>();
    // if (!beenUpdated) {
    // beenUpdated = true;
    // pieces.add(this);
    // for (PieceSide side : sides) {
    // ShipPiece connected = side.getConnected(this);
    // if (connected != null)
    // pieces.addAll(connected.updateList());
    // }
    // }
    // return pieces;
    // }
    //
    // public void resetUpdated() {
    // if (beenUpdated) {
    // beenUpdated = false;
    // for (PieceSide side : sides) {
    // ShipPiece connected = side.getConnected(this);
    // if (connected != null)
    // connected.resetUpdated();
    // }
    // }
    // }
}
