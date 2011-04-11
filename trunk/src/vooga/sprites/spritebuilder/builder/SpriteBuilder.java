package vooga.sprites.spritebuilder.builder;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Queue;
import vooga.sprites.improvedsprites.Sprite;
import vooga.util.buildable.BuildException;
import vooga.util.buildable.components.ComponentResources;
import vooga.util.buildable.components.IComponent;
import vooga.util.buildable.components.predefined.basic.IntegerC;

public class SpriteBuilder
{

    
    private ArrayList<Class<? extends IComponent>> myConstructors;
    
    public SpriteBuilder(Class<? extends IComponent>...classes){
        myConstructors = new ArrayList<Class<? extends IComponent>>();
        myConstructors.addAll(Arrays.asList(classes));
    }
    
    public Sprite createSprite(Object ...in){
        Iterator<Class<? extends IComponent>> iter1 = myConstructors.iterator();
        Queue<Object> input = new PriorityQueue<Object>(Arrays.asList(in));
        Sprite bs = new Sprite();

        while(iter1.hasNext()){
            if (!iter1.hasNext())
                throw new BuildException(BuildException.BAD_INPUT);
            
            try
            {
               Constructor<? extends IComponent> c = ComponentResources.findConstructor(iter1.next(), input.peek()); //find constructor
               Object[] args = getConstructorArgs(c, input); //get arguements for constructor
                
               bs.addComponent((IComponent) c.newInstance(args));
            }
            catch (Exception e)
            {
                throw new BuildException(BuildException.BUILDING_ERROR);
            }
            
        }
        
        
        return bs;
        
        
    }

   

    /**
     * Finds all of the objects required to fill the input constructor. If the constructor requires an 
     * object that is not present in the input, fills in a null.
     * @param c
     * @param input
     * @return
     * @throws BuildException
     */
    private Object[] getConstructorArgs (Constructor<? extends IComponent> c, Queue<Object> input) throws BuildException
    {

        Class[] params = c.getParameterTypes();
        ArrayList<Object> args = new  ArrayList<Object>();
        
        for (Class<?> p: params){
            if (input.peek().getClass().equals(p))
                args.add(input.poll());
            else
                args.add(null);
            
        }
        
        return args.toArray();
    }
    
    public static boolean carrySameComponents(Sprite s1, Sprite s2){
        return ComponentResources.carrySameComponent(s1.getComponents(), s2.getComponents());
    }

}
