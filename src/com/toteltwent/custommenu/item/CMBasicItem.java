package com.toteltwent.custommenu.item;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.Inventory;

import com.toteltwent.custommenu.utils.CMUtils;

public class CMBasicItem extends CMObjectItem {

	@Override
	public void display(Inventory inventory) {
		inventory.setItem(position, CMUtils.mergeItemMeta(itemStack, name, description));
	}

	@Override
	public void action(Player player, Inventory inventory, ClickType clickType) {
		
	}
}