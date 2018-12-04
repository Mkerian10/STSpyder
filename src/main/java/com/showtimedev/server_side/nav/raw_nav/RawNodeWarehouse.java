package com.showtimedev.server_side.nav.raw_nav;

import com.google.common.annotations.VisibleForTesting;
import com.showtimedev.shared.misc.GenericTile;
import com.showtimedev.shared.misc.utils.SerializationUtils;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.annotation.Nullable;
import javax.annotation.WillClose;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

@NoArgsConstructor
public class RawNodeWarehouse{
	
	private static RawNodeWarehouse instance = new RawNodeWarehouse();
	
	public static RawNodeWarehouse getInstance(){
		return instance;
	}
	
	private final Map<Integer, RawNode> worldGraph = new HashMap<>();
	
	@Nullable
	public RawNode retrieve(int x, int y, int z){
		return worldGraph.getOrDefault(GenericTile.hashCode(x, y, z), null);
	}
	
	public void loadInto(@NonNull InputStream stream){
		try{
			var nodes = SerializationUtils.deserialize(stream);
			
			try{
				populateGraph((ArrayList<RawNode>) nodes);
				
			}catch(ClassCastException e){
				e.printStackTrace();
				throw new RuntimeException("Deserialized instance not ArrayList object during RawNodeWarehouse init");
			}
		}catch(IOException e){
			e.printStackTrace();
			throw new RuntimeException("IO Error during RawNodeWarehouse loading.");
		}catch(ClassNotFoundException e){
			e.printStackTrace();
			throw new RuntimeException("Failed to cast classes for RawNodeWarehouse loading!");
		}
	}
	
	private void populateGraph(Collection<RawNode> nodes){
		nodes.forEach(n -> worldGraph.put(n.hashCode(), n));
	}
	
	@Nullable
	public byte[] serialize(){
		var serializeableList = new ArrayList<>(worldGraph.values());
		
		try{
			return SerializationUtils.serialize(serializeableList);
		}catch(IOException e){
			e.printStackTrace();
			System.err.println("Failed to serialize raw node warehouse!");
			return null;
		}
	}
	
	@VisibleForTesting
	private void loadInto(RawNode[] rnarr){
		Arrays.stream(rnarr).forEach(rawNode -> {
			if(rawNode != null)
				worldGraph.put(rawNode.hashCode(), rawNode);
		});
	}
	
}
