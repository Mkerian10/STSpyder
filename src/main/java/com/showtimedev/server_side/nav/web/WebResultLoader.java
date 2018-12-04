package com.showtimedev.server_side.nav.web;

import com.showtimedev.server_side.nav.algorithms.AStarAlgorithm;
import com.showtimedev.server_side.nav.discrete_nav.DiscreteEdgeLocatorFactory;
import com.showtimedev.server_side.nav.discrete_nav.DiscreteNode;
import com.showtimedev.server_side.nav.discrete_nav.DiscreteNodeWarehouse;
import com.showtimedev.server_side.nav.discrete_nav.Teleports;
import com.showtimedev.server_side.nav.raw_nav.RawPathLoader;
import com.showtimedev.shared.misc.PlayerConfig;
import com.showtimedev.shared.misc.WebResult;
import com.showtimedev.shared.misc.WebTile;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class WebResultLoader{
	
	private final RawPathLoader rawPathLoader;
	
	public WebResult load(PlayerConfig config, WebTile src, WebTile dest){
		var startNodes = teleports(config);
		startNodes.add(convertWebTileToDiscreteNode(src));
		DiscreteEdgeLocatorFactory.getFactory().setPlayerConfig(config);
		
		var discretePath = findDiscretePath(startNodes, convertWebTileToDiscreteNode(src), convertWebTileToDiscreteNode(dest));
		if(discretePath == null) return null;
		
		return discretePath.generateWebResult();
	}
	
	private DiscretePath findDiscretePath(List<DiscreteNode> starts, DiscreteNode src, DiscreteNode dest){
		var algorithm = AStarAlgorithm.<DiscreteNode>builder().build();
		List<DiscreteNode> nodes = algorithm.findPath(starts, src, dest);
		return nodes == null ? null : new DiscretePath(rawPathLoader, nodes);
	}
	
	private DiscreteNode convertWebTileToDiscreteNode(WebTile t){
		return DiscreteNodeWarehouse.getInstance().retrieveNode(t.x, t.y, t.z);
	}
	
	private List<DiscreteNode> teleports(PlayerConfig config){
		if(config.isDisableTeleports()){
			return new ArrayList<>();
		}
		
		return Arrays.stream(Teleports.values())
				.filter(teleports -> teleports.accepts(config))
				.map(Teleports::getDest)
				.collect(Collectors.toList());
	}
	
	
}
