package npc.interactables;

import java.util.HashMap;
import java.util.LinkedHashMap;

import npc.DialogueNode;
import npc.DialogueTree;

public class FatherYannick extends GameObject {//npc has a dialogue tree from dialogues.txt
	
	private static DialogueNode tree;
	private boolean talkedWithCaptain;
	
	public FatherYannick(String ID) {
		tree = DialogueTree.createTree(ID);
		talkedWithCaptain = false;
	}
	
	public boolean interact() {
		return DialogueTree.runDialogue(tree, "Father Yannick", false, false, false);

	}
	
	public boolean interactInChurch() { //interaction different because you need to finish talk bfore leaving
		DialogueTree.runDialogue(tree, "Father Yannick", true, true, false);
		if (tree.getOptions().size() == 0)
			return true;
		else
			return false;

	}
	
	public void updateCaptainDialogue() {
		tree = null;
		tree = DialogueTree.createTree("100-02-FY");
		talkedWithCaptain = true;
	}

	public boolean isTalkedWithCaptain() {
		return talkedWithCaptain;
	}

	public void setTalkedWithCaptain(boolean talkedWithCaptain) {
		this.talkedWithCaptain = talkedWithCaptain;
	}
	
	

}
