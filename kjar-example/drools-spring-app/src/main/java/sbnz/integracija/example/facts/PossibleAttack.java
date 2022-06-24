package sbnz.integracija.example.facts;

public class PossibleAttack {
	private Troop attacker;
	private Troop victim;
	private Position position;
	private Position movePosition;
	private boolean attacked;
	private boolean range;
	
	public PossibleAttack() {
		super();
	}

	public PossibleAttack(Troop attacker, Troop victim, Position position, Position movePosition, boolean attack, boolean range) {
		super();
		this.attacker = attacker;
		this.victim = victim;
		this.position = position;
		this.movePosition = movePosition;
		this.attacked = attack;
		this.range = range;
	}

	public Troop getAttacker() {
		return attacker;
	}

	public void setAttacker(Troop attacker) {
		this.attacker = attacker;
	}

	public Troop getVictim() {
		return victim;
	}

	public void setVictim(Troop victim) {
		this.victim = victim;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public boolean isAttack() {
		return attacked;
	}

	public void setAttack(boolean attacked) {
		this.attacked = attacked;
	}

	public boolean isAttacked() {
		return attacked;
	}

	public void setAttacked(boolean attacked) {
		this.attacked = attacked;
	}

	public boolean isRange() {
		return range;
	}

	public void setRange(boolean range) {
		this.range = range;
	}

	public Position getMovePosition() {
		return movePosition;
	}

	public void setMovePosition(Position movePosition) {
		this.movePosition = movePosition;
	}
	
	
	
	
	
}
