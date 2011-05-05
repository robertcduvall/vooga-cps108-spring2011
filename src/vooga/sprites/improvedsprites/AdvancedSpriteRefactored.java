/*
 * Copyright (c) 2008 Golden T Studios. This program is free software: you can
 * redistribute it and/or modify it under the terms of the GNU Lesser General
 * Public License as published by the Free Software Foundation, either version 3
 * of the License, or (at your option) any later version. This program is
 * distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A
 * PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details. You should have received a copy of the GNU Lesser General Public
 * License along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package vooga.sprites.improvedsprites;

import java.awt.Graphics2D;
import vooga.core.VoogaGame;
import vooga.resources.Direction;
import vooga.resources.images.ImageLoader;

/**
 * The new version of the AdvanceSprite class uses
 * the ImageLoader to manage things like sprite state,
 * direction, animation, etc.
 * 
 * @author Misha
 */
public class AdvancedSpriteRefactored extends Sprite
{

    /**
     * 
     */
    private static final long serialVersionUID = 6610463722369315414L;

    private VoogaGame game;
    private ImageLoader imageLoader;
    
    private String imageName;
    private int state;
    private Direction direction;
    private long animationTime;
    private RotationMode rotationMode;
    
    /**
     * A rotation mode for the sprite.
     * 
     * @author Misha
     *
     */
    public static enum RotationMode {
        /**
         * The sprite image is independent of the direction.
         */
        NO_ROTATE,
        /**
         * The direction is approximated by choosing one of four
         * compass directions to rotate the image.
         */
        ROTATE_CARDINAL,
        /**
         * The sprite image is rotated according to the angle of 
         * motion.
         */
        ROTATE_360
    }
    /**
     * Empty constructor.
     * 
     * If you use this, you MUST initialize fields yourself!
     */
    public AdvancedSpriteRefactored() {}
    
    public AdvancedSpriteRefactored(VoogaGame game)
    {
        this(game, 0, 0, null);
    }
    
    public AdvancedSpriteRefactored(VoogaGame game, String imageName)
    {
        this(game, 0, 0, imageName);
    }

    public AdvancedSpriteRefactored(VoogaGame game, int x, int y)
    {
        this(game, x, y, null);
    }
    
    public AdvancedSpriteRefactored(VoogaGame game, int x, int y, String imageName)
    {
        super(x, y);
        this.game = game;
        imageLoader = game.getImageLoader();
    
        this.imageName = imageName;
        this.state = 0;
        this.direction = Direction.NORTH;
        this.animationTime = 0;
        this.rotationMode = RotationMode.ROTATE_360;
        
        setAngle(direction.getAngle());
    }

    /**
     * Internal method to keep the image up-to-date.
     */
    protected void updateImage()
    {
        switch(rotationMode)
        {
            case NO_ROTATE:
            case ROTATE_360:
                setImage(imageLoader.getImage(imageName, state, Direction.NORTH, animationTime));
            case ROTATE_CARDINAL:
                setImage(imageLoader.getImage(imageName, state, direction, animationTime));
                return;
        }
    }
    
    @Override
    public void render (Graphics2D g, int x, int y)
    {
        if (rotationMode == RotationMode.ROTATE_360)
        {
            g.drawImage(image.getScaledInstance(width, height, 0), x, y, null);
            renderComponents(g, x, y);
        }
        else
        {
            super.render(g, x, y);
        }
    }
    
    @Override
    public void update (long elapsedTime)
    {
        super.update(elapsedTime);
        animationTime += elapsedTime;
        
        if (rotationMode == RotationMode.ROTATE_CARDINAL)
        {
            Direction bestDir = Direction.NORTH;
            
            
            for(Direction dir : Direction.values())
            {
                double bestDiff = Math.abs(bestDir.getAngle() - getAngle()) % 360;
                double diff = Math.abs(dir.getAngle() - getAngle()) % 360;
                
                if (diff < bestDiff)
                    bestDir = dir;
            }
            
            direction = bestDir;
        }
    }
    
    public VoogaGame getGame ()
    {
        return game;
    }

    public void setGame (VoogaGame game)
    {
        this.game = game;
        this.imageLoader = game.getImageLoader();
    }

    public String getImageName ()
    {
        return imageName;
    }

    public void setImageName (String imageName)
    {
        this.imageName = imageName;
    }

    public int getState ()
    {
        return state;
    }

    public void setState (int state)
    {
        this.state = state;
    }

    /**
     * Returns the direction the sprite is facing.
     * 
     * @return direction (north, south, east, west)
     */
    public Direction getDirection ()
    {
        return direction;
    }

    /**
     * Sets the direction the sprite is facing.
     * 
     * @param direction (north, south, east, west)
     */
    public void setDirection (Direction direction)
    {
        this.direction = direction;
    }

    /**
     * Sets the rotation mode for the sprite image.
     */
    public void setRotationMode (RotationMode rotationMode)
    {
        this.rotationMode = rotationMode;
    }
    
    /**
     * Returns the rotation mode for the sprite image.
     */
    public RotationMode getRotationMode ()
    {
        return rotationMode;
    }
}
