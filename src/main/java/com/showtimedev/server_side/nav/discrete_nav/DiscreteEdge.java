package com.showtimedev.server_side.nav.discrete_nav;

import com.showtimedev.server_side.nav.AbstractDynamic;
import com.showtimedev.server_side.nav.DynamicAccepter;

public class DiscreteEdge extends AbstractDynamic{
	
	public DiscreteEdge(DynamicAccepter accepter, DiscreteNode src, DiscreteNode dest){
		super(accepter);
		this.src = src;
		this.dest = dest;
	}
	
	public DiscreteEdge(DiscreteNode src, DiscreteNode dest){
		this.src = src;
		this.dest = dest;
	}
	
	public final DiscreteNode src;
	
	public final DiscreteNode dest;
	
	enum EdgeType{
		REGULAR,
		GATEWAY
	}
	
}
