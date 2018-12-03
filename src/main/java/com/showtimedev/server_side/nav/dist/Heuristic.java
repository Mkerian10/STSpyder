package com.showtimedev.server_side.nav.dist;

import com.showtimedev.shared.misc.GenericTile;

public interface Heuristic{
	
	double distance(GenericTile a, GenericTile b);
	
}
