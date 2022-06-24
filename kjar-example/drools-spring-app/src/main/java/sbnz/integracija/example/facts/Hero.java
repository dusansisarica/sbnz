package sbnz.integracija.example.facts;

import java.util.ArrayList;
import java.util.List;

public class Hero {
	private int att;
	private int def;
	private int spellPoints;
	private List<Spell> learnedSpells = new ArrayList<>();
	private List<Troop> troops = new ArrayList<>();
	
	public Hero() {
		super();
	}

	public Hero(int att, int def, int spellPoints, List<Spell> learnedSpells, List<Troop> troops) {
		super();
		this.att = att;
		this.def = def;
		this.spellPoints = spellPoints;
		this.learnedSpells = learnedSpells;
		this.troops = troops;
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

	public int getSpellPoints() {
		return spellPoints;
	}

	public void setSpellPoints(int spellPoints) {
		this.spellPoints = spellPoints;
	}

	public List<Spell> getLearnedSpells() {
		return learnedSpells;
	}

	public void setLearnedSpells(List<Spell> learnedSpells) {
		this.learnedSpells = learnedSpells;
	}

	public List<Troop> getTroops() {
		return troops;
	}

	public void setTroops(List<Troop> troops) {
		this.troops = troops;
	}
	
	
	
}
