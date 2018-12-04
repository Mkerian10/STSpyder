package com.showtimedev.server_side.nav;

import com.showtimedev.shared.misc.PlayerConfig;

import java.io.Serializable;

public interface DynamicAccepter extends Serializable{
	
	boolean accept(PlayerConfig config);
	
	static DynamicAccepter join(DynamicAccepter a, DynamicAccepter b){
		return config -> a.accept(config) && b.accept(config);
	}
}
