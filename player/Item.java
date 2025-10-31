package player;

import npc.interactables.GameObject;

public class Item extends GameObject{

	
	public Item(String name, String crudeName, String description) {
		super();
		this.name = name;
		this.crudeName = crudeName;
		this.description = description;
	}
	
	//name is their name of course, crude name is what the player refers the item by in commands, and description
	private String name, crudeName, description;

	public String getName() {
		return name;
	}

	public String getCrudeName() {
		return crudeName;
	}

	public void setCrudeName(String crudeName) {
		this.crudeName = crudeName;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "You have the " + name + ". " + description;
	}
	
	
}