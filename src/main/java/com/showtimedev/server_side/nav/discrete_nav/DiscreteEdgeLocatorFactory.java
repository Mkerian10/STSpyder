package com.showtimedev.server_side.nav.discrete_nav;

import com.showtimedev.shared.misc.PlayerConfig;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DiscreteEdgeLocatorFactory{
	
	private static DiscreteEdgeLocatorFactory instance;
	
	public static DiscreteEdgeLocatorFactory getFactory(){
		if(instance == null){
			instance = new DiscreteEdgeLocatorFactory();
		}
		return instance;
	}
	
	private final ThreadLocal<PlayerConfig> configThreadLocal = new ThreadLocal<>();
	
	public void setPlayerConfig(PlayerConfig config){
		configThreadLocal.set(config);
	}
	
	public Collection<DiscreteNode> edges(DiscreteNode node){
		PlayerConfig config = configThreadLocal.get();
		Stream<DiscreteEdge> edgeStream = DiscreteNodeWarehouse.getInstance().retrieveEdges(node).stream();
		return edgeStream.filter(e -> e.valid(config)).map(discreteEdge -> discreteEdge.dest).collect(Collectors.toList());
	}
}
