package com.showtimedev.server_side.nav.algorithms;

import com.showtimedev.server_side.nav.Connectable;
import com.showtimedev.shared.misc.GenericTile;

import java.util.List;

public interface NoPathFoundBehavior<T extends GenericTile & Connectable<T>>{
	
	List<T> returnNoPath();
	
	static<K extends GenericTile & Connectable<K>> NoPathFoundBehavior<K> defaultBehavior(){
		return () -> null;
	}
	
}
