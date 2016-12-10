package com.toteltwent.custommenu.item;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.toteltwent.custommenu.CMInventory;

public abstract class CMObjectItem {
	protected ItemStack itemStack;
	protected int itemData = 0;
	protected int position = 0;
	protected String name = "";
	protected String description = "";
	protected String[] param;
	protected CMInventory inventory;
	
	public abstract void display(Inventory inventory);
	
	public abstract void action(Player player, Inventory inventory, ClickType clickType);
	
	public ItemStack getItemStack() {
		return itemStack;
	}
	
	public void setItemStack(ItemStack itemStack) {
		this.itemStack = itemStack;
	}
	
	public int getItemData() {
		return itemData;
	}
	
	public void setItemData(int itemData) {
		this.itemData = itemData;
	}
	
	public int getPosition() {
		return position;
	}
	
	public void setPosition(int position) {
		this.position = position;
	}
	
	public String getName() {
		return name;
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
	
	public String[] getParam() {
		return param;
	}
	
	public void setParam(String[] param) {
		this.param = param;
	}
	
	public CMInventory getInventory() {
		return inventory;
	}
	
	public void setInventory(CMInventory inventory) {
		this.inventory = inventory;
	}
}
