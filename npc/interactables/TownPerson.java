package npc.interactables;

import npc.DialogueNode;
import npc.DialogueTree;

public class TownPerson extends GameObject{//npc has a dialogue tree from dialogues.txt
	private static DialogueNode tree;
	
	public TownPerson(String ID) {
		tree = DialogueTree.createTree(ID);
	}
	
	public boolean interact() {
		return DialogueTree.runDialogue(tree, "Town Person", false, false, true);
	}
}