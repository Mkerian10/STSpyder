package com.showtimedev.server_side.nav.algorithms;

import com.showtimedev.server_side.nav.Connectable;
import com.showtimedev.shared.misc.GenericTile;

import javax.annotation.Nullable;
import java.util.Collection;
import java.util.List;

public interface PathfindingAlgorithm<T extends GenericTile & Connectable<T>>{
	
	@Nullable
	List<T> findPath(Collection<T> extraStarts, T trueStart, T dest);
	
}
