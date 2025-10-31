package locations;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import npc.interactables.*;
import player.*;
import util.Room;
import util.TextFont;

public class Basement {
	private static Room[][] basement;
	static int x = 2;
	static int y = 2;
	private static Scanner sc = new Scanner(System.in);
	
	private static Room[][] createBasement(WhiteLady lady) {
		Room[][] basement = new Room[3][5];
		
		//you find yourself at...
		basement[2][0] = new Room("The Chamber", "the first of three rooms. There's a sign calling it the Chamber.", 
			"You sense multiple vengeful and hostile presences. The room is dimly lit and iron bars line the walls like prison cells", null, null, null);
		basement[2][1] = new Room("Hallway no.1", "hallway no.1. This hallway should lead you to the Chamber.",
			"You make out faint scratch marks on the walls that get more and more frequent as you approach the Chamber.\n"
			+ "Multiple ominous presences get stronger as you approach.", null, null, null);
		basement[2][2] = new Room("Entrance", "the entrance to the basement. Three hallways await exploration.",
			"You try to find where you came in, but all you see is an oddly colored ceiling tile.", null, null, null);
		basement[1][2] = new Room("Hallway no.2", "hallway no.2. This hallway should lead you to the Clinic.",
			"The sickly smell of antiseptic gets stronger as you approach the Clinic.\n"
			+ "You can feel the unheard screams in the air.", null, null, null);
		basement[0][2] = new Room("The Clinic", "the second of three rooms. There's a sign calling it the Clinic.", 
			"There's a strange absence of life, almost as if it's been sucked out on purpose", null, null, null);
		basement[2][3] = new Room("Hallway no.3", "hallway no.3. This hallway shouuld lead you to the Office.",
			"An air of doubt fills the hallway as you wonder what you're even doing here.", null, null, null);
		basement[2][4] = new Room("The Office", "the last of three rooms. There's a sign calling it the Office.", 
				"You see a door hastily hidden behind a shelf. The desk is oddly empty", lady, null, null);
		
		return basement;
	}
	
	public static void runBasementDayThree(String name, Player player) throws InterruptedException {
		basement = createBasement(new WhiteLady("800-04-WL"));
		String basicDescription = "You fall into the basment. The smell of rust and metal tells you that it hasn't been very well kept.";
		boolean basementStay = false;
		p("For once in a very a long time, FEAR runs down your spine.");
		p("!!! Looking is key.");
		
		boolean room1Clear = false;
		boolean room2Clear = false;
		boolean room3Clear = false;
		
		while (!basementStay) {
			p(basicDescription);
			if((!room1Clear || !room2Clear) && (x == 2 && y == 4)) //locks the OFFICE until CHAMBER and CLINIC are explored
			{
				p("You should try exploring the other rooms first...");
				x = 2;
				y = 2;
				basicDescription = "You find yourself at the entrance to the basement. Three hallways await exploration.";
				continue;
			}
			
	        // Prompt user for action
	        System.out.println("What do you want to do?");
	        String[] action = sc.nextLine().toUpperCase().split(" ");
	        System.out.println("\n");
	        // Handle action
	        if (action.length > 0) {
	        	switch (action[0]) {
			        case "GO":
			            if (action.length > 1) {
			                String direction = action[1].toUpperCase();
			                switch (direction) {
			                    case "LEFT":
			                    case "WEST":
			                    case "W":
			                        if (y > 0 && basement[x][y - 1] != null) {
			                            y--;
			                            System.out.println("You move left.");
			                        } else {
			                            System.out.println("You can't go left from here.");
			                        }
			                        break;
			                    case "RIGHT":
			                    case "EAST":
			                    case "E":
			                        if (y < basement[0].length - 1 && basement[x][y + 1] != null) {
			                            y++;
			                            System.out.println("You move right.");
			                        } else {
			                            System.out.println("You can't go right from here.");
			                        }
			                        break;
			                    case "FORWARD":
			                    case "NORTH":
			                    case "N":
			                    case "FRONT":
			                        if (x > 0 && basement[x - 1][y] != null) {
			                            x--;
			                            System.out.println("You move forward.");
			                        } else {
			                            System.out.println("You can't go forward from here.");
			                        }
			                        break;
			                    case "BACKWARD":
			                    case "SOUTH":
			                    case "S":
			                    case "BACK":
			                    case "BEHIND":
			                        if (x < basement.length - 1 && basement[x + 1][y] != null) {
			                            x++;
			                            System.out.println("You move backward.");
			                        } else {
			                            System.out.println("You can't go backward from here.");
			                        }
			                        break;
			                    default:
			                        System.out.println("You can't go in that direction. Try using cardinal directions.");
			                    
			                }
			            } else {
			                System.out.println("Invalid. Please provide a direction.");
			            }
		                basicDescription = "You find yourself at " + basement[x][y].getBasicDesc(); //fix this grammar
		                
			            break;
		            case "EXPLORE":
		                System.out.println("You look around...");
		                System.out.println("Where you're standing: " + basement[x][y].getBasicDesc());
	
		                // Check left direction
		                if (y > 0 && basement[x][y - 1] != null) {
		                    System.out.println("To the left: " + basement[x][y - 1].getBasicDesc());
		                } else {
		                    System.out.println("To the left: A wall. Do not try to go through the wall. It will hurt...");
		                }
	
		                // Check right direction
		                if (y < basement[0].length - 1 && basement[x][y + 1] != null) {
		                    System.out.println("To the right: " + basement[x][y + 1].getBasicDesc());
		                } else {
		                    System.out.println("To the right: A wall. Do not try to go through the wall. It will hurt...");
		                }
	
		                // Check forward direction
		                if (x > 0 && basement[x - 1][y] != null) {
		                    System.out.println("In front: " + basement[x - 1][y].getBasicDesc());
		                } else {
		                    System.out.println("In front: A wall. Do not try to go through the wall. It will hurt...");
		                }
	
		                // Check backward direction
		                if (x < basement.length - 1 && basement[x + 1][y] != null) {
		                    System.out.println("Behind: " + basement[x + 1][y].getBasicDesc());
		                } else {
		                    System.out.println("Behind: A wall. Do not try to go through the wall. It will hurt...");
		                }
		                
		                break;
		            case "TALK":
		            	if ( basement[x][y].hasNPC() ) {
		            		if(x == 2 && y == 4)
		            		{
		            			p("The WHITE LADY reveals her name...");
		            			basement[x][y].getNpc().interact();
		            			Weapon heart = new Weapon("Heart of Torment", "HEART", "A spectral heart representing the White Lady's pain.",
		            						new SpecialAttack(80, 500, "Haunting Tears", "A crushing wave of darkness envelops the enemy"), 300, 1000, 0.07);
		            			player.pickUp(heart);
		            			player.unequipWeapon(new Weapon("The Esteemed Cross", "CROSS", "This is a highly holy item given to you by Father Yannick. It can be used to fend off cursed spirits.",
		            					new SpecialAttack(), 100, 100, 0.01));
		            			player.equipWeapon(heart);
		            			
		            			p("\nAnd that's when it all clicks.");
		            			p("The ghost that has been tormenting the island has been the people of the island the whole time.");
		            			p("Whoever these people are... they are simply leeching off of the island to further their research. Despicable.");
		            			
		            			p("But you know it isn't over yet. You feel a repulsive presence everywhere, as you turn around to see a ghastly beast, covered in blood, standing on all fours.",4);
		            			p("This is what you are here for.",2);
		            			p("Everything before was a trial.",2);
		            			p("THIS IS IT. You can't run.",2);
		            			
		            			p("The White Lady gives you her special powers ot take care of the situation.",2);
		            			p("----------------------------------",4);
		            			TextFont.printTitle("FINAL BOSS");
		            			if(Battle.play(player, new Enemy("Hungry Ghost", 100, 1000, 1000, 0.07, new SpecialAttack(100, 300,
		            					                      "Haunting Tears", "A crushing wave of darkness envelops the enemy."))))
		            			{
		            				TextFont.printTitle("GOOD ENDING");
			        				p("GOOD ENDING - The Savior.",2);
			        				p("You triumph at last.",2);
			        				p("As you defeat the ravenous demon, it explodes, bursting open the top of the ceiling, revealing daylight above.",2);
			        				p("You leave Kinmouth for good, after showcasing how terrible it was. How the entirety of the island was a mere ploy for scientific research.",3);
			        				p("But that same week, as you returned to the Vatican to report your findings, you find that the entirety of Kinmouth has dissapeared.",3);
			        				p("You never found why everything there happened for what reason.",4);
			        				
			        				TextFont.printTitle("Thank you");
			        				p("...for engaging in The Sinclair Experiment.");
			        				System.exit(0);
		            			}
		            			else
		            			{
		            				TextFont.printTitle("BAD ENDING");
			        				p("BAD ENDING - The Coward.",2);
			        				p("After all this hard work, you've still managed to fail.",2);
			        				p("Maybe you aren't cut out to be an exorcist.",2);
			        				p("As the demon charges at you, you let go of your cross, and embrace the loss. That's the most you could do.", 2);
			        				TextFont.printTitle("Thank you");
			        				p("...for engaging in The Sinclair Experiment.");
			        				System.exit(0);
		            			}
		            			
		            		}
		            		
		            		
		            	}
		            	else {
		            		System.out.println("There's no one at this spot to talk to. Are you going mad?");
		            	}
		            	break;
		            case "LOOK":
		            	System.out.println(basement[x][y].getDetailedDesc());
		            	if((x == 2 && y == 0) && !room1Clear) { //battle only happens once //determines if they've been to this room before
		            		boolean winStreak = false;
		            		boolean ending = false;
		            		p("You can make out traces of shadowy wisps in the air, some brighter than others.",2);
		            		p("The spirits are enraged by your presence...",2);
		            		p("█████ ████ prepares to judge you.");
		            		p("",3);
		            		
		            		Enemy funnel = new Enemy("Funnel Ghost", 100, 500, 500, 0.07, //creates 
		            				new SpecialAttack(100, 200, "SubZero", "A cold frost starts to manifest"));
		            		Enemy poltergeist = new Enemy("Poltergeist", 100, 500, 500, 0.07,
		            				new SpecialAttack(100, 200, "Levitation", "The air feels lighter as you begin to hover off the ground."));
		            		Enemy orb = new Enemy("Orb", 100, 500, 500, 0.07,
		            				new SpecialAttack(100, 200, "Flashbang", "You squint as everything before your eyes becomes light."));
		           
		            		winStreak = Battle.play(player, funnel);
		            		if(winStreak)
		            		{
		            			winStreak = Battle.play(player, poltergeist);
		            			if(winStreak)
		            			{
		            				winStreak = Battle.play(player, orb);
		            				if(winStreak)
		            				{
		            					ending = true;
		            				}
		            			}
		            		}
		            		
		            		if(ending)
		            		{
		            			TextFont.printTitle("ENDING FIVE");
		        				p("ENDING FIVE - Merciless Genocide",2);
		        				p("In your endless battles against these spirits, you fail to recognize true evil. Instead, you killed the spirits of the innocent.",2);
		        				p("Professionals should know when to quit.",2);
		        				System.out.print("\nReplay From Last Save? Y/N \n> ");
		        	    		String play = "";
		        	    		while (!(play.equals("Y") || play.equals("N"))) {
		        	    			try {
		        	    				play = sc.next().toUpperCase();
		        	    			}
		        	    			catch (Exception e) {
		        	    				sc.nextLine();
		        	    				p("Invalid input. Try again.");
		        	    			}
		        	    			
		        	    			if (!(play.equals("Y") || play.equals("N")))
		        	    				p("That's not an option.\nReplay From Last Save? Y/N \n>");
		        	    				sc.nextLine();
		        	    		}
		        	    		
		        	    		if (play.equals("Y")) {
		        	    			System.out.println("... you are brave for trying again, Father " + name + ". Good luck to you.");
		        	    			TimeUnit.SECONDS.sleep(1);
		        	    			System.out.println("Loading... \n");
		        	    			int i = 0;
		        	    			while(i < 40) {
		        	    				System.out.println(".");
		        	    				TimeUnit.MILLISECONDS.sleep(50);
		        	    				i++;
		        	    			}
		        	    			x = 2;
		        	    			y = 2;
		        	    		}
		        	    		else {
		        	    			System.out.println("I wish you well...");
		        	    			System.exit(0);
		        	    		}
		            		}
		            		else {
		            			p("Or have you?");
		            			p("█████ ████ has determined that you are the chosen one.");
		            			x = 2;
		            			y = 2;
		            			room1Clear = true;
		            			basicDescription = "You find yourself at the entrance to the basement. Three hallways await exploration.";
		            		}
		            	}	
		            	if((x == 0 && y == 2) && !room2Clear) {
		            		p("As you enter the Clinic, there are empty hospital beds.",2);
		            		p("Needles, syringes, and other dirty medical equipment stain the floor.",2);
		            		p("You can sense the souls of hundreds clawing at the walls. Death lives here.",2);
		            		p("As you pass through, you see huge tubes filled with a gelatinous mixture."
		            			+ "Rows and rows of humans, some you recognize as the missing victims, line that section of the clinic.",4);
		            		p("You realize... all these ghosts... the victims... the whole island is an experiment.",2);
		            		p("All to torture, to brutalize, to create demons who haunt the land, the mountains, the forests.");
		            		p("These people... were artifically making ghosts on this island. Using victims. To create demons.");
		            		room2Clear = true;
		            	}
		            		
		            	
		            	break;
		            case "TAKE":
		            	p("There's nothing here to take.");
		            	break;
		            case "LEAVE":
		            	p("You can't leave just yet. You have to find a way to escape.");
		            	break;
		            case "HELP":
		            	displayHelp();
		            	break;
		            default:
		                System.out.println("You can't really... do that.");
		        }
	        }
	        else
	        	System.out.println("You didn't even type anything.");
	        
	        System.out.println("\n");
	    }
	}
	
	private static void displayHelp() {
    	System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("COMMANDS:");
        System.out.println("GO - Move to another area");
        System.out.println("EXPLORE - Scan the whole area");
        System.out.println("TALK - Talk with someone in the area");
        System.out.println("USE - Interact with an item in the area");
        System.out.println("LOOK - Take a closer look in the specified area");
        System.out.println("TAKE - Take the item in the specified area"); 
        System.out.println("LEAVE - Leave the area");
        System.out.println("INVEN / INVENTORY - Check your inventory");
        System.out.println("HELP - Display this help message");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }
		
	public static void p(String txt){
		System.out.println(txt);
	}
	
	public static void p(String txt, int time) throws InterruptedException {
		TimeUnit.SECONDS.sleep(time);
		System.out.println(txt);
	}
}