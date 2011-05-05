package games.patterson_game.refactoredVooga.util.buildable.components;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.TreeSet;

public class ComponentResources
{

    public static boolean areSameComponent (IComponent bc1,
                                            IComponent bc2)
    {
        return ComponentResources.areSameComponent(bc1.getClass(), bc2.getClass());
    }

    public static boolean areSameComponent (Class<? extends IComponent> c1,
                                             Class<? extends IComponent> c2)
    {
        return c1.equals(c2);
    }

    public static List<Class<? extends IComponent>> convertToClassList (Set<IComponent> comps)
    {
        List<Class<? extends IComponent>> cls =
            new ArrayList<Class<? extends IComponent>>();
        for (IComponent c : comps)
            cls.add(c.getClass());
        return cls;
    }
    
    
    /**
     * Finds the fist constructor in the class that carries this object type as its first parameter
     * @param next
     * @param input 
     * @param firstarg - first arguement in constuctor
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public static Constructor<? extends IComponent> findConstructor (Class<? extends IComponent> next, Iterable<Object> input
                                         ) throws Exception
    {
        
        Iterator<Object> in = input.iterator();
        Object firstarg = null;
        for (Constructor<?> c: next.getDeclaredConstructors())
        {
            Class<?>[] params = c.getParameterTypes();
            while (firstarg == null && in.hasNext())
                firstarg = in.next();
            if (params.length != 0 && firstarg != null && params[0].isInstance(firstarg))
                return (Constructor<? extends IComponent>) c;
        }
        return next.getConstructor();
    }

    public static boolean carrySameComponent (TreeSet<IComponent> c1,
                                              TreeSet<IComponent> c2)
    {
        return carrySameComponent(convertToClassList(c1), convertToClassList(c2));
    }

    private static boolean carrySameComponent (List<Class<? extends IComponent>> c1,
                                               List<Class<? extends IComponent>> c2)
    {
        for (Class<? extends IComponent> c: c1){
            if (!c2.contains(c))
                return false;
        }
        return true;
    }
    
    
}
