package com.showtimedev.server_side.nav.algorithms;

import com.showtimedev.server_side.nav.Connectable;
import com.showtimedev.server_side.nav.Weighable;
import com.showtimedev.server_side.nav.dist.Heuristic;
import com.showtimedev.shared.misc.GenericTile;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.annotation.Nullable;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;

public final class DijkstrasAlgorithm<T extends GenericTile & Connectable<T> & Weighable> extends AbstractAlgorithm<T>{
	
	@Builder
	public DijkstrasAlgorithm(Heuristic heuristic, AlgorithmEndBehavior<T> algorithmEndBehavior){
		super(heuristic, algorithmEndBehavior);
	}
	
	@Nullable
	@Override
	public List<T> findPath(@NonNull T start, @NonNull T dest){
		var queue = new PriorityQueue<DijkstrasNode>(Comparator.comparingDouble(value -> value.weight));
		var closedSet = new HashSet<T>();
		
		queue.add(new DijkstrasNode(start, 0));
		
		while(queue.size() > 0){
			var curr = queue.poll();
			
			if(curr.node.equals(dest)){
				return finishAndReturn(start, dest);
			}
			
			curr.node.edges().forEach(t -> {
				if(closedSet.contains(t)) return;
				queue.add(new DijkstrasNode(t, curr.weight + curr.node.weight(heuristic, t)));
				pathMap.put(t, curr.node);
			});
			closedSet.add(curr.node);
		}
		
		return algorithmEndBehavior.returnNoPath();
	}
	
	@EqualsAndHashCode
	@RequiredArgsConstructor
	private class DijkstrasNode{
		
		private final T node;
		
		private final double weight;
		
	}
}
