package npc.interactables;

import npc.DialogueNode;
import npc.DialogueTree;

public class Sheriff extends GameObject{//npc has a dialogue tree from dialogues.txt
	private static DialogueNode tree;
	
	public Sheriff(String ID) {
		tree = DialogueTree.createTree(ID);
	}
	
	public boolean interact() {
		return DialogueTree.runDialogue(tree, "Sheriff Stone", false, false, true);
	}
}