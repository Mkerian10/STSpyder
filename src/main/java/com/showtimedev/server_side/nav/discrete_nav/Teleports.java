package com.showtimedev.server_side.nav.discrete_nav;

import com.showtimedev.server_side.nav.DynamicAccepter;
import com.showtimedev.shared.misc.PlayerConfig;

import java.util.Arrays;

public enum Teleports{
	EDGEVILLE_GLORY(new DiscreteNode(3093, 3492, 0), (DynamicAccepter) config -> {
		final int[] gloryIds = {11978, 11976, 1712, 1710, 1708, 1706};
		return anyMatch(config, gloryIds);
	});
	
	Teleports(DiscreteNode dest, DynamicAccepter accepter){
		this.dest = dest;
		this.accepter = accepter;
	}
	
	private final DiscreteNode dest;
	
	private final DynamicAccepter accepter;
	
	public DiscreteNode getDest(){
		return dest;
	}
	
	public boolean accepts(PlayerConfig config){
		return accepter.accept(config);
	}
	
	private static boolean anyMatch(PlayerConfig config, int... ints){
		//don't worry about how this works, just trust the process
		return Arrays.stream(config.getItems())
				.map(webItemPair -> webItemPair.getItem().getIds())
				.flatMapToInt(Arrays::stream)
				.anyMatch(value -> Arrays.stream(ints).anyMatch(v -> value == v));
	}
	
}
