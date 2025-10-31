package npc.interactables;

import java.util.HashMap;
import java.util.LinkedHashMap;

import npc.DialogueNode;
import npc.DialogueTree;

public class CaptainShaughnessy extends GameObject{
	//npc has a dialogue tree from dialogues.txt
	private static DialogueNode tree;
	
	public CaptainShaughnessy() {
		tree = DialogueTree.createTree("100-01-CS");
	}
	
	public boolean interact() {
		DialogueTree.runDialogue(tree, "Captain Shaughnessy", true, true, true);
		if (tree.getOptions().size() == 0)
			return true; //to test functionality of interaction and progression
		else
			return false;
	}

}