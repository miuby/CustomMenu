package com.toteltwent.custommenu.item.switchitem;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.PluginManager;

import com.toteltwent.custommenu.item.CMObjectItem;
import com.toteltwent.custommenu.utils.CMUtils;

public class CMSwitchItem extends CMObjectItem {

	private List<CMSwitchItem> switchItems = new ArrayList<CMSwitchItem>();
	private int numSwitch = 0;
	
	@Override
	public void internalDisplay(Player player, Inventory inventory) {
		createList();
		
		if(numSwitch == Integer.valueOf(param[1])){
			inventory.setItem(position, CMUtils.mergeItemMeta(itemStack, name, description));
		}
	}

	@Override
	public void action(Player player, Inventory inventory, ClickType clickType) {
		if(numSwitch >= switchItems.size() -1){
			numSwitch = 0;
		}else{
			numSwitch++;
		}
		
		for(CMSwitchItem switchItem : switchItems){
			switchItem.setNumSwitch(numSwitch);
			switchItem.display(player, inventory);
		}
		
		CMSwitchEvent event = new CMSwitchEvent(player, param[0], numSwitch);
		PluginManager pm = Bukkit.getServer().getPluginManager();
		pm.callEvent(event);
	}
	
	private void setNumSwitch(int numSwitch){
		this.numSwitch = numSwitch;
	}
	
	private void createList(){
		if(switchItems.isEmpty()){
			for(CMObjectItem objectItem : inventory.getItems().values()){
				if(objectItem instanceof CMSwitchItem){
					if(param[0].equals(objectItem.getParam()[0])){
						switchItems.add((CMSwitchItem) objectItem);
					}
				}
			}
		}
	}

}
