package com.toteltwent.custommenu;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import com.toteltwent.custommenu.item.CMObjectItem;

public class CMDisplayEvent extends Event {

	private static final HandlerList handlers = new HandlerList();
	private Player player = null;
	private String eventName = "";
	private CMObjectItem item = null;
	
	public CMDisplayEvent(Player player, String eventName, CMObjectItem item) {
		this.player = player;
		this.eventName = eventName;
		this.item = item;
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

	public String getEventName() {
		return eventName;
	}

	public CMObjectItem getItem() {
		return item;
	}

}
