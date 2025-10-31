package player;

import java.util.ArrayList;
import java.util.Scanner;

public class Player {
	
	public static Scanner sc = new Scanner(System.in);
	private String name; //The player's desired name
	private String status; //any status effects applied to the player (default: Normal)
	private int atk; //Attack pts that define how much damage an attack can do
	private int def; //extra amount added to health and maxHealth
	private int health, maxHealth; // hp is health/maxHealth
	private int energy, maxEnergy; // energy == maxEnergy to pull off a special attack (item specific)
	private SpecialAttack special;
	private double critChance; //how likely to pull off a critical hit (deals 1.5x dmg)
	
	private ArrayList<Item> regularInventory = new ArrayList<Item>();
	
	public ArrayList<Item> getRegularInventory() {
		return regularInventory;
	}

	public void setRegularInventory(ArrayList<Item> regularInventory) {
		this.regularInventory = regularInventory;
	}
	
	public void addRegularInventory(Item x) {
		regularInventory.add(x);
	}
	
	public void removeRegularInventory(String x) {
		for (int i = 0; i < regularInventory.size(); i++) {
            if (regularInventory.get(i).getCrudeName().equalsIgnoreCase(x)) {
                regularInventory.remove(i);
                break; // Exit the loop once a match is found
            }
		}
	}
	
	public String displayRegularInventory() {
	    if (regularInventory.size() == 0)
	        return "Regular Inventory: [Empty]";
	    
	    StringBuilder all = new StringBuilder();
	    for (int i = 0; i < regularInventory.size(); i++) {
	        if (i == 0) {
	            all.append(regularInventory.get(i).getName() + "(referenced by " + regularInventory.get(i).getCrudeName() + ")");
	        } else {
	            all.append(", ").append(regularInventory.get(i).getName() + "(referenced by " + regularInventory.get(i).getCrudeName() + ")");
	        }
	    }
	    return "Regular Inventory: [" + all.toString() + "]";
	}
	
	public void sort() { //sorts alphabetically
		
		System.out.println(displayRegularInventory());
        regularInventory.sort((o1, o2)
                  -> o1.getName().compareTo(
                      o2.getName()));
        System.out.println("in alphabetical order");
        System.out.println(displayRegularInventory());
	}
	
	public void searchRegInventory() {
	    System.out.println("What Item would you like to find in your regular inventory? (Reference the upper case names)");
	    boolean validAnswer = false;
	    String playerChoice = "";
	            
	    while (!validAnswer) {
	        try {
	            playerChoice = sc.next(); // reads player's choice from system.in  
	            for (int i = 0; i < regularInventory.size(); i++) {
	                if (regularInventory.get(i).getCrudeName().equalsIgnoreCase(playerChoice)) {
	                    System.out.println(regularInventory.get(i).toString());
	                    validAnswer = true; //set validAnswer to true only if a match is found
	                    break; //exit the loop once a match is found
	                }
	            }
	            if (!validAnswer) {
	                System.out.println("Are you tripping? " + playerChoice + " not found in inventory bro\n\n");
	            }
	        } catch (Exception ex) {
	            System.out.println("Are you tripping? Type the name of an item in your inventory, bro..\n\n");
	            sc.nextLine();
	        }
	    }
	}

	
	private ArrayList<Weapon> weaponInventory;
	
	public Player(String name) {
		super();
		this.name = name;
		this.status = "Normal";
		this.atk = 100;
		this.def = 100;
		this.critChance = 0.05;
		this.health = 100 + def;
		this.maxHealth = 100 + def;
		this.special = new SpecialAttack();
		this.maxEnergy = special.getEnergyReq();
		this.weaponInventory = new ArrayList<Weapon>();
	}
	
	/* PLAYER CAN ONLY EQUIP ONE WEAPON
	 *
	 */
	public String equipWeapon(Weapon x) {
		
		if(weaponInventory.indexOf(x) < 0)
		{
			return "Are you tripping?" + x.getName() + " not found in inventory bro\n\n";
		}
		
		atk += x.getAtkBuff();
		
		double healthRatio = ((double)health)/maxHealth; //keeps track of the current health/maxHealth ratio
		maxHealth -= def; //gets rid of current holistic DefBuff's effect on maxHealth
		
		def += x.getDefBuff();
		
		maxHealth = 100 + def; //sets new value of maxHealth by adding back the new def to the original value of 100;
		health = (int)(maxHealth*healthRatio);
		 
		critChance += x.getCritChanceBuff();
		critChance = roundAvoid(critChance,2); //truncates critChance to two decimals
		
		double energyRatio = ((double)energy)/maxEnergy; //keeps track of current energy ratio
		maxEnergy = x.getSpecial().getEnergyReq();
		energy = (int)(maxEnergy*energyRatio); //sets new value of energy by multiplying the original ratio to the new denominator
		
		special = x.getSpecial(); //sets the player's special to the item's special
		
		x.setEquipped(true);
		return x.getName() + " equipped\n\n";
	}
	
	public String unequipWeapon(Weapon x) {
		
		if(weaponInventory.indexOf(x) < 0)
		{
			return "Are you tripping?" + x.getName() + " not found in inventory bro\n\n";
		}
		
		atk -= x.getAtkBuff();
		
		double healthRatio = ((double)health)/maxHealth; //keeps track of the current health/maxHealth ratio
		maxHealth -= def;
		
		def -= x.getDefBuff();
		
		maxHealth = 100 + def; //sets new value of maxHealth by adding back the new def to the original value of 100;
		health = (int)(maxHealth*healthRatio); //sets health to an integer value of the previous ratio multiplied to the new maxHealth
		
		critChance -= x.getCritChanceBuff();
		critChance = roundAvoid(critChance,2); //truncates critChance to two decimals
		
		double energyRatio = ((double)energy)/maxEnergy; //keeps track of current energy ratio
		maxEnergy = x.getSpecial().getEnergyReq();
		energy = (int)(maxEnergy*energyRatio); //sets new value of energy by multiplying the original ratio to the new denominator
		
		special = new SpecialAttack(); //sets player's special to default special
		
		x.setEquipped(false);
		return x.getName() + " unequipped\n\n";
	}
	
	public String pickUp(Weapon x) {
		weaponInventory.add(x);
		return "[" + x.getName() + "] " + " \n"
				+ "ATK= " + x.getAtkBuff() + ", DEF= " + x.getDefBuff() + ", Crit Chance=" + x.getCritChanceBuff() + "\n\n"
				+ x.getDescription() + "\nThis item was placed in your inventory\n\n";
	}
	
	public String drop(Weapon x) {
		for(int i = 0; i < weaponInventory.size(); i++)
		{
			if(weaponInventory.get(i).equals(x))
			{
				if(weaponInventory.get(i).isEquipped())
					unequipWeapon(weaponInventory.get(i));
				weaponInventory.remove(i);
				return "[" + x.getName() + "] " + " \n"
						+ "ATK= " + x.getAtkBuff() + ", DEF= " + x.getDefBuff() + ", Crit Chance=" + x.getCritChanceBuff() + "\n\n"
						+ x.getDescription() +  "\n\nThis item was dropped from your inventory\n\n";
			}
		}
		return "ITEM NOT FOUND";
	}

	public String toString() {
		String equipped = "";
		
		for(int i = 0; i < weaponInventory.size(); i++)
		{
			if(weaponInventory.get(i).isEquipped())
				equipped += weaponInventory.get(i).getName();
		}
		
		if(equipped.equals(""))
			equipped = "nothing";
		return "[Father " + name + "] STATUS:" + status + "\n"
				+ "ATK= " + atk + ", DEF= " + def + ", Crit Chance=" + critChance + ", Health= " + health + "/" + maxHealth + "\n\n"
				+ "[Currently Equipped] "
				+ equipped + "\n\n";
	}
	
	public String takeDamage(int dmg) //need to establish that below 0 not good
	{
		this.health -= dmg;
		return "Father " + this.name + " has lost" + dmg + " hp";
	}
	
	public String heal(int pts) //need to establish that above maxHealth not good
	{
		this.health += pts;
		return "Father " + this.name + " has gained" + pts + " hp";
	}
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getAtk() {
		return atk;
	}

	public void setAtk(int atk) {
		this.atk = atk;
	}

	public int getDef() {
		return def;
	}

	public void setDef(int def) {
		this.def = def;
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

	public SpecialAttack getSpecial() {
		return special;
	}

	public void setSpecial(SpecialAttack special) {
		this.special = special;
	}

	public double getCritChance() {
		return critChance;
	}

	public void setCritChance(double critChance) {
		this.critChance = critChance;
	}

	public ArrayList<Weapon> getWeaponInventory() {
		return weaponInventory;
	}

	public void setWeaponInventory(ArrayList<Weapon> weaponInventory) {
		this.weaponInventory = weaponInventory;
	}

	//truncates value to places number of decimals (USE WITH CAUTION)
	public static double roundAvoid(double value, int places) {
	    double scale = Math.pow(10, places);
	    return Math.round(value * scale) / scale;
	}
	
}