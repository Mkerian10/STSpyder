package com.showtimedev.server_side.nav.raw_nav;

import com.google.common.annotations.VisibleForTesting;
import com.showtimedev.shared.misc.GenericTile;
import lombok.NoArgsConstructor;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

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
	
	@VisibleForTesting
	private void loadInto(RawNode[] rnarr){
		Arrays.stream(rnarr).forEach(rawNode -> {
			if(rawNode != null)
				worldGraph.put(rawNode.hashCode(), rawNode);
		});
	}
	
	public Collection<RawNode> nodes(){
		return worldGraph.values();
	}
	
}
