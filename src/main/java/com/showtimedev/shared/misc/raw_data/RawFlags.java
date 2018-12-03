package com.showtimedev.client.raw_data;

/**
 * @Source Runelite
 */
public class RawFlags{
	
	public static final int BLOCK_MOVEMENT_NORTH_WEST = 0x1;
	public static final int BLOCK_MOVEMENT_NORTH = 0x2;
	public static final int BLOCK_MOVEMENT_NORTH_EAST = 0x4;
	public static final int BLOCK_MOVEMENT_EAST = 0x8;
	public static final int BLOCK_MOVEMENT_SOUTH_EAST = 0x10;
	public static final int BLOCK_MOVEMENT_SOUTH = 0x20;
	public static final int BLOCK_MOVEMENT_SOUTH_WEST = 0x40;
	public static final int BLOCK_MOVEMENT_WEST = 0x80;
	
	public static final int BLOCK_MOVEMENT_OBJECT = 0x100;
	public static final int BLOCK_MOVEMENT_FLOOR_DECORATION = 0x40000;
	public static final int BLOCK_MOVEMENT_FLOOR = 0x200000; // Eg. water
	
	public static final int BLOCK_MOVEMENT_FULL = BLOCK_MOVEMENT_OBJECT | BLOCK_MOVEMENT_FLOOR_DECORATION | BLOCK_MOVEMENT_FLOOR;
	


}
