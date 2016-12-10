package com.toteltwent.custommenu;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import com.toteltwent.custommenu.item.CMObjectItem;
import com.toteltwent.custommenu.utils.CMItemFactory;
import com.toteltwent.custommenu.utils.CMItemFactory.ItemType;

public class CustomMenu {
	private static JavaPlugin plugin;
	
	private static Map<String, CMInventory> inventories = new HashMap<String, CMInventory>();
	
	public CustomMenu(JavaPlugin plugin){
		CustomMenu.plugin = plugin;
		
		CMListener listener = new CMListener(plugin);
		plugin.getServer().getPluginManager().registerEvents(listener, plugin);
		
		CustomMenu.plugin.getServer().getMessenger().registerOutgoingPluginChannel(CustomMenu.plugin, "BungeeCord");
		CustomMenu.plugin.getServer().getMessenger().registerIncomingPluginChannel(CustomMenu.plugin, "BungeeCord", listener);
		
		load();
	}
	
	public static CMInventory getInventory(String name){
		return inventories.get(name);
	}
	
	public static Map<String, CMInventory> getInventories(){
		return inventories;
	}
	
	public static JavaPlugin getPlugin(){
		return plugin;
	}
	
	private void load(){
		CMItemFactory itemFactory = new CMItemFactory();
		
		File file = new File(plugin.getDataFolder(), "menus.yml");
		if(file.exists()){
			FileConfiguration menuFile = YamlConfiguration.loadConfiguration(file);
			for(String inventoryName : menuFile.getKeys(false)){
				CMInventory inventory = new CMInventory(inventoryName);
				
				for(String item : menuFile.getConfigurationSection(inventoryName).getKeys(false)){
					String itemPath = inventoryName + "." + item;
					if(item.equalsIgnoreCase("numLines")){
						inventory.setNumLines(menuFile.getInt(itemPath));
					}else{
						CMObjectItem objectItem;
						if(menuFile.contains(itemPath + ".action")){
							try{
								objectItem = itemFactory.buildItem(ItemType.valueOf(menuFile.getString(itemPath + ".action")));
							}catch(IllegalArgumentException e){
								objectItem = itemFactory.buildItem(ItemType.NONE);
							}
						}else{
							objectItem = itemFactory.buildItem(ItemType.NONE);
						}
						
						if(menuFile.contains(itemPath + ".material")){
							if(menuFile.contains(itemPath + ".itemData")){
								objectItem.setItemStack(new ItemStack(Material.getMaterial(menuFile.getString(itemPath + ".material")), 1, (short)menuFile.getInt(itemPath + ".itemData")));
							}else{
								objectItem.setItemStack(new ItemStack(Material.getMaterial(menuFile.getString(itemPath + ".material"))));
							}
						}else{
							objectItem.setItemStack(new ItemStack(Material.AIR));
						}
												
						if(menuFile.contains(itemPath + ".position")){
							objectItem.setPosition(menuFile.getInt(itemPath + ".position"));
						}
						
						if(menuFile.contains(itemPath + ".name")){
							objectItem.setName(menuFile.getString(itemPath + ".name"));
						}
						
						if(menuFile.contains(itemPath + ".description")){
							objectItem.setDescription(menuFile.getString(itemPath + ".description"));
						}
						
						if(menuFile.contains(itemPath + ".param")){
							objectItem.setParam(menuFile.getString(itemPath + ".param").split(","));
						}
						
						objectItem.setInventory(inventory);
						inventory.addItem(objectItem);
					}
				}
				inventories.put(inventoryName, inventory);
			}
		}else{
			try {
				plugin.getDataFolder().mkdir();
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
			Bukkit.getLogger().info("menus.yml is empty !");
		}
	}
}
