package com.showtimedev.server_side.nav.algorithms;

import com.showtimedev.server_side.nav.Connectable;
import com.showtimedev.server_side.nav.dist.Heuristic;
import com.showtimedev.server_side.nav.dist.Heuristics;
import com.showtimedev.shared.misc.GenericTile;
import lombok.NonNull;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public abstract class AbstractAlgorithm<T extends GenericTile & Connectable<T>> implements PathfindingAlgorithm<T>{
	
	public AbstractAlgorithm(Heuristic heuristic, AlgorithmEndBehavior<T> algorithmEndBehavior){
		if(heuristic != null) this.heuristic = heuristic;
		if(algorithmEndBehavior != null) this.algorithmEndBehavior = algorithmEndBehavior;
	}
	
	protected Heuristic heuristic = Heuristics.euclidean;
	
	protected AlgorithmEndBehavior<T> algorithmEndBehavior = AlgorithmEndBehavior.defaultBehavior();
	
	protected transient final Map<T, T> pathMap = new HashMap<>();
	
	@Nullable
	List<T> finishAndReturn(T start, T dest){
		List<T> path = retrace(start, dest);
		if(path == null) return algorithmEndBehavior.returnNoPath();
		return algorithmEndBehavior.pathFound(path);
	}
	
	@Nullable
	private List<T> retrace(@NonNull T start, @NonNull T dest){
		var path = new LinkedList<T>();
		var curr = dest;
		
		while(curr != null){
			path.addFirst(curr);
			if(curr.equals(start)){
				return path;
			}
			curr = pathMap.getOrDefault(curr, null);
		}
		
		return null;
	}
}
