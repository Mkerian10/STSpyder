package com.showtimedev.shared.misc;

import java.io.Serializable;

public abstract class GenericTile implements Serializable{
	
	public GenericTile(short x, short y, byte z){
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public final short x, y;
	
	public final byte z;
	
	@Override
	public int hashCode(){
		int hash = 0;
		hash += z;
		hash += (x << 2);
		hash += (y << 8);
		return hash;
	}
	
	public static int hashCode(int x, int y, int z){
		int hash = 0;
		hash += z;
		hash += (x << 2);
		hash += (y << 8);
		return hash;
	}
}
