package npc.interactables;

import npc.DialogueNode;
import npc.DialogueTree;

public class Receptionist extends GameObject{//npc has a dialogue tree from dialogues.txt
	private static DialogueNode tree;
	
	public Receptionist(String ID) {
		tree = DialogueTree.createTree(ID);
	}
	
	public boolean interact() {
		return DialogueTree.runDialogue(tree, "Receptionist Lopez", false, true, true);
	}
}