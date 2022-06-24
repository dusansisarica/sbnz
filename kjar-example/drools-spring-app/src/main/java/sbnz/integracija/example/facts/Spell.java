package sbnz.integracija.example.facts;

public class Spell {
	
	public enum SpellType {
		FIRE_MAGIC, WATER_MAGIC, AIR_MAGIC, EARTH_MAGIC, ALL_SCHOOLS
	}
	
	public String name;
	public String description;
	public int spellPoints;
	public int dmg;
	public SpellType spellType;
	
	public Spell() {
		super();
	}

	public Spell(String name, String description, int spellPoints, int dmg, SpellType spellType) {
		super();
		this.name = name;
		this.description = description;
		this.spellPoints = spellPoints;
		this.dmg = dmg;
		this.spellType = spellType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getSpellPoints() {
		return spellPoints;
	}

	public void setSpellPoints(int spellPoints) {
		this.spellPoints = spellPoints;
	}

	public int getDmg() {
		return dmg;
	}

	public void setDmg(int dmg) {
		this.dmg = dmg;
	}

	public SpellType getSpellType() {
		return spellType;
	}

	public void setSpellType(SpellType spellType) {
		this.spellType = spellType;
	}
	
	
	
	
}
