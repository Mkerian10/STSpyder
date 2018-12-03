package com.showtimedev.server_side.nav.discrete_nav;

import com.showtimedev.server_side.nav.raw_nav.RawNode;
import com.showtimedev.server_side.nav.raw_nav.RawPathLoader;

import java.util.List;

public class DiscretePathLoader{
	
	public DiscretePathLoader(RawPathLoader rawPathLoader){
		this.rawPathLoader = rawPathLoader;
	}
	
	private final RawPathLoader rawPathLoader;
	
	public List<RawNode> load(){
		return null;
	}
	
	
}
