package locations;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import player.Item;
import player.Player;
import util.*;

public class Cemetery {
	private static Room[][] cemetary;
	
	static int x = 1;
	static int y = 1;
	private static Scanner sc = new Scanner(System.in);
	
	private static Room[][] createCemetary(Item bunny) {
		Room[][] cemetary = new Room[2][3];
	
		//you find yourself at...
		cemetary[1][0] = new Room("The West Face of the Cemetary", "the west face of the cemetary. The gravestones look worn down and many show signs of crumbling in places.", 
			"Many of the names carved into the gravestones have become illegible over the years. Nobody has been here in quite a while. Even the grass appears aged.", null, null, null);
		cemetary[0][1] = new Room("The North Face of the Cemetary", "the north face of the cemetary. The gravestones look well kept, with well placed flowers.",
			"The flowers look and smell fresh. This place of the dead is somehow very lively. Many of the gravestones have crosses on them.", null, null, null);
		cemetary[1][1] = new Room("Cemetary Enterance", "cemetary enterance. There are three gates, each leading to either the North, East, or West face.", 
			"The gates are all made of different materials, almost like each was constructed at a different time.", 
			null, null, null);
		cemetary[1][2] = new Room("The East Face of the Cemetary", "the east face of the cemetary. The place has an air of freshness, and the gravestones look almost new. There is a BUNNY here.", 
			"The grass looks rushed, almost as if it has not had the time to settle in. The gravestones appear to shine under the light, spotlighing the BUNNY.", null, bunny, null);
		return cemetary;
	}
	
	public static boolean runCemeteryBeforeBunny(String name, Player player) {
		boolean bunnyFound = false;
		Item bunny = new Item("Some Kid's Pet Bunny", "BUNNY", "From the collar and stains of pink marker on this rabbit, it seems to be a local's lost pet.");
		cemetary = createCemetary(bunny);
		String basicDescription = "You arrive at the cemetary. The souls of the island cry for salvation.";
		boolean cemetaryStay = false;
		
		while (!cemetaryStay) {
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
			                        if (y > 0 && cemetary[x][y - 1] != null) {
			                            y--;
			                            System.out.println("You move left.");
			                        } else {
			                            System.out.println("You can't go left from here.");
			                        }
			                        break;
			                    case "RIGHT":
			                    case "EAST":
			                    case "E":
			                        if (y < cemetary[0].length - 1 && cemetary[x][y + 1] != null) {
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
			                        if (x > 0 && cemetary[x - 1][y] != null) {
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
			                        if (x < cemetary.length - 1 && cemetary[x + 1][y] != null) {
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
		                basicDescription = "You find yourself at " + cemetary[x][y].getBasicDesc(); //fix this grammar
			            break;
		            case "EXPLORE":
		                System.out.println("You look around...");
		                System.out.println("Where you're standing: " + cemetary[x][y].getBasicDesc());
	
		                // Check left direction
		                if (y > 0 && cemetary[x][y - 1] != null) {
		                    System.out.println("To the left: " + cemetary[x][y - 1].getBasicDesc());
		                } else {
		                    System.out.println("To the left: dense fog. You can't go that way...");
		                }
	
		                // Check right direction
		                if (y < cemetary[0].length - 1 && cemetary[x][y + 1] != null) {
		                    System.out.println("To the right: " + cemetary[x][y + 1].getBasicDesc());
		                } else {
		                    System.out.println("To the right: dense fog. You can't go that way...");
		                }
	
		                // Check forward direction
		                if (x > 0 && cemetary[x - 1][y] != null) {
		                    System.out.println("In front: " + cemetary[x - 1][y].getBasicDesc());
		                } else {
		                    System.out.println("In front: dense fog. You can't go that way...");
		                }
	
		                // Check backward direction
		                if (x < cemetary.length - 1 && cemetary[x + 1][y] != null) {
		                    System.out.println("Behind: " + cemetary[x + 1][y].getBasicDesc());
		                } else {
		                    System.out.println("Behind: dense fog. You can't go that way...");
		                }
		                
		                break;
		            case "TALK":
		            	if ( cemetary[x][y].hasNPC() ) {
		            		cemetary[x][y].getNpc().interact();
		            	}
		            	else {
		            		System.out.println("There's no one at this spot to talk to. Are you going mad?");
		            	}
		            	break;
		            case "LOOK":
		            	System.out.println(cemetary[x][y].getDetailedDesc());
		            	break;
		            case "TAKE":
		            	if (action.length > 1) {
		            		String item = action[1].toUpperCase();
		            		
		            		//checks for the bunny
		            		
		            		
		            		if (cemetary[x][y].getItem() == null)
		            			p("There's nothing here to pick up.");
		            		else if (cemetary[x][y].getItem().getCrudeName().equals(item)) {
			                	System.out.println("You picked up " + cemetary[x][y].getItem().getName() +".");
			                	player.addRegularInventory(cemetary[x][y].getItem());
			                	cemetary[x][y].pickedUpItem();
			                	cemetary[x][y].setBasicDesc("You find yourself at the east face of the cemetary. The place has an air of freshness, and the gravestones look almost new.");
			                	basicDescription = cemetary[x][y].getBasicDesc();
			                	bunnyFound = true;
			                }
			                else
			                	System.out.println("Specify correctly what you want to take. Type in the word that's in all caps to take it.");	
		            	}
		            	else
		            		p("You gotta specify something to take.");	                	
		                break;
		            case "HELP":
		            	displayHelp();
		            	break;
		            case "LEAVE":	
		            	cemetaryStay = true;
		            	p("You decide to leave the cemetary.");
		            	
		            	break;
		            default:
		                System.out.println("You can't really... do that.");
		        }
	        }
	        else
	        	System.out.println("You didn't even type anything.");
	        
	        System.out.println("\n");
	    }
		
		return bunnyFound;
	    

	}
	
	public static void runCemeteryAfterBunny(String name, Player player) {
		x = 0;
		y = 0;
		cemetary = createCemetary(null);
		String basicDescription = "You arrive at the cemetary. The souls of the island cry for salvation.";
		cemetary[1][2].setBasicDesc("You find yourself at the east face of the cemetary. The place has an air of freshness, and the gravestones look almost new.");
		boolean cemetaryStay = false;
		
		while (!cemetaryStay) {
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
			                        if (y > 0 && cemetary[x][y - 1] != null) {
			                            y--;
			                            System.out.println("You move left.");
			                        } else {
			                            System.out.println("You can't go left from here.");
			                        }
			                        break;
			                    case "RIGHT":
			                    case "EAST":
			                    case "E":
			                        if (y < cemetary[0].length - 1 && cemetary[x][y + 1] != null) {
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
			                        if (x > 0 && cemetary[x - 1][y] != null) {
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
			                        if (x < cemetary.length - 1 && cemetary[x + 1][y] != null) {
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
		                basicDescription = "You find yourself at " + cemetary[x][y].getBasicDesc(); //fix this grammar
			            break;
		            case "EXPLORE":
		                System.out.println("You look around...");
		                System.out.println("Where you're standing: " + cemetary[x][y].getBasicDesc());
	
		                // Check left direction
		                if (y > 0 && cemetary[x][y - 1] != null) {
		                    System.out.println("To the left: " + cemetary[x][y - 1].getBasicDesc());
		                } else {
		                    System.out.println("To the left: dense fog. You can't go that way...");
		                }
	
		                // Check right direction
		                if (y < cemetary[0].length - 1 && cemetary[x][y + 1] != null) {
		                    System.out.println("To the right: " + cemetary[x][y + 1].getBasicDesc());
		                } else {
		                    System.out.println("To the right: dense fog. You can't go that way...");
		                }
	
		                // Check forward direction
		                if (x > 0 && cemetary[x - 1][y] != null) {
		                    System.out.println("In front: " + cemetary[x - 1][y].getBasicDesc());
		                } else {
		                    System.out.println("In front: dense fog. You can't go that way...");
		                }
	
		                // Check backward direction
		                if (x < cemetary.length - 1 && cemetary[x + 1][y] != null) {
		                    System.out.println("Behind: " + cemetary[x + 1][y].getBasicDesc());
		                } else {
		                    System.out.println("Behind: dense fog. You can't go that way...");
		                }
		                
		                break;
		            case "TALK":
		            	if ( cemetary[x][y].hasNPC() ) {
		            		cemetary[x][y].getNpc().interact();
		            	}
		            	else {
		            		System.out.println("There's no one at this spot to talk to. Are you going mad?");
		            	}
		            	break;
		            case "LOOK":
		            	System.out.println(cemetary[x][y].getDetailedDesc());
		            	break;
		            case "TAKE":		            		
		            	p("There's nothing here to pick up.");              	
		                break;
		            case "HELP":
		            	displayHelp();
		            	break;
		            case "LEAVE":	
		            	cemetaryStay = true;
		            	p("You decide to leave the cemetary.");
		            	
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