package vooga.collisions.collisionCheckers;

import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.HashMap;

import vooga.collisions.shapes.Polygon;
import vooga.collisions.shapes.Side;


public class PolygonCollider 
{
	HashMap<Polygon, Side[]> polygonToLineMap;

	public PolygonCollider(ArrayList<Polygon> polygonList)
	{
		polygonToLineMap = new HashMap<Polygon, Side[]>();

		for(Polygon comp : polygonList)
		{
			polygonToLineMap.put(comp, comp.getSides());
		}
	}

	public void addPolygon(Polygon addPolygon)
	{
		polygonToLineMap.put(addPolygon, addPolygon.getSides());
	}

	public void removePolygon(Polygon removePolygon)
	{
		polygonToLineMap.remove(removePolygon);
	}

	public HashMap<Polygon, Side[]> collisions()
	{
		HashMap<Polygon, Side[]> collisionsMap = new HashMap<Polygon, Side[]>();
		Polygon[] polygonArray = (Polygon[]) polygonToLineMap.keySet().toArray();

		for(int i = 0; i < polygonToLineMap.size(); i++)
		{
			for(int j = i + 1; j < polygonToLineMap.size(); j++)
			{
				Side[][] collisions = advancedCollisionCheck(polygonArray[i], polygonArray[j]);
				collisionsMap.put(polygonArray[i], collisions[0]);
				collisionsMap.put(polygonArray[j], collisions[1]);
			}
		}
		return collisionsMap;
	}

	
	
	public Side[][] advancedCollisionCheck(Polygon p1, Polygon p2)
	{
		Side[] p1Sides = polygonToLineMap.get(p1);
		Side[] p2Sides = polygonToLineMap.get(p2);

		ArrayList<Side> p1CollisionList = new ArrayList<Side>();
		ArrayList<Side> p2CollisionList = new ArrayList<Side>();

		for(int i = 0; i < p1Sides.length; i++)
		{
			for(int j = 0; i < p2Sides.length; j++)
			{
				if(p1Sides[i].intersects(p2Sides[j]))
				{
					p1CollisionList.add(p1Sides[i]);
					p2CollisionList.add(p2Sides[j]);
				}
			}
		}

		Side[][] returnArray = new Side[2][p1CollisionList.size()];
		
		returnArray[0] = (Side[]) p1CollisionList.toArray();
		returnArray[1] = (Side[]) p2CollisionList.toArray();
		
		return returnArray;
	}
	
	public boolean simpleCollisionCheck(Polygon p1, Polygon p2)
	{
		Side[] p1Sides = polygonToLineMap.get(p1);
		Side[] p2Sides = polygonToLineMap.get(p2);
		
		for(int i = 0; i < p1Sides.length; i++)
		{
			for(int j = 0; i < p2Sides.length; j++)
			{
				if(p1Sides[i].intersects(p2Sides[j]))
				{
					return true;
				}
			}
		}
		return false;
	}
}
