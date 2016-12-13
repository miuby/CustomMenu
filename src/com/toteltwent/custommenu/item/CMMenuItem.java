package com.toteltwent.custommenu.item;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.Inventory;

import com.toteltwent.custommenu.CustomMenu;
import com.toteltwent.custommenu.utils.CMUtils;

public class CMMenuItem extends CMObjectItem {

	@Override
	public void internalDisplay(Player player, Inventory inventory) {
		inventory.setItem(position, CMUtils.mergeItemMeta(itemStack, name, description));
	}

	@Override
	public void action(Player player, Inventory inventory, ClickType clickType) {
		CustomMenu.getInventory(param[0]).open(param[1], player);
	}
}
