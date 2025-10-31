package npc.interactables;

import npc.DialogueNode;
import npc.DialogueTree;

public class CultLeader extends GameObject{//npc has a dialogue tree from dialogues.txt
	private static DialogueNode tree;
	
	public CultLeader(String ID) {
		tree = DialogueTree.createTree(ID);
	}
	
	public boolean interact() {
		return DialogueTree.runDialogue(tree, "The Cult Leader", false, false, true);
	}
}