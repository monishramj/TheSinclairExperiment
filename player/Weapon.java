package player;

import npc.interactables.GameObject;

public class Weapon extends Item{
	

	private SpecialAttack special; //look at SpecialAttack class
	private int atkBuff, defBuff; //extra buffs given by the respective item
	private double critChanceBuff; //increases critChance by x percent
	private boolean isEquipped; //true if equipped, false if otherwise
	
	/* Preconditions:
	 * rarity must be one of the predefined rarities
	 * critChancBuff <= 0.95
	 */	
	public Weapon(String name, String crudeName, String description,  SpecialAttack special, int atkBuff, int defBuff,
			double critChanceBuff) {
		super(name, crudeName, description);
		this.special = special;
		this.atkBuff = atkBuff;
		this.defBuff = defBuff;
		this.critChanceBuff = critChanceBuff;
		this.isEquipped = false;
	}

	public String getName() {
		return super.getName();
	}

	public void setName(String name) {
		super.setName(name);
	}

	public String getDescription() {
		return super.getDescription();
	}

	public void setDescription(String description) {
		super.setDescription(description);
	}

	public SpecialAttack getSpecial() {
		return special;
	}

	public void setSpecial(SpecialAttack special) {
		this.special = special;
	}

	public int getAtkBuff() {
		return atkBuff;
	}

	public void setAtkBuff(int atkBuff) {
		this.atkBuff = atkBuff;
	}

	public int getDefBuff() {
		return defBuff;
	}

	public void setDefBuff(int defBuff) {
		this.defBuff = defBuff;
	}

	public double getCritChanceBuff() {
		return critChanceBuff;
	}

	public void setCritChanceBuff(double critChanceBuff) {
		this.critChanceBuff = critChanceBuff;
	}

	public boolean isEquipped() {
		return isEquipped;
	}

	public void setEquipped(boolean isEquipped) {
		this.isEquipped = isEquipped;
	}
	
}