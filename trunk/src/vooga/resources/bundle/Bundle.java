package vooga.resources.bundle;

import java.net.URL;
import java.util.*;


/**
 * Bundle class that represents key/value pairs.
 * Modified from the original ResourceManager class.
 * 
 * @author Robert C. Duvall
 * @author Sterling Dorminey
 */
public class Bundle implements Iterable<String>
{
    public static final String DEFAULT_DELIMITER = ",";
    private static final String MISSING_RESOURCE = "Missing resource file: %s.%s";
    private Map<String, String> strings;


    public Bundle ()
    {
    	strings = new HashMap<String, String>();
    }

    /**
     * Add an item to the "resource bundle."
     * @param key the key name for the value.
     * @param value the value of the entry.
     */
    public void addEntry(String key, String value) {
    	strings.put(key, value);
    }
    
    /**
     * Returns value associated with given key as a boolean value.
     */
    public boolean getBoolean (String key)
    {
        return getString(key).equalsIgnoreCase("true");
    }


    /**
     * Returns value associated with given key as a integer value.
     */
    public int getInteger (String key)
    {
        return Integer.parseInt(getString(key));
    }


    /**
     * Returns value associated with given key as a double value.
     */
    public double getDouble (String key)
    {
        return Double.parseDouble(getString(key));
    }


    /**
     * Returns value associated with given key as a string value.
     */
    public String getString (String key)
    {
    	return strings.get(key);
    }


    /**
     * Returns value associated with given key as a formatted string, 
     * with the given values filling in the format arguments.
     */
    public String getFormattedString (String key, Object ... values)
    {
        return String.format(getString(key), values);
    }
    
    
    /**
     * Returns value associated with given key as an array of boolean values,
     * using DEFAULT_DELIMITER as the separator between properties.
     */
    public boolean[] getBooleanArray (String key)
    {
        return getBooleanArray(key, DEFAULT_DELIMITER);
    }


    /**
     * Returns value associated with given key as an array of boolean values,
     * using given delimiter as the separator between properties.
     */
    public boolean[] getBooleanArray (String key, String delimeter)
    {
        String[] properties = getString(key).split(delimeter);
        boolean[] results = new boolean[properties.length];
        for (int k = 0; k < results.length; k++)
        {
            results[k] = properties[k].equalsIgnoreCase("true");
        }
        return results;
    }


    /**
     * Returns value associated with given key as an array of integer values,
     * using DEFAULT_DELIMITER as the separator between properties.
     */
    public int[] getIntegerArray (String key)
    {
        return getIntegerArray(key, DEFAULT_DELIMITER);
    }


    /**
     * Returns value associated with given key as an array of integer values,
     * using given delimiter as the separator between properties.
     */
    public int[] getIntegerArray (String key, String delimeter)
    {
        String[] properties = getString(key).split(delimeter);
        int[] results = new int[properties.length];
        for (int k = 0; k < results.length; k++)
        {
            results[k] = Integer.parseInt(properties[k]);
        }
        return results;
    }


    /**
     * Returns value associated with given key as an array of double values,
     * using DEFAULT_DELIMITER as the separator between properties.
     */
    public double[] getDoubleArray (String key)
    {
        return getDoubleArray(key, DEFAULT_DELIMITER);
    }


    /**
     * Returns value associated with given key as an array of double values,
     * using given delimiter as the separator between properties.
     */
    public double[] getDoubleArray (String key, String delimeter)
    {
        String[] properties = getString(key).split(delimeter);
        double[] results = new double[properties.length];
        for (int k = 0; k < results.length; k++)
        {
            results[k] = Double.parseDouble(properties[k]);
        }
        return results;
    }


    /**
     * Returns value associated with given key as an array of string values,
     * using DEFAULT_DELIMITER as the separator between properties.
     */
    public String[] getStringArray (String key)
    {
        return getStringArray(key, DEFAULT_DELIMITER);
    }


    /**
     * Returns value associated with given key as an array of string values,
     * using given delimiter as the separator between properties.
     */
    public String[] getStringArray (String key, String delimeter)
    {
        return getString(key).split(delimeter);
    }


    /**
     * Iterate through all resources in this bundle.
     */
    @Override
	public Iterator<String> iterator ()
    {
        return strings.keySet().iterator();
    }

    /**
     * Get a file name based on the location of the given object.
     */
    public URL getFile (Object root, String path)
    {
        return root.getClass().getResource(path);
    }


    /**
     * Returns the name of the class to use in determining which resource 
     * file to use.
     */
    protected String getClassName (Object target)
    {
        return target.getClass().getSimpleName();
    }
}
