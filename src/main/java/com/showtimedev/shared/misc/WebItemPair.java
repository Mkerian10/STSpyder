package com.showtimedev.shared.misc;

import java.io.Serializable;

public final class WebItemPair implements Serializable{
	
	public WebItemPair(WebItem item, int amount){
		this.item = item;
		this.amount = amount;
	}
	
	public WebItem getItem(){
		return item;
	}
	
	public int getAmount(){
		return amount;
	}
	
	private final WebItem item;
	
	private final int amount;
}
