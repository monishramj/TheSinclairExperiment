package locations;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import player.Player;
import util.*;

public class FrenchRestaurant {
    private static Room[][] restaurant;
    static int x = 2;
    static int y = 1;
    private static Scanner sc = new Scanner(System.in);

    private static Room[][] createRestaurant() {
        Room[][] restaurant = new Room[3][3];
        // you find yourself at...
        restaurant[0][1] = new Room("The Wine Cellar", "the wine cellar. Rows of vintage wines line the walls, each bottle carefully stored.", 
            "The room is cool and dimly lit, with the faint scent of oak and grapes.", null, null, null);
        restaurant[0][2] = new Room("The Chef's Table", "the chef's table. A small, exclusive area where you can watch the chef at work.", 
            "The atmosphere is intimate, with a view of the bustling kitchen.", null, null, null);
        restaurant[1][2] = new Room("The Dessert Display", "the dessert display. An array of exquisite pastries and sweets are showcased here.", 
            "The glass case is filled with colorful macarons, delicate tarts, and rich chocolate cakes.", null, null, null);
        restaurant[1][0] = new Room("The Private Dining Room", "the private dining room. A quiet, elegant space for special occasions.", 
            "Fine china and crystal adorn the table, with a chandelier casting a warm glow.", null, null, null);
        restaurant[1][1] = new Room("The Main Dining Area", "the main dining area. The air is filled with the aroma of French cuisine and the sound of gentle chatter.", 
            "White tablecloths and candlelight create a sophisticated ambiance.", null, null, null);
        restaurant[2][1] = new Room("The Entrance", "the entrance. The décor is inviting, with a hint of Parisian charm.", 
            "A host podium stands at the front, though no one is there to greet you.", null, null, null);
        return restaurant;
    }

    public static void runRestaurantDayOne(String name, Player player) {
        restaurant = createRestaurant();
        String basicDescription = "You arrive at Le Jardin Gourmet, a charming French restaurant. The smell of freshly baked bread wafts through the air.";
        boolean restaurantStay = false;

        while (!restaurantStay) {
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
                                    if (y > 0 && restaurant[x][y - 1] != null) {
                                        y--;
                                        System.out.println("You move left.");
                                    } else {
                                        System.out.println("You can't go left from here.");
                                    }
                                    break;
                                case "RIGHT":
                                case "EAST":
                                case "E":
                                    if (y < restaurant[0].length - 1 && restaurant[x][y + 1] != null) {
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
                                    if (x > 0 && restaurant[x - 1][y] != null) {
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
                                    if (x < restaurant.length - 1 && restaurant[x + 1][y] != null) {
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
                        basicDescription = "You find yourself at " + restaurant[x][y].getBasicDesc(); // fix this grammar
                        break;
                    case "EXPLORE":
                        System.out.println("You look around...");
                        System.out.println("Where you're standing: " + restaurant[x][y].getBasicDesc());

                        // Check left direction
                        if (y > 0 && restaurant[x][y - 1] != null) {
                            System.out.println("To the left: " + restaurant[x][y - 1].getBasicDesc());
                        } else {
                            System.out.println("To the left: A wall. Do not try to go through the wall. It will hurt...");
                        }

                        // Check right direction
                        if (y < restaurant[0].length - 1 && restaurant[x][y + 1] != null) {
                            System.out.println("To the right: " + restaurant[x][y + 1].getBasicDesc());
                        } else {
                            System.out.println("To the right: A wall. Do not try to go through the wall. It will hurt...");
                        }

                        // Check forward direction
                        if (x > 0 && restaurant[x - 1][y] != null) {
                            System.out.println("In front: " + restaurant[x - 1][y].getBasicDesc());
                        } else {
                            System.out.println("In front: A wall. Do not try to go through the wall. It will hurt...");
                        }

                        // Check backward direction
                        if (x < restaurant.length - 1 && restaurant[x + 1][y] != null) {
                            System.out.println("Behind: " + restaurant[x + 1][y].getBasicDesc());
                        } else {
                            System.out.println("Behind: A wall. Do not try to go through the wall. It will hurt...");
                        }

                        break;
                    case "TALK":
                        if (restaurant[x][y].hasNPC()) {
                            restaurant[x][y].getNpc().interact();
                        } else {
                            System.out.println("There's no one at this spot to talk to. Are you going mad?");
                        }
                        break;
                    case "TAKE":
                    	p("There's nothing here to pick up.");
                    	break;
                    case "LOOK":
                        System.out.println(restaurant[x][y].getDetailedDesc());
                        break;
                    case "HELP":
                        displayHelp();
                        break;
                    case "LEAVE":
                        restaurantStay = true;
                        p("You decide to leave the restaurant.");
                        break;
                    default:
                        System.out.println("You can't really... do that.");
                }
            } else {
                System.out.println("You didn't even type anything.");
            }

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
        System.out.println("LEAVE - Leave the area");
        System.out.println("INVEN / INVENTORY - Check your inventory");
        System.out.println("HELP - Display this help message");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

    public static void p(String txt) {
        System.out.println(txt);
    }

    public static void p(String txt, int time) throws InterruptedException {
        TimeUnit.SECONDS.sleep(time);
        System.out.println(txt);
    }
}
