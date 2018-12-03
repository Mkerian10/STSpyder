package com.showtimedev.shared.misc.raw_data;

public class RawTile{
	
	public RawTile(int x, int y, int z, int flags){
		this.x = x;
		this.y = y;
		this.z = z;
		this.flags = flags;
	}
	
	public final int x;
	
	public final int y;
	
	public final int z;
	
	public final int flags;
	
}
