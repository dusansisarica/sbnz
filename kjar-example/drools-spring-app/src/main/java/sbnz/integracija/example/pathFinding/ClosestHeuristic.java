package sbnz.integracija.example.pathFinding;

public class ClosestHeuristic implements AStarHeuristic {
	
	public float getCost(int x, int y, int tx, int ty) {		
		float dx = tx - x;
		float dy = ty - y;
		
		float result = (float) (Math.sqrt((dx*dx)+(dy*dy)));
		
		return result;
	}
}
