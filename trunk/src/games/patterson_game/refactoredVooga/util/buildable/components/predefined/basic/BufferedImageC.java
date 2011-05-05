package games.patterson_game.refactoredVooga.util.buildable.components.predefined.basic;

import games.patterson_game.refactoredVooga.sprites.improvedsprites.interfaces.IRenderXY;
import games.patterson_game.refactoredVooga.util.buildable.components.BasicComponent;
import games.patterson_game.refactoredVooga.util.buildable.components.IComponent;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.IndexColorModel;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.util.Hashtable;
import javax.imageio.ImageIO;


public class BufferedImageC extends BasicComponent implements IRenderXY
{

    private BufferedImage myImage;
    
    public BufferedImageC (ColorModel arg0,
                           WritableRaster arg1,
                           boolean arg2,
                           Hashtable<?, ?> arg3)
    {
        this(new BufferedImage(arg0, arg1, arg2, arg3));
    }

    public BufferedImageC (int arg0, int arg1, int arg2, IndexColorModel arg3)
    {
        this(new BufferedImage(arg0, arg1, arg2, arg3));
    }

    public BufferedImageC (int arg0, int arg1, int arg2)
    {
        this(new BufferedImage(arg0, arg1, arg2));
    }

    
    public BufferedImageC (BufferedImage bufferedImage)
    {
        myImage = bufferedImage;
    }

    @Override
    public void setTo (IComponent c)
    {
        myImage =((BufferedImageC) c).getImage();
    }

    private BufferedImage getImage ()
    {
        return myImage;
    }


    @Override
    public void render (Graphics2D g)
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void render (Graphics2D g, int x, int y)
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    protected int compareTo (BasicComponent o)
    {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    protected Object[] getFieldValues ()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    protected void setFieldValues (Object ... fields)
    {
        // TODO Auto-generated method stub
        
    }


    

   
}
