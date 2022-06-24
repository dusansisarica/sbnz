package sbnz.integracija.example.facts;

public class Creature {
	private String name;
	private int att;
	private int def;
	private int hp;
	private int speed;
	private int movement;
	private boolean isShooter;
	
	public Creature() {
		super();
	}

	public Creature(String name, int att, int def, int hp, int speed, int movement, boolean isShooter) {
		super();
		this.name = name;
		this.att = att;
		this.def = def;
		this.hp = hp;
		this.speed = speed;
		this.movement = movement;
		this.isShooter = isShooter;
	}

	public boolean getisShooter() {
		return isShooter;
	}

	public void setShooter(boolean isShooter) {
		this.isShooter = isShooter;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAtt() {
		return att;
	}

	public void setAtt(int att) {
		this.att = att;
	}

	public int getDef() {
		return def;
	}

	public void setDef(int def) {
		this.def = def;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getMovement() {
		return movement;
	}

	public void setMovement(int movement) {
		this.movement = movement;
	}
	
	
}
