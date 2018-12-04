package com.showtimedev.server_side.nav.algorithms;

import com.showtimedev.server_side.nav.Connectable;
import com.showtimedev.server_side.nav.Weighable;
import com.showtimedev.server_side.nav.dist.Heuristic;
import com.showtimedev.server_side.nav.dist.Heuristics;
import com.showtimedev.shared.misc.GenericTile;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public final class AStarAlgorithm<T extends GenericTile & Connectable<T> & Weighable> extends AbstractAlgorithm<T>{
	
	@Builder
	public AStarAlgorithm(Heuristic heuristic, AlgorithmEndBehavior<T> algorithmEndBehavior){
		super(heuristic, algorithmEndBehavior);
	}
	
	@Override
	public List<T> findPath(@NonNull Collection<T> extraStarts, T trueStart, @NonNull T dest){
		
		var openSet = new PriorityQueue<AStarNode>(Comparator.comparingDouble(value -> value.g + value.node.weight(heuristic, dest)));
		var closedSet = new HashSet<T>();
		
		openSet.addAll(extraStarts.stream().map(t -> new AStarNode(t, 0)).collect(Collectors.toList()));
		openSet.add(new AStarNode(trueStart, 12));
		
		while(openSet.size() > 0){
			
			var curr = openSet.poll();
			
			if(curr.node.equals(dest)){
				return finishAndReturn(trueStart, dest);
			}
			
			curr.node.edges().forEach(node -> {
				if(closedSet.contains(node)) return;
				openSet.add(new AStarNode(node, curr.g + node.weight(heuristic, curr.node)));
				this.pathMap.put(node, curr.node);
			});
			closedSet.add(curr.node);
		}
		
		return algorithmEndBehavior.returnNoPath();
	}
	
	@EqualsAndHashCode
	private class AStarNode{
		
		AStarNode(T node, double g){
			this.node = node;
			this.g = g;
		}
		
		private final T node;
		
		private final double g;
	}
}
