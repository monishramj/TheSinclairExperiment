package npc.interactables;

import npc.DialogueNode;
import npc.DialogueTree;

public class MicrofilmReader extends GameObject{//npc has a dialogue tree from dialogues.txt
	private static DialogueNode tree;
	
	public MicrofilmReader(String ID) {
		tree = DialogueTree.createTree(ID);
	}
	
	public boolean interact() {
		return DialogueTree.runDialogue(tree, "Microfilm Reader", false, false, false);
	}
}
