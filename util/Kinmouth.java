package util;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import locations.*;
import npc.interactables.FatherYannick;
import npc.interactables.GameObject;
import player.*;

public class Kinmouth {
	private static int x=1,y=1;
	private static Scanner sc = new Scanner(System.in);
	private static Player player;
	
	public static void dayOneChurch(String name) throws InterruptedException { //you start off at a church, this is where the player gets their first weapon and heads off
		
		TextFont.printTitle("DAY ONE");
		p("                  _|_\r\n"
				+ "                   |\r\n"
				+ "                  / \\\r\n"
				+ "                 //_\\\\\r\n"
				+ "                //(_)\\\\\r\n"
				+ "                 |/^\\| \r\n"
				+ "       ,%%%%     // \\\\    ,@@@@@@@,\r\n"
				+ "     ,%%%%/%%%  //   \\\\ ,@@@\\@@@@/@@,\r\n"
				+ " @@@%%%\\%%//%%%// === \\\\ @@\\@@@/@@@@@\r\n"
				+ "@@@@%%%%\\%%%%%// =-=-= \\\\@@@@\\@@@@@@;%#####,\r\n"
				+ "@@@@%%%\\%%/%%//   ===   \\\\@@@@@@/@@@%%%######,\r\n"
				+ "@@@@@%%%%/%%//|         |\\\\@\\\\//@@%%%%%%#/####\r\n"
				+ "'@@@@@%%\\\\/%~ |         | ~ @|| %\\\\//%%%#####;\r\n"
				+ "  @@\\\\//@||   |  __ __  |    || %%||%%'######\r\n"
				+ "   '@||  ||   | |  |  | |    ||   ||##\\//####\r\n"
				+ "     ||  ||   | | -|- | |    ||   ||'#||###'\r\n"
				+ "     ||  ||   |_|__|__|_|    ||   ||  ||\r\n"
				+ "     ||  ||_/`  =======  `\\__||_._||  ||\r\n"
				+ "   __||_/`      =======            `\\_||___");

		
		FatherYannick priest = new FatherYannick("200-01-FY");
		
		Room[][] church = createChurch(priest); //create the church area
		String basicDescription = "After the long ride, you arrive to Kinmouth. Father Yannick drives you over to the local church to get supplies, and thus you find yourself inside the church of St. Augustine.";
		boolean churchScene = false;
		boolean tookCross = false;
		boolean talkedWithPriest = false;
		player = new Player(name);
		p("!!! You can now use the command TAKE [mention item name] to add said item to your inventory.");
		p("!!! You may also use the command LEAVE when the whole area has been explored for people to talk to, or items to discover.\n");
		
		//game loop for the church area
		while (!churchScene) {
	        p(basicDescription);

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
			                        if (y > 0 && church[x][y - 1] != null) {
			                            y--;
			                            System.out.println("You move left.");
			                        } else {
			                            System.out.println("You can't go left from here.");
			                        }
			                        break;
			                    case "RIGHT":
			                    case "EAST":
			                    case "E":
			                        if (y < church[0].length - 1 && church[x][y + 1] != null) {
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
			                        if (x > 0 && church[x - 1][y] != null) {
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
			                        if (x < church.length - 1 && church[x + 1][y] != null) {
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
		                basicDescription = "You find yourself at " + church[x][y].getBasicDesc(); //fix this grammar
			            break;
		            case "EXPLORE":
		                System.out.println("You look around...");
		                System.out.println("Where you're standing: " + church[x][y].getBasicDesc());
	
		                // Check left direction
		                if (y > 0 && church[x][y - 1] != null) {
		                    System.out.println("To the left: " + church[x][y - 1].getBasicDesc());
		                } else {
		                    System.out.println("To the left: A wall. Do not try to go through the wall. It will hurt...");
		                }
	
		                // Check right direction
		                if (y < church[0].length - 1 && church[x][y + 1] != null) {
		                    System.out.println("To the right: " + church[x][y + 1].getBasicDesc());
		                } else {
		                    System.out.println("To the right: A wall. Do not try to go through the wall. It will hurt...");
		                }
	
		                // Check forward direction
		                if (x > 0 && church[x - 1][y] != null) {
		                    System.out.println("In front: " + church[x - 1][y].getBasicDesc());
		                } else {
		                    System.out.println("In front: A wall. Do not try to go through the wall. It will hurt...");
		                }
	
		                // Check backward direction
		                if (x < church.length - 1 && church[x + 1][y] != null) {
		                    System.out.println("Behind: " + church[x + 1][y].getBasicDesc());
		                } else {
		                    System.out.println("Behind: A wall. Do not try to go through the wall. It will hurt...");
		                }
		                
		                break;
		            case "TALK":
		            	if ( church[x][y].hasNPC() ) {
		            		boolean talked = ((FatherYannick) church[x][y].getNpc()).interactInChurch();
		            		if (talked)
		            			talkedWithPriest = true;
		            	}
		            	else {
		            		System.out.println("There's no one at this spot to talk to. Are you going mad?");
		            	}
		            	break;
		            case "LOOK":
		            	System.out.println(church[x][y].getDetailedDesc());
		            	break;
		            case "TAKE":
		            	if (action.length > 1) {
		            		String item = action[1].toUpperCase();
		            		
		            		//checks for if there's a weapon (the cross)
		            		
		            		if (church[x][y].getWeapon() == null)
		            			p("There's nothing here to pick up.");
		            		else if (church[x][y].getWeapon().getCrudeName().equals(item)) {
			                	System.out.println("You picked up " + church[x][y].getWeapon().getName() +".");
			                	player.pickUp(church[x][y].getWeapon());
			                	church[x][y].pickedUpWeapon();
			                	church[x][y].setBasicDesc("You find yourself at the church podium. There is a dark, empty oak chest in front of it.");
			                	basicDescription = church[x][y].getBasicDesc();
			                	tookCross = true;
			                }
			                else
			                	System.out.println("Specify correctly what you want to take. Type in the word that's in all caps to take it.");	
		            	}
		            	else
		            		p("You gotta specify something to take.");	                	
		                break;
		            case "HELP":
		            	displayHelpChurch();
		            	break;
		            case "LEAVE":		            	
		            	if (tookCross && talkedWithPriest) {
		            		exitChurchDialogue(name);
		            		churchScene = true;
		            	}
		            	else {
		            		p("There's still some things to discover, or people to talk to.");
		            	}
		            	
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
	
	//prints out the cutscene of exiting the church, starts the actual first day
		
	private static void exitChurchDialogue(String name) throws InterruptedException {
		p("\nCross in one hand, map in the other, you politely say your thanks to Father Yannick and leave.");
		p("You would love to talk more, but you have come to do a job. You must figure out what's wrong with this island.", 3);
		p("And the worst part. You have felt a spine-chilling aura ever since you got on the island. It was with you in the car, as you walked into the church, and as you walk out now.", 4);
		p("It is something you have never felt before. Out of all your exorcism jobs, having to exorcise a spirit of the whole town is on a scale you haven't dealt with before.", 6);
		p("The thing here is something of a caliber of demonic prowess you have yet to see.", 5);
		p("There are things yet to come. ", 5);
		
		player.addRegularInventory(new Item("Case Files", "FILES", "They are the case files."));
		player.addRegularInventory(new Item("Belmont Hotel Pass", "PASS", "This is the Belmont Hotel Pass, that Father Yannick gave for your stay at the island. How corteous."));
		player.addRegularInventory(new Item("Map of Kinmouth","MAP","This is a map of the island, given to you by Father Yannick. It will help you navigate."));
		
		islandViewDayOne(name); return;
	}
		
	
	//method creates the church
	private static Room[][] createChurch(FatherYannick priest) {
		Room[][] church = new Room[2][3];
		
		SpecialAttack esteemedCrossSpecialAtk = new SpecialAttack();
		
		Weapon esteemedCross = new Weapon("The Esteemed Cross", "CROSS", "This is a highly holy item given to you by Father Yannick. It can be used to fend off cursed spirits",
				esteemedCrossSpecialAtk, 100, 100, 0.01);
		
		//you find yourself at...
		church[0][1] = new Room("The Church Podium", "the church podium. There is a dark oak chest in front of it, and a CROSS inside the chest.", 
				"Nothing much that stands out. It might be beneficial to take the CROSS.", null, null, esteemedCross); // add cross
		church[1][1] = new Room("The Church Entrance", "the church's entrance, the ornate decorations of the old building in front of you. Father Yannick is standing here as well.",
				"Nothing much that stands out, other than Father Yannick standing here, face contorted as if in thought.", priest, null, null);
		church[1][0] = new Room("The Left Side of Church", "the left side of the church. There are decorations on the wall, and rows of chairs.", 
				"Rows of long wooden chairs line the dark gray floor. There is a painting of a religious figure hung on this wall. You can't make out who it depicts.", 
				null, null, null);
		church[1][2] = new Room("The Right Side of Church", "the right side of the church. There is a glass mural on the wall, rows of chairs, and a confession booth.", 
				"Rows of long wooden chairs line the floor. There is a stained glass mural of Jesus that paints this side of the room in a faint mix of blue, red, and yellow. \n"
				+ "The confession booth is ornate and looks ancient compared to the rest of the church.", null, null, null);
		return church;
	}
	
	//displays all commands
    private static void displayHelpChurch() {
    	System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("COMMANDS:");
        System.out.println("GO - Move to another area");
        System.out.println("EXPLORE - Scan the whole area");
        System.out.println("TALK - Talk with someone in the area");
        System.out.println("LOOK - Take a closer look in the specified area");
        System.out.println("TAKE - Take the item in the specified area"); 
        System.out.println("LEAVE - Once all items have been interacted with, you may leave the area");
        System.out.println("HELP - Display this help message");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }
    
    //--------------------------------------------------------------------------------------------------------------------------------------
    
    public static void islandViewDayOne(String name) throws InterruptedException {    	//Start of the first day actually
    	//police station
    	//bar
    	//victim's neighborhood
    	//town hall
    	//forest
    	//library
    	//cemetary
    	
    	boolean bunnyFound = false;  //Checks for all the items in searches thru out 
    	boolean bunnyGivenToGirl = false;
    	boolean barTalk = false;
    	boolean finishFirstDay = false;
    	
    	p("You check your map on where you could go.");
    	p("There aren't many main locations, but some places of interest look to be:");
    	while (!finishFirstDay) {
    		//Map view
    		p("-----------------------------------------------------");
        	p("- The POLICE STATION.");
        	p("- The local BAR, the Beer Bungalow.");
        	p("- The victim's HOUSE.");
        	p("- The CEMETERY on the outskirts of the island.");
        	p("-----------------------------------------------------");
	        // Prompt user for action
	        System.out.println("Where do you want to go? Type the name of the place that's in all caps.");
	        String action = sc.nextLine().toUpperCase();
	        System.out.println("\n");
	        // Handle actions
	        	switch (action) {
	        		case "POLICE":
	        		case "STATION":
	        		case "POLICE STATION":
	        			PoliceStation.runStationDayOne(name, player);
	        			break;
	        		case "BAR":
	        		case "BEER":
	        		case "BUNGALOW":
	        			if (bunnyFound && bunnyGivenToGirl) {
	        				boolean talkedWithBarDude = Bar.runBarAfterBunny(name, player);
	        				if (talkedWithBarDude)
	        					finishFirstDay = true;
	        			}
	        			else 
	        				Bar.runBarBeforeBunny(name, player);
	        			break;
	        		case "HOUSE":
	        			if (!bunnyFound)
	        				House.runResBeforeBunny(name, player);
	        			else if (bunnyFound && !bunnyGivenToGirl)
	        				bunnyGivenToGirl = House.runResBunnyFound(name, player);
	        			else
	        				p("You've already done your business there.");
	        			break;
	        		case "CEMETERY":
	        			if (!bunnyFound)
	        				bunnyFound = Cemetery.runCemeteryBeforeBunny(name, player);
	        			else
	        				Cemetery.runCemeteryAfterBunny(name, player);
	        			break;
	        		default:
	        			p("That isn't a place you really want to go to. Maybe stick to the places you already have in mind.");
	        			break;
	        	}
	        
    	}
    	
    	String answer = "";
    	
    	while(!answer.equals("LIBRARY")) { //Runs the segments after the info from the bar
	    	p("You leave the bar, with new found information. As the sun sets, you feel the spirital pressure of demons around you. The fog of the town continues to darken the land.");
	    	p("You don't have enough gas to go visit multiple places, and the gas station is closed for the night. You have enough for one trip to the place and back to your hotel.", 2);
	    	p("You understand that you have to correctly understand where to go. There's barely any more gas in your car. The sun is setting. And the ghouls are hunting.", 2);
	    	p("Now, choose:",2);
			p("-----------------------------------------------------",1);
	    	p("- The POLICE STATION.");
	    	p("- The local BAR, the Beer Bungalow.");
	    	p("- The victim's HOUSE.");
	    	p("- The CEMETERY on the outskirts of the island.");
	    	p("- The french RESTAURANT, Le Jardin Gourmet.");
	    	p("- The TOWN HALL, home to the island's history.");
	    	p("- The LIBRARY, housing famous literature.");
	    	p("- The Local RADIO STATION, playing tunes 24/7");
	    	p("-----------------------------------------------------");	    
	    	
	    	
	    	boolean validAnswer = false;
	
	    	while (!validAnswer) {
	    	    System.out.println("Where do you want to go? Type the name of the place that's in all caps.");
	    	    try {
	    	        answer = sc.nextLine().toUpperCase();
	    	        if (answer.equals("POLICE STATION") || answer.equals("BAR") || answer.equals("HOUSE") ||
	    	            answer.equals("CEMETERY") || answer.equals("RESTAURANT") || answer.equals("TOWN HALL") ||
	    	            answer.equals("LIBRARY") || answer.equals("RADIO STATION")) 
	    	            validAnswer = true;
	    	        else
	    	            System.out.println("Invalid choice. Please select one of the options listed.");
	    	    }
	    	    
	    	    catch(Exception e) {
	    	        System.out.println("Invalid input. Please try again.");
	    	        sc.nextLine(); 
	    	    }
	    	}
	    	
	    	if (answer.equals("LIBRARY")) {
	    		break;
	    	}
	    	else {
	    		p("As you drive into the fading blue sky, a slight hope in your eyes, you are oblivious to the violence you have cause.");
	    		p("You chose wrong.", 2);
	    		p("And that night you wasted at the " + answer.toLowerCase() + " without understanding where you could've gottten the real answers and saved this island.", 1);
	    		System.out.println("");
	    		TextFont.printTitle("ENDING ONE"); //FIRST ENDING!!
	    		p("ENDING ONE - Wrong Turn.",2);
	    		p("You made the wrong choice of where to investigate, and as you would've learned, time is of the essence.");
	    		//Replay
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
	    		}
	    		else {
	    			System.out.println("I wish you well...");
	    			System.exit(0);
	    		}
	    	}
    	}
    	
		p("As you drive into the fading blue sky to the library, a slight hope in your eyes, you are oblivious to the violence left in your wake.");
		p("You have chosen correctly.");
    	
    	
    	Library.runlibraryDayOne(name, player);
    	
    }

	public static void p(String txt){
		System.out.println(txt);
	}
	public static void p(String txt, int time) throws InterruptedException {
		TimeUnit.SECONDS.sleep(time);
		System.out.println(txt);
	}
	
}