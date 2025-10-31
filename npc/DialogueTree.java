package npc;

import java.util.ArrayList;
import java.util.Scanner;

import npc.interactables.GameObject;

public class DialogueTree extends GameObject{
	private static DialogueNode root;
	private static Scanner sc = new Scanner(System.in);

	public DialogueTree() {
		DialogueNode root = new DialogueNode(null,null, null);
	}
	
	public static DialogueNode createTree(String key) { //key is the general code for the character (ex. 100-01-CS)
		
		DialogueNode output = createTreeHelper(key, "0");
		
		return output;
	}
	
	private static DialogueNode createTreeHelper(String key, String ID) {
		DialogueNode output = new DialogueNode(null,new ArrayList<DialogueNode>(), null);
		String txt = DialogueStore.getDialogue(key + "-" + ID); //stores the talk in the body of the node
		
		if (txt == null)
			return null; //base case and null check
		output.setTxt(txt);
		output.setID(key + "-" + ID);
		boolean initializeConnections = true;
		
		int count = 1; //checks thru all keys of child nodes
		while (initializeConnections) {
			DialogueNode node = createTreeHelper(key, ID + "." + count); //
			if (node == null) 
				initializeConnections = false; //tells it when to stop			
			else
				output.addResponse(node); //recursive call to continue building the tree
			count++;
		}
		
		//System.out.println(ID + ": " + output.toString());
		return output;
	}
	
	public boolean interact() {
		return false;
		
	}
	
	//simulates interaction with NPC
	// deleteDialogue: removes dialogue option once it's been gone through
	// repeatInteraction: prompts the dialogue again once one whole tree has been gone through
	// exitDialogue: gives option to exit the dialogue
	public static boolean runDialogue(DialogueNode tree, String charName, boolean deleteDialogue, boolean repeatInteraction, boolean exitDialogue) {
		if (tree.getOptions().size() == 0) {
			System.out.println();
			String ID = tree.getID();
			ID = ID.substring(0, 10) + "Quit";
			System.out.println(charName + ": " + DialogueStore.getDialogue(ID));
			return false;
		}
					
		boolean endDialogue = false;
		do {		
			endDialogue = runDialogueHelper(tree, charName, 0, deleteDialogue, exitDialogue);
		}
		while(!endDialogue && tree.getOptions().size() != 0 && repeatInteraction); 
		
		return true;
	}
	//recursive method to go through all the dialogues
	private static boolean runDialogueHelper(DialogueNode tree, String charName, int loopNumber, boolean deleteDialogue, boolean exitDialogue) {
        if (loopNumber % 2 == 1) {
            p("You: " + tree.getTxt());
            if (tree.getOptions().size() == 0) {
                return true;
            }
            
            runDialogueHelper(tree.getOptions().get(0), charName, loopNumber + 1, deleteDialogue, exitDialogue);
            return false;
        } else {
            p(charName + ": " + tree.getTxt());
            int count = 1;
            if (tree.getOptions().size() == 0) {
                return true;
            }
 

            int optionIndex = -1;
            while (optionIndex == -1) {
            	count = 1;
            	p("Options:");
                for (DialogueNode response : tree.getOptions()) {
                    p(count + ") " + response.getTxt());
                    count++;
                }
                if (loopNumber == 0 && exitDialogue)
                	p(count + ") Stop talking."); 
            	
                try {
                	int temp = sc.nextInt();
                    // Valid input range check
                    if (temp <= 0 || (loopNumber == 0 && temp > tree.getOptions().size() + 1) || (loopNumber > 0 && temp > tree.getOptions().size())) {
                        System.out.println("That's not an option... Type the number of the line you want to say.");
                        
                    } else if (temp == tree.getOptions().size() + 1 && loopNumber == 0) {
                        System.out.println("You leave " + charName + " be.");
                        return true;
                    } else {
                        optionIndex = temp - 1;
                        runDialogueHelper(tree.getOptions().get(optionIndex), charName, loopNumber + 1, deleteDialogue, exitDialogue);
                        if (deleteDialogue) {
                            tree.getOptions().remove(optionIndex);
                        }
                        return false;
                    }
                } catch (Exception e) {
                    System.out.println("That's not an option... Type the number of the line you want to say.");
                    sc.next();
                }
            }
        }
        
        return false;
    }
	
	public static void p(String txt){
		System.out.println(txt);
	}

}
	