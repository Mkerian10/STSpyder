package com.showtimedev.server_side.nav.dist;

public interface Heuristics{
	
	Heuristic euclidean = (a, b) -> Math.sqrt(Math.pow(Math.abs(a.x - b.x), 2) + Math.pow(Math.abs(a.y - b.y), 2));
	
	Heuristic manhattan = (a, b) -> Math.abs(a.x - b.x) + Math.abs(a.y - b.y);
	
	Heuristic diagonal = (a, b) -> {
		int dX = Math.abs(a.x - b.x);
		int dY = Math.abs(b.x - b.y);
		return (dX + dY) + (1.4 - 2) * Math.min(dX, dY);
	};
	
}
