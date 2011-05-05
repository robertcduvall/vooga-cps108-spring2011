package games.pacman.resources;

public class LevelXMLCreator {
	
	public static void main(String[] args){
		int[] points= new int[]{
			new Point(	2,2),
					new Point(	2,3),
							new Point(2,4),
									new Point(3,2),
											new Point(3,3),
													new Point(3,4),
															new Point(4,2),
																	new Point(4,3),
																			new Point(4,4),
				
				2,6,
				2,7,
				2,8,
				2,9,
				3,6,
				3,7,
				3,8,
				3,9,
				4,6,
				4,7,
				4,8,
				4,9,
				
				1,11,
				1,12,
				1,13,
				1,14,				
				2,11,
				2,12,
				2,13,
				2,14,
				3,11,
				3,12,
				3,13,
				3,14,
				4,11,
				4,12,
				4,13,
				4,14,

				6,1,
				6,2,
				6,3,
				7,1,
				7,2,
				7,3,
				8,1,
				8,2,
				8,3,
				9,1,
				9,2,
				9,3,
				10,1,
				10,2,
				10,3,
				10,4,
				10,5,
				10,6,
				10,7,
				
				6,7,
				6,5,
				6,6,
				7,7,
				7,5,
				7,6,
				8,7,
				8,5,
				8,6,
				
				6,9,
				6,10,
				6,11,
				6,12,
				6,13,
				6,14,
				
				7,9,
				7,10,
				7,11,
				7,12,
				7,13,
				7,14,

				
				9,9,
				9,10,
				9,11,
				9,12,
				9,13,
				9,14,

		};
		for(int i=0;i<points.length;i=i+2){
			int x = points[i+1]-1;
			int y = points[i]-1;

			System.out.println("<instance type=\"wall\">" +
					"<x>"+x+"</x>" +
							"<y>"+y+"</y></instance>");
		}
	}
	
	private class Point{
		public int x;
		public int y;
		
		public Point(int x, int y){
			this.x=x;
			this.y=y;
		}
	}
}
