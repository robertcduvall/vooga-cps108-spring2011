package vooga.sprites.improvedsprites.interfaces;

import java.awt.Graphics2D;


public interface IRender 
{




    /**
     * Renders this sprite to specified graphics context.
     * 
     * @param g graphics context
     */
    public abstract void render (Graphics2D g);


    /**
     * Renders sprite image to specified graphics context and specified
     * location.
     * 
     * @param g graphics context
     * @param x screen x-coordinate
     * @param y screen y-coordinate
     */
    public abstract void render (Graphics2D g, int x, int y);



    

}
