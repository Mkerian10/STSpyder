package com.showtimedev.shared.misc;

import java.io.Serializable;

public final class PlayerConfig implements Serializable{
	
	private static final long serialVersionUID = 39571279812L;
	
	public PlayerConfig(WebItemPair[] items, boolean disableTeleports, int agilityLevel, int mageLevel){
		this.items = items;
		this.disableTeleports = disableTeleports;
		this.agilityLevel = agilityLevel;
		this.mageLevel = mageLevel;
	}
	
	private final WebItemPair[] items;
	
	private final boolean disableTeleports;
	
	private final int agilityLevel;
	
	private final int mageLevel;
	
	public WebItemPair[] getItems(){
		return items;
	}
	
	public boolean isDisableTeleports(){
		return disableTeleports;
	}
	
	public int getAgilityLevel(){
		return agilityLevel;
	}
	
	public int getMageLevel(){
		return mageLevel;
	}
}
