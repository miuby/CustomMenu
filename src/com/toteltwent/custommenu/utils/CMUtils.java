package com.toteltwent.custommenu.utils;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class CMUtils {
	
	public static ItemStack mergeItemMeta(final ItemStack itemStack, final String name, final String description){
		ItemMeta itemMeta = itemStack.getItemMeta();
		itemMeta.setDisplayName(name);
		
		if(!description.equalsIgnoreCase("")){
			List<String> lore = new ArrayList<String>();
			lore.add("");
			for(String line : description.split("\\|")){
				lore.add(line);
			}
			itemMeta.setLore(lore);
		}
		
		itemStack.setItemMeta(itemMeta);
		return itemStack;
	}
}
