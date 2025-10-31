package locations;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import npc.interactables.Bartender;
import npc.interactables.GameObject;
import player.Player;
import util.Room;

public class Bar {
	private static Room[][] bar;
	static int x = 0;
	static int y = 0;
	private static Scanner sc = new Scanner(System.in);
	
	private static Room[][] createBar(GameObject a) {
		Room[][] bar = new Room[3][2];
		
		//you find yourself at...
		bar[1][0] = new Room("Bar", "the bar. The bartender welcomes you and offers you a stool.", 
			"The onyx black table shines like it has just been cleaned. An array of fine wines are displayed in exquisite bottles behind the bartneder", a, null, null);
		bar[0][0] = new Room("Bar Entrance", "the entrance to the bar. You can immediately hear the loud music",
			"Everyone seems to be enjoying themselves. Miraculously, this town does have a party life.", null, null, null);
		bar[0][1] = new Room("Pool Table", "the pool table. There is an intense game going on.", 
			"As the cue stick hits the cue ball, their glares seem to bore into your skin, And they're not even looking at you", 
			null, null, null);
		bar[2][1] = new Room("The Piano", "the piano. The piano is being played by someone you don't recognize.", 
			"As the piano is played, the loud music begins to quiet down as people stop to watch.", null, null, null);
		return bar;
	}
	
	public static void runBarBeforeBunny(String name, Player player) {
		bar = createBar(new Bartender("400-02-BT"));
		String basicDescription = "You arrive at the Beer Bungalow. It has a nice, cozy charm to it. ";
		boolean barStay = false;
		
		while (!barStay) {
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
			                        if (y > 0 && bar[x][y - 1] != null) {
			                            y--;
			                            System.out.println("You move left.");
			                        } else {
			                            System.out.println("You can't go left from here.");
			                        }
			                        break;
			                    case "RIGHT":
			                    case "EAST":
			                    case "E":
			                        if (y < bar[0].length - 1 && bar[x][y + 1] != null) {
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
			                        if (x > 0 && bar[x - 1][y] != null) {
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
			                        if (x < bar.length - 1 && bar[x + 1][y] != null) {
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
		                basicDescription = "You find yourself at " + bar[x][y].getBasicDesc(); //fix this grammar
			            break;
		            case "EXPLORE":
		                System.out.println("You look around...");
		                System.out.println("Where you're standing: " + bar[x][y].getBasicDesc());
	
		                // Check left direction
		                if (y > 0 && bar[x][y - 1] != null) {
		                    System.out.println("To the left: " + bar[x][y - 1].getBasicDesc());
		                } else {
		                    System.out.println("To the left: A wall. Do not try to go through the wall. It will hurt...");
		                }
	
		                // Check right direction
		                if (y < bar[0].length - 1 && bar[x][y + 1] != null) {
		                    System.out.println("To the right: " + bar[x][y + 1].getBasicDesc());
		                } else {
		                    System.out.println("To the right: A wall. Do not try to go through the wall. It will hurt...");
		                }
	
		                // Check forward direction
		                if (x > 0 && bar[x - 1][y] != null) {
		                    System.out.println("In front: " + bar[x - 1][y].getBasicDesc());
		                } else {
		                    System.out.println("In front: A wall. Do not try to go through the wall. It will hurt...");
		                }
	
		                // Check backward direction
		                if (x < bar.length - 1 && bar[x + 1][y] != null) {
		                    System.out.println("Behind: " + bar[x + 1][y].getBasicDesc());
		                } else {
		                    System.out.println("Behind: A wall. Do not try to go through the wall. It will hurt...");
		                }
		                
		                break;
		            case "TALK":
		            	if ( bar[x][y].hasNPC() ) {
		            		bar[x][y].getNpc().interact();
		            	}
		            	else {
		            		System.out.println("There's no one at this spot to talk to. Are you going mad?");
		            	}
		            	break;
		            case "LOOK":
		            	System.out.println(bar[x][y].getDetailedDesc());
		            	break;
		            case "TAKE":
		            	p("There's nothing here to take.");
		                break;
		            case "HELP":
		            	displayHelp();
		            	break;
		            case "LEAVE":	
		            	barStay = true;
		            	p("You decide to leave the bar.");
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
	
	public static boolean runBarAfterBunny(String name, Player player) {
		bar = createBar(new Bartender("400-03-BT"));
		String basicDescription = "You arrive at the Beer Bungalow. It has a nice, cozy charm to it. ";
		boolean barStay = false;
		boolean gotInfo = false;
		
		while (!barStay) {
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
			                        if (y > 0 && bar[x][y - 1] != null) {
			                            y--;
			                            System.out.println("You move left.");
			                        } else {
			                            System.out.println("You can't go left from here.");
			                        }
			                        break;
			                    case "RIGHT":
			                    case "EAST":
			                    case "E":
			                        if (y < bar[0].length - 1 && bar[x][y + 1] != null) {
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
			                        if (x > 0 && bar[x - 1][y] != null) {
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
			                        if (x < bar.length - 1 && bar[x + 1][y] != null) {
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
		                basicDescription = "You find yourself at " + bar[x][y].getBasicDesc(); //fix this grammar
			            break;
		            case "EXPLORE":
		                System.out.println("You look around...");
		                System.out.println("Where you're standing: " + bar[x][y].getBasicDesc());
	
		                // Check left direction
		                if (y > 0 && bar[x][y - 1] != null) {
		                    System.out.println("To the left: " + bar[x][y - 1].getBasicDesc());
		                } else {
		                    System.out.println("To the left: A wall. Do not try to go through the wall. It will hurt...");
		                }
	
		                // Check right direction
		                if (y < bar[0].length - 1 && bar[x][y + 1] != null) {
		                    System.out.println("To the right: " + bar[x][y + 1].getBasicDesc());
		                } else {
		                    System.out.println("To the right: A wall. Do not try to go through the wall. It will hurt...");
		                }
	
		                // Check forward direction
		                if (x > 0 && bar[x - 1][y] != null) {
		                    System.out.println("In front: " + bar[x - 1][y].getBasicDesc());
		                } else {
		                    System.out.println("In front: A wall. Do not try to go through the wall. It will hurt...");
		                }
	
		                // Check backward direction
		                if (x < bar.length - 1 && bar[x + 1][y] != null) {
		                    System.out.println("Behind: " + bar[x + 1][y].getBasicDesc());
		                } else {
		                    System.out.println("Behind: A wall. Do not try to go through the wall. It will hurt...");
		                }
		                
		                break;
		            case "TALK":
		            	if ( bar[x][y].hasNPC() ) {
		            		bar[x][y].getNpc().interact();
		            		player.removeRegularInventory("BRIBEMONEY");
		            		gotInfo = true;
		            	}
		            	else {
		            		System.out.println("There's no one at this spot to talk to. Are you going mad?");
		            	}
		            	break;
		            case "LOOK":
		            	System.out.println(bar[x][y].getDetailedDesc());
		            	break;
		            case "TAKE":
		            	p("There's nothing here to take.");
		                break;
		            case "HELP":
		            	displayHelp();
		            	break;
		            case "LEAVE":	
		            	barStay = true;
		            	p("You decide to leave the bar.");
		            	break;
		            default:
		                System.out.println("You can't really... do that.");
		        }
	        }
	        else
	        	System.out.println("You didn't even type anything.");
	        
	        System.out.println("\n");
	    }
	    
		return gotInfo;

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