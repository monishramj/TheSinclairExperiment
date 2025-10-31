package player;

import java.util.Random;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JTextField;
import java.util.random.*;


public class Battle {
	
	public static Scanner sc = new Scanner(System.in);
	
	//JFrame frame = new JFrame();
	//JTextField text = new JTextField("text",20);
	
	/*
	public Battle(Player p, Enemy e) //messing with JFrames
	{		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Battle");
		frame.setSize(420, 420);
		frame.setLayout(null);
		frame.setVisible(true);
		frame.add(text);
		
	}
	*/
	
	public static String healthBar(int health, int maxHealth) //outputs a string representation of a health bar
	{
		double healthRatio = (((double)(health))/maxHealth)*8; //gives the original ratio in eight sections
		
		String healthBar = "[";
		
		for(int i = 0; i < 8; i++)
		{
			if (healthRatio <= 0)
			{
				healthBar += "░";
			}
			else if (healthRatio >= 1)
			{
					healthRatio--;
					healthBar += "█"; //if this eighth is full (>=1) add a full box
			}
			else if (healthRatio < 1)
			{
				if (healthRatio <= 3.0/4 && healthRatio >= 1.0/4)
				{
					healthRatio--;
					healthBar += "▌"; //if this eighth is between a quarter and three quarters full, add a half box
				}
				else
				{
					healthRatio--;
					healthBar += "░";
				}
				
			}
		}
		return healthBar + "]" + health + "/" + maxHealth +" hp";
	}
	
	public static String energyBar(int energy, int maxEnergy) //outputs a string representation of a energy bar
	{
		double energyRatio = (((double)(energy))/maxEnergy)*8; //gives the original ratio in eight sections
		
		String energyBar = "[";
		
		for(int i = 0; i < 8; i++)
		{
			if (energyRatio <= 0)
			{
				energyBar += "░";
			}
			else if (energyRatio >= 1)
			{
					energyRatio--;
					energyBar += "█"; //if this eighth is full (>=1) add a full box
			}
			else if (energyRatio < 1)
			{
				if (energyRatio <= 3.0/4 && energyRatio >= 1.0/4)
				{
					energyRatio--;
					energyBar += "▌"; //if this eighth is between a quarter and three quarters full, add a half box
				}
				else
				{
					energyRatio--;
					energyBar += "░";
				}
				
			}
		}
		return energyBar + "]" + energy + "/" + maxEnergy +" energy";
	}
	
	public static boolean play(Player p, Enemy e)
	{	
		int rounds = 1;
		
		while(p.getHealth() > 0 && e.getHealth() > 0)
		{
			System.out.println("Round " + rounds + "\n");
			System.out.println(e.getName() + "\n"
								+ healthBar(e.getHealth(), e.getMaxHealth()) + "\n"
								+ energyBar(e.getEnergy(), e.getMaxEnergy()) + "\n\n"
								+ "Father " +  p.getName() + "\n"
								+ healthBar(p.getHealth(), p.getMaxHealth()) + "\n"
								+ energyBar(p.getEnergy(), p.getMaxEnergy()) + "\n\n");
			System.out.println("Options:\n"
								+ "0) INFO\n" //displays the function of each command
								+ "1) Normal Attack\n" //we need a better name
								+ "2) " + p.getSpecial().getName() + "\n" //this attack should get stronger as progression happens
								+ "3) Skip Turn (Charges Special)\n" //charges special
								+ "4) Flee (might fail)"); //50% chance of not fleeing
			
			int enemyChoice = 1; //Enemy can either normal attack or special attack
			
			if(e.getEnergy() >= e.getMaxEnergy())
			{
				enemyChoice = 2;
				e.setEnergy(e.getEnergy() - e.getMaxEnergy());
			}
			boolean isInt = true; //assume the input is an int
			
			boolean validAnswer = false;
			
			int playerChoice = -1;
			
	    	while (!validAnswer) {
	    	    try {
	    	        playerChoice = sc.nextInt(); //reads player's choice from system.in	
	    	        if (playerChoice == 0 || playerChoice == 1 || playerChoice == 2 || 
	    	        	playerChoice == 3 || playerChoice == 4) 
	    	            validAnswer = true;
	    	        else
	    	            System.out.println("Invalid input. Please input an integer from 0 to 4");
	    	    }
	    	    
	    	    catch(Exception ex) {
	    	        System.out.println("Invalid input. Please input an integer from 0 to 4");
	    	        sc.nextLine(); 
	    	    }
	    	}
			
			int eDmg;
			int pDmg;
			
			if(playerChoice == 0)
			{
				System.out.println("Options:\n"
						+ "0) INFO (displays this info page) \n"
						+ "1) Normal Attack (deals the normal amount of damage with a chance to miss) \n"
						+ "2) " + p.getSpecial().getName() + " (deals more than the normal amount of damage with a chance to miss)\n"
						+ "3) Skip Turn (Skips your turn and charges up the special attack twice as fast as usual)\n"
						+ "4) Flee (might fail)");
				continue;
			}
			
			if(playerChoice == 2 && p.getEnergy() < p.getMaxEnergy())
			{
				System.out.println("You don't have enough energy, silly!");
				continue;
			}
			
			Random random = new Random();
			//double randomGaussian = random.nextGaussian(x.getAtk(), x.getAtk()/10);
			//generates a random value from a normal distribution with a mean of the player's atk and a standard deviation of 10% of that atk
			//x can be either the player or enemy
			
			boolean eCrit = Math.random() <= e.getCritChance(); //if this Math.random() is greater than the crit chance, it is a critical hit
			boolean pCrit = Math.random() <= p.getCritChance();
			
			boolean eMissed = Math.random() <= 0.06; //6% chance of missing an attack
			boolean pMissed = Math.random() <= 0.06;
			
			boolean triedToFlee = false; //only the player can try to flee
			
			boolean eUsedSpecial = false;
			boolean pUsedSpecial = false;
			
			if(playerChoice == 4)
			{	
				if(Math.random() >= 0.8)
				{
					System.out.println("Father " + p.getName() + " tried to get away! \n" +
										"And it succeeded! \n");
					return true;
				}
				else
				{
					playerChoice = 3;
					triedToFlee = true;
					System.out.println("Father " + p.getName() + " tried to get away! \n" +
										"But it failed... \n");
				}
			}

			
			if(eMissed)
			{
				System.out.println(e.getName() + "'s attack missed! \n");
			}
			else if(eCrit && enemyChoice == 1)
			{
				eDmg = (int)(random.nextGaussian(e.getAtk(), e.getAtk()/10)*2);
				p.takeDamage(eDmg);
				
				System.out.println(e.getName() + " used CLAW\n"
									+ "it's a CRITICAL HIT and it dealt " + eDmg + " dmg to Father " + p.getName() + "! \n");
			}
			else if(eCrit && enemyChoice == 2)
			{
				eDmg = (int)(random.nextGaussian(e.getSpecial().getAtk(), e.getSpecial().getAtk()/10)*2);
				p.takeDamage(eDmg);
				eUsedSpecial = true;
				e.setEnergy(e.getEnergy() - e.getMaxEnergy());
				System.out.println(e.getName() + " used " + e.getSpecial().getName() + "\n"
									+ "it's a CRITICAL HIT and it dealt " + eDmg + " dmg to Father " + p.getName() + "! \n");
				System.out.println(e.getSpecial().getDesc());
			}
			else if(enemyChoice == 1)
			{
				eDmg = (int)(random.nextGaussian(e.getAtk(), e.getAtk()/10));
				p.takeDamage(eDmg);
				System.out.println(e.getName() + " used CLAW\n"
									+ "it dealt " + eDmg + " dmg to Father " + p.getName() + "! \n");
			}
			else if(enemyChoice == 2)
			{
				eDmg = (int)(random.nextGaussian(e.getSpecial().getAtk(), e.getSpecial().getAtk()/10));
				p.takeDamage(eDmg);
				eUsedSpecial = true;
				e.setEnergy(e.getEnergy() - e.getMaxEnergy());
				System.out.println(e.getName() + " used " + e.getSpecial().getName() + "\n"
									+ "it dealt " + eDmg + " dmg to Father " + p.getName() + "! \n");
				System.out.println(e.getSpecial().getDesc());
			}
			
			
			if(playerChoice == 3)
			{
				System.out.println("Father " + p.getName() + " skipped their turn \n");
				if(!triedToFlee)
					p.setEnergy(p.getEnergy() + 20);
			}
			else if(pMissed)
			{
				System.out.println("Father " + p.getName() + "'s attack missed! \n");
				
				if(playerChoice == 2)
				{
					p.setEnergy(p.getEnergy() - p.getMaxEnergy());
				}
			}
			else if(pCrit && playerChoice == 1)
			{
				pDmg = (int)(random.nextGaussian(p.getAtk(), p.getAtk()/10)*2);
				e.takeDamage(pDmg);
				System.out.println("Father " + p.getName() + " used Normal Attack\n"
									+ "it's a CRITICAL HIT and it dealt " + pDmg + " dmg to " + e.getName() + "! \n");
			}
			else if(pCrit && playerChoice == 2)
			{
				pDmg = (int)(random.nextGaussian(p.getSpecial().getAtk(), p.getSpecial().getAtk()/10)*2);
				e.takeDamage(pDmg);
				pUsedSpecial = true;
				p.setEnergy(p.getEnergy() - p.getMaxEnergy());
				System.out.println("Father " + p.getName() + " used " + p.getSpecial().getName() + "\n"
									+ "it's a CRITICAL HIT and it dealt " + pDmg + " dmg to " + e.getName() + "! \n");
				System.out.println(p.getSpecial().getDesc());
			}
			else if(playerChoice == 1)
			{
				pDmg = (int)(random.nextGaussian(p.getAtk(), p.getAtk()/10));
				e.takeDamage(pDmg);
				System.out.println("Father " + p.getName() + " used Normal Attack\n"
									+ "it dealt " + pDmg + " dmg to " + e.getName() + "! \n");
			}
			else if(playerChoice == 2)
			{
				pDmg = (int)(random.nextGaussian(p.getSpecial().getAtk(), p.getSpecial().getAtk()/10));
				e.takeDamage(pDmg);
				pUsedSpecial = true;
				p.setEnergy(p.getEnergy() - p.getMaxEnergy());
				System.out.println("Father " + p.getName() + " used " + p.getSpecial().getName() + "\n"
									+ "it dealt " + pDmg + " dmg to " + e.getName() + "! \n");
				System.out.println(p.getSpecial().getDesc());
			}
			
			if(!eUsedSpecial) {e.setEnergy(e.getEnergy() + 20);}
			if(!pUsedSpecial) {p.setEnergy(p.getEnergy() + 20);} //only increments energy if the special wasn't called
			
			rounds++;
			
			if (e.getHealth() <= 0) {e.setHealth(0);} //making sure no one ends up with negative health
			if (p.getHealth() <= 0) {p.setHealth(0);}
			
		}
		
		System.out.println(e.getName() + "\n"
				+ healthBar(e.getHealth(), e.getMaxHealth()) + "\n"
				+ energyBar(e.getEnergy(), e.getMaxEnergy()) + "\n\n"
				+ "Father " +  p.getName() + "\n"
				+ healthBar(p.getHealth(), p.getMaxHealth()) + "\n"
				+ energyBar(p.getEnergy(), p.getMaxEnergy()) + "\n\n");
		
		if (p.getHealth() == 0 && e.getHealth() == 0) {
			System.out.println("In a close round, you barely made it by the" + e.getName() + ". You may continue on your journey.\n");
			p.setHealth(p.getMaxHealth());
			p.setEnergy(0);
			return true;
		}
		else if(p.getHealth() == 0) //if both parties simultaneously die, the enemy has the advantage
		{
			System.out.println("You have been defeated by the " + e.getName() + ". You have failed your mission.\n");
			p.setHealth(p.getMaxHealth());
			p.setEnergy(0);
			return false;
		}
		else if (e.getHealth() == 0) 
		{
			System.out.println("You have defeated the " + e.getName() + ". You may continue on your journey.\n");
			p.setHealth(p.getMaxHealth());
			p.setEnergy(0);
			return true;
		}
		
		return false;
		
	}
	
}