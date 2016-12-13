package com.toteltwent.custommenu.item;

import net.md_5.bungee.api.ChatColor;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.Inventory;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import com.toteltwent.custommenu.CustomMenu;
import com.toteltwent.custommenu.utils.CMUtils;

public class CMServerItem extends CMObjectItem {
	
	private Inventory inventoryTemp;
	private int playerCount;
	
	@Override
	public void internalDisplay(Player player, Inventory inventory) {
		ByteArrayDataOutput out = ByteStreams.newDataOutput();
		out.writeUTF("PlayerCount");
		out.writeUTF(param[0]);
		Bukkit.getServer().sendPluginMessage(CustomMenu.getPlugin(), "BungeeCord", out.toByteArray());
		inventoryTemp = inventory;
		
		if(!description.equals("")){
			inventory.setItem(position, CMUtils.mergeItemMeta(itemStack, name, description + "|" + ChatColor.GOLD + playerCount + " players"));
		}else{
			inventory.setItem(position, CMUtils.mergeItemMeta(itemStack, name, ChatColor.GOLD + "" + playerCount + " players"));
		}
	}

	@Override
	public void action(Player player, Inventory inventory, ClickType clickType) {
		ByteArrayDataOutput out = ByteStreams.newDataOutput();
		out.writeUTF("Connect");
		out.writeUTF(param[0]);
		player.sendPluginMessage(CustomMenu.getPlugin(), "BungeeCord", out.toByteArray());
	}

	public void setPlayerCount(int playerCount){
		this.playerCount = playerCount;
		if(inventoryTemp != null){
			if(!description.equals("")){
				inventoryTemp.setItem(position, CMUtils.mergeItemMeta(itemStack, name, description + "|" + ChatColor.GOLD + playerCount + " players"));
			}else{
				inventoryTemp.setItem(position, CMUtils.mergeItemMeta(itemStack, name, ChatColor.GOLD + "" + playerCount + " players"));
			}
		}
	}
}
