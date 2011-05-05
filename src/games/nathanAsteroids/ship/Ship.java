package games.nathanAsteroids.ship;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EventListener;
import java.util.HashSet;
import java.util.List;
import vooga.core.VoogaGame;
import vooga.physics.VoogaPhysicsMediator;
import vooga.physics.forceGenerator.MassProportionalForceGenerator;
import vooga.physics.util.Force;
import vooga.physics.util.Velocity;
import vooga.sprites.improvedsprites.Sprite;
import vooga.sprites.spritebuilder.components.physics.PhysicsVelocityC;
import vooga.sprites.spritegroups.SpriteGroup;

public class Ship extends SpriteGroup<Sprite> {
    private CommandCenterPiece commandCenter;
    private int totalMass;

    // private List<ShipPiece> pieces;
    // private SpriteGroup<Sprite> shipGroup;

    public Ship() {
        super("ship");
    }

    public void createShipFromXML(VoogaGame game) {
        ShipParser parser = new ShipParser(this, game);
        parser.setLocation(100, 100);
        parser.parse("src/games/nathanAsteroids/resources/ship.xml");
    }

    // public SpriteGroup getShipSprites() {
    // return shipGroup;
    // }
    //
    // public void setShipSprites(SpriteGroup shipGroup) {
    // this.shipGroup = shipGroup;
    // }

    public void setCommandCenter(CommandCenterPiece commandCenter) {
        this.commandCenter = commandCenter;
    }

    public CommandCenterPiece getCommandCenter() {
        return commandCenter;
    }

    public void setShipRelativeTo(ShipPiece centerPiece) {
        for (Sprite piece : this.getSprites()) {
            piece.setX(centerPiece.getX());
            piece.setY(centerPiece.getY());
            //piece.setAngle(centerPiece.getAngle()+((ShipPiece)piece).getAngleOffset().getDegrees());
        }
    }

    // public void update(long elapsedTime) {
    // super.update(elapsedTime);
    // //shipGroup.update(elapsedTime);
    // }

    // private void updateList() {
    // pieces = commandCenter.updateList();
    // }

    // public void render(Graphics2D g) {
    // shipGroup.render(g);
    // }

    public void increaseVelocityByVector(Velocity additionalVelocity) {
        //This should work, but it doesnt
        //VoogaPhysicsMediator.applyForceToSprite(commandCenter, new MassProportionalForceGenerator(new Force(0.001,commandCenter.getAngle())), 1);
        commandCenter.addSpeed(additionalVelocity.getXComponent(), additionalVelocity.getYComponent());
    }
}
