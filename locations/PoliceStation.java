package locations;


import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import npc.interactables.GameObject;
import npc.interactables.Receptionist;
import npc.interactables.Sheriff;
import player.Player;
import util.*;

public class PoliceStation {
	private static Room[][] station;
	static int x = 2;
	static int y = 1;
	private static Scanner sc = new Scanner(System.in);
	
	private static Room[][] createPoliceStation(GameObject sheriff, GameObject frontDeskPerson) {//creates area
		Room[][] station = new Room[3][3];
		//you find yourself at...
		station[0][1] = new Room("The Sheriff's Office", "the sheriff's office. There's a large wooden desk overseeing the room with stacks of paperwork", 
			"The sheriff is engrossed on his computer, a sandwich in hand. He did not notice your presence.", sheriff, null, null);
		station[0][2] = new Room("The Records Room", "the records room. This is where the police store their records. A guard suspiciously eyes you.",
			"The door to the room is shut with various locks. The guard does not seem very talkative.", null, null, null);
		station[1][2] = new Room("The Interrogation Room", "the interrogation room. There is a one way mirror on the wall.", 
			"A bleak table is centered in the middle of the room, with two chairs on one side and one on the other", 
			null, null, null);
		station[1][0] = new Room("The Meeting Room", "the meeting room. Chairs line the long table in the middle. There seems to be a projector at the end of the table.", 
			"Chairs line the long wooden table in the middle. They seem more comfortable than the ones in the interrogation room", null, null, null);
		station[1][1] = new Room("The Public Area", "the public space. The space smells like coffee, donuts, and justice.", 
				"There are cubicles in the middle of the area, with a few policemen fervorously working", null, null, null);
		station[2][1] = new Room("The Front Desk", "the front desk. There's a someone at the desk who welcomes you with a warm smile.", 
				"Whatever they did with the lighting looks cool. There isn't much of a line here.", frontDeskPerson, null, null);
		return station;
	}
	
	public static void runStationDayOne(String name, Player player) {//runs game loop
		station = createPoliceStation(new Sheriff("300-02-SS"), new Receptionist("300-02-RL"));
		String basicDescription = "You arrive at the local police station. It smells of sweat.";
		boolean stationStay = false;
		
		while (!stationStay) {
	        p(basicDescription);

	        // Prompt user for action
	        System.out.println("What do you want to do?");
	        String[] action = sc.nextLine().toUpperCase().split(" ");
	        System.out.println("\n");
	        // Handle action
	        if (action.length > 0) {
	        	switch (action[0]) {
	            case "INVEN":
	            case "INVENTORY":
	            	p(player.displayRegularInventory());
	            	player.searchRegInventory();
	            	break;
			        case "GO":
			            if (action.length > 1) {
			                String direction = action[1].toUpperCase();
			                switch (direction) {
			                    case "LEFT":
			                    case "WEST":
			                    case "W":
			                        if (y > 0 && station[x][y - 1] != null) {
			                            y--;
			                            System.out.println("You move left.");
			                        } else {
			                            System.out.println("You can't go left from here.");
			                        }
			                        break;
			                    case "RIGHT":
			                    case "EAST":
			                    case "E":
			                        if (y < station[0].length - 1 && station[x][y + 1] != null) {
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
			                        if (x > 0 && station[x - 1][y] != null) {
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
			                        if (x < station.length - 1 && station[x + 1][y] != null) {
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
		                basicDescription = "You find yourself at " + station[x][y].getBasicDesc(); //fix this grammar
			            break;
		            case "EXPLORE":
		                System.out.println("You look around...");
		                System.out.println("Where you're standing: " + station[x][y].getBasicDesc());
	
		                // Check left direction
		                if (y > 0 && station[x][y - 1] != null) {
		                    System.out.println("To the left: " + station[x][y - 1].getBasicDesc());
		                } else {
		                    System.out.println("To the left: A wall. Do not try to go through the wall. It will hurt...");
		                }
	
		                // Check right direction
		                if (y < station[0].length - 1 && station[x][y + 1] != null) {
		                    System.out.println("To the right: " + station[x][y + 1].getBasicDesc());
		                } else {
		                    System.out.println("To the right: A wall. Do not try to go through the wall. It will hurt...");
		                }
	
		                // Check forward direction
		                if (x > 0 && station[x - 1][y] != null) {
		                    System.out.println("In front: " + station[x - 1][y].getBasicDesc());
		                } else {
		                    System.out.println("In front: A wall. Do not try to go through the wall. It will hurt...");
		                }
	
		                // Check backward direction
		                if (x < station.length - 1 && station[x + 1][y] != null) {
		                    System.out.println("Behind: " + station[x + 1][y].getBasicDesc());
		                } else {
		                    System.out.println("Behind: A wall. Do not try to go through the wall. It will hurt...");
		                }
		                
		                break;
		            case "TALK":
		            	if ( station[x][y].hasNPC() ) {
		            		station[x][y].getNpc().interact();
		            	}
		            	else {
		            		System.out.println("There's no one at this spot to talk to. Are you going mad?");
		            	}
		            	break;
		            case "LOOK":
		            	System.out.println(station[x][y].getDetailedDesc());
		            	break;
		            case "TAKE":
		            	p("There's nothing here to pick up.");	
		                break;
		            case "HELP":
		            	displayHelp();
		            	break;
		            case "LEAVE":	
		            	stationStay = true;
		            	p("You decide to leave the police station.");
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