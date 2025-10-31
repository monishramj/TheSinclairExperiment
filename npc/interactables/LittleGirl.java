package npc.interactables;

import npc.DialogueNode;
import npc.DialogueTree;

public class LittleGirl extends GameObject{//npc has a dialogue tree from dialogues.txt
	private static DialogueNode tree;
	
	public LittleGirl(String ID) {
		tree = DialogueTree.createTree(ID);
	}
	
	public boolean interact() {
		return DialogueTree.runDialogue(tree, "Some Little Girl", false, false, true);
	}
}