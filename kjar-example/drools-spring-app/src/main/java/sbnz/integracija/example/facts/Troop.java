package sbnz.integracija.example.facts;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import sbnz.integracija.example.pathFinding.AStarPathFinder;

public class Troop implements Serializable {
	private Creature creature;
	private int amount;
	private Position currentPosition;
	private List<Position> availablePositions = new ArrayList<>();
	private boolean isEnemy;
	private Map map;
	private boolean retaliation = false;
	
	public Troop() {
		super();
	}

	
	
	public Troop(Creature creature, int amount, Position currentPosition, boolean isEnemy) {
		super();
		this.creature = creature;
		this.amount = amount;
		this.currentPosition = currentPosition;
		this.isEnemy = isEnemy;
	}



	public Troop(Creature creature, int amount, Position currentPosition, List<Position> availablePositions, boolean isEnemy) {
		super();
		this.creature = creature;
		this.amount = amount;
		this.currentPosition = currentPosition;
		this.availablePositions = availablePositions;
		this.isEnemy = isEnemy;
	}

	public Position getCurrentPosition() {
		return currentPosition;
	}
	
	public int getCurrentPositionX() {
		return this.currentPosition.getPosX();
	}
	
	public int getCurrentPositionY() {
		return this.currentPosition.getPosY();
	}

	public void setCurrentPosition(Position currentPosition) {
		this.currentPosition = currentPosition;
	}

	public List<Position> getAvailablePositions() {
		return availablePositions;
	}

	public void setAvailablePositions(List<Position> availablePositions) {
		this.availablePositions = availablePositions;
	}
	
	public void addAvailablePosition(Position position) {
		this.availablePositions.add(position);
	}

	public Creature getCreature() {
		return creature;
	}

	public void setCreature(Creature creature) {
		this.creature = creature;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public boolean getisEnemy() {
		return isEnemy;
	}

	public void setEnemy(boolean isEnemy) {
		this.isEnemy = isEnemy;
	}
	
	public Position positionForAttack(List<Position> availablePositions, Position targetPosition) {
		for (int x=-1;x<2;x++) {
			for (int y=-1;y<2;y++) {
				int xp = x + targetPosition.getPosX();
				int yp = y + targetPosition.getPosY();
				if (xp < 0 || yp < 0 || xp > 9 || yp > 9) {
					continue;
				}
				Position newPosition = new Position(xp, yp);
				for (Position p : availablePositions) {
					if (p.getPosX() == xp && p.getPosY() == yp) {
						return newPosition;
					}
				}
			}
		}
		return null;
	}
	
	public void setMap(Map map) {
		this.map = map;
	}
	
	public Position getClosestEnemy() {
		return new AStarPathFinder(map, creature.getMovement()).findClosestEnemy(currentPosition.getPosX(), currentPosition.getPosY());
	}



	public boolean isRetaliation() {
		return retaliation;
	}



	public void setRetaliation(boolean retaliation) {
		this.retaliation = retaliation;
	}
	
	
	
}
