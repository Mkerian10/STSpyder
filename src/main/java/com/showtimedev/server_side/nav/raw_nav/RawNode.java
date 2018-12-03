package com.showtimedev.server_side.nav.raw_nav;

import com.showtimedev.server_side.nav.Connectable;
import com.showtimedev.server_side.nav.Weighable;
import com.showtimedev.server_side.nav.dist.Heuristic;
import com.showtimedev.shared.misc.GenericTile;
import com.showtimedev.shared.misc.Jsonable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class RawNode extends GenericTile implements Connectable<RawNode>, Weighable, Jsonable, Serializable{
	
	private static final long serialVersionUID = 1L;
	
	public static final byte N = 0x1;
	public static final byte NE = 0x2;
	public static final byte E = 0x4;
	public static final byte SE = 0x8;
	public static final byte S = 0x10;
	public static final byte SW = 0x20;
	public static final byte W = 0x40;
	public static final byte NW = (byte) 0x80;
	
	public RawNode(short x, short y, byte z, byte navFlags){
		super(x, y, z);
		this.navFlags = navFlags;
	}
	
	public RawNode(int x, int y, int z, int navFlags){
		this((short)x, (short)y, (byte)(z), (byte)navFlags);
	}
	
	public final byte navFlags;
	
	private transient static RawNodeWarehouse warehouse;
	
	public Collection<RawNode> edges(){
		int popcount = Integer.bitCount(navFlags);
		
		if(popcount == 0){
			return new ArrayList<>();
		}
		
		List<RawNode> nodes = new ArrayList<>(popcount);
		addTilesFromFlags(nodes);
		return nodes;
	}
	
	private RawNode retrieve(int x, int y, int z){
		if(warehouse == null){
			warehouse = RawNodeWarehouse.getInstance();
		}
		return Objects.requireNonNull(warehouse.retrieve(x, y, z), "Raw Node not found in RawNodeWarehouse");
	}
	
	private void addTilesFromFlags(List<RawNode> nodes){
		if((N & navFlags) != 0){
			nodes.add(retrieve(x, y + 1, z));
		}
		
		if((NE & navFlags) != 0){
			nodes.add(retrieve(x + 1, y + 1, z));
		}
		
		if((E & navFlags) != 0){
			nodes.add(retrieve(x + 1, y, z));
		}
		
		if((SE & navFlags) != 0){
			nodes.add(retrieve(x + 1, y - 1, z));
		}
		
		if((S & navFlags) != 0){
			nodes.add(retrieve(x, y - 1, z));
		}
		
		if((SW & navFlags) != 0){
			nodes.add(retrieve(x - 1, y - 1, z));
		}
		
		if((W & navFlags) != 0){
			nodes.add(retrieve(x - 1, y, z));
		}
		
		if((NW & navFlags) != 0){
			nodes.add(retrieve(x - 1, y + 1, z));
		}
	}
	
	@Override
	public double weight(Heuristic heuristic, GenericTile comp){
		return heuristic.distance(this, comp);
	}
	
	@Override
	public boolean equals(Object obj){
		return obj instanceof RawNode && ((RawNode) obj).x == x && ((RawNode) obj).y == y && ((RawNode) obj).z == z;
	}
	
	@Override
	public String toString(){
		return String.format("(%d,%d,%d)", x, y, z);
	}
}
