package com.showtimedev.server_side.nav;

import com.showtimedev.shared.misc.PlayerConfig;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode
public abstract class AbstractDynamic implements Serializable{
	
	public AbstractDynamic(DynamicAccepter accepter){
		this.accepter = accepter;
	}
	
	public AbstractDynamic(){
		this.accepter = null;
	}
	
	protected final DynamicAccepter accepter;
	
	public boolean valid(PlayerConfig config){
		return accepter == null || accepter.accept(config);
	}
}
