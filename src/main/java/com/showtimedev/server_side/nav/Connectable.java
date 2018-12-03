package com.showtimedev.server_side.nav;

import com.showtimedev.shared.misc.GenericTile;

import java.util.Collection;

public interface Connectable<T extends GenericTile>{
	Collection<T> edges();
}
