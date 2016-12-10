package com.toteltwent.custommenu;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.PluginMessageListener;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;
import com.toteltwent.custommenu.item.CMObjectItem;
import com.toteltwent.custommenu.item.CMServerItem;


public class CMListener implements Listener, PluginMessageListener {
	JavaPlugin plugin;

	public CMListener(JavaPlugin plugin){
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onInventoryClick(InventoryClickEvent event){
		Player player = (Player)event.getWhoClicked();
		if(player.getGameMode() != GameMode.CREATIVE) {
			event.setCancelled(true);
			
			if(event.getCurrentItem().getType() != Material.AIR){
				for(CMInventory inventory : CustomMenu.getInventories().values()){
					for(CMObjectItem objectItem : inventory.getItems().values()){
						if(event.getCurrentItem().getItemMeta().getDisplayName().equals(objectItem.getName())){
							objectItem.action(player, event.getClickedInventory(), event.getClick());
						}
					}
				}
			}
		}
	}
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event){
		Player player = event.getPlayer();
		if(player.getGameMode() != GameMode.CREATIVE) {
			event.setCancelled(true);
			
			if(event.getAction() != Action.PHYSICAL && event.getItem() != null && event.getItem().getType() != Material.AIR && event.getItem().getItemMeta().getDisplayName() != null)
			{
				for(CMInventory inventory : CustomMenu.getInventories().values()){
					for(CMObjectItem objectItem : inventory.getItems().values()){
						if(event.getItem().getItemMeta().getDisplayName().equals(objectItem.getName())){
							if(event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK){
								objectItem.action(player, player.getInventory(), ClickType.LEFT);
							}else if(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK){
								objectItem.action(player, player.getInventory(), ClickType.RIGHT);
							}
						}
					}
				}
			}
		}
	}

	@Override
	public void onPluginMessageReceived(String channel, Player player, byte[] message) {
		if (!channel.equals("BungeeCord")) {
			return;
	    }
		
	    ByteArrayDataInput in = ByteStreams.newDataInput(message);
	    String subchannel = in.readUTF();
	    if (subchannel.equals("PlayerCount")) {
	    	String server = in.readUTF();
	    	int playerCount = in.readInt();
	    	
	    	for(CMInventory inventory : CustomMenu.getInventories().values()){
		    	for(CMObjectItem objectItem : inventory.getItems().values()){
					if(objectItem instanceof CMServerItem){
						if(objectItem.getParam()[0].equals(server)){
							((CMServerItem)objectItem).setPlayerCount(playerCount);
						}
					}
				}
	    	}
	    }
	    
	}
}
