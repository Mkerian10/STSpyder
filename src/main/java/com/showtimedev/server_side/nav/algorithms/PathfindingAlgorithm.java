package com.showtimedev.server_side.nav.algorithms;

import com.showtimedev.server_side.nav.Connectable;
import com.showtimedev.server_side.nav.RawNode;
import com.showtimedev.shared.misc.GenericTile;

import javax.annotation.Nullable;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public interface AbstractAlgorithm<T extends GenericTile & Connectable<T>>{
	
	@Nullable
	List<T> findPath(T start, T dest);
	
	@Nullable
	default List<T> retrace(T start, T dest, Map<T, T> pathMap){
		var path = new LinkedList<T>();
		
		var curr = dest;
		
		while(curr != null){
			path.addFirst(curr);
			if(curr.equals(start)){
				return path;
			}
			curr = pathMap.getOrDefault(curr, null);
		}
	}
	
}
