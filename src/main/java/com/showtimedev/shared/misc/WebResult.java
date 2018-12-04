package com.showtimedev.shared.misc;

import java.io.Serializable;

public final class WebResult implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	WebResult(WebTile[] path, WebItemPair[] items, boolean teleportUsed, int gateways, boolean pathFound){
		this.path = path;
		this.items = items;
		this.teleportUsed = teleportUsed;
		this.gateways = gateways;
		this.pathFound = pathFound;
	}
	
	private WebTile[] path;
	
	private WebItemPair[] items;
	
	private boolean teleportUsed = false;
	
	private int gateways = 0;
	
	private boolean pathFound = true;
	
}
