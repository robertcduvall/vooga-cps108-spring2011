package games.jezzball.collision;

import games.jezzball.sprite.Ball;
import games.jezzball.sprite.Tile;
import vooga.collisions.collisionManager.BasicCollisionGroup;
import vooga.core.VoogaGame;
import vooga.sprites.improvedsprites.Sprite;
import vooga.sprites.spritegroups.SpriteGroup;

public class CreateWallCollision extends BasicCollisionGroup<Sprite, Sprite>{

    private VoogaGame game;
    public CreateWallCollision(VoogaGame game, SpriteGroup<Sprite> sg1, SpriteGroup<Sprite> sg2){
        super(sg1, sg2);
        this.game = game;
    }
    @Override
    public void collided(Sprite ball, Sprite tile) {
        game.fireEvent(this, "Level.collideWithTile");
    }
}
