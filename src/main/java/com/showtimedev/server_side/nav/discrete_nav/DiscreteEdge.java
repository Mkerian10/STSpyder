package com.showtimedev.server_side.nav.discrete_nav;

import com.showtimedev.server_side.nav.AbstractDynamic;
import com.showtimedev.shared.misc.WebItemPair;
import lombok.Builder;

@Builder
public class DiscreteEdge extends AbstractDynamic{
	
	public final DiscreteNode src;
	
	public final DiscreteNode dest;
	
	public @Builder.Default
	final EdgeType type = EdgeType.REGULAR;
	
	public @Builder.Default
	final WebItemPair[] webItemPairs = new WebItemPair[0];
	
	public enum EdgeType{
		REGULAR,
		TELEPORT,
		GATEWAY_ALL,
		GATEWAY_REQ
	}
	
}
