package com.showtimedev.server_side.nav;

import lombok.Builder;

@Builder
public class PlayerConfig{

	private final int agilityLevel;
	
	private final int mageLevel;
	
	public int getAgilityLevel(){
		return agilityLevel;
	}
	
	public int getMageLevel(){
		return mageLevel;
	}
}
