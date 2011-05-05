package games.patterson_game.refactoredVooga.resources;

import java.util.*;

/**
 * 
 * Picks random objects from a given collection.
 * 
 * <br /><br />
 * Allows for a gradation of fairness assurances, up to a maximum fairness
 * in which all objects are picked once before any object is picked a second time.
 * 
 * <br /><br />
 * Allows for objects with varying frequency.
 * 
 * @author Misha
 *
 * @param <T> The type of object generated
 */
public class RandomChooser<T>
{
    /**
     * Indicates a fairness mode in which no assurances of fairness are given.
     */
    public static int NO_FAIRNESS = 0;
    /**
     * Indicates a fairness mode with maximal assurance of fairness: 
     * essentially, drawing without replacement.
     */
    public static int MAX_FAIRNESS = 1;
    
    private List<T> choices;
    private List<Integer> baseFreqs, currentFreqs;
    private int total;
    
    private Queue<T> pregenerated = new LinkedList<T>();
    private int fairnessMode = NO_FAIRNESS;
    private Random random;

    /**
     * Constructs a random chooser from a map
     * from objects to their frequency. 
     * 
     * @param choices A map from objects to integer frequencies
     * @param random The Java Random instance this object will use
     */
    public RandomChooser(Map<T, Integer> choices, Random random)
    {
        this.choices = new ArrayList<T>();
        baseFreqs = new ArrayList<Integer>();
        this.random = random;

        for(T t : choices.keySet())
        {
            this.choices.add(t);
            baseFreqs.add(choices.get(t));
        }
        
        refresh();
    }
    
    /**
     * Construct a random chooser from a collection of
     * objects that are assumed to have equal frequency.
     * 
     * @param choices A collection of objects to choose from
     * @param random The Java Random instance this object will use
     */    
    public RandomChooser(Collection<T> choices, Random random)
    {
        this.choices = new ArrayList<T>();
        baseFreqs = new ArrayList<Integer>();

        this.random = random;
        this.choices.addAll(choices);        
        
        for(int i=0; i<choices.size(); i++)
        {
            baseFreqs.add(1);
        }
        
        refresh();
    }

    /**
     * Construct a random chooser from a collection of
     * objects that are assumed to have equal frequency.
     * 
     * @param choices A collection of objects to choose from
     */
    public RandomChooser(Collection<T> choices)
    {
        this(choices, new Random());
    }

    /**
     * Constructs a random chooser from a map
     * from objects to their frequency. 
     * 
     * @param choices A map from objects to integer frequencies
     */
    public RandomChooser(Map<T, Integer> choices)
    {
        this(choices, new Random());
    }
    
    /**
     * Constructs a random chooser with no elements.
     */
    public RandomChooser()
    {
        this(new ArrayList<T>());
    }
    
    /**
     * If a fairness mode is turned on, clears the history
     * of which objects have been returned.
     */
    public void refresh()
    {
        if (fairnessMode == NO_FAIRNESS)
        {
            currentFreqs = baseFreqs;
        }
        else
        {
            currentFreqs = new ArrayList<Integer>();
            total = 0;
            
            for(int freq : baseFreqs)
            {
                currentFreqs.add(fairnessMode * freq);
                total += freq;
            }
        }
    }
    
    /**
     * Generates a sequence of random objects in advance.
     * If objects have been pre-generated, ensures that the 
     * length of the existing sequence is at least the given
     * length.
     * 
     * @param length The desired length of the pre-generated sequence.
     */
    public void pregenerate(int length)
    {
        while (fairnessMode != NO_FAIRNESS && 
                pregenerated.size() + total < length)
        {
            List<T> cycle = new ArrayList<T>();
            
            for(int i=0; i<choices.size(); i++)
            {
                for(int j=0; j<currentFreqs.get(i); j++)
                    cycle.add(choices.get(i));
            }
            
            Collections.shuffle(cycle, random);
            
            pregenerated.addAll(cycle);
            refresh();
        }
        
        while(pregenerated.size() < length)
        {
            pregenerated.add(internalGenerate());
        }
    }
    
    /**
     * An internal method for getting a random object
     * that does not access pregenerated objects.
     * 
     * @return a random object
     */
    protected T internalGenerate()
    {
        if (total == 0)
        {
            refresh();
        }

        int r = random.nextInt(total); 
        
        for(int i=0; i<choices.size(); i++)
        {
            r -= currentFreqs.get(i);
            
            if (r < 0)
            {
                if (fairnessMode != NO_FAIRNESS)
                    currentFreqs.set(i, currentFreqs.get(i) - 1);
                    
                return choices.get(i);
            }
        }

        return null; // should never happen
    }
    
    /**
     * Chooses a random object
     * 
     * @return A random object
     */
    public T nextChoice()
    {
        if (pregenerated.isEmpty())
            return internalGenerate();
        else
            return pregenerated.poll();
    }
    
    /**
     * Returns the fairness mode currently turned on.
     * The default mode is NO_FAIRNESS.
     * 
     * @return The current fairness mode
     */
    public int getFairnessMode()
    {
        return fairnessMode;
    }
    
    /**
     * Sets the fairness mode to the desired value.
     * This method clears the history and the pre-generated values.
     * 
     * @param fairnessMode the desired fairness mode
     */
    public void setFairnessMode(int fairnessMode)
    {
        this.fairnessMode = fairnessMode;
        pregenerated.clear();
        refresh();
    }
    
    /**
     * Removes an object from the collection of choices.
     * 
     * @param object The object to be removed
     */
    public void remove(T object)
    {
        int index = choices.indexOf(object);
        
        total -= currentFreqs.get(index);
        choices.remove(index);
        baseFreqs.remove(index);
        currentFreqs.remove(index);
        
        while (pregenerated.remove(object)) 
            /* nothing inside loop */;
    }
    
    /**
     * Adds an object to the collection of choices. 
     * The object has a frequency of 1, which is the default frequency if
     * no frequencies were supplied for objects when this instance of 
     * RandomChooser was initialized.
     * 
     * <br /><br />
     * This method clears the history and the pre-generated values.
     * 
     * @param object The object to be added
     */
    public void add(T object)
    {
        add(object, 1);
    }
    
    /**
     * Adds an object to the collection from which objects are generated,
     * giving it the desired frequency.
     * 
     * <br /><br />
     * This method clears the history and the pre-generated values.
     * 
     * @param object the object to be added
     * @param frequency The frequency with which it is generated
     */
    public void add(T object, int frequency)
    {
        choices.add(object);
        baseFreqs.add(frequency);
        pregenerated.clear();
        refresh();
    }
    
    /**
     * Returns an unmodifiable view of the choices.
     * 
     * @return The objects this generator chooses from.
     */
    public Collection<T> getChoices()
    {
        return Collections.unmodifiableCollection(choices);
    }

    /**
     * Returns the frequency of a given object.
     * 
     * @param object The object to look up.
     * @return The integer frequency of the object.
     */
    public int getFrequency(T object)
    {
        return baseFreqs.get(choices.indexOf(object));
    }
    
    /**
     * Sets the frequency of a given object.
     * 
     * <br /><br />
     * This method clears the history and the pre-generated values.
     * 
     * @param object The object to look up.
     * @param frequency The integer frequency of the object.
     */
    public void setFrequency(T object, int frequency)
    {
        baseFreqs.set(choices.indexOf(choices), frequency);
        pregenerated.clear();
        refresh();
    }
}
