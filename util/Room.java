package util;
import java.util.ArrayList;

import npc.DialogueTree;
import npc.interactables.GameObject;
import player.Item;
import player.Weapon;

public class Room {
	private String name, basicDesc, detailedDesc;
	private GameObject npc;
	private Item item;
	private Weapon weapon; //a room may have an item or weapon they can pick up

	public Room(String name, String basicDesc, String detailedDesc, GameObject npc, Item item,
			Weapon weapon) {
		super();
		this.name = name;
		this.basicDesc = basicDesc;
		this.detailedDesc = detailedDesc;
		this.npc = npc;
		this.item = item;
		this.weapon = weapon;
		
	}
	
	//these are all just getters and setters
	
	public void pickedUpWeapon() {
		weapon = null;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public GameObject getNpc() {
		return npc;
	}

	public void setNpc(GameObject npc) {
		this.npc = npc;
	}

	public String getBasicDesc() {
		return basicDesc;
	}

	public void setBasicDesc(String basicDesc) {
		this.basicDesc = basicDesc;
	}

	public String getDetailedDesc() {
		return detailedDesc;
	}

	public void setDetailedDesc(String detailedDesc) {
		this.detailedDesc = detailedDesc;
	}


	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public void pickedUpItem() {
		item = null;
	}
	
	public Weapon getWeapon() {
		return weapon;
	}

	public void setWeapon(Weapon weapon) {
		this.weapon = weapon;
	}

	public boolean hasNPC() {
		return npc!=null;
	}
	
	
}