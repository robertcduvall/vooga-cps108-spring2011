package vooga.sprites.improvedsprites;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import vooga.sprites.improvedsprites.interfaces.IRender;
import vooga.sprites.improvedsprites.interfaces.ISpriteBase;
import vooga.sprites.improvedsprites.interfaces.ITargetable;
import collisions.collisionshapes.CollisionRect;
import collisions.collisionshapes.CollisionShape;
import com.golden.gamedev.object.Background;

public class BaseSprite implements ISpriteBase, IRender, ITargetable
{
 // /////// optimization /////////
    // private final Rectangle collisionOffset = new Rectangle(0,0,0,0); //
    // offset collision
    
    /**
     * 
     */
    private static final long serialVersionUID = -4499098097309229784L;
    
    /** ************************** SPRITE BACKGROUND **************************** */
    
    protected Background background;
    
    /** *************************** SPRITE POSITION ***************************** */
    
    protected double x, y;
    protected double oldX, oldY; // old position before this sprite moves
            
            
    /** **************************** SPRITE IMAGES ****************************** */
    
    protected transient BufferedImage image;
    
    /**
     * The width of this sprite.
     */
    protected int width;
    
    /**
     * The height of this sprite.
     */
    protected int height;
    
    /**
     * Default collision shape used in {@link #getDefaultCollisionShape()}, can
     * be used in along with collision manager.
     */
    
    /** **************************** SPRITE FLAGS ******************************* */
    
    
    protected int layer; // for layering purpose only
    
    private boolean active = true;
    private boolean immutable; // immutable sprite won't be disposed/thrown

    
    // from its group
    
    /** ************************************************************************* */
    /** ***************************** CONSTRUCTOR ******************************* */
    /** ************************************************************************* */
    
    /**
     * Creates new <code>Sprite</code> with specified image and location.
     */
    public BaseSprite(BufferedImage image, double x, double y) {
        // init variables
        this.x = this.oldX = x;
        this.y = this.oldY = y;
        
        // sprite image
        if (image != null) {
            this.image = image;
            this.width = image.getWidth();
            this.height = image.getHeight();
        }
        
        this.background = Background.getDefaultBackground();
    }
    
    /**
     * Creates new <code>Sprite</code> with specified image and located at (0,
     * 0).
     * <p>
     * 
     * @see #setLocation(double, double)
     */
    public BaseSprite(BufferedImage image) {
        this(image, 0, 0);
    }
    
    /**
     * Creates new <code>Sprite</code> with specified position and null image.
     * <p>
     * 
     * <b>Note: the image must be set before rendering by using
     * {@link #setImage(BufferedImage)}.</b>
     * 
     * @see #setImage(BufferedImage)
     */
    public BaseSprite(double x, double y) {
        this(null, x, y);
    }
    
    /**
     * Creates new <code>Sprite</code> with null image and located at (0, 0).
     * <p>
     * 
     * <b>Note: the image must be set before rendering by using
     * {@link #setImage(BufferedImage)}.</b>
     * 
     * @see #setImage(BufferedImage)
     * @see #setLocation(double, double)
     */
    public BaseSprite() {
        this(0, 0);
    }
    
    /** ************************************************************************* */
    /** *********************** SPRITE BACKGROUND ******************************* */
    /** ************************************************************************* */
    
    /* (non-Javadoc)
     * @see sprites.oldsprites.ISprite#setBackground(com.golden.gamedev.object.Background)
     */
    @Override
    public void setBackground(Background backgr) {
        this.background = backgr;
        if (this.background == null) {
            this.background = Background.getDefaultBackground();
        }
    }
    
    /* (non-Javadoc)
     * @see sprites.oldsprites.ISprite#getBackground()
     */
    @Override
    public Background getBackground() {
        return this.background;
    }
    
    /** ************************************************************************* */
    /** ************************ IMAGE OPERATION ******************************** */
    /** ************************************************************************* */
    
    /* (non-Javadoc)
     * @see sprites.oldsprites.ISprite#getImage()
     */
    @Override
    public BufferedImage getImage() {
        return this.image;
    }
    
    /* (non-Javadoc)
     * @see sprites.oldsprites.ISprite#setImage(java.awt.image.BufferedImage)
     */
    @Override
    public void setImage(BufferedImage image) {
        this.image = image;
        
        this.width = this.height = 0;
        if (image != null) {
            this.width = image.getWidth();
            this.height = image.getHeight();
        }
    }
    
    /* (non-Javadoc)
     * @see sprites.oldsprites.ISprite#getWidth()
     */
    @Override
    public int getWidth() {
        return this.width;
    }
    
    /* (non-Javadoc)
     * @see sprites.oldsprites.ISprite#getHeight()
     */
    @Override
    public int getHeight() {
        return this.height;
    }
    
    
    
    /** ************************************************************************* */
    /** ********************** MOVEMENT OPERATION ******************************* */
    /** ************************************************************************* */
    
    
    
    /* (non-Javadoc)
     * @see sprites.oldsprites.ISprite#setLocation(double, double)
     */
    @Override
    public void setLocation(double xs, double ys) {
        this.oldX = this.x = xs;
        this.oldY = this.y = ys;
    }
    
    /* (non-Javadoc)
     * @see sprites.oldsprites.ISprite#move(double, double)
     */
    @Override
    public void move(double dx, double dy) {
        this.moveX(dx);
        this.moveY(dy);
        
        // if (dx != 0) {
        // oldX = x;
        // x += dx;
        // }
        //
        // if (dy != 0) {
        // oldY = y;
        // y += dy;
        // }
    }
    
    /* (non-Javadoc)
     * @see sprites.oldsprites.ISprite#moveX(double)
     */
    @Override
    public void moveX(double dx) {
        if (dx != 0) {
            this.oldX = this.x;
            this.x += dx;
        }
    }
    
    /* (non-Javadoc)
     * @see sprites.oldsprites.ISprite#moveY(double)
     */
    @Override
    public void moveY(double dy) {
        if (dy != 0) {
            this.oldY = this.y;
            this.y += dy;
        }
    }
    
    /* (non-Javadoc)
     * @see sprites.oldsprites.ISprite#setX(double)
     */
    @Override
    public void setX(double xs) {
        this.oldX = this.x = xs;
    }
    
    /* (non-Javadoc)
     * @see sprites.oldsprites.ISprite#setY(double)
     */
    @Override
    public void setY(double ys) {
        this.oldY = this.y = ys;
    }
    
    /* (non-Javadoc)
     * @see sprites.oldsprites.ISprite#forceX(double)
     */
    @Override
    public void forceX(double xs) {
        this.x = xs;
    }
    
    /* (non-Javadoc)
     * @see sprites.oldsprites.ISprite#forceY(double)
     */
    @Override
    public void forceY(double ys) {
        this.y = ys;
    }
    
    /* (non-Javadoc)
     * @see sprites.oldsprites.ISprite#getX()
     */
    @Override
    public double getX() {
        return this.x;
    }
    
    /* (non-Javadoc)
     * @see sprites.oldsprites.ISprite#getY()
     */
    @Override
    public double getY() {
        return this.y;
    }
    
    /* (non-Javadoc)
     * @see sprites.oldsprites.ISprite#getOldX()
     */
    @Override
    public double getOldX() {
        return this.oldX;
    }
    
    /* (non-Javadoc)
     * @see sprites.oldsprites.ISprite#getOldY()
     */
    @Override
    public double getOldY() {
        return this.oldY;
    }
    
    
    /** ************************************************************************* */
    /** ******************* OTHER SPRITE POSITION FUNCTIONS ********************* */
    /** ************************************************************************* */
    
    /* (non-Javadoc)
     * @see sprites.oldsprites.ISprite#getScreenX()
     */
    @Override
    public double getScreenX() {
        return this.x - this.background.getX() + this.background.getClip().x;
    }
    
    /* (non-Javadoc)
     * @see sprites.oldsprites.ISprite#getScreenY()
     */
    @Override
    public double getScreenY() {
        return this.y - this.background.getY() + this.background.getClip().y;
    }
    
    /* (non-Javadoc)
     * @see sprites.oldsprites.ISprite#getCenterX()
     */
    @Override
    public double getCenterX() {
        return this.x + (this.width / 2);
    }
    
    /* (non-Javadoc)
     * @see sprites.oldsprites.ISprite#getCenterY()
     */
    @Override
    public double getCenterY() {
        return this.y + (this.height / 2);
    }
    
    /* (non-Javadoc)
     * @see sprites.oldsprites.ISprite#isOnScreen(int, int, int, int)
     */
    @Override
    public boolean isOnScreen(int leftOffset, int topOffset, int rightOffset, int bottomOffset) {
        double sx = this.x - this.background.getX();
        double sy = this.y - this.background.getY();
        
        return (sx + this.width > -leftOffset
                && sy + this.height > -topOffset
                && sx < this.background.getClip().width
                        + rightOffset && sy < this.background
                .getClip().height
                + bottomOffset);
    }
    
    /* (non-Javadoc)
     * @see sprites.oldsprites.ISprite#isOnScreen()
     */
    @Override
    public boolean isOnScreen() {
        return this.isOnScreen(0, 0, 0, 0);
    }
    
    
    /** ************************************************************************* */
    /** ************************* RENDER SPRITE ********************************* */
    /** ************************************************************************* */
    
    /* (non-Javadoc)
     * @see sprites.oldsprites.ISprite#render(java.awt.Graphics2D)
     */
    @Override
    public void render(Graphics2D g) {
        double sx = this.x - this.background.getX();
        double sy = this.y - this.background.getY();
        
        // check whether the sprite is still on screen rendering area
        if ( sx + this.width <= 0
                || sy + this.height <= 0
                ||  sx > this.background.getClip().width
                || sy > this.background.getClip().height) {
            return;
        }
        
        sx += this.background.getClip().x;
        sy += this.background.getClip().y;
        
        this.render(g, (int) sx, (int) sy);
    }
    
    /* (non-Javadoc)
     * @see sprites.oldsprites.ISprite#render(java.awt.Graphics2D, int, int)
     */
    @Override
    public void render(Graphics2D g, int x, int y) {
        g.drawImage(this.image.getScaledInstance(width, height, 0), x, y, null);
    }
    
    /** ************************************************************************* */
    /** ************************** SPRITE FLAGS ********************************* */
    /** ************************************************************************* */
    
    /* (non-Javadoc)
     * @see sprites.oldsprites.ISprite#getLayer()
     */
    @Override
    public int getLayer() {
        return this.layer;
    }
    
    /* (non-Javadoc)
     * @see sprites.oldsprites.ISprite#setLayer(int)
     */
    @Override
    public void setLayer(int i) {
        this.layer = i;
    }
    
    /* (non-Javadoc)
     * @see sprites.oldsprites.ISprite#isActive()
     */
    @Override
    public boolean isActive() {
        return this.active;
    }
    
    /* (non-Javadoc)
     * @see sprites.oldsprites.ISprite#setActive(boolean)
     */
    @Override
    public void setActive(boolean b) {
        this.active = b;
    }
    
    /* (non-Javadoc)
     * @see sprites.oldsprites.ISprite#isImmutable()
     */
    @Override
    public boolean isImmutable() {
        return this.immutable;
    }
    
    /* (non-Javadoc)
     * @see sprites.oldsprites.ISprite#setImmutable(boolean)
     */
    @Override
    public void setImmutable(boolean b) {
        this.immutable = true;
    }
    
    /* (non-Javadoc)
     * @see sprites.oldsprites.ISprite#getDistance(sprites.oldsprites.ISprite)
     */
    @Override
    public double getDistance(ISpriteBase other) {
        return Math.sqrt(Math.pow(this.getCenterX() - other.getCenterX(), 2)
                + Math.pow(this.getCenterY() - other.getCenterY(), 2));
    }



    
    // private static int garbagecount = 0;
    // protected void finalize() throws Throwable {
    // System.out.println("Total sprite garbaged = " + (++garbagecount) + " = "
    // + this);
    // super.finalize();
    // }
}
