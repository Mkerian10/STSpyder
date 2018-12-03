package com.showtimedev.server_side.nav;

import com.showtimedev.server_side.nav.dist.Heuristic;
import com.showtimedev.server_side.nav.dist.Heuristics;
import com.showtimedev.shared.misc.GenericTile;

public interface Weighable{
	
	double weight(Heuristic heuristic, GenericTile comp);
	
	default double weight(GenericTile comp){
		return weight(Heuristics.euclidean, comp);
	}
}
