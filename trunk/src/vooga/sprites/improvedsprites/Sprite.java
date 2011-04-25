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

// JFC
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;
import vooga.collisions.ICollidable;
import vooga.collisions.shapes.Vertex;
import vooga.collisions.shapes.collisionShapes.CollisionPolygon;
import vooga.collisions.shapes.collisionShapes.CollisionQuadrilateral;
import vooga.collisions.shapes.collisionShapes.ICollisionShape;
import vooga.collisions.shapes.regularShapes.Polygon;
import vooga.collisions.shapes.regularShapes.Quadrilateral;
import vooga.sprites.improvedsprites.interfaces.IMobility;
import vooga.sprites.improvedsprites.interfaces.IRenderXY;
import vooga.sprites.improvedsprites.interfaces.ISprite;
import vooga.sprites.improvedsprites.interfaces.ISpriteUpdater;
import vooga.sprites.spritebuilder.components.collisions.CollisionPolygonC;
import vooga.sprites.spritebuilder.components.collisions.CollisionShapeC;
import vooga.util.buildable.BuildException;
import vooga.util.buildable.IBuildable;
import vooga.util.buildable.components.ComponentResources;
import vooga.util.buildable.components.ComponentSet;
import vooga.util.buildable.components.IComponent;
import vooga.util.buildable.components.predefined.movement.HeadingC;
import vooga.util.buildable.components.predefined.movement.Speed2DC;
import vooga.util.math.LineMath;
import com.golden.gamedev.object.Background;
import com.golden.gamedev.object.collision.CollisionShape;


/**
 * <code>Sprite</code> is the object in game that has graphical look and has its
 * own behaviour.
 * <p>
 * Every sprite is lived in a background, by default sprite is attached to
 * {@linkplain Background#getDefaultBackground default background}, always
 * remember to set the sprite to the game background or use {@link SpriteGroup}
 * class in {@link PlayField} to take care the sprite background set
 * automatically.
 * <p>
 * Sprite is located somewhere in the background, to set sprite location simply
 * use {@linkplain #setLocation(double, double)}. <br>
 * And to move the sprite use either by moving the sprite directly by using
 * {@linkplain #move(double, double)} or give speed to the sprite by using
 * {@linkplain #setSpeed(double, double)}.
 * <p>
 * In conjunction with sprite group/playfield, every sprite has active state,
 * this active state that determine whether the sprite is alive or not. Thus to
 * remove a sprite from playfield, simply set the sprite active state to false
 * by using {@linkplain #setActive(boolean) setActive(false)}.
 * <p>
 * To create sprite behaviour, always use {@link Timer} class utility in order
 * to make the sprite behaviour independent of frame rate.
 * 
 * @see com.golden.gamedev.object.SpriteGroup
 * @see com.golden.gamedev.object.PlayField
 * @see com.golden.gamedev.object.Timer
 */
public class Sprite extends BaseSprite
    implements java.io.Serializable, IBuildable, ICollidable, IMobility, ISprite
{

    // /////// optimization /////////
    // private final Rectangle collisionOffset = new Rectangle(0,0,0,0); //
    // offset collision

    /**
     * 
     */
    private static final long serialVersionUID = -4499098097309229784L;


    protected ComponentSet<IComponent> myComponents;


    /**
     * Creates new <code>Sprite</code> with null image and located at (0, 0).
     * <p>
     * <b>Note: the image must be set before rendering by using
     * {@link #setImage(BufferedImage)}.</b>
     * 
     * @see #setImage(BufferedImage)
     * @see #setLocation(double, double)
     */
    public Sprite ()
    {
        this(0, 0);
    }


    /**
     * Creates new <code>Sprite</code> with specified image and located at (0,
     * 0).
     * <p>
     * 
     * @see #setLocation(double, double)
     */
    public Sprite (BufferedImage image)
    {
        this(image, 0, 0);
    }


    /**
     * Creates new <code>Sprite</code> with specified image and location.
     */
    public Sprite (BufferedImage image, double x, double y)
    {
        // init variables
        this.x = this.oldX = x;
        this.y = this.oldY = y;
        myComponents = new ComponentSet<IComponent>();
        myComponents.addAll(Arrays.asList(new IComponent[]{new Speed2DC(), new HeadingC()}));
        // sprite image
        if (image != null)
        {
            this.image = image;
            this.width = image.getWidth();
            this.height = image.getHeight();
        }

        this.background = Background.getDefaultBackground();
    }
    
//    /**
//     * Constuctor used to clone a sprite
//     * @param s
//     */
//    public Sprite(Sprite s){
//        this(s.getImage(), s.getX(), s.getY());
//        s.setComponents(s.getComponents().toArray());
//    }


    /**
     * Creates new <code>Sprite</code> with specified position and null image.
     * <p>
     * <b>Note: the image must be set before rendering by using
     * {@link #setImage(BufferedImage)}.</b>
     * 
     * @see #setImage(BufferedImage)
     */
    public Sprite (double x, double y)
    {
        this(null, x, y);
    }

    public Sprite (BufferedImage image, double x, double y, IComponent...comps){
        this(image,x,y);
        this.addComponents(comps);
    }

    /**
     * Adds a default version of this component to this Container
     * 
     * @param c
     */
    public void addComponent (Class<? extends IComponent> c)
    {
        try
        {
            myComponents.add(c.newInstance());
        }
        catch (Exception e)
        {
            throw new BuildException(BuildException.EMPTY_CONSTRUCTOR_DNE);
        }
    }


    /**
     * *************************************************************************
     */
    /**
     * ********************** MOVEMENT OPERATION *******************************
     */
    /**
     * *************************************************************************
     */

    /**
     * Adds a clone of this component to this Container
     * 
     * @param c
     */
    public void addComponent (IComponent c)
    {
        myComponents.add(c);
    }


    /*
     * (non-Javadoc)
     * @see util.buildable.IBuildable#addComponents(java.lang.Class)
     */
    @Override
    public void addComponents (Class<? extends IComponent> ... components)
    {
        for (Class<? extends IComponent> c : components)
        {
            this.addComponent(c);
        }

    }


    /*
     * (non-Javadoc)
     * @see
     * util.buildable.IBuildable#addComponents(util.buildable.components.IComponent
     * )
     */
    @Override
    public void addComponents (IComponent ... components)
    {
        myComponents.addAll(Arrays.asList(components));
    }


    /*
     * (non-Javadoc)
     * @see sprites.oldsprites.ISprite#addHorizontalSpeed(long, double, double)
     */
    @Override
    public void addHorizontalSpeed (long elapsedTime,
                                    double accel,
                                    double maxSpeed)
    {
        this.addSpeed(elapsedTime, accel, 0, maxSpeed);

    }


    /*
     * (non-Javadoc)
     * @see sprites.oldsprites.ISprite#addVerticalSpeed(long, double, double)
     */
    @Override
    public void addVerticalSpeed (long elapsedTime,
                                  double accel,
                                  double maxSpeed)
    {
        this.addSpeed(elapsedTime, accel, 90, maxSpeed);
    }
    
    @Override
    public void addSpeed(long elapsedTime, double accel, double direction, double max){
        this.getComponent(Speed2DC.class).setSpeed((this.getComponent(Speed2DC.class).getAbsoluteVelocity()+elapsedTime*accel), direction, max);
    }



    protected void canCompare (IBuildable o)
    {
        this.carriesComponents((IComponent[]) o.getComponents().toArray());

    }


    /**
     * Returns true this Container carries a component of the same class as the
     * input class
     * 
     * @param obj
     * @return
     */
    public boolean carriesComponent (Class<? extends IComponent> cls)
    {
        if (getComponent(cls) == null) return false;
        return true;
    }


    /**
     * Returns true this Container carries a component of the same class as the
     * input component
     * 
     * @param obj
     * @return
     */
    public boolean carriesComponent (IComponent obj)
    {
        return carriesComponent(obj.getClass());
    }


    /*
     * (non-Javadoc)
     * @see util.buildable.IBuildable#carriesComponents(java.lang.Class)
     */
    @Override
    public boolean carriesComponents (Class<? extends IComponent> ... classes)
    {
        for (Class<? extends IComponent> c : classes)
        {
            if (!this.carriesComponent(c)) return false;
        }
        return true;
    }


    /*
     * (non-Javadoc)
     * @see
     * util.buildable.IBuildable#carriesComponents(util.buildable.components
     * .IComponent)
     */
    @Override
    public boolean carriesComponents (IComponent ... comps)
    {
        for (IComponent c : comps)
        {
            if (!this.carriesComponent(c)) return false;
        }
        return true;
    }


    /*
     * (non-Javadoc)
     * @see
     * util.buildable.IBuildable#carriesExactComponents(util.buildable.components
     * .IComponent)
     */
    @Override
    public boolean carriesExactComponents (IComponent ... comps)
    {
        for (IComponent c : comps)
        {
            if (!this.carriesComponent(c.getClass())) return false;
            else if (!this.getComponent(c.getClass()).equals(c)) return false;
        }
        return true;
    }


    /*
     * (non-Javadoc)
     * @see util.buildable.IBuildable#clear()
     */
    @Override
    public void clearComponents ()
    {
        myComponents.clear();
    }


    @Override
    public IBuildable clone ()
    {
        try
        {
            return this.getClass()
                       .getConstructor(BufferedImage.class, double.class, double.class, TreeSet.class)
                       .newInstance(this.getImage(), this.getX(), this.getY(), myComponents.toArray());//this needs to be an array
        }
        catch (Exception e)
        {
            throw new BuildException(BuildException.ERROR_IN_CLONING);
        }
    }

    
    @Override
    public <T extends ICollisionShape> T getCollisionShape ()
    {
        if (this.getComponent(CollisionShapeC.class) == null)
        {
        	
            this.addComponent(new CollisionPolygonC(
            		new CollisionPolygon(new Vertex(this.getX(),this.getY()),
                                  new Vertex(this.getX()+this.getHeight(), this.getY()),
                                  new Vertex(this.getX(),this.getY() + this.getHeight()),
                                  new Vertex(this.getX()+this.getHeight(),this.getY() + this.getHeight()))));
        }

        return (T) this.getComponentsWhichSubclass(CollisionShapeC.class).get(0).getCollisionShape();
    }


    public <T extends IComponent> ArrayList<T> getComponentsWhichSubclass(Class<T> clazz) {
    	ArrayList<T> comps = new ArrayList<T>();
    	for (IComponent comp: myComponents){
    		if (comp.getClass().getSuperclass().equals(clazz)){
    			comps.add((T) comp);
    		}
    	}
    	
    	
		return comps;
	}


	/*
     * (non-Javadoc)
     * @see util.buildable.IBuildable#getComponent(java.lang.Class)
     */
    @Override
    public <T extends IComponent> T getComponent (Class<T> cls)
    {
        for (IComponent mq : myComponents)
        {
            if (ComponentResources.areSameComponent(mq.getClass(), cls))
            {
                return cls.cast(mq);
            }
        }
        return null;
    }


    /*
     * (non-Javadoc)
     * @see
     * util.buildable.IBuildable#getComponent(util.buildable.components.IComponent
     * )
     */
    @Override
    public <T extends IComponent> T getComponent (T comp)
    {
        return (T) this.getComponent(comp.getClass());
    }
    


    /*
     * (non-Javadoc)
     * @see util.buildable.IBuildable#getComponents()
     */
    @Override
    public TreeSet<IComponent> getComponents ()
    {
        return myComponents;
    }


    /*
     * (non-Javadoc)
     * @see sprites.oldsprites.ISprite#getHorizontalSpeed()
     */
    @Override
    public double getHorizontalSpeed ()
    {
        return this.getComponent(Speed2DC.class).getXVelocity();
    }


    /*
     * (non-Javadoc)
     * @see sprites.oldsprites.ISprite#getVerticalSpeed()
     */
    @Override
    public double getVerticalSpeed ()
    {
    	
        return this.getComponent(Speed2DC.class).getYVelocity();
    }


    @Override
    public boolean moveTo (long elapsedTime, double xs, double ys)
    {
        if (this.getX() == xs && this.getY() == ys) return true;

        double mag =
            Math.sqrt(Math.pow(this.getHorizontalSpeed(), 2) +
                      (Math.pow(this.getVerticalSpeed(), 2)));
        double dir = LineMath.findDirection(this.getX(), this.getY(), xs, ys);

        this.setHorizontalSpeed(mag * Math.cos(dir));
        this.setHorizontalSpeed(mag * Math.cos(dir));

        return false;
    }


    /*
     * (non-Javadoc)
     * @see util.buildable.IBuildable#removeComponent(java.lang.Class)
     */
    @Override
    public boolean removeComponent (Class<? extends IComponent> ... comps)
    {
        boolean b = true;
        for (Class<? extends IComponent> c : comps)
            if (this.carriesComponent(c)) myComponents.remove(this.getComponent(c));
            else b = false;
        return b;
    }


    /*
     * (non-Javadoc)
     * @see util.buildable.IBuildable#removeComponent(util.buildable.components.
     * IComponent)
     */
    @Override
    public void removeComponent (IComponent ... comps)
    {
        for (IComponent c : comps)
            removeComponent(c);
    }


    /*
     * (non-Javadoc)
     * @see util.buildable.IBuildable#removeComponent(util.buildable.components.
     * IComponent)
     */
    @Override
    @SuppressWarnings("unchecked")
    public void removeComponent (IComponent c)
    {
        removeComponent(c.getClass());
    }


    


    /*
     * (non-Javadoc)
     * @see sprites.oldsprites.ISprite#render(java.awt.Graphics2D, int, int)
     */
    @Override
    public void render (Graphics2D g, int x, int y)
    {
        super.render(g, x, y);
        renderComponents(g, x, y);
    }


    protected void renderComponents (Graphics2D g, int x, int y)
    {
        for (IComponent c: myComponents){
            if (c instanceof IRenderXY)
                ((IRenderXY) c).render(g,x,y);
        }
        
    }


    /*
     * (non-Javadoc)
     * @see util.buildable.IBuildable#replaceAllComponents(java.lang.Class)
     */
    @Override
    public void replaceAllComponents (Class<? extends IComponent> ... components)
    {
        myComponents = new ComponentSet<IComponent>();
        for (Class<? extends IComponent> c : components)
            try
            {
                myComponents.add(c.newInstance());
            }
            catch (Exception e)
            {
                throw new BuildException(BuildException.NO_DEFAULT_CONSTRUCTOR);
            }
    }


    /*
     * (non-Javadoc)
     * @see
     * util.buildable.IBuildable#replaceAllComponents(util.buildable.components
     * .IComponent)
     */
    @Override
    public void replaceAllComponents (IComponent ... components)
    {
        myComponents = new ComponentSet<IComponent>();
        myComponents.addAll(Arrays.asList(components));
    }


    /*
     * (non-Javadoc)
     * @see util.buildable.IBuildable#replaceComponent(java.lang.Class,
     * java.lang.Class)
     */
    @Override
    public void replaceComponent (Class<? extends IComponent> toReplace,
                                  Class<? extends IComponent> replaceWith)
    {
        this.removeComponent(toReplace);
        this.addComponent(replaceWith);
    }


    /*
     * (non-Javadoc)
     * @see util.buildable.IBuildable#replaceComponent(java.lang.Class,
     * util.buildable.components.IComponent)
     */
    @Override
    public void replaceComponent (Class<? extends IComponent> toReplace,
                                  IComponent replaceWith)
    {
        this.removeComponent(toReplace);
        this.addComponent(replaceWith);
    }


    /*
     * (non-Javadoc)
     * @see
     * util.buildable.IBuildable#replaceComponent(util.buildable.components.
     * IComponent, util.buildable.components.IComponent)
     */
    @Override
    public void replaceComponent (IComponent toReplace, IComponent replaceWith)
    {

    }




    /*
     * (non-Javadoc)
     * @see
     * sprites.oldsprites.ISprite#setCollisionShape(collisions.collisionshapes
     * .CollisionShape)
     */
    @Override
    public void setCollisionShape (ICollisionShape cs)
    {

        this.setComponent(CollisionShapeC.class, cs);
    }


    @Override
    public void setComponent (Class<? extends IComponent> comp, Object ... args)
    {
        if (this.carriesComponent(comp))
        {
            try
            {
                this.addComponent(ComponentResources.findConstructor(comp,
                                                                     Arrays.asList(args))
                                                    .newInstance(args));
            }
            catch (Exception e)
            {
                throw new BuildException(BuildException.BAD_INPUT);
            }
        }
    }


    /*
     * (non-Javadoc)
     * @see
     * util.buildable.IBuildable#setComponents(util.buildable.components.IComponent
     * )
     */
    @Override
    public void setComponents (IComponent ... comps)
    {
        for (IComponent c : comps)
            if (!this.carriesComponent(c)) this.addComponent(c);
            else this.getComponent(c).setTo(c);
    }


    /*
     * (non-Javadoc)
     * @see sprites.oldsprites.ISprite#setHorizontalSpeed(double)
     */
    @Override
    public void setHorizontalSpeed (double vx)
    {
        this.setSpeed(vx, this.getVerticalSpeed());
    }


    /*
     * (non-Javadoc)
     * @see sprites.oldsprites.ISprite#setMovement(double, double)
     */
    @Override
    public void setMovement (double speed, double angleDir)
    {
        // convert degrees to radians
        double radians = Math.toRadians(angleDir);

        this.setSpeed(Math.sin(radians) * speed, -Math.cos(radians) * speed);
    }


    /**
     * *************************************************************************
     */
    /**
     * ************************* SPEED VARIABLES *******************************
     */
    /**
     * *************************************************************************
     */

    /*
     * (non-Javadoc)
     * @see sprites.oldsprites.ISprite#setSpeed(double, double)
     */
    @Override
    public void setSpeed (double vx, double vy)
    {
        this.setComponent(Speed2DC.class, vx, vy);
    }


    /*
     * (non-Javadoc)
     * @see sprites.oldsprites.ISprite#setVerticalSpeed(double)
     */
    @Override
    public void setVerticalSpeed (double vy)
    {
    	
        this.setSpeed(this.getHorizontalSpeed(), vy);
    }


    /**
     * *************************************************************************
     */
    /**
     * ************************* UPDATE SPRITE *********************************
     */
    /**
     * *************************************************************************
     */

    /*
     * (non-Javadoc)
     * @see sprites.oldsprites.ISprite#update(long)
     */
    @Override
    public void update (long elapsedTime)
    {
        this.updateMovement(elapsedTime);
        this.updateComponents(elapsedTime);
    }

    /**
     * @param elapsedTime
     */
    private void updateComponents (long elapsedTime)
    {
        for (IComponent c: myComponents){
            if (c instanceof ISpriteUpdater)
                ((ISpriteUpdater) c).update(this, elapsedTime);
        }
    }


    /*
     * (non-Javadoc)
     * @see sprites.oldsprites.ISprite#updateMovement(long)
     */
    @Override
    public void updateMovement (long elapsedTime)
    {
        this.move(this.getHorizontalSpeed() * elapsedTime, this.getVerticalSpeed() *
                                                      elapsedTime);
    }


	@Override
	public void setAngle(double angle) {
		this.getComponent(HeadingC.class).setAngle(angle);
	}


	@Override
	public double getAngle() {
		return this.getComponent(HeadingC.class).getAngle();
	}


	@Override
	public double rotate(double dAngle) {
		return this.getComponent(HeadingC.class).rotate(dAngle);
	}





}
