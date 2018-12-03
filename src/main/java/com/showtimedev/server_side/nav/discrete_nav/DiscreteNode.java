package com.showtimedev.server_side.nav.discrete_nav;

import com.showtimedev.server_side.nav.Connectable;
import com.showtimedev.server_side.nav.Weighable;
import com.showtimedev.server_side.nav.dist.Heuristic;
import com.showtimedev.shared.misc.GenericTile;

import java.util.Collection;

public class DiscreteNode extends GenericTile implements Connectable<DiscreteNode>, Weighable{
	
	public DiscreteNode(short x, short y, byte z){
		super(x, y, z);
	}
	
	@Override
	public Collection<DiscreteNode> edges(){
		return null;
	}
	
	@Override
	public double weight(Heuristic heuristic, GenericTile comp){
		return heuristic.distance(this, comp);
	}
	
	/**
	 * Returns an integer that allows for easy comparisons. Z-axis is shifted over so numbers will primarily stay
	 * in their own plane. X-axis and Y-axis are summed to provide equal weight for both
	 */
	public static int comparitiveInt(int x, int y, int z){
		int zed = (z << 12);
		int cosum = x + y;
		return zed + cosum;
	}
	
	@Override
	public int hashCode(){
		return comparitiveInt(x, y, z);
	}
}
