package locations;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import npc.interactables.Bartender;
import npc.interactables.CultLeader;
import npc.interactables.GameObject;
import player.Player;
import util.Room;
import util.TextFont;

public class Forest {
	private static Room[][] trail;
	static int x = 4;
	static int y = 1;
	private static Scanner sc = new Scanner(System.in);
	private static Player player;
	
	private static Room[][] createMountainTrail() {
	    Room[][] mountainTrail = new Room[5][3];
	    
	    //you find yourself at...
	    mountainTrail[4][1] = new Room("Trail Entrance", "the entrance to Pine Ridge Trail. The air is fresh, and the scent of pine trees fills your lungs.", 
	        "You see the trail winding up the mountain, with a dense forest on either side.", null, null, null);
	    mountainTrail[3][1] = new Room("Lookout Point", "a lookout point. The view here is breathtaking, with the entire valley stretching out before you.", 
	        "You can see the distant mountains and a river winding through the valley.", null, null, null);
	    mountainTrail[2][1] = new Room("Mystery Clearing", "a clearing along the trail. An eerie feeling washes over you, as if you're being watched.", 
	        "The grass here is slightly trampled, and there's an unsettling silence.", null, null, null);
	    mountainTrail[1][1] = new Room("Aura of Death", "a spot on the trail where the aura of a dead body can be felt. The air here is noticeably colder.", 
	        "You can almost see the outline of where the body must have lain. The atmosphere is heavy with sorrow.", null, null, null);
	    mountainTrail[0][1] = new Room("Ghostly Corner", "the far corner of the trail, where you can sense a deadly aura.", 
	        "Her figure is translucent, and she seems to be watching you with a sorrowful expression.", null, null, null);
	    
	    //empty woods surrounding the trail
	    mountainTrail[4][0] = new Room("Dense Woods", "dense woods. The trees are thick, and it's difficult to see far through them.", 
	        "The sound of birds and rustling leaves fills the air.", null, null, null);
	    mountainTrail[3][0] = new Room("Forest Clearing", "a small clearing in the woods. Sunlight filters through the canopy.", 
	        "In the middle of the clearing, there's an old, weathered bench.", null, null, null);
	    mountainTrail[2][0] = new Room("Old Oak Tree", "a part of the woods dominated by a massive, ancient oak tree.", 
	        "The oak's branches are wide and gnarled, making a thick canopy overhead.", null, null, null);
	    mountainTrail[1][0] = new Room("Abandoned Campsite", "a small, abandoned campsite in the woods.", 
	        "You see remnants of a campfire and a few scattered, broken camping supplies.", null, null, null);
	    mountainTrail[0][0] = new Room("Dense Woods", "dense woods. The trees are thick, and it's difficult to see far through them.", 
	        "The sound of birds and rustling leaves fills the air.", null, null, null);

	    mountainTrail[4][2] = new Room("Dense Woods", "dense woods. The trees are thick, and it's difficult to see far through them.", 
	        "The sound of birds and rustling leaves fills the air.", null, null, null);
	    mountainTrail[3][2] = new Room("Rocky Stream", "a rocky stream cutting through the woods.", 
	        "You can hear the sound of water rushing over rocks and see a few small fish darting around.", null, null, null);
	    mountainTrail[2][2] = new Room("Wildflower Meadow", "a beautiful meadow filled with wildflowers.", 
	        "The flowers are in full bloom, adding a splash of color to the forest.", null, null, null);
	    mountainTrail[1][2] = new Room("Fallen Tree", "a part of the woods where a large tree has fallen.", 
	        "The fallen tree creates an obstacle, but also a place to sit and rest.", null, null, null);
	    mountainTrail[0][2] = new Room("Dense Woods", "dense woods. The trees are thick, and it's difficult to see far through them.", 
	        "The sound of birds and rustling leaves fills the air.", null, null, null);
	    
	    return mountainTrail;
	}
	
	public static void dayTwoMountain(String name, Player player) throws InterruptedException {
			TextFont.printTitle("DAY TWO");
			trail = createMountainTrail();
			String basicDescription = "You arrive at the entrance to Pine Ridge Trail. The air is fresh, and the scent of pine trees fills your lungs. ";
			boolean trailStay = false;
			
			while (!trailStay) {
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
				                        if (y > 0 && trail[x][y - 1] != null) {
				                            y--;
				                            System.out.println("You move left.");
				                        } else {
				                            System.out.println("You can't go left from here.");
				                        }
				                        break;
				                    case "RIGHT":
				                    case "EAST":
				                    case "E":
				                        if (y < trail[0].length - 1 && trail[x][y + 1] != null) {
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
				                        if (x > 0 && trail[x - 1][y] != null) {
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
				                        if (x < trail.length - 1 && trail[x + 1][y] != null) {
				                            x++;
				                            System.out.println("You move backward.");
				                        } else {
				                            System.out.println("You can't go backward from here.");
				                        }
				                        break;
				                    default:
				                        System.out.println("You can't go in that direction. Try using cardinal directions.");
				                    
				                }
				                
				                if (x==0 && y==1) {
				                	trailStay=true;
				                }
				            }
				            else {
				                System.out.println("Invalid. Please provide a direction.");
				            }
			                basicDescription = "You find yourself at " + trail[x][y].getBasicDesc(); //fix this grammar
				            break;
			            case "EXPLORE":
			                System.out.println("You look around...");
			                System.out.println("Where you're standing: " + trail[x][y].getBasicDesc());
		
			                // Check left direction
			                if (y > 0 && trail[x][y - 1] != null) {
			                    System.out.println("To the left: " + trail[x][y - 1].getBasicDesc());
			                } else {
			                    System.out.println("To the left: dense fog. You can't go that way...");
			                }
		
			                // Check right direction
			                if (y < trail[0].length - 1 && trail[x][y + 1] != null) {
			                    System.out.println("To the right: " + trail[x][y + 1].getBasicDesc());
			                } else {
			                    System.out.println("To the right: dense fog. You can't go that way...");
			                }
		
			                // Check forward direction
			                if (x > 0 && trail[x - 1][y] != null) {
			                    System.out.println("In front: " + trail[x - 1][y].getBasicDesc());
			                } else {
			                    System.out.println("In front: dense fog. You can't go that way...");
			                }
		
			                // Check backward direction
			                if (x < trail.length - 1 && trail[x + 1][y] != null) {
			                    System.out.println("Behind: " + trail[x + 1][y].getBasicDesc());
			                } else {
			                    System.out.println("Behind: dense fog. You can't go that way...");
			                }
			                
			                break;
			            case "TALK":
			            	System.out.println("There's no one at this spot to talk to. Are you going mad?");
			            	break;
			            case "LOOK":
			            	System.out.println(trail[x][y].getDetailedDesc());
			            	break;
			            case "TAKE":
			            	p("There's nothing here to take.");
			                break;
			            case "HELP":
			            	displayHelp();
			            	break;
			            case "LEAVE":	
			            	p("You can't leave the trail. You sense there's something here.");
			            	break;
			            default:
			                System.out.println("You can't really... do that.");
			        }
		        }
		        else
		        	System.out.println("You didn't even type anything.");
		        
		        System.out.println("\n");
		    } 
			
			p("The deadly aura in front of you starts to manifest into something horrifiyng."); //run the animation!!
			
			
			
			p("                                                            \r\n"
					+ "                                                            \r\n"
					+ "                                                            \r\n"
					+ "                                                            \r\n"
					+ "                                                            \r\n"
					+ "                                                            \r\n"
					+ "                                                            \r\n"
					+ "                                                            \r\n"
					+ "                                                            \r\n"
					+ "                                                            \r\n"
					+ "                                                            \r\n"
					+ "                                                            \r\n"
					+ "                                                            \r\n"
					+ "        ▓▓                                           ▓      \r\n"
					+ "       ▓▓  ▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓  ▓     \r\n"
					+ "      ▓▓                                             ▓      \r\n"
					+ "      ▓                                              ▓▓     \r\n"
					+ "      ▓▓▓▓                                                  \r\n"
					+ "                                                            \r\n"
					+ "                                                            \r\n"
					+ "                                                            \r\n"
					+ "                                                            \r\n"
					+ "                                                            \r\n"
					+ "                                                            \r\n"
					+ "                                                            \r\n"
					+ "                                                            \r\n"
					+ "                                                            \r\n"
					+ "                                                            \r\n"
					+ "                                                            \r\n"
					+ "                                                            ",4);
			
			p("                                                            \r\n"
					+ "                                                            \r\n"
					+ "                                                            \r\n"
					+ "                                                            \r\n"
					+ "                                                            \r\n"
					+ "                                                            \r\n"
					+ "                                                            \r\n"
					+ "                                                            \r\n"
					+ "                                                            \r\n"
					+ "                                                            \r\n"
					+ "                                                            \r\n"
					+ "                                                            \r\n"
					+ "       ▓▓                ▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓          ▓      \r\n"
					+ "      ▓▓▓▓  ▓▓▓▓▓▓▓▓▓▓▓▓▓▓                ▓▓▓▓▓▓▓    ▓▓     \r\n"
					+ "      ▓▓▓  ▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓  ▓     \r\n"
					+ "      ▓▓                ▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓             ▓▓▓    \r\n"
					+ "      ▓▓▓▓▓                                          ▓▓▓    \r\n"
					+ "      ▓▓▓▓▓▓▓                                        ▓      \r\n"
					+ "            ▓ ▓                                             \r\n"
					+ "                                                            \r\n"
					+ "                                                            \r\n"
					+ "                                                            \r\n"
					+ "                                                            \r\n"
					+ "                                                            \r\n"
					+ "                                                            \r\n"
					+ "                                                            \r\n"
					+ "                                                            \r\n"
					+ "                                                            \r\n"
					+ "                                                            \r\n"
					+ "                                                            ",1);
			
			p("                                                            \r\n"
					+ "                                                            \r\n"
					+ "                                                            \r\n"
					+ "                                                            \r\n"
					+ "                                                            \r\n"
					+ "                                                            \r\n"
					+ "                                                            \r\n"
					+ "                                                            \r\n"
					+ "                                                            \r\n"
					+ "                                                            \r\n"
					+ "                                                ▓▓          \r\n"
					+ "                          ▓▓▓▓▓▓▓▓▓▓▓▓▓▓         ▓▓▓        \r\n"
					+ "             ▓▓▓▓▓▓▓▓▓▓▓▓ ▓  ▓   ▓▓▓▓▓  ▓▓▓▓▓▓     ▓        \r\n"
					+ "           ▓▓▓ ▓▓      ▓▓        ▓▓ ▓        ▓▓▓▓   ▓       \r\n"
					+ "        ▓▓▓▓▓▓         ▓▓        ▓▓ ▓     ▓▓▓▓     ▓▓       \r\n"
					+ "             ▓▓ ▓▓▓▓▓▓▓ ▓▓       ▓▓▓     ▓▓▓▓      ▓        \r\n"
					+ "     ▓▓▓▓▓▓▓        ▓▓▓▓▓▓▓▓ ▓▓▓▓▓▓ ▓▓▓▓▓▓▓        ▓        \r\n"
					+ "       ▓    ▓ ▓              ▓▓▓▓▓▓▓▓                       \r\n"
					+ "              ▓▓ ▓                                          \r\n"
					+ "                 ▓                                          \r\n"
					+ "                                                            \r\n"
					+ "                                                            \r\n"
					+ "                                                            \r\n"
					+ "                                                            \r\n"
					+ "                                                            \r\n"
					+ "                                                            \r\n"
					+ "                                                            \r\n"
					+ "                                                            \r\n"
					+ "                                                            \r\n"
					+ "                                                            ",1);
			
			p("                                                            \r\n"
					+ "                                                            \r\n"
					+ "                                                            \r\n"
					+ "                                                            \r\n"
					+ "                                                            \r\n"
					+ "                                                            \r\n"
					+ "                                                            \r\n"
					+ "                                                            \r\n"
					+ "                       ▓▓▓▓▓▓▓▓▓▓▓▓▓                        \r\n"
					+ "              ▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓    ▓▓▓▓▓                     \r\n"
					+ "            ▓▓▓▓  ▓▓▓▓       ▓▓        ▓▓▓▓                 \r\n"
					+ "          ▓▓▓     ▓ ▓  ▓▓▓  ▓▓▓          ▓▓▓▓▓              \r\n"
					+ "         ▓▓      ▓ ▓▓  ▓▓▓   ▓▓           ▓▓▓▓              \r\n"
					+ "       ▓▓▓       ▓ ▓▓       ▓▓           ▓▓ ▓ ▓             \r\n"
					+ "     ▓▓▓ ▓▓      ▓▓ ▓▓▓ ▓▓▓▓▓▓         ▓▓   ▓               \r\n"
					+ "    ▓      ▓▓▓▓▓ ▓▓▓▓▓▓▓▓▓▓ ▓          ▓  ▓▓                \r\n"
					+ "              ▓▓▓     ▓▓ ▓▓▓         ▓▓ ▓▓▓                 \r\n"
					+ "                ▓▓▓▓                ▓▓▓▓▓                   \r\n"
					+ "                   ▓▓▓▓▓▓▓▓▓   ▓▓▓▓▓▓▓                      \r\n"
					+ "                      ▓ ▓▓▓▓▓▓▓▓ ▓▓                         \r\n"
					+ "                                                            \r\n"
					+ "                                                            \r\n"
					+ "                                                            \r\n"
					+ "                                                            \r\n"
					+ "                                                            \r\n"
					+ "                                                            \r\n"
					+ "                                                            \r\n"
					+ "                                                            \r\n"
					+ "                                                            \r\n"
					+ "                                                            ",1);
			
			p("                                                            \r\n"
					+ "                                                            \r\n"
					+ "                                                            \r\n"
					+ "                                                            \r\n"
					+ "                                                            \r\n"
					+ "                  ▓    ▓▓▓▓▓ ▓▓▓▓▓▓▓                        \r\n"
					+ "                ▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓  ▓▓                      \r\n"
					+ "              ▓▓▓ ▓▓▓ ▓▓▓▓▓▓▓     ▓▓▓▓▓▓▓                   \r\n"
					+ "            ▓▓▓▓▓▓   ▓▓▓▓▓▓ ▓▓▓▓▓     ▓▓▓▓▓                 \r\n"
					+ "           ▓▓▓▓     ▓▓▓▓▓      ▓▓▓      ▓▓ ▓▓               \r\n"
					+ "           ▓▓▓      ▓▓ ▓   ▓▓    ▓▓       ▓  ▓▓             \r\n"
					+ "         ▓▓▓        ▓▓▓   ▓▓▓▓    ▓        ▓▓ ▓▓            \r\n"
					+ "        ▓▓          ▓▓▓   ▓▓▓▓    ▓         ▓▓ ▓▓           \r\n"
					+ "       ▓▓           ▓▓▓▓         ▓▓          ▓▓▓▓▓          \r\n"
					+ "      ▓▓ ▓           ▓▓▓▓     ▓▓▓▓          ▓▓  ▓▓          \r\n"
					+ "     ▓▓   ▓▓           ▓▓▓▓▓▓▓▓▓           ▓▓    ▓▓         \r\n"
					+ "           ▓▓▓                            ▓▓▓     ▓▓        \r\n"
					+ "            ▓▓▓▓                         ▓▓▓       ▓        \r\n"
					+ "              ▓▓▓                     ▓▓▓▓▓▓        ▓       \r\n"
					+ "                ▓▓▓▓               ▓▓▓▓▓▓▓                  \r\n"
					+ "                  ▓▓▓▓▓▓ ▓▓▓▓▓▓▓▓▓▓▓▓ ▓                     \r\n"
					+ "                         ▓▓▓▓▓ ▓▓▓▓▓▓                       \r\n"
					+ "                                                            \r\n"
					+ "                                                            \r\n"
					+ "                                                            \r\n"
					+ "                                                            \r\n"
					+ "                                                            \r\n"
					+ "                                                            \r\n"
					+ "                                                            \r\n"
					+ "                                                            ",1);
			
			p("                                                            \r\n"
					+ "                                                            \r\n"
					+ "                                                            \r\n"
					+ "                                                            \r\n"
					+ "                                                            \r\n"
					+ "                             ▓   ▓▓                         \r\n"
					+ "                         ▓▓▓▓▓▓▓▓▓▓▓▓▓▓                     \r\n"
					+ "                   ▓ ▓▓▓▓ ▓       ▓  ▓▓▓▓▓▓                 \r\n"
					+ "                ▓▓▓▓▓ ▓▓▓▓ ▓▓▓▓▓▓▓▓▓▓▓▓▓▓ ▓▓                \r\n"
					+ "             ▓▓▓▓▓▓▓▓   ▓▓▓▓▓▓▓▓▓▓▓   ▓▓▓▓  ▓▓              \r\n"
					+ "            ▓▓▓▓▓     ▓▓▓▓       ▓▓▓     ▓▓▓▓▓▓             \r\n"
					+ "          ▓▓▓▓        ▓▓▓          ▓▓      ▓▓▓▓  ▓          \r\n"
					+ "         ▓▓▓         ▓▓ ▓  ▓▓▓      ▓▓      ▓▓▓▓▓           \r\n"
					+ "         ▓           ▓▓ ▓  ▓▓▓      ▓▓▓      ▓▓▓▓           \r\n"
					+ "        ▓▓           ▓▓ ▓           ▓ ▓        ▓▓▓▓         \r\n"
					+ "       ▓▓▓▓▓          ▓  ▓        ▓▓  ▓       ▓  ▓▓▓        \r\n"
					+ "      ▓▓   ▓▓▓         ▓▓  ▓▓▓ ▓▓ ▓  ▓▓     ▓▓▓▓   ▓▓       \r\n"
					+ "     ▓▓▓    ▓▓▓          ▓ ▓▓▓▓▓▓▓▓▓▓▓     ▓▓▓▓▓    ▓       \r\n"
					+ "     ▓▓        ▓▓                        ▓▓▓▓       ▓▓      \r\n"
					+ "      ▓         ▓▓▓▓                  ▓ ▓▓▓                 \r\n"
					+ "                  ▓▓▓ ▓ ▓▓ ▓▓▓     ▓▓ ▓ ▓                   \r\n"
					+ "                       ▓▓▓▓▓▓▓▓▓▓▓▓▓▓                       \r\n"
					+ "                              ▓▓▓                           \r\n"
					+ "                                                            \r\n"
					+ "                                                            \r\n"
					+ "                                                            \r\n"
					+ "                                                            \r\n"
					+ "                                                            \r\n"
					+ "                                                            \r\n"
					+ "                                                            ",1);
			TimeUnit.SECONDS.sleep(1);
			int i=0;
			while (i<10) {
			p("⠀⠀⠀⠀⠀⠀⠀⢠⠣⡑⡕⡱⡸⡀⡢⡂⢨⠀⡌⠀⠀⠀⠀⠀⠀ \r\n"
					+ "⠀⠀⠀⠀⠀⠀⠀⡕⢅⠕⢘⢜⠰⣱⢱⢱⢕⢵⠰⡱⡱⢘⡄⡎⠌⡀⠀⠀⠀⠀ \r\n"
					+ "⠀⠀⠀⠀⠀⠀⠱⡸⡸⡨⢸⢸⢈⢮⡪⣣⣣⡣⡇⣫⡺⡸⡜⡎⡢⠀⠀⠀⠀⠀ \r\n"
					+ "⠀⠀⠀⠀⠀⠀⢱⢱⠵⢹⢸⢼⡐⡵⣝⢮⢖⢯⡪⡲⡝⠕⣝⢮⢪⢀⠀⠀⠀⠀ \r\n"
					+ "⠀⠀⠀⠀⢀⠂⡮⠁⠐⠀⡀⡀⠑⢝⢮⣳⣫⢳⡙⠐⠀⡠⡀⠀⠑⠀⠀⠀⠀⠀ \r\n"
					+ "⠀⠀⠀⠀⢠⠣⠐⠀ ⭕ ￼ ⠀⠀⢪⢺⣪⢣⠀⡀ ⭕     .⠈⡈⠀⡀⠀⠀ \r\n"
					+ "⠀⠀⠀⠀⠐⡝⣕⢄⡀⠑⢙⠉⠁⡠⡣⢯⡪⣇⢇⢀⠀⠡⠁⠁⡠⡢⠡⠀⠀⠀ \r\n"
					+ "⠀⠀⠀⠀⠀⢑⢕⢧⣣⢐⡄⣄⡍⡎⡮⣳⢽⡸⡸⡊⣧⣢⠀⣕⠜⡌⠌⠀⠀⠀ \r\n"
					+ "⠀⠀⠀⠀⠀⠀⠌⡪⡪⠳⣝⢞⡆⡇⡣⡯⣞⢜⡜⡄⡧⡗⡇⠣⡃⡂⠀⠀⠀⠀ \r\n"
					+ "⠀⠀⠀⠀⠀⠀⠀⠨⢊⢜⢜⣝⣪⢪⠌⢩⢪⢃⢱⣱⢹⢪⢪⠊⠀⠀⠀⠀⠀⠀ \r\n"
					+ "⠀⠀⠀⠀⠀⠀⠀⠀⠐⠡⡑⠜⢎⢗⢕⢘⢜⢜⢜⠜⠕⠡⠡⡈⠀⠀⠀⠀⠀⠀ \r\n"
					+ "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠁⡢⢀⠈⠨⣂⡐⢅⢕⢐⠁⠡⠡⢁⠀⠀⠀⠀⠀⠀⠀ \r\n"
					+ "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢈⠢⠀⡀⡐⡍⢪⢘⠀⠀⠡⡑⡀⠀⠀⠀⠀⠀⠀⠀ \r\n"
					+ "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠨⢂⠀⠌⠘⢜⠘⠀⢌⠰⡈⠀⠀⠀⠀⠀⠀⠀⠀ \r\n"
					+ "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢑⢸⢌⢖⢠⢀⠪⡂"
					+ "                              \n"
					+ "                              \n"
					+ "                              \n");
			TimeUnit.MILLISECONDS.sleep(100);
			TextFont.printTitle("HELP   ME");
			TimeUnit.MILLISECONDS.sleep(100);
			i++;
			}
			
			i=0;
			while (i<10) {
			TextFont.printTitle("I");
			TimeUnit.MILLISECONDS.sleep(100);
			i++;
			}
			
			i=0;
			while (i<10) {
			p("⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿\r\n"
					+ "⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⢿⣿⣿⣿⣿⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿\r\n"
					+ "⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠟⠁⣾⣟⣛⣿⣿⡌⠛⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠏⠁⣼⣿⣽⣿⣿⣧⠈⢻⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿\r\n"
					+ "⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡯⡀⠀⢿⣿⣿⣿⣿⠳⠀⢸⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠄⠀⠹⣿⣿⣿⣿⠋⠃⢸⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿\r\n"
					+ "⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣦⣄⣈⡙⠛⣛⣁⣤⣶⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣤⣤⣌⣉⣭⣥⣴⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿\r\n"
					+ "⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿");
			TimeUnit.MILLISECONDS.sleep(100);
			p("                                                            \r\n"
					+ "                                                            \r\n"
					+ "                                                            \r\n"
					+ "                                                            \r\n"
					+ "                                                            \r\n"
					+ "                             ▓   ▓▓                         \r\n"
					+ "                         ▓▓▓▓▓▓▓▓▓▓▓▓▓▓                     \r\n"
					+ "                   ▓ ▓▓▓▓ ▓       ▓  ▓▓▓▓▓▓                 \r\n"
					+ "                ▓▓▓▓▓ ▓▓▓▓ ▓▓▓▓▓▓▓▓▓▓▓▓▓▓ ▓▓                \r\n"
					+ "             ▓▓▓▓▓▓▓▓   ▓▓▓▓▓▓▓▓▓▓▓   ▓▓▓▓  ▓▓              \r\n"
					+ "            ▓▓▓▓▓     ▓▓▓▓       ▓▓▓     ▓▓▓▓▓▓             \r\n"
					+ "          ▓▓▓▓        ▓▓▓          ▓▓      ▓▓▓▓  ▓          \r\n"
					+ "         ▓▓▓         ▓▓ ▓  ▓▓▓      ▓▓      ▓▓▓▓▓           \r\n"
					+ "         ▓           ▓▓ ▓  ▓▓▓      ▓▓▓      ▓▓▓▓           \r\n"
					+ "        ▓▓           ▓▓ ▓           ▓ ▓        ▓▓▓▓         \r\n"
					+ "       ▓▓▓▓▓          ▓  ▓        ▓▓  ▓       ▓  ▓▓▓        \r\n"
					+ "      ▓▓   ▓▓▓         ▓▓  ▓▓▓ ▓▓ ▓  ▓▓     ▓▓▓▓   ▓▓       \r\n"
					+ "     ▓▓▓    ▓▓▓          ▓ ▓▓▓▓▓▓▓▓▓▓▓     ▓▓▓▓▓    ▓       \r\n"
					+ "     ▓▓        ▓▓                        ▓▓▓▓       ▓▓      \r\n"
					+ "      ▓         ▓▓▓▓                  ▓ ▓▓▓                 \r\n"
					+ "                  ▓▓▓ ▓ ▓▓ ▓▓▓     ▓▓ ▓ ▓                   \r\n"
					+ "                       ▓▓▓▓▓▓▓▓▓▓▓▓▓▓                       \r\n"
					+ "                              ▓▓▓                           \r\n"
					+ "                                                            \r\n"
					+ "                                                            \r\n"
					+ "                                                            \r\n"
					+ "                                                            \r\n"
					+ "                                                            \r\n"
					+ "                                                            \r\n"
					+ "                                                            ");
			TimeUnit.MILLISECONDS.sleep(100);
			i++;
			}
			
			i=0;
			while (i<10) {
			TextFont.printTitle("SEE");
			TimeUnit.MILLISECONDS.sleep(100);
			TextFont.printTitle("YOU");
			TimeUnit.MILLISECONDS.sleep(100);
			i++;
			}
			
			i=0;
			while (i<10) {
			TextFont.printTitle(name.toUpperCase());
			TimeUnit.MILLISECONDS.sleep(100);
			TextFont.printTitle("DIE");
			TimeUnit.MILLISECONDS.sleep(100);
			i++;
			}
			p("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⣤⢚⣿⣏⣭⣷⣿⣿⣶⣯⣽⡒⡤⢤⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
					+ "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⡴⣪⣷⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣝⢦⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
					+ "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡠⣯⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⡝⢦⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
					+ "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣸⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣮⣷⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
					+ "⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⡞⣵⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⣌⢢⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
					+ "⠀⠀⠀⠀⠀⠀⠀⠀⡠⣺⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣟⢷⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
					+ "⠀⠀⠀⠀⠀⠀⠀⣼⣵⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣾⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
					+ "⠀⠀⠀⠀⠀⠀⠀⡧⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡭⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
					+ "⠀⠀⠀⠀⠀⠀⢰⢻⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠿⣿⣟⠛⢿⣿⣿⣿⣿⣿⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
					+ "⠀⠀⠀⠀⠀⠀⢸⢸⣿⣿⣿⣿⣿⣿⣿⡿⣿⠿⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠁⣼⣿⣿⣷⡄⢹⣿⣿⣿⣿⣯⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
					+ "⠀⠀⠀⠀⠀⠀⠈⣿⣿⣿⣿⣿⣿⡟⢁⣾⣿⣿⡆⢻⣿⣿⣿⣿⣿⣿⣿⣿⣄⣿⣿⣿⣿⣇⣼⣿⣿⣿⣿⢸⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
					+ "⠀⠀⠀⠀⠀⠀⠀⣧⣿⣿⣿⣿⣿⣧⣀⣿⣿⣿⣧⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
					+ "⠀⠀⠀⠀⠀⠀⠀⢸⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
					+ "⠀⠀⠀⠀⠀⠀⠀⠘⣧⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣬⣻⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
					+ "⠀⠀⠀⠀⠀⠀⠀⠀⣸⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠿⣿⣸⠃⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
					+ "⠀⠀⠀⠀⠀⠀⠀⢸⢷⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠏⠀⣏⡿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
					+ "⠀⠀⠀⠀⠀⠀⠀⠘⣦⢻⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡀⢸⣿⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
					+ "⠀⠀⠀⠀⠀⠀⠀⠀⠈⣏⣻⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⠉⢀⣏⡿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
					+ "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠘⣯⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⢃⣠⣾⡾⠃⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
					+ "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠹⣮⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣟⡾⠏⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
					+ "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢻⣻⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣟⣻⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
					+ "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢳⣸⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⡟⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
					+ "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⣿⣷⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
					+ "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡇⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠃⡷⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
					+ "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣧⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠃⠀⣿⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
					+ "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠟⠀⠀⢿⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
					+ "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢿⣼⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠏⠀⠀⠀⢸⣿⡆⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
					+ "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⣤⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠟⠁⠀⠀⠀⠀⠀⢷⣷⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
					+ "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣸⣸⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡟⠋⠀⠀⠀⠀⠀⠀⠀⠘⣿⣇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
					+ "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣏⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠛⠻⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
					+ "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣼⣹⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢮⡘⠶⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
					+ "⠀⠀⠀⠀⠀⠀⠀⠀⢀⡴⠋⣹⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡟⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢿⡄⠘⠶⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
					+ "⠀⠀⠀⠀⠀⠀⣀⡖⠋⠀⣼⣿⣿⣿⣿⣿⡿⠛⠿⢿⣿⣿⠿⠋⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢳⡄⠀⠘⢓⣦⣀⠀⠀⠀⠀⠀⠀\r\n"
					+ "⠀⠀⠀⢀⣤⠞⠃⠀⠀⣸⣿⣿⣿⣿⣿⠏⠀⠀⠀⠀⠀⠀⠂⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣷⠀⠀⠀⠈⠈⠳⢦⣀⠀⠀⠀\r\n"
					+ "⢠⠖⢺⣁⣈⣶⢀⣀⣀⣿⣿⣿⣿⣿⠋⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣹⡆⠀⣀⣀⣀⡆⢀⣈⠙⠲⣄");
			p("",2);
			TextFont.printTitle("ICANSEEYOU");
			p("",1);
			i=0;
			while (i<10) {
			p("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⣤⢚⣿⣏⣭⣷⣿⣿⣶⣯⣽⡒⡤⢤⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
					+ "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⡴⣪⣷⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣝⢦⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
					+ "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡠⣯⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⡝⢦⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
					+ "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣸⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣮⣷⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
					+ "⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⡞⣵⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⣌⢢⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
					+ "⠀⠀⠀⠀⠀⠀⠀⠀⡠⣺⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣟⢷⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
					+ "⠀⠀⠀⠀⠀⠀⠀⣼⣵⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣾⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
					+ "⠀⠀⠀⠀⠀⠀⠀⡧⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡭⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
					+ "⠀⠀⠀⠀⠀⠀⢰⢻⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠿⣿⣟⠛⢿⣿⣿⣿⣿⣿⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
					+ "⠀⠀⠀⠀⠀⠀⢸⢸⣿⣿⣿⣿⣿⣿⣿⡿⣿⠿⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠁⣼⣿⣿⣷⡄⢹⣿⣿⣿⣿⣯⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
					+ "⠀⠀⠀⠀⠀⠀⠈⣿⣿⣿⣿⣿⣿⡟⢁⣾⣿⣿⡆⢻⣿⣿⣿⣿⣿⣿⣿⣿⣄⣿⣿⣿⣿⣇⣼⣿⣿⣿⣿⢸⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
					+ "⠀⠀⠀⠀⠀⠀⠀⣧⣿⣿⣿⣿⣿⣧⣀⣿⣿⣿⣧⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
					+ "⠀⠀⠀⠀⠀⠀⠀⢸⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
					+ "⠀⠀⠀⠀⠀⠀⠀⠘⣧⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣬⣻⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
					+ "⠀⠀⠀⠀⠀⠀⠀⠀⣸⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠿⣿⣸⠃⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
					+ "⠀⠀⠀⠀⠀⠀⠀⢸⢷⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠏⠀⣏⡿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
					+ "⠀⠀⠀⠀⠀⠀⠀⠘⣦⢻⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡀⢸⣿⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
					+ "⠀⠀⠀⠀⠀⠀⠀⠀⠈⣏⣻⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⠉⢀⣏⡿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
					+ "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠘⣯⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⢃⣠⣾⡾⠃⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
					+ "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠹⣮⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣟⡾⠏⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
					+ "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢻⣻⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣟⣻⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
					+ "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢳⣸⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⡟⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
					+ "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⣿⣷⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
					+ "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡇⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠃⡷⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
					+ "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣧⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠃⠀⣿⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
					+ "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠟⠀⠀⢿⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
					+ "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢿⣼⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠏⠀⠀⠀⢸⣿⡆⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
					+ "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⣤⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠟⠁⠀⠀⠀⠀⠀⢷⣷⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
					+ "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣸⣸⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡟⠋⠀⠀⠀⠀⠀⠀⠀⠘⣿⣇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
					+ "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣏⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠛⠻⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
					+ "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣼⣹⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢮⡘⠶⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
					+ "⠀⠀⠀⠀⠀⠀⠀⠀⢀⡴⠋⣹⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡟⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢿⡄⠘⠶⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
					+ "⠀⠀⠀⠀⠀⠀⣀⡖⠋⠀⣼⣿⣿⣿⣿⣿⡿⠛⠿⢿⣿⣿⠿⠋⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢳⡄⠀⠘⢓⣦⣀⠀⠀⠀⠀⠀⠀\r\n"
					+ "⠀⠀⠀⢀⣤⠞⠃⠀⠀⣸⣿⣿⣿⣿⣿⠏⠀⠀⠀⠀⠀⠀⠂⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣷⠀⠀⠀⠈⠈⠳⢦⣀⠀⠀⠀\r\n"
					+ "⢠⠖⢺⣁⣈⣶⢀⣀⣀⣿⣿⣿⣿⣿⠋⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣹⡆⠀⣀⣀⣀⡆⢀⣈⠙⠲⣄");
			TimeUnit.MILLISECONDS.sleep(100);
			p("                                                            \r\n"
					+ "                                                            \r\n"
					+ "                                                            \r\n"
					+ "                                        ▓▓▓▓  ▓             \r\n"
					+ "                              ▓ ▓▓▓▓▓▓▓▓▓▓▓▓                \r\n"
					+ "                   ▓      ▓▓▓▓▓▓▓▓ ▓                        \r\n"
					+ "                  ▓▓▓     ▓▓▓  ▓                            \r\n"
					+ "  ▓▓▓▓▓▓▓         ▓▓▓▓    ▓▓▓                               \r\n"
					+ "     ▓▓▓▓▓▓        ▓▓▓    ▓▓▓                               \r\n"
					+ "      ▓▓▓▓▓▓▓      ▓▓▓▓    ▓▓                               \r\n"
					+ "      ▓▓▓ ▓▓▓▓▓▓▓  ▓▓▓▓    ▓▓▓                              \r\n"
					+ "      ▓▓▓   ▓  ▓▓   ▓ ▓     ▓▓         ▓▓▓                  \r\n"
					+ "      ▓▓     ▓ ▓▓▓▓ ▓▓▓▓    ▓▓▓  ▓▓▓▓▓▓                     \r\n"
					+ "      ▓▓     ▓  ▓▓▓ ▓ ▓     ▓▓▓▓ ▓ ▓ ▓▓▓▓                   \r\n"
					+ "      ▓▓▓     ▓ ▓▓▓   ▓▓    ▓▓▓▓▓   ▓▓                      \r\n"
					+ "      ▓ ▓     ▓ ▓▓▓  ▓ ▓▓    ▓▓                             \r\n"
					+ "      ▓▓▓     ▓ ▓▓▓  ▓ ▓▓    ▓▓▓                            \r\n"
					+ "      ▓▓▓     ▓ ▓▓▓     ▓     ▓▓                            \r\n"
					+ "      ▓▓▓▓    ▓ ▓▓    ▓ ▓      ▓                            \r\n"
					+ "      ▓▓▓     ▓▓▓▓     ▓▓     ▓▓                            \r\n"
					+ "       ▓▓▓   ▓▓▓▓▓     ▓▓▓   ▓ ▓   ▓                        \r\n"
					+ "       ▓▓▓  ▓▓ ▓▓       ▓▓    ▓▓▓     ▓▓▓▓                  \r\n"
					+ "       ▓▓▓▓▓▓▓▓         ▓▓▓▓▓▓ ▓▓▓▓▓▓▓▓▓▓                   \r\n"
					+ "       ▓ ▓▓▓▓            ▓▓▓   ▓                            \r\n"
					+ "       ▓▓▓           ▓▓▓  ▓   ▓▓                            \r\n"
					+ "     ▓▓▓                       ▓                            \r\n"
					+ "     ▓▓▓                                                    \r\n"
					+ "                                                            \r\n"
					+ "                                                            \r\n"
					+ "                                                            ");
			TimeUnit.MILLISECONDS.sleep(100);
			i++;
			}
			
			p("⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣛⠟⢒⠀⠂⠀⠐⠋⡰⢗⠳⠖⢷⣻⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿\r\n"
					+ "⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⢿⣿⣷⣉⢶⣲⣐⡀⠀⣀⣨⣷⣳⡀⠈⢈⢨⣻⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿\r\n"
					+ "⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣹⠟⣟⠁⢿⢦⢊⠊⡗⠘⡑⠙⠊⠀⠈⠘⣿⣟⢿⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿\r\n"
					+ "⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣯⠉⡢⠁⠀⠈⠉⠀⠁⠘⠈⠉⠀⠀⠀⠀⠀⠀⠀⠕⠆⡫⠿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿\r\n"
					+ "⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⢿⣅⡒⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠢⠊⠛⢟⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿\r\n"
					+ "⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣯⡿⣲⣾⡕⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠙⢶⡶⣻⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿\r\n"
					+ "⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⢶⡭⠅⠋⠊⠍⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠸⠼⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿\r\n"
					+ "⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣌⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⢼⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿\r\n"
					+ "⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⢱⣠⡐⣂⣦⣼⡿⣿⣆⣆⠀⠀⠀⠀⠀⢀⣠⣴⣶⣶⣦⣤⠀⠀⠘⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿\r\n"
					+ "⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⣾⣿⣿⣿⣿⡿⣎⠿⣿⣿⣷⡀⠀⠀⣾⣿⣻⣿⣿⣷⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿\r\n"
					+ "⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣺⣿⣿⣿⣿⣿⣿⣿⣙⣷⣼⣿⣿⠧⠀⠀⢸⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣻⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿\r\n"
					+ "⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠟⢿⣿⣿⣿⣿⣿⡿⣿⣿⡿⠋⠋⠁⠀⠀⠻⣿⡿⣿⣿⣿⡿⢿⣿⣟⣻⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿\r\n"
					+ "⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣤⣬⣿⣷⣿⣽⣥⣼⡿⠛⠁⠀⠀⠀⠀⠀⠀⠘⠳⣬⣽⣭⣾⠿⠛⣡⡿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿\r\n"
					+ "⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⣏⠁⠁⠈⠉⠉⠉⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢿⢯⢛⣼⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿\r\n"
					+ "⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣯⣯⡀⠀⠀⠀⠀⠀⠀⠀⠀⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⠠⣬⣢⣽⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿\r\n"
					+ "⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣮⣧⡼⠀⠈⠀⠀⠀⠸⠆⠀⠀⠠⠀⠀⠀⠀⠀⠀⠀⣰⣾⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿\r\n"
					+ "⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣯⣿⣽⣦⣀⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢨⡟⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿\r\n"
					+ "⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⣶⣶⣤⣤⣄⣤⣤⣤⣶⣴⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿\r\n"
					+ "⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿\r\n"
					+ "⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿\r\n"
					+ "⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣻⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿\r\n"
					+ "⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⢿⣟⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿\r\n"
					+ "⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿\r\n"
					+ "⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣟⣿⣿⣿⣿⣛⣻⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿\r\n"
					+ "⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⡻⢞⡿⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿\r\n"
					+ "⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⣿⣽⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿\r\n"
					+ "⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿\r\n"
					+ "⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿\r\n"
					+ "⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿");
			i=0;
			while (i<30) {
				p("RUN.");
				TimeUnit.MILLISECONDS.sleep(100);
				i++;
			}
			
		
			String chase = "WIP";
			
			while (!chase.equals("true")) {
				ForestChaseGame runGame = new ForestChaseGame();
				while(runGame.isEscaped().equals("WIP")) {
					TimeUnit.SECONDS.sleep(1); //wait until the whole chase game is done
				}
				
				chase = runGame.isEscaped();
				
				if (chase.equals("false")) {
					p("⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣛⠟⢒⠀⠂⠀⠐⠋⡰⢗⠳⠖⢷⣻⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿\r\n"
							+ "⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⢿⣿⣷⣉⢶⣲⣐⡀⠀⣀⣨⣷⣳⡀⠈⢈⢨⣻⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿\r\n"
							+ "⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣹⠟⣟⠁⢿⢦⢊⠊⡗⠘⡑⠙⠊⠀⠈⠘⣿⣟⢿⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿\r\n"
							+ "⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣯⠉⡢⠁⠀⠈⠉⠀⠁⠘⠈⠉⠀⠀⠀⠀⠀⠀⠀⠕⠆⡫⠿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿\r\n"
							+ "⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⢿⣅⡒⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠢⠊⠛⢟⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿\r\n"
							+ "⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣯⡿⣲⣾⡕⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠙⢶⡶⣻⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿\r\n"
							+ "⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⢶⡭⠅⠋⠊⠍⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠸⠼⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿\r\n"
							+ "⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣌⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⢼⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿\r\n"
							+ "⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⢱⣠⡐⣂⣦⣼⡿⣿⣆⣆⠀⠀⠀⠀⠀⢀⣠⣴⣶⣶⣦⣤⠀⠀⠘⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿\r\n"
							+ "⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⣾⣿⣿⣿⣿⡿⣎⠿⣿⣿⣷⡀⠀⠀⣾⣿⣻⣿⣿⣷⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿\r\n"
							+ "⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣺⣿⣿⣿⣿⣿⣿⣿⣙⣷⣼⣿⣿⠧⠀⠀⢸⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣻⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿\r\n"
							+ "⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠟⢿⣿⣿⣿⣿⣿⡿⣿⣿⡿⠋⠋⠁⠀⠀⠻⣿⡿⣿⣿⣿⡿⢿⣿⣟⣻⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿\r\n"
							+ "⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣤⣬⣿⣷⣿⣽⣥⣼⡿⠛⠁⠀⠀⠀⠀⠀⠀⠘⠳⣬⣽⣭⣾⠿⠛⣡⡿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿\r\n"
							+ "⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⣏⠁⠁⠈⠉⠉⠉⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢿⢯⢛⣼⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿\r\n"
							+ "⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣯⣯⡀⠀⠀⠀⠀⠀⠀⠀⠀⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⠠⣬⣢⣽⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿\r\n"
							+ "⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣮⣧⡼⠀⠈⠀⠀⠀⠸⠆⠀⠀⠠⠀⠀⠀⠀⠀⠀⠀⣰⣾⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿\r\n"
							+ "⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣯⣿⣽⣦⣀⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢨⡟⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿\r\n"
							+ "⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⣶⣶⣤⣤⣄⣤⣤⣤⣶⣴⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿\r\n"
							+ "⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿\r\n"
							+ "⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿\r\n"
							+ "⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣻⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿\r\n"
							+ "⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⢿⣟⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿\r\n"
							+ "⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿\r\n"
							+ "⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣟⣿⣿⣿⣿⣛⣻⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿\r\n"
							+ "⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⡻⢞⡿⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿\r\n"
							+ "⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⣿⣽⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿\r\n"
							+ "⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿\r\n"
							+ "⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿\r\n"
							+ "⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿\n\n\n");
					TextFont.printTitle("ENDING THREE");//THIRD ENDING!!!
					p("ENDING THREE - Committing the Sin of Sloth.",2);
					p("In an effort to escape the surprising ghost, you trip and fall, where in the ghost absorbs you into its own spirit. Only your clothes remain on the forest floor. Forgotten.");
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
		    			int x = 0;
		    			while(x < 40) {
		    				System.out.println(".");
		    				TimeUnit.MILLISECONDS.sleep(50);
		    				x++;
		    			}
		    		}
		    		else {
		    			System.out.println("I wish you well...");
		    			System.exit(0);
		    		}
				}
			}
			//cutscene of going to cult
			p("With all your might, you escaped the demon's sight, and you stay hidden in the woods all night.",2);
			p("You could hear the cries of the dead around you. You're too exhausted to try and communicate. Your legs feel like they could snap any moment.",4);
			p("You are strong enough to beat it, you know, but your resources right now, and the unpredictability of the event made you lose focus.",4);
			p("You've seen it. And even with your expertise in exorcism, this thing sends a chill down your spine.",4);
			
			p("As you contemplate, you notice a faint light in the distance. You decide to walk to it.",4);
			cave(name,player);
	}
	
	private static Room[][] createCultistCave(GameObject leader) {
		  Room[][] cave = new Room[3][3];
		    
		    //you find yourself at...
		    cave[1][0] = new Room("Forest Clearing", "a small clearing in the woods, surrounded by tall trees. The sunlight filters through the canopy above.", 
		        "You can hear the sounds of birds chirping and leaves rustling in the gentle breeze.", null, null, null);
		    cave[1][1] = new Room("Cave Entrance", "the entrance to a dark cave hidden deep in the woods. A sense of foreboding hangs in the air. Firelight comes from within.", 
		        "The mouth of the cave yawns open before you, its darkness inviting yet ominous.", null, null, null);
		    cave[1][2] = new Room("Walls of the Cave", "the rough walls of the cave close in around you, their surface damp and cold to the touch. Strange cultic symbols are painted on the walls.", 
		        "The dim light from outside barely penetrates the darkness of the cave.", null, null, null);
		    cave[0][1] = new Room("Ritual Chamber", "a large chamber within the cave where the cultists gather for their dark rituals. Symbols and runes cover the walls, glowing faintly in the dim light.", 
		        "The flickering torchlight illuminates the walls, revealing intricate symbols carved into the stone.", null, null, null);
		    cave[2][1] = new Room("Cultist Leader's Chamber", "the chamber of the cult's leader, adorned with sinister decorations and trophies of past sacrifices. A large stone altar dominates the room, stained with blood. The LEADER is sitting on the his throne.", 
		        "A throne of bones sits at the far end of the room, and the leader's presence seems to linger in the air. The LEADER, dressed in all black, stares at you through the darkness of his hood.", leader, null, null);
	    return cave;
	}
	
	public static void cave(String name, Player player) throws InterruptedException { //run cave game
		Room[][] cave = createCultistCave(new CultLeader("700-04-CL"));
		String basicDescription = "You find yourself at a small clearing in the woods, surrounded by tall trees. The moonlight filters through the canopy above.";
		boolean caveStay = false;
		boolean talkedWithLeader = false;
		
		while (!caveStay) {
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
			                        if (y > 0 && cave[x][y - 1] != null) {
			                            y--;
			                            System.out.println("You move left.");
			                        } else {
			                            System.out.println("You can't go left from here.");
			                        }
			                        break;
			                    case "RIGHT":
			                    case "EAST":
			                    case "E":
			                        if (y < cave[0].length - 1 && cave[x][y + 1] != null) {
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
			                        if (x > 0 && cave[x - 1][y] != null) {
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
			                        if (x < cave.length - 1 && cave[x + 1][y] != null) {
			                            x++;
			                            System.out.println("You move backward.");
			                        } else {
			                            System.out.println("You can't go backward from here.");
			                        }
			                        break;
			                    default:
			                        System.out.println("You can't go in that direction. Try using cardinal directions.");
			                    
			                }
			                
			                if (x==0 && y==1) {
			                	caveStay=true;
			                }
			            }
			            else {
			                System.out.println("Invalid. Please provide a direction.");
			            }
		                basicDescription = "You find yourself at " + cave[x][y].getBasicDesc(); //fix this grammar
			            break;
		            case "EXPLORE":
		                System.out.println("You look around...");
		                System.out.println("Where you're standing: " + cave[x][y].getBasicDesc());
	
		                // Check left direction
		                if (y > 0 && cave[x][y - 1] != null) {
		                    System.out.println("To the left: " + cave[x][y - 1].getBasicDesc());
		                } else {
		                    System.out.println("To the left: dense fog. You can't go that way...");
		                }
	
		                // Check right direction
		                if (y < cave[0].length - 1 && cave[x][y + 1] != null) {
		                    System.out.println("To the right: " + cave[x][y + 1].getBasicDesc());
		                } else {
		                    System.out.println("To the right: dense fog. You can't go that way...");
		                }
	
		                // Check forward direction
		                if (x > 0 && cave[x - 1][y] != null) {
		                    System.out.println("In front: " + cave[x - 1][y].getBasicDesc());
		                } else {
		                    System.out.println("In front: dense fog. You can't go that way...");
		                }
	
		                // Check backward direction
		                if (x < cave.length - 1 && cave[x + 1][y] != null) {
		                    System.out.println("Behind: " + cave[x + 1][y].getBasicDesc());
		                } else {
		                    System.out.println("Behind: dense fog. You can't go that way...");
		                }
		                
		                break;
		            case "TALK":
		            	if ( cave[x][y].hasNPC() ) {
		            		cave[x][y].getNpc().interact();
		            		talkedWithLeader = true;
		            	}
		            	else {
		            		System.out.println("There's no one at this spot to talk to. Are you going mad?");
		            	}
		            	break;
		            case "LOOK":
		            	System.out.println(cave[x][y].getDetailedDesc());
		            	break;
		            case "TAKE":
		            	p("There's nothing here to take.");
		                break;
		            case "HELP":
		            	displayHelp();
		            	break;
		            case "LEAVE":	
		            	if (talkedWithLeader)
		            		caveStay = true;
		            	else
		            		p("You can't leave the cave. You sense there's something here.");
		            	break;
		            default:
		                System.out.println("You can't really... do that.");
		        }
	        }
	        else
	        	System.out.println("You didn't even type anything.");
	        
	        System.out.println("\n");
	    } 
		
		p("You understand the Cult Leader was referencing to some location on the island. But where?",2);
		p("As the sun rose above, you needed to find out fast, so you can quickly head there on foot. Time is of the essence, you can feel it.");
		
		p("Now, choose:",2);
		
    	//choosing to go to the townhall for the next puzzle of the trap door
    	String answer = "";
    	boolean validAnswer = false;

    	while (!validAnswer) {
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
    	        sc.nextLine(); continue;
    	    }
    	
    	
    	if (answer.equals("TOWN HALL")) {
    		break;
    	}
    	else {
    		p("As you drive into the fading blue sky, a slight hope in your eyes, you are oblivious to the violence you have cause.");
    		p("You chose wrong.", 2);
    		p("And that night you wasted at the " + answer.toLowerCase() + " without understanding where you could've gottten the real answers and saved this island.", 1);
    		System.out.println("");
    		TextFont.printTitle("ENDING FOUR");//ENDING FOUR!!!
    		p("ENDING FOUR - Yet Another Wrong Turn.",2);
    		p("You made the wrong choice of where to investigate, and as you would've learned, time is of the essence.");
    		
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
    	
    	p("You make your way to the Town Hall. There you find a statue, which you do not recall being there the day before.");
    	p("It is of a man holding a sword in one hand, raised high, and a cane in the other.");
    	p("The inscription on the golden plate on the base of the statue reads:");
    	p("-----------");
    	p("Arthur Sinclair.");
    	p("'epxivi rixlivasvph' -  Julius CAESER");
    	
    	p("-----------");
    	p("You understand it is some kind of CIPHER. Maybe if you say the right words, something might happen (...or you could Google it. Up to you.)");
    	p("From prior knowledge, you know that a Caesar cipher works by shifting the letters by a set amount. In this case, it could be by 4 letters...");
    	p("So the first e, could become an a by going back 4 letters...");
    	p("...interesting.");
    	
    	String cipher = "";

    	while (!cipher.equals("ALTERE NETHERWORLD")) {
    	    System.out.println("Type in what you think the code says.");
    	    try {
    	    	cipher = sc.nextLine().toUpperCase();
    	        System.out.println("You say what you thought it was out loud...");
    	    }
    	    
    	    catch(Exception e) {
    	        System.out.println("That didn't work...");
    	        sc.nextLine(); 
    	    }
    	    
    	    if (cipher.equals("ALTERE NETHERWORLD")) {
    	    	p("It worked! You hear the sound of concrete and rock scraping the gorund as the statue slowly moves to reveal a trap door.");
    	    }
    	    else
    	    	p("You wait... and nothing happened. You might've gotten it wrong.");
    	}
    	
    	p("Things don't make sense... There's a cult in the mountains. There's a ghost taking teenagers. And there's a trap door in the middle of the island.");
    	p("The issue was way worse than you imagined.");
    	p("And so, feeling compelled to get to the bottom of this, you decide to enter into the trap door.",5);
    	p("As it closes on you, you realize there's no turning back.",3);
    	p("Not one bit.", 5);
    	
    	p("",2);
    	p("---------------------------------------------");
    	TextFont.printTitle("DAY THREE");
    	Basement.runBasementDayThree(name, player);
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
