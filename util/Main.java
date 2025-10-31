package util;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Main {
	
	private static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) throws InterruptedException {
		TimeUnit.SECONDS.sleep(1);
		TextFont.printTitle("The");
		TimeUnit.SECONDS.sleep(1);
		TextFont.printTitle("sinclair");
		TimeUnit.SECONDS.sleep(1);
		TextFont.printTitle("experiment");
		TimeUnit.SECONDS.sleep(1);
		System.out.println("Created by Monish R.J. and Vighnesh S. \n \n \n");
		TimeUnit.SECONDS.sleep(2);
		System.out.print("\nPlay? Y/N \n> ");
		
		String play = sc.next().toUpperCase();
		
		if (play.equals("Y")) {
			//start the game
			System.out.println("Enter your last name.\n> ");
			String name = sc.next();
			name = name.substring(0,1).toUpperCase() + name.substring(1).toLowerCase();
			System.out.println("... you are brave for enbarking on this journey, Father " + name + ". Good luck to you.");
			TimeUnit.SECONDS.sleep(1);
			System.out.println("Loading... \n");
			int i = 0;
			while(i < 40) { //this is to add an animation like the program is loading
				System.out.println(".");
				TimeUnit.MILLISECONDS.sleep(50);
				i++;
			}
			IntroArrival.startArrival(name);
		}
		else if (play.equals("N")) {
			System.out.println("Screw you!"); //prematurely end the game before it has begin
			System.exit(0);
		}
		else {
			System.out.println("You had two options. That was neither of them... Goodbye!");
			System.exit(0); //end the program bcuz the player is so dumb they could've choose
		}
		

	}

	
}