package sbnz.integracija.example.facts;

public class Attack {

	private PossibleAttack attack;
	private int ret;
	private int baseDamage;
	private boolean flag = false;

	public Attack() {
	}

	public Attack(PossibleAttack attack) {
		super();
		this.attack = attack;
	}

	public PossibleAttack getAttack() {
		return attack;
	}

	public void setAttack(PossibleAttack attack) {
		this.attack = attack;
	}

	public int getRet() {
		return ret;
	}

	public void setRet(int ret) {
		this.ret = ret;
	}

	public int getBaseDamage() {
		return baseDamage;
	}

	public void setBaseDamage(int baseDamage) {
		this.baseDamage = baseDamage;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	
	 
	
	
}
