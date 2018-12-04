package com.showtimedev.server_side.nav.algorithms;

import com.showtimedev.server_side.nav.Connectable;
import com.showtimedev.server_side.nav.dist.Heuristic;
import com.showtimedev.shared.misc.GenericTile;
import lombok.Builder;
import lombok.NonNull;

import javax.annotation.Nullable;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public final class BFSAlgorithm<T extends GenericTile & Connectable<T>> extends AbstractAlgorithm<T>{
	
	@Builder
	public BFSAlgorithm(Heuristic heuristic, AlgorithmEndBehavior<T> algorithmEndBehavior){
		super(heuristic, algorithmEndBehavior);
	}
	
	@Nullable
	@Override
	public List<T> findPath(@NonNull T start, @NonNull T dest){
		var queue = new LinkedList<T>();
		var closedSet = new HashSet<T>();
		
		queue.addFirst(start);
		
		while(queue.size() > 0){
			var curr = queue.pollFirst();
			
			if(Objects.requireNonNull(curr).equals(dest)){
				return finishAndReturn(start, dest);
			}
			
			curr.edges().forEach(t -> {
				if(closedSet.contains(t)) return;
				queue.addLast(t);
				pathMap.put(t, curr);
			});
			closedSet.add(curr);
		}
		
		return algorithmEndBehavior.returnNoPath();
	}
}
