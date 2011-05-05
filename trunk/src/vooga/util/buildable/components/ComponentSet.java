package vooga.util.buildable.components;

import java.util.Collection;
import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;

public class ComponentSet extends TreeSet<IComponent>
{

	@Override
	public boolean add(IComponent n) {
		for (IComponent c : this)
        {
            if (n.equals(c)) {
            	this.remove(c);
            	break;
            }
        }
		super.add(n);
		return true;
	}

	public ComponentSet ()
    {
        super(new Comparator<IComponent>()
        {

            @Override
            public int compare (IComponent o1, IComponent o2)
            {
                if (o1.getClass().equals(o2.getClass()))
                    return 0;
                
                return o1.getClass().toString().compareTo(o2.getClass().toString());
            }}
            );
    }


}
