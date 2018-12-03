package com.showtimedev.server_side.nav.algorithms;

import com.showtimedev.server_side.nav.Connectable;
import com.showtimedev.shared.misc.GenericTile;

import java.util.List;

public interface AlgorithmEndBehavior<T extends GenericTile & Connectable<T>>{
	
	List<T> returnNoPath();
	
	default List<T> pathFound(List<T> path){
		return path;
	}
	
	static<K extends GenericTile & Connectable<K>> AlgorithmEndBehavior<K> defaultBehavior(){
		return () -> null;
	}
	
}
