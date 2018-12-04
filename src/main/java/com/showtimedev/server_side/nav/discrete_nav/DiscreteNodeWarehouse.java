package com.showtimedev.server_side.nav.discrete_nav;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.showtimedev.server_side.nav.raw_nav.RawNode;
import com.showtimedev.shared.misc.utils.SerializationUtils;
import lombok.NonNull;

import javax.annotation.Nullable;
import javax.annotation.WillClose;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class DiscreteNodeWarehouse{
	
	private static DiscreteNodeWarehouse instance;
	
	public static DiscreteNodeWarehouse getInstance(){
		return instance;
	}
	
	private final TreeMap<Integer, DiscreteNode> discreteGraph = new TreeMap<>(Comparator.comparingInt(i -> i));
	
	private final Multimap<Integer, DiscreteEdge> discreteEdges = HashMultimap.create();
	
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
		return discreteEdges.get(node.hashCode());
	}
	
	@Nullable
	public byte[] serialize(){
		var serializeableList = new ArrayList<>(discreteGraph.values());
		
		try{
			return SerializationUtils.serialize(serializeableList);
		}catch(IOException e){
			e.printStackTrace();
			System.err.println("Failed to serialize discrete node warehouse!");
			return null;
		}
	}
	
	public void loadInto(@NonNull InputStream nodeStream, @NonNull InputStream edgeStream){
		
		var sb = new StringBuilder();
		
		try{
			sb.append("Initing graph...");
			var nodes = SerializationUtils.deserialize(nodeStream);
			fillGraph((ArrayList<DiscreteNode>)nodes);
			sb.append("Graph initialized");
			sb.append("Initing edges");
			var edges = SerializationUtils.deserialize(edgeStream);
			fillEdges((ArrayList<DiscreteEdge>)edges);
			sb.append("Edges initialized");
			
			try{
			
			}catch(ClassCastException e){
				e.printStackTrace();
				throw new RuntimeException("Deserialized instance not ArrayList object during DiscreteNodeWarehouse init. " +
						sb.toString());
			}
		}catch(IOException | ClassNotFoundException e){
			e.printStackTrace();
		}
	}
	
	private void fillGraph(ArrayList<DiscreteNode> nodes){
		nodes.forEach(n -> discreteGraph.put(n.hashCode(), n));
	}
	
	private void fillEdges(ArrayList<DiscreteEdge> edges){
		edges.forEach(e -> discreteEdges.put(e.dest.hashCode(), e));
	}
	
}
