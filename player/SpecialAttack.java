package player;

public class SpecialAttack {
	private int energyReq, atk;
	private String name, desc;
	
	public SpecialAttack(int energyReq, int atk, String name, String desc) {
		super();
		this.energyReq = energyReq;
		this.atk = atk;
		this.name = name;
		this.desc = desc;
	}
	
	public SpecialAttack() {
		this(40, 300, "Esteemed Strike", "A more powerful strike that damages the enemy greatly.");
	}

	public int getEnergyReq() {
		return energyReq;
	}

	public void setEnergyReq(int energyReq) {
		this.energyReq = energyReq;
	}
	
	public int getAtk() {
		return atk;
	}

	public void setAtk(int atk) {
		this.atk = atk;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	
}