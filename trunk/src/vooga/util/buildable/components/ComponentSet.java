package vooga.util.buildable.components;

import java.util.Collection;
import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;

public class ComponentSet<IComponent> extends TreeSet<IComponent>
{

    public ComponentSet(Collection<? extends IComponent> arg0) {
		super(arg0);
	}

	public ComponentSet(Comparator<? super IComponent> arg0) {
		super(arg0);
	}

	public ComponentSet(SortedSet<IComponent> arg0) {
		super(arg0);
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
