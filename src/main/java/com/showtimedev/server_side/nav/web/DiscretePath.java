package com.showtimedev.server_side.nav.web;

import com.showtimedev.server_side.nav.discrete_nav.DiscreteEdge;
import com.showtimedev.server_side.nav.discrete_nav.DiscreteNode;
import com.showtimedev.server_side.nav.discrete_nav.DiscreteNodeWarehouse;
import com.showtimedev.server_side.nav.raw_nav.RawNode;
import com.showtimedev.server_side.nav.raw_nav.RawNodeWarehouse;
import com.showtimedev.server_side.nav.raw_nav.RawPathLoader;
import com.showtimedev.shared.misc.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DiscretePath{
	
	public DiscretePath(RawPathLoader loader, List<DiscreteNode> discreteNodes){
		this.loader = loader;
		this.discreteNodes = discreteNodes;
	}
	
	private final RawPathLoader loader;
	
	private final List<DiscreteNode> discreteNodes;
	
	private int gateways = 0;
	
	private boolean teleport = false;
	
	private List<WebItemPair> items = new ArrayList<>();
	
	private List<RawNode> rawPath = new ArrayList<>();
	
	public WebResult generateWebResult(){
		List<DiscreteEdge> edges = mergeToEdges();
		edges.forEach(this::processEdge);
		
		WebItemPair[] itemPairs = items.toArray(new WebItemPair[items.size()]);
		WebTile[] tiles = rawPath.stream().map(rawNode -> new WebTile(rawNode.x, rawNode.y, rawNode.z)).toArray(WebTile[]::new);
		
		return WebResultBuilder.builder()
				.setPathFound(true)
				.setGateways(gateways)
				.setTeleportUsed(teleport)
				.setPath(tiles)
				.setItems(itemPairs)
				.build();
	}
	
	private void processEdge(DiscreteEdge e){
		switch(e.type){
			case TELEPORT:
				teleport = true;
			case GATEWAY_REQ:
				items.addAll(Arrays.asList(e.webItemPairs));
			case GATEWAY_ALL:
				gateways++;
				break;
			case REGULAR:
				rawPath.addAll(loader.load(convertToRaw(e.src), convertToRaw(e.dest)));
		}
	}
	
	private RawNode convertToRaw(GenericTile tile){
		return RawNodeWarehouse.getInstance().retrieve(tile.x, tile.y, tile.z);
	}
	
	private List<DiscreteEdge> mergeToEdges(){
		var edges = new ArrayList<DiscreteEdge>();
		var warehouse = DiscreteNodeWarehouse.getInstance();
		for(int i = 0; i < discreteNodes.size() - 1; i++){
			edges.add(warehouse.retrieveEdge(discreteNodes.get(i), discreteNodes.get(i + 1)));
		}
		return edges;
	}
}
