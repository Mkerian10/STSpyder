package com.showtimedev.shared.misc;

public final class WebResultBuilder{
	
	public static WebResultBuilder builder(){
		return new WebResultBuilder();
	}
	
	public WebResultBuilder setPath(WebTile[] path){
		this.path = path;
		return this;
	}
	
	public WebResultBuilder setItems(WebItemPair[] items){
		this.items = items;
		return this;
	}
	
	public WebResultBuilder setTeleportUsed(boolean teleportUsed){
		this.teleportUsed = teleportUsed;
		return this;
	}
	
	public WebResultBuilder setPathFound(boolean pathFound){
		this.pathFound = pathFound;
		return this;
	}
	
	public WebResultBuilder setGateways(int gateways){
		this.gateways = gateways;
		return this;
	}
	
	public WebResult build(){
		return new WebResult(path, items, teleportUsed, gateways, pathFound);
	}
	
	private WebTile[] path = null;
	
	private WebItemPair[] items = null;
	
	private boolean teleportUsed = false;
	
	private boolean pathFound = false;
	
	private int gateways = 0;
}
