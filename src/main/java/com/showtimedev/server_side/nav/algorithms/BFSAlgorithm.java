package com.showtimedev.server_side.nav.algorithms;

import com.showtimedev.server_side.nav.Connectable;
import com.showtimedev.server_side.nav.dist.Heuristic;
import com.showtimedev.shared.misc.GenericTile;
import lombok.Builder;
import lombok.NonNull;

import javax.annotation.Nullable;
import java.util.*;

public final class BFSAlgorithm<T extends GenericTile & Connectable<T>> extends AbstractAlgorithm<T>{
	
	@Builder
	public BFSAlgorithm(Heuristic heuristic, AlgorithmEndBehavior<T> algorithmEndBehavior){
		super(heuristic, algorithmEndBehavior);
	}
	
	@Nullable
	@Override
	public List<T> findPath(@NonNull Collection<T> extraStarts, T trueStart, @NonNull T dest){
		var closedSet = new HashSet<T>();
		var queue = new LinkedList<>(extraStarts);
		queue.add(trueStart);
		
		while(queue.size() > 0){
			var curr = queue.pollFirst();
			
			if(Objects.requireNonNull(curr).equals(dest)){
				return finishAndReturn(trueStart, dest);
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
