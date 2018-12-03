package com.showtimedev.server_side.nav;

import com.showtimedev.server_side.nav.dist.Heuristic;
import com.showtimedev.server_side.nav.dist.Heuristics;
import com.showtimedev.shared.misc.GenericTile;

public interface Weightable<T extends GenericTile>{
	
	double weight(Heuristic heuristic, T comp);
	
	default double weight(T comp){
		return weight(Heuristics.euclidean, comp);
	}
}
