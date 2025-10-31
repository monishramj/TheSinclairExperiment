package util;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import npc.interactables.CaptainShaughnessy;
import npc.interactables.FatherYannick;

public class IntroArrival {
    private static int x=1,y=1;
	private static Scanner sc = new Scanner(System.in);
	
	public static void p(String txt){  //just to make life easier for printing, instead of having to type everything out
		System.out.println(txt);
	}
	public static void p(String txt, int time) throws InterruptedException {
		TimeUnit.SECONDS.sleep(time);
		System.out.println(txt);
	}
	
	public static void startArrival(String name) throws InterruptedException {
		System.out.println("\n\n");
		TextFont.printTitle("prologue"); //change font
		p("You arrive at the coast near an island at the call of many of the locals who live there,");
		p("after asking many captains (who refused or didn't know what the island was), you find one that is willing to take you to Kinmouth, England. \n");
		p("And you set sail to Kinmouth... \n");
		p("                               \r\n"
				+ "                              \r\n"
				+ "               --\"\"-------   0      ^^\r\n"
				+ "     .___...../ /__| |__\\ \\_/H\\___      ^^\r\n"
				+ "      \\            < < <         /\r\n"
				+ "    #####^~^~^~^~^~^~^~^~^~^~^~^~^~^~^~~~~~~~~~~~~~ \r\n"
				+ "                                                \r\n");


	    //"You arrive at a small wooden dock, nearing the end of its days, overlooking a trail in a forest with enormous trees that all look just about ready to fall but miraculously stay standing.");
	    Room[][] dockRooms = new Room[2][2];
	    
	    CaptainShaughnessy captain = new CaptainShaughnessy(); //create npcs
	    FatherYannick priest = new FatherYannick("100-01-FY");

	    
	    //initialize the dock area
	    dockRooms[1][0] = new Room("The Captain", 
	    		"the captain, who is smoking a cigarette while humming a tune.",
	    		"This is Captain Shaughnessy, the person who brought you here. You should probably talk to him. ",
	    		captain, null, null);
	    dockRooms[0][1] = new Room("The Forest", 
	    		"an ominous forest, and a run down car in front of it. There seems to be a man waiting inside, the car window all the way down.",
	    		"In front of you is the infamous forest that encircles the island. A car is waiting for you here, an old black Honda Civic.\n"
	    		+ "It seems the driver, a fellow priest, is here to drive you through the forest trail into town.", //fix this
	    		priest, null, null); // No NPC in this room
	    dockRooms[1][1] = new Room("StartPos",
	    		"the dock itself, in all its misery.",
	    		"The dock has nothing special to it, per se. Two or three dingy boats to the side, a flag of the UK flying high, and the smell of damp wood. Somewhat revolting. ",
	    		null, null, null);
	    String basicDescription = "You find yourself at a small wooden dock, nearing the end of its days, overlooking a trail in a forest with enormous trees\nthat all look just about ready to fall but miraculously stay standing.";
	    boolean forestCutscene = false;
	    
	    
	    p("HINT: Use the command 'HELP' if you're stuck. Use your commands to navigate and solve the intricacies of The Sinclair Experiment.");

	    // Game loop
	    while (!forestCutscene) {
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
			                        if (y > 0 && dockRooms[x][y - 1] != null) {
			                            y--;
			                            System.out.println("You move left.");
			                        } else {
			                            System.out.println("You can't go left from here.");
			                        }
			                        break;
			                    case "RIGHT":
			                    case "EAST":
			                    case "E":
			                        if (y < dockRooms[0].length - 1 && dockRooms[x][y + 1] != null) {
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
			                        if (x > 0 && dockRooms[x - 1][y] != null) {
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
			                        if (x < dockRooms.length - 1 && dockRooms[x + 1][y] != null) {
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
		                basicDescription = "You find yourself at " + dockRooms[x][y].getBasicDesc(); //fix this grammar
			            break;
		            case "EXPLORE":
		                System.out.println("You look around...");
		                System.out.println("Where you're standing: " + dockRooms[x][y].getBasicDesc());
	
		                // Check left direction
		                if (y > 0 && dockRooms[x][y - 1] != null) {
		                    System.out.println("To the left: " + dockRooms[x][y - 1].getBasicDesc());
		                } else {
		                    System.out.println("To the left: There's dense fog. You can't go that way.");
		                }
	
		                // Check right direction
		                if (y < dockRooms[0].length - 1 && dockRooms[x][y + 1] != null) {
		                    System.out.println("To the right: " + dockRooms[x][y + 1].getBasicDesc());
		                } else {
		                    System.out.println("To the right: There's dense fog. You can't go that way.");
		                }
	
		                // Check forward direction
		                if (x > 0 && dockRooms[x - 1][y] != null) {
		                    System.out.println("In front: " + dockRooms[x - 1][y].getBasicDesc());
		                } else {
		                    System.out.println("In front: dense fog. You can't go that way.");
		                }
	
		                // Check backward direction
		                if (x < dockRooms.length - 1 && dockRooms[x + 1][y] != null) {
		                    System.out.println("Behind: " + dockRooms[x + 1][y].getBasicDesc());
		                } else {
		                    System.out.println("Behind: There's dense fog. You can't go that way.");
		                }
		                
		                break;
		            case "TALK":
		            	//promote talking with an npc
		            	if ( dockRooms[x][y].hasNPC() ) {
		            		boolean talked = dockRooms[x][y].getNpc().interact();
		            		
		            		if (talked && x == 1 && y == 0) 
		            			priest.updateCaptainDialogue();
		            		if (priest.isTalkedWithCaptain() && x == 0 && y ==1 && talked)
		            			forestCutscene = true;
		            	}
		            	else {
		            		System.out.println("There's no one at this spot to talk to. Are you going mad?");
		            	}
		            	break;
		            case "LOOK":
		            	System.out.println(dockRooms[x][y].getDetailedDesc());
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
	    
	    forestCutscene(name);

	}
	
	//this method plays the cutscene before moving to the island
	public static void forestCutscene(String name) throws InterruptedException {
		TimeUnit.SECONDS.sleep(2);
		int i = 0;
		while(i < 20) {
			System.out.println(".");
			TimeUnit.MILLISECONDS.sleep(50);
			i++;
		}
		i = 0;
		p("                       ____________________\r\n"
				+ "                     //|           |        \\\r\n"
				+ "                   //  |           |          \\\r\n"
				+ "      ___________//____|___________|__________()\\__________________\r\n"
				+ "    /__________________|_=_________|_=___________|_________________{}\r\n"
				+ "    [           ______ |           | .           | ==  ______      { }\r\n"
				+ "  __[__        /##  ##\\|           |             |    /##  ##\\    _{# }_\r\n"
				+ " {_____)______|##    ##|___________|_____________|___|##    ##|__(______}\r\n"
				+ "           .....##__##                            .....##__##        \\\r\n"
				+ "----------------------------------------------------------------------------");
		
		p("Father Yannick: So, Father " + name + "... it is unfortunate to meet you for the first time in these circumstances.", 5);
		p("You: I agree. Could you tell me a little more about the occurances? I read the files sent to me but-", 3);
		p("Father Yannick: Yes, yes. Many of the locals continously hear weeping in the night. Local police couldn't locate the origin of the sound.",3);
		p("You: And the missing people?", 5);
		p("Father Yannick: Yes. On espiecally loud, long nights, one young women of the town goes missing. It seems to be happening every two weeks.", 2);
		p("You: You mentioned on the phone that it was-", 5);
		p("Father Yannick: Like clockwork, yes. Genuinely terrifying. Of course, you are here, the esteemed last exorcist of the Vatican.", 2);
		p("You: Let me be the judge of unholiness, Father Yannick. Usually, there are logical explanations to most of these kinds of phenomena.",4);
		p("Father Yannick: But-",5);
		p("You: Hopefully it is. We will pray that it is logical.",1);
		p("Father Yannick: ...", 3);
		p("You: The fog?", 4);
		p("Father Yannick: Yes. The fog is a staple of the town. Around this time it tends to surround the island, but this year it's extremely dense.",1);
		p("Father Yannick: Passing through it might even suffocate you. Highly unusual.", 5);
		p("You: Okay. I will try my best to investigate the situation today.", 3);
		
		p("                                            /\\ \r\n"
				+ "/\\                                         /%%\\  /\\\r\n"
				+ "%%\\            ,                          /%%%%\\/%%\\\r\n"
				+ "%%%\\          ,~,                /\\       /%%%%/%%%%\\    ,   /\\\r\n"
				+ "%%%\\         ,~~~,   /\\         /%%\\  /\\ /%%%%%//\\%%\\/\\ ,~, /%%\\\r\n"
				+ "%%%%\\  /\\   ,~~~~~, /%%\\   /\\   /%%\\ /%%\\%/\\%/\\/%%\\%/%%\\~~~/%%%%\\\r\n"
				+ "%%%%\\ /%%\\ /\\~~~~~~/%%%%\\ /%%\\ /%%%%\\/%%\\/%%\\%%\\%%%/%(%%\\~~/%%%%\\\r\n"
				+ "%%%%%\\/%%\\/%%\\~/\\~~/%%%%\\/%%%%/%%%%%%\\%%/%%%%\\%%\\%%/)%%%\\~/%%%%%%\\\r\n"
				+ "%/\\%%/%%%%\\%%%/%%\\/%%%%%%\\%%/\\/%%/\\%%\\%%/\\%%%\\%%\\%%(%%%%%/%%%%%%%%\\ \r\n"
				+ "/%%\\/%%%%%%\\/\\/%%\\/%%%%%%\\%/%%\\%/%%\\%%\\/%%\\_______[_]________%%%%%\\\r\n"
				+ "%%%%/%%%%%%/%%\\%%/%%%%%%%%\\/%%\\%/%%\\%%/%%%%\\ _-       _-  _- \\%%%%%\\\r\n"
				+ "%%%/%%%%%%%/%%\\%%/%%/\\%%%%/%%%%\\%%%%\\/%%%%%%\\______-__________\\\"\"\",\"\r\n"
				+ "lc/%%%%%%%/%%%%\\/%%/%%\\%%%/%%%%\\%%%%\\/%%%%%%\\__===______====_]   ,~,  _-\r\n"
				+ "\"\"/%%%%%%/%%%%%%,%%/%%\\%%/%%%%%%\\%%%/%%%%%%%%\\_|_|______|  |_]  ,~~~,\r\n"
				+ " /%%%%%%%/%%%%%,~,/%%%%\\/%%%%%%%%\\%/%%%%%%%%%%\\_________|- |_] ,~~~~~,\r\n"
				+ " /%%%%%%/%%%%%,~~~,%%%%\\/%%%%%%%%\\%/%%%%%%%%%%\\___#__#__|__|_],~~~~~~~,\r\n"
				+ "/%%%%%#%/#%%%,~~~~~,%%%/%%%%%%%%%%/%%%%%%%%%%%%\\\"\"\"\\/\"\"\"/  \\\"  ,~~;~~,\r\n"
				+ "\"\"\"\"\"\"\"\\/\"\"\",~~~~~~~,\"\"/%%%%%%%%%/%%%%%%%%%%%%%%\\   _-            |\r\n"
				+ " -_          ,~~;~~,   \"\"\"\"\"\"\"\"\"\"/%%%%%%%%%%%%%%\\       ^^      ~\"\"\"~\r\n"
				+ "      ^^        |   ,    _-     /%%%%%%%%%%%%#%%#\\        _-\r\n"
				+ "            _-    ,`|`,         \"\"\"\"\"\"\"\"\"\"\"\"\"\"\\/\"\"   _-          _-\r\n"
				+ "  _-               \\ /           _-         _-   ~~  \r\n"
				+ "                  ~\"\"\"~",4);
		p("The tall, dark green trees swallow the sunlight, and leave you and Father Yannick in almost utter darkness on the trail.\n"
				+ "Through the corner of your eye, you sense a presence clinging to your side window. You brush it off.",4);
		p("You: Do you know anything about any folklore in the town, Father?", 6);
		p("Father Yannick: There is a children's story, I believe, of an old lady who would punish bad kids, or something to that liking.\n"
				+ "Frankly, I am not the best source for such - I keep to myself.",4);
		p("You: I see.", 5);
		p("The trees seem to be aware of your presence, watching you, judging you.\r\n"
				+ "And you question what there is to see.\r\n"
				+ "After all, there isn’t much about you other than your coveted exorcism skills.\r\n"
				+ "",2);
		p("Is ignorance truly bliss?\n",6);
		TimeUnit.SECONDS.sleep(5);
		Kinmouth.dayOneChurch(name); //this method starts up the island for day one
		
		
	}
	
    private static void displayHelp() {
    	System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("COMMANDS:");
        System.out.println("GO - Move to another area");
        System.out.println("EXPLORE - Scan the whole area");
        System.out.println("TALK - Talk with someone in the area");
        System.out.println("LOOK - Take a closer look in the specified area"); //todo
        System.out.println("HELP - Display this help message");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }
	

}