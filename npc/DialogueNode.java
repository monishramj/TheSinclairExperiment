package npc;

import java.util.ArrayList;

public class DialogueNode {
	private String txt, ID;
	private ArrayList<DialogueNode> options;
	//can add an action to this...
	
	
	public DialogueNode(String txt, ArrayList<DialogueNode> options, String ID) {
		this.txt = txt;
		this.ID = ID;
		this.options = options;
	}
	

	public String getID() {
		return ID;
	}


	public void setID(String iD) {
		ID = iD;
	}


	public ArrayList<DialogueNode> getOptions() {
		return options;
	}

	public void setOptions(ArrayList<DialogueNode> options) {
		this.options = options;
	}


	public String getTxt() {
		return txt;
	}

	@Override
	public String toString() {
		return "DialogueNode [txt=" + txt + ", options=" + options + "]";
	}


	public void setTxt(String txt) {
		this.txt = txt;
	}

	public void setResponse(ArrayList<DialogueNode> options) {
		this.options = options;
	}

	public void addResponse(DialogueNode node) {
		options.add(node);	
	}
	
}