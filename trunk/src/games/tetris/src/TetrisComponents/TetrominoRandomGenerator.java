package games.tetris.src.TetrisComponents;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Random;

public class TetrominoRandomGenerator extends Random
{
	private static final long serialVersionUID = 1L;
	private Map<Integer,LinkedList<Integer>> numberBag = new HashMap<Integer, LinkedList<Integer>>();
	
	@Override
	public int nextInt(int n)
	{
		if(!numberBag.containsKey(n))
		{
			numberBag.put(n, new LinkedList<Integer>());
		}
		
		if(numberBag.get(n).isEmpty())
		{
			for(int i = 0; i < n; i++)
			{
				numberBag.get(n).addLast(i);
			}
			Collections.shuffle(numberBag.get(n));
		}
		
		return numberBag.get(n).removeFirst();
	}
}
