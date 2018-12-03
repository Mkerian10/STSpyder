package com.showtimedev.server_side.nav.discrete_nav;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.showtimedev.server_side.nav.raw_nav.RawNode;

import java.util.Collection;
import java.util.Comparator;
import java.util.Objects;
import java.util.TreeMap;

public class DiscreteNodeWarehouse{
	
	private static DiscreteNodeWarehouse instance;
	
	public static DiscreteNodeWarehouse getInstance(){
		return instance;
	}
	
	private final TreeMap<Integer, DiscreteNode> discreteGraph = new TreeMap<>(Comparator.comparingInt(i -> i));
	
	private final Multimap<DiscreteNode, DiscreteEdge> discreteEdges = HashMultimap.create();
	
	public DiscreteNode retrieveNode(int x, int y, int z){
		return discreteGraph.get(RawNode.hashCode(x, y, z));
	}
	
	private DiscreteNode getNode(int x, int y, int z){
		int key = DiscreteNode.comparitiveInt(x, y, z);
		var floor = discreteGraph.floorEntry(key);
		var ceil = discreteGraph.ceilingEntry(key);
		int fd = floor != null ? Math.abs(floor.getKey() - key): -1;
		int cd = ceil != null ? Math.abs(ceil.getKey() - key): -1;
		
		//gets nearest node taking into account nulls
		var nd = floor != null ? ceil != null ? fd > cd ? floor: ceil: floor: ceil;
		return Objects.requireNonNull(nd, "Null discrete node found in Discrete Graph").getValue();
	}
	
	public Collection<DiscreteEdge> retrieveEdges(DiscreteNode node){
		return discreteEdges.get(node);
	}
	
}
