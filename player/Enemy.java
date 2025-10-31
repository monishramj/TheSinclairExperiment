package player;

public class Enemy {
	
	private String name;
	private int atk, health, maxHealth, energy, maxEnergy; //def does not apply to enemies
	private double critChance;
	private SpecialAttack special;
	
	public Enemy(String name, int atk, int health, int maxHealth, double critChance, SpecialAttack special) {
		super();
		this.name = name;
		this.atk = atk;
		this.health = health;
		this.maxHealth = maxHealth;
		this.energy = 0; //enemies start out with 0 energy in each battle
		this.maxEnergy = special.getEnergyReq();
		this.critChance = critChance;
		this.special = special;
	}
	
	public String takeDamage(int dmg) //need to establish that below 0 not good
	{
		this.health -= dmg;
		return this.name + " has lost" + dmg + " hp";
	}
	
	public String heal(int pts) //need to establish that above maxHealth not possible
	{
		this.health += pts;
		return this.name + " has gained" + pts + " hp";
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAtk() {
		return atk;
	}

	public void setAtk(int atk) {
		this.atk = atk;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public int getMaxHealth() {
		return maxHealth;
	}

	public void setMaxHealth(int maxHealth) {
		this.maxHealth = maxHealth;
	}

	public int getEnergy() {
		return energy;
	}

	public void setEnergy(int energy) {
		this.energy = energy;
	}

	public int getMaxEnergy() {
		return maxEnergy;
	}

	public void setMaxEnergy(int maxEnergy) {
		this.maxEnergy = maxEnergy;
	}

	public double getCritChance() {
		return critChance;
	}

	public void setCritChance(double critChance) {
		this.critChance = critChance;
	}

	public SpecialAttack getSpecial() {
		return special;
	}

	public void setSpecial(SpecialAttack special) {
		this.special = special;
	}
	
}