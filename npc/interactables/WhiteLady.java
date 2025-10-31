package npc.interactables;

import npc.DialogueNode;
import npc.DialogueTree;

public class WhiteLady extends GameObject{
	private static DialogueNode tree;
	
	public WhiteLady(String ID) {
		tree = DialogueTree.createTree(ID);
	}
	
	public boolean interact() {
		return DialogueTree.runDialogue(tree, "The White Lady", true, true, false);
	}
}