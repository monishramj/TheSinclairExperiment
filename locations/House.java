package locations;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import npc.interactables.*;
import player.Item;
import player.Player;
import util.Room;

public class House {
	private static Room[][] neighborhood;
	static int x = 2;
	static int y = 1;
	private static Scanner sc = new Scanner(System.in);
	
	private static Room[][] createResArea(GameObject a, GameObject b, GameObject c, GameObject d, GameObject e, GameObject f) {
		Room[][] area = new Room[3][3];
	
		//you find yourself at...
		area[2][1] = new Room("Road", "the road. House no.1009 is to your left and house no.1008 is to your right.", //this is the starting point
			"The road hasn't been paved for a long time. The road almost crumbles beneath your feet.", null, null, null);
		area[1][1] = new Room("Road", "the road. House no.1011 is to your left and house no.1010 is to your right", 
			"The road hasn't been paved for a long time. The road almost crumbles beneath your feet.", null, null, null);
		area[0][1] = new Room("Road", "the road. House no.1013 is to your left and house no.1014 is to your right", 
			"The road hasn't been paved for a long time. The road almost crumbles beneath your feet.", null, null, null);
		
		area[2][0] = new Room("House No. 1009", "House no.1009. The house is lively and two cars are parked outside. A POTENTIAL WITNESS might be here.", 
			"It's just another normal house. You could talk to the people who live here.", a, null, null);
		
		area[1][0] = new Room("House No. 1011", "House no.1011. The house is freshly painted. There is a sobbing LITTLE GIRL standing in front of it.", 
			"The bright blue paint is not to your taste, but to each their own, right? Blue like the little girl's mood. You could talk to the people who live here.", b, null, null); //little girl
		
		area[0][0] = new Room("House No. 1013", "House no.1013. The house is pretty small compared to the others and something feels off. A POTENTIAL WITNESS might be here.", 
			"The house has been through some things. The bricks show in some places. You could talk to the people who live here.", c, null, null); //do something with this one pls
		
		area[2][2] = new Room("House No. 1008", "House no.1008. The house is one of the larger ones, with contemporary-styled square corners eveywhere. A POTENTIAL WITNESS might be here.", 
			"The garage is see-through for some reason. You wonder how all those cars even got there. You could talk to the people who live here.", d, null, null);
		
		area[1][2] = new Room("House No. 1010", "House no.1010. Someone's tinkering with stuff in his garage, and he waves at you as you pass by.  A POTENTIAL WITNESS might be here.", 
			"The house looks well-loved, yet pristine in its unique way. You could talk to the people who live here.", e, null, null);
		
		area[0][2] = new Room("House No. 1012", "House no.1012. As a stark contrast to the house across the street, this one is a sprawling gothic mansion.  A POTENTIAL WITNESS might be here.", 
			"All the other houses seem to cower in the mere presence of this one. The garden seems to roll on forever. You could talk to the people who live here.", f, null, null);
		
		return area;
	}
	
	public static void runResBeforeBunny(String name, Player player) {
		neighborhood = createResArea(new TownPerson("500-02-T1"), new LittleGirl("500-02-LG"), new TownPerson("500-02-T3"),new TownPerson("500-02-T4"),new TownPerson("500-02-T2"),new TownPerson("500-02-T5"));
		String basicDescription = "You arrive at the residential area of the victims, looking for clues.";
		boolean neighborhoodStay = false;
		
		while (!neighborhoodStay) {
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
			                        if (y > 0 && neighborhood[x][y - 1] != null) {
			                            y--;
			                            System.out.println("You move left.");
			                        } else {
			                            System.out.println("You can't go left from here.");
			                        }
			                        break;
			                    case "RIGHT":
			                    case "EAST":
			                    case "E":
			                        if (y < neighborhood[0].length - 1 && neighborhood[x][y + 1] != null) {
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
			                        if (x > 0 && neighborhood[x - 1][y] != null) {
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
			                        if (x < neighborhood.length - 1 && neighborhood[x + 1][y] != null) {
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
		                basicDescription = "You find yourself at " + neighborhood[x][y].getBasicDesc(); //fix this grammar
			            break;
		            case "EXPLORE":
		                System.out.println("You look around...");
		                System.out.println("Where you're standing: " + neighborhood[x][y].getBasicDesc());
	
		                // Check left direction
		                if (y > 0 && neighborhood[x][y - 1] != null) {
		                    System.out.println("To the left: " + neighborhood[x][y - 1].getBasicDesc());
		                } else {
		                    System.out.println("To the left: dense fog. You can't go that way...");
		                }
	
		                // Check right direction
		                if (y < neighborhood[0].length - 1 && neighborhood[x][y + 1] != null) {
		                    System.out.println("To the right: " + neighborhood[x][y + 1].getBasicDesc());
		                } else {
		                    System.out.println("To the right: dense fog. You can't go that way...");
		                }
	
		                // Check forward direction
		                if (x > 0 && neighborhood[x - 1][y] != null) {
		                    System.out.println("In front: " + neighborhood[x - 1][y].getBasicDesc());
		                } else {
		                    System.out.println("In front: dense fog. You can't go that way...");
		                }
	
		                // Check backward direction
		                if (x < neighborhood.length - 1 && neighborhood[x + 1][y] != null) {
		                    System.out.println("Behind: " + neighborhood[x + 1][y].getBasicDesc());
		                } else {
		                    System.out.println("Behind: dense fog. You can't go that way...");
		                }
		                
		                break;
		            case "TALK":
		            	if ( neighborhood[x][y].hasNPC() ) {
		            		neighborhood[x][y].getNpc().interact();
		            	}
		            	else {
		            		System.out.println("There's no one at this spot to talk to. Are you going mad?");
		            	}
		            	break;
		            case "LOOK":
		            	System.out.println(neighborhood[x][y].getDetailedDesc());
		            	break;
		            case "TAKE":
		            	p("There's nothing here to pick up.");
		                break;
		            case "HELP":
		            	displayHelp();
		            	break;
		            case "LEAVE":	
		            	neighborhoodStay = true;
		            	p("You decide to leave the neighborhood.");
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
	
	public static boolean runResBunnyFound(String name, Player player) {
		neighborhood = createResArea(new TownPerson("500-02-T1"), new LittleGirl("500-03-LG"), new TownPerson("500-02-T3"),new TownPerson("500-02-T4"),new TownPerson("500-02-T2"),new TownPerson("500-02-T5"));
		String basicDescription = "You arrive at the residential area of the victims, looking for clues.";
		boolean neighborhoodStay = false;
		boolean gotMoney = false;
		
		while (!neighborhoodStay) {
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
			                        if (y > 0 && neighborhood[x][y - 1] != null) {
			                            y--;
			                            System.out.println("You move left.");
			                        } else {
			                            System.out.println("You can't go left from here.");
			                        }
			                        break;
			                    case "RIGHT":
			                    case "EAST":
			                    case "E":
			                        if (y < neighborhood[0].length - 1 && neighborhood[x][y + 1] != null) {
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
			                        if (x > 0 && neighborhood[x - 1][y] != null) {
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
			                        if (x < neighborhood.length - 1 && neighborhood[x + 1][y] != null) {
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
		                basicDescription = "You find yourself at " + neighborhood[x][y].getBasicDesc(); //fix this grammar
			            break;
		            case "EXPLORE":
		                System.out.println("You look around...");
		                System.out.println("Where you're standing: " + neighborhood[x][y].getBasicDesc());
	
		                // Check left direction
		                if (y > 0 && neighborhood[x][y - 1] != null) {
		                    System.out.println("To the left: " + neighborhood[x][y - 1].getBasicDesc());
		                } else {
		                    System.out.println("To the left: dense fog. You can't go that way...");
		                }
	
		                // Check right direction
		                if (y < neighborhood[0].length - 1 && neighborhood[x][y + 1] != null) {
		                    System.out.println("To the right: " + neighborhood[x][y + 1].getBasicDesc());
		                } else {
		                    System.out.println("To the right: dense fog. You can't go that way...");
		                }
	
		                // Check forward direction
		                if (x > 0 && neighborhood[x - 1][y] != null) {
		                    System.out.println("In front: " + neighborhood[x - 1][y].getBasicDesc());
		                } else {
		                    System.out.println("In front: dense fog. You can't go that way...");
		                }
	
		                // Check backward direction
		                if (x < neighborhood.length - 1 && neighborhood[x + 1][y] != null) {
		                    System.out.println("Behind: " + neighborhood[x + 1][y].getBasicDesc());
		                } else {
		                    System.out.println("Behind: dense fog. You can't go that way...");
		                }
		                
		                break;
		            case "TALK":
		            	if ( neighborhood[x][y].hasNPC() ) {
		            		neighborhood[x][y].getNpc().interact();
		            		if (x==1 && y==0) {
		            			System.out.println("You got 30 euros." );
			                	player.addRegularInventory(new Item("Little Girl's Payment", "BRIBEMONEY", "This is the money the little girl gave you in payment of finding her rabbit. They are old bills, kept for a long time.\nYou feel bad having this money, but the kid insisted on you keeping it. Use it well."));
			                	player.removeRegularInventory("BUNNY");
		            			gotMoney = true;
		            		}
		            	}
		            	else {
		            		System.out.println("There's no one at this spot to talk to. Are you going mad?");
		            	}
		            	break;
		            case "LOOK":
		            	System.out.println(neighborhood[x][y].getDetailedDesc());
		            	break;
		            case "TAKE":
		            	p("There's nothing here to pick up.");
		                break;
		            case "HELP":
		            	displayHelp();
		            	break;
		            case "LEAVE":	
		            	neighborhoodStay = true;
		            	p("You decide to leave the neighborhood.");
		            	break;
		            default:
		                System.out.println("You can't really... do that.");
		        }
	        }
	        else
	        	System.out.println("You didn't even type anything.");
	        
	        System.out.println("\n");
	    }
		
		return gotMoney;

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