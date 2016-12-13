package com.toteltwent.custommenu;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

import com.toteltwent.custommenu.item.CMObjectItem;

public class CMInventory {
	private String name = "";
	private int numLines = 4;
	private Map<String, CMObjectItem> items = new HashMap<String, CMObjectItem>();
	
	public CMInventory(String name){
		this.name = name;
	}
	
	public void open(String title, Player player){
		Inventory menuInventory = Bukkit.createInventory((InventoryHolder)null, (getNumLines()*9), title);
		for(CMObjectItem item : getItems().values()){
			item.display(player, menuInventory);
		}
		player.openInventory(menuInventory);
		player.playSound(player.getLocation(), Sound.ORB_PICKUP, 1, 1);
	}
	
	public void addItem(CMObjectItem item){
		items.put(item.getName(), item);
	}
	
	public CMObjectItem getItem(String name){
		return items.get(name);
	}
	
	public Map<String, CMObjectItem> getItems(){
		return items;
	}

	public String getName() {
		return name;
	}

	public int getNumLines() {
		return numLines;
	}

	public void setNumLines(int numLines) {
		this.numLines = numLines;
	}
}
