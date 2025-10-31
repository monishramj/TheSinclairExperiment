package locations;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import npc.interactables.*;
import player.*;
import util.Room;
import util.TextFont;

public class Library {
	private static Room[][] library;
	static int x = 1;
	static int y = 0;
	private static Scanner sc = new Scanner(System.in);
	
	private static Room[][] createLibrary(GameObject a) {
        Room[][] library = new Room[2][3];
        
        //you find yourself at...
        library[1][0] = new Room("Library Entrance", "the enterance to the library. A sense of calmness and comfort washes over you", 
            "The warm lighting and the smell of fresh books is inviting.", null, null, null);
        library[1][1] = new Room("Librarian Desk", "the librarian's desk. No one seems to be there now.",
            "A few books are piled in the corner, and a few more seem to have been meticulously placed in a cart.", null, null, null);
        library[1][2] = new Room("Microfilm Reader", "the microfilm reader, used to read old newspapers. It's amazing that they have one of these ancient machines here.", 
            "There seems to be a spot to load a microfilm reel.",a , null, null);
        library[0][2] = new Room("Bookshelves", "the bookshelves. You see a wide array of books here.", 
            "Some notable titles include Zork by Infocom, Colossal Cave Adventure by William Crowther, and Faith: The Unholy Trinity by John Ward.", null, null, null);
        library[0][1] = new Room("Study Area", "the study area. A few students seem to be fervorously scribbling on their notebooks.", 
            "Most of the students are either notetaking or playing some sort of game with their phones.", null, null, null);
        return library;
    }

	public static void runlibraryDayOne(String name, Player player) throws InterruptedException {
		
		library = createLibrary(new MicrofilmReader("600-02-MR"));
		String basicDescription = "You arrive at the local library. It is a quaint location, mostly silent except the occasional giggle or book drop.";
		boolean libraryStay = false;
		p("You're simply looking for a way to find what happened on May 19th (the date the bartender told you), at the library. You don't have time to talk.");
		p("!!! You may use the command USE to interact with an object in the area.");
		
		while (!libraryStay) {
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
			                        if (y > 0 && library[x][y - 1] != null) {
			                            y--;
			                            System.out.println("You move left.");
			                        } else {
			                            System.out.println("You can't go left from here.");
			                        }
			                        break;
			                    case "RIGHT":
			                    case "EAST":
			                    case "E":
			                        if (y < library[0].length - 1 && library[x][y + 1] != null) {
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
			                        if (x > 0 && library[x - 1][y] != null) {
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
			                        if (x < library.length - 1 && library[x + 1][y] != null) {
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
		                basicDescription = "You find yourself at " + library[x][y].getBasicDesc(); //fix this grammar
			            break;
		            case "EXPLORE":
		                System.out.println("You look around...");
		                System.out.println("Where you're standing: " + library[x][y].getBasicDesc());
	
		                // Check left direction
		                if (y > 0 && library[x][y - 1] != null) {
		                    System.out.println("To the left: " + library[x][y - 1].getBasicDesc());
		                } else {
		                    System.out.println("To the left: A wall. Do not try to go through the wall. It will hurt...");
		                }
	
		                // Check right direction
		                if (y < library[0].length - 1 && library[x][y + 1] != null) {
		                    System.out.println("To the right: " + library[x][y + 1].getBasicDesc());
		                } else {
		                    System.out.println("To the right: A wall. Do not try to go through the wall. It will hurt...");
		                }
	
		                // Check forward direction
		                if (x > 0 && library[x - 1][y] != null) {
		                    System.out.println("In front: " + library[x - 1][y].getBasicDesc());
		                } else {
		                    System.out.println("In front: A wall. Do not try to go through the wall. It will hurt...");
		                }
	
		                // Check backward direction
		                if (x < library.length - 1 && library[x + 1][y] != null) {
		                    System.out.println("Behind: " + library[x + 1][y].getBasicDesc());
		                } else {
		                    System.out.println("Behind: A wall. Do not try to go through the wall. It will hurt...");
		                }
		                
		                break;
		            case "TALK":
		            	p("There's no one here to go to. Are you going mad?");
		            case "USE":
		            	if ( library[x][y].hasNPC() ) {
		            		library[x][y].getNpc().interact();
		            		libraryStay = true;
		            	}
		            	else {
		            		System.out.println("There's nothing here to interact with.");
		            	}
		            	break;
		            case "LOOK":
		            	System.out.println(library[x][y].getDetailedDesc());
		            	break;
		            case "TAKE":
		            	p("There's nothing here to take.");
		            	break;
		            case "LEAVE":
		            	p("You can't leave just yet. You have to figure out what the bartender was referencing.");
		            	break;
		            case "INVEN":
		            case "INVENTORY":
		            	p(player.displayRegularInventory());
		            	player.searchRegInventory();
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
	    
		p("From reading further in the article, you find the witnesses of the dead body were all the victims who have gone missing.",3);
		p("Rosa Stone, the first missing person... the name Stone sounds familiar to you. Stern Sheriff Stone's name.",3);
		p("Maybe his authority is making the people reserved about the situation.");
		
		p("You feel a sense of danger, the creeping eerieness of an aura behind you beginning to grow. You're getting closer to the truth.",3);
		p("Information in hand, you leave the library to find that the island has gone completely dark. And there is a figure standing in front of you.",3);
		
		p("You can hear whispers from it. Speaking in tongues. Language of hostility.",3);
		
		
		
		
		boolean firstBattleWin = false;
		
		while(!firstBattleWin) {
			p("               ...\r\n"
					+ "             ;::::;\r\n"
					+ "           ;::::; :;\r\n"
					+ "         ;:::::'   :;\r\n"
					+ "        ;:::::;     ;.\r\n"
					+ "       ,:::::' .   . ;           DEE\\\r\n"
					+ "       ::::::;       ;          DOOOO\\\r\n"
					+ "       ;:::::;       ;         DOOOOOOO\r\n"
					+ "      ,;::::::;     ;'         / OOOOOOO\r\n"
					+ "    ;:::::::::`. ,,,;.        /  / DOOOOOO\r\n"
					+ "  .';:::::::::::::::::;,     /  /     DOOOO\r\n"
					+ " ,::::::;::::::;;;;::::;,   /  /        DOOO\r\n"
					+ ";`::::::`'::::::;;;::::: ,#/  /          DOOO\r\n"
					+ ":`:::::::`;::::::;;::: ;::#  /            DOOO\r\n"
					+ "::`:::::::`;:::::::: ;::::# /              DOO\r\n"
					+ "`:`:::::::`;:::::: ;::::::#/               DOO\r\n"
					+ " :::`:::::::`;; ;:::::::::##                OO\r\n"
					+ " ::::`:::::::`;::::::::;:::#                OO\r\n"
					+ " `:::::`::::::::::::;'`:;::#                V\r\n"
					+ "  `:::::`::::::::;' /  / `:#\r\n"
					+ "   ::::::`:::::;'  /  /   `#",1);
			
			p("Wandering Spirit: i seeeee you thereeee, " + name.toLowerCase() + ". i wont let you gooooo...",1);
			p("------------------------------------------------------------------------------------",3);
			TextFont.printTitle("BATTLE");
			Enemy firstGhost = new Enemy("Wandering Spirit", 30, 600, 600, .4, new SpecialAttack(30, 100, "Whispers Of Doom", 
					"The ghost starts spewing demonic rhymes that undermine holiness."));
			firstBattleWin = Battle.play(player, firstGhost);
			 
			if (!firstBattleWin) {
				TextFont.printTitle("ENDING TWO");
				p("ENDING TWO - The Ringing Bells of Soft Voice.",2);
				p("In your battle in facing off the being, you die valiantly. It comes up to your ears, and speaks of words that never shall be heard. You convulse and fall to the ground.");
				p("Officials find your body at the front entrance of the library. Mangled.");
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
		
		p("You: In the name of the Father, the Son, and the Holy Spirit, speak to me, spirit.",3);
		p("Wandering Spirit: i can still hear their screams.",4);
		p("You: What is wrong with this land? You do not seem like the terror leeching from it.",4);
		p("Wandering Spirit: she is. up on the mountain. the men are all there.",4);
		p("You press The Esteemed Cross on the ghoul's body. It glows bright red as it singes the skin. The spirit screams.",4);
		p("Wandering Spirit: ...it's too late. the mountain is alive tonight... " + name.toLowerCase() +".",4);
		p("You: ...",4);
		p("Wandering Spirit: what a funny name " + name.toLowerCase() + " is...",4);
		
		int i = 0;
		while(i < 40) {
			System.out.println(".");
			TimeUnit.MILLISECONDS.sleep(50);
			i++;
		}
		p("You realize that you must go to the mountain. There's no time to you to go back to the hotel.");
		p(".......... you don't know what's at stake here.", 2);
		TimeUnit.SECONDS.sleep(2);
		Forest.dayTwoMountain(name, player);
		
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
