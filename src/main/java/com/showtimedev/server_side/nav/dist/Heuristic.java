package com.showtimedev.server_side.nav.pathfinder;

import com.showtimedev.server_side.nav.RawNode;

public interface Heuristic{

	double distance(RawNode a, RawNode b);

}
