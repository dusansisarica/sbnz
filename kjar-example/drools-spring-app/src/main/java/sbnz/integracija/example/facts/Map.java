package sbnz.integracija.example.facts;

import java.util.ArrayList;
import java.util.List;

public class Map {
	private List<Troop> allTroops = new ArrayList<>();
	private boolean[][] visited = new boolean[10][10];

	
	public Map(List<Troop> troops) {
		this.allTroops = troops;
	}
	
	public void removeTroop(Troop troop) {
		this.allTroops.remove(troop);
	}
	
	public List<Troop> getTroops(){
		return this.allTroops;
	}
	
	public boolean checkField(int x, int y) {
		for (Troop t : this.allTroops) {
			if (t.getCurrentPositionX() == x && t.getCurrentPositionY() == y) {
				return false;
			}
		}
		return true;
	}
	
	public void pathFinderVisited(int x, int y) {
		visited[x][y] = true;
	}

	public float getCost(int sx, int sy, int tx, int ty) {
		return 1;
	}
}
