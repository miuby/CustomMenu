package com.toteltwent.custommenu.item.switchitem;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class CMSwitchEvent extends Event {
	
	private static final HandlerList handlers = new HandlerList();
	private Player player = null;
	private String name = "";
	private int num = 0;
	
	public CMSwitchEvent(Player player, String name, int num){
		this.player = player;
		this.name = name;
		this.num = num;
	}

	@Override
	public HandlerList getHandlers() {
		return handlers;
	}
	
	public static HandlerList getHandlerList() {
        return handlers;
    }
	
	public Player getPlayer(){
		return player;
	}

	public String getName(){
		return name;
	}
	
	public int getNum(){
		return num;
	}
}
