package npc.interactables;

import npc.DialogueNode;
import npc.DialogueTree;

public class Bartender extends GameObject{
	
	//npc has a dialogue tree from dialogues.txt
	private static DialogueNode tree;
	
	public Bartender(String ID) {
		tree = DialogueTree.createTree(ID);
	}
	
	public boolean interact() {
		return DialogueTree.runDialogue(tree, "The Bartender", false, false, true);
	}
}