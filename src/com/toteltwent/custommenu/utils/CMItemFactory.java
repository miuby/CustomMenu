package com.toteltwent.custommenu.utils;

import com.toteltwent.custommenu.item.CMBasicItem;
import com.toteltwent.custommenu.item.CMMenuItem;
import com.toteltwent.custommenu.item.CMObjectItem;
import com.toteltwent.custommenu.item.CMServerItem;
import com.toteltwent.custommenu.item.switchitem.CMSwitchItem;

public class CMItemFactory {

	public enum ItemType{
		MENU,
		CLICKABLESWITCH,
		SERVER,
		CUSTOM,
		NONE
	}
	
	public CMObjectItem buildItem(ItemType type){
		CMObjectItem objectItem = null;
		
		switch (type){
		case MENU:
			objectItem = new CMMenuItem();
			break;
		case CLICKABLESWITCH:
			objectItem = new CMSwitchItem();
			break;
		case SERVER:
			objectItem = new CMServerItem();
			break;
		default:
			objectItem = new CMBasicItem();
			break;
		}
		return objectItem;
	}
}
