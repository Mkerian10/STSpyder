package com.showtimedev.server_side.nav;

import com.showtimedev.server_side.nav.raw_nav.RawNode;
import com.showtimedev.server_side.nav.raw_nav.RawNodeWarehouse;
import org.junit.Test;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

import static com.showtimedev.server_side.nav.raw_nav.RawNode.*;

public class RawNodeWarehouseTest{
	
	private static RawNode[][] testNodes;
	
	@Test
	public void serializeTest() throws IOException, ClassNotFoundException{
		
		RawNodeWarehouse warehouse = testWarehouse();
		
		RawNode r = warehouse.retrieve(0 ,0, 0);
		String json = r.toJson();
		
	}
	
	/**
	 * Gets a RawNodeWarehouse for testing purposes. Reference image can be found in test/externals/WarehouseTestImage.png
	 * <p>
	 * Size: 12x11
	 */
	public static RawNodeWarehouse testWarehouse(){
		RawNodeWarehouse nw = RawNodeWarehouse.getInstance();
		loadInto(testNodes, nw);
		return nw;
	}
	
	private static void loadInto(RawNode[][] rnarr, RawNodeWarehouse nw){
		
		RawNode[] rarr = Arrays.stream(rnarr).flatMap(Arrays::stream).toArray(RawNode[]::new);
		
		try{
			Method m = getLoadIntoMethod(nw);
			m.invoke(nw, (Object) rarr);
		}catch(IllegalAccessException | InvocationTargetException e){
			e.printStackTrace();
		}
	}
	
	private static Method getLoadIntoMethod(Object mw){
		try{
			Method m = RawNodeWarehouse.class.getDeclaredMethod("loadInto", RawNode[].class);
			m.setAccessible(true);
			return m;
		}catch(NoSuchMethodException e){
			e.printStackTrace();
			throw new NullPointerException();
		}
	}
	
	
	private static RawNode create(int x, int y, int z, int flags){
		return new RawNode((short) x, (short) (11 - y), (byte) z, (byte) flags);
	}
	
	private static RawNode create(int x, int y, int flags){
		return create(x, y, 0, flags);
	}
	
	static{
		testNodes = new RawNode[11][12];
		
		//Row 0
		testNodes[0][0] = create(0, 0, 0, (E | S));
		testNodes[0][1] = create(1, 0, 0, (E | W));
		testNodes[0][2] = create(2, 0, 0, W | S);
		testNodes[0][3] = null; //BLOCKED wall is here
		testNodes[0][4] = create(4, 0, 0, S);
		testNodes[0][5] = null;
		testNodes[0][6] = create(6, 0, 0, E | S | SE);
		testNodes[0][7] = create(7, 0, 0, W | SW | S);
		testNodes[0][8] = null;
		testNodes[0][9] = create(9, 0, 0, E);
		testNodes[0][10] = create(10, 0, 0, W | E | S);
		testNodes[0][11] = create(11, 0, 0, W);
		
		//Row 1
		testNodes[1][0] = create(0, 1, 0, N | S);
		testNodes[1][1] = null;
		testNodes[1][2] = create(2, 1, N);
		testNodes[1][3] = null;
		testNodes[1][4] = create(4, 1, N | S);
		testNodes[1][5] = null;
		testNodes[1][6] = create(6, 1, N | NE | E | S);
		testNodes[1][7] = create(7, 1, W | N | NW);
		testNodes[1][8] = null;
		testNodes[1][9] = null;
		testNodes[1][10] = create(10, 1, N | S);
		testNodes[1][11] = null;
		
		//Row 2
		testNodes[2][0] = create(0, 2, N | S);
		testNodes[2][1] = null;
		testNodes[2][2] = null;
		testNodes[2][3] = create(3, 2, E);
		testNodes[2][4] = create(4, 2, W | N | S);
		testNodes[2][5] = null;
		testNodes[2][6] = create(6, 2, N | S);
		testNodes[2][7] = null;
		testNodes[2][8] = create(8, 2, S);
		testNodes[2][9] = null;
		testNodes[2][10] = create(10, 2, N | E | S | SE);
		testNodes[2][11] = create(11, 2, W | S | SW);
		
		//Row 3
		testNodes[3][0] = create(0, 3, N | S);
		testNodes[3][1] = null;
		testNodes[3][2] = null;
		testNodes[3][3] = null;
		testNodes[3][4] = create(4, 3, N | E | S);
		testNodes[3][5] = create(5, 3, E | W);
		testNodes[3][6] = create(6, 3, N | W | S);
		testNodes[3][7] = null;
		testNodes[3][8] = create(8, 3, N | E);
		testNodes[3][9] = create(9, 3, W | E);
		testNodes[3][10] = create(10, 3, W | N | NE | E | SE | S);
		testNodes[3][11] = create(11, 3, W | NW | N | S | SW);
		
		//Row 4
		testNodes[4][0] = create(0, 4, N | E | S | SE);
		testNodes[4][1] = create(1, 4, W | SW | S);
		testNodes[4][2] = null;
		testNodes[4][3] = null;
		testNodes[4][4] = create(4, 4, N | S);
		testNodes[4][5] = null;
		testNodes[4][6] = create(6, 4, N | S);
		testNodes[4][7] = null;
		testNodes[4][8] = null;
		testNodes[4][9] = null;
		testNodes[4][10] = create(10, 4, N | NE | E | SE | S);
		testNodes[4][11] = create(11, 4, W | NW | SW | S | N);
		
		//Row 5
		testNodes[5][0] = create(0, 5, N | E | S);
		testNodes[5][1] = create(1, 5, N | NW | W | E);
		testNodes[5][2] = create(2, 5, W | S);
		testNodes[5][3] = null;
		testNodes[5][4] = create(4, 5, N | S);
		testNodes[5][5] = null;
		testNodes[5][6] = create(6, 5, N | S);
		testNodes[5][7] = null;
		testNodes[5][8] = create(8, 5, E | S | SE);
		testNodes[5][9] = create(9, 5, S | SW | W | E);
		testNodes[5][10] = create(10, 5, W | N | NE | E);
		testNodes[5][11] = create(11, 5, N | NW | W | S);
		
		//Row 6
		testNodes[6][0] = create(0, 6, N | S);
		testNodes[6][1] = null;
		testNodes[6][2] = create(2, 6, N | E);
		testNodes[6][3] = create(3, 6, W | S | E);
		testNodes[6][4] = create(4, 6, W | N | E);
		testNodes[6][5] = create(5, 6, W | E);
		testNodes[6][6] = create(6, 6, N | W | E);
		testNodes[6][7] = create(7, 6, W | E);
		testNodes[6][8] = create(8, 6, W | N | NE | E);
		testNodes[6][9] = create(9, 6, S | W | NW | N);
		testNodes[6][10] = null;
		testNodes[6][11] = create(11, 6, N);
		
		//Row 7
		testNodes[7][0] = create(0, 7, N | S);
		testNodes[7][1] = null;
		testNodes[7][2] = null;
		testNodes[7][3] = create(3, 7, N | S);
		testNodes[7][4] = null;
		testNodes[7][5] = null;
		testNodes[7][6] = null;
		testNodes[7][7] = null;
		testNodes[7][8] = null;
		testNodes[7][9] = create(9, 7, N | S);
		testNodes[7][10] = null;
		testNodes[7][11] = null;
		
		//Row 8
		testNodes[8][0] = create(0, 8, N | S | E);
		testNodes[8][1] = create(1, 8, W | E);
		testNodes[8][2] = create(2, 8, W | E);
		testNodes[8][3] = create(3, 8, W | N | E);
		testNodes[8][4] = create(4, 8, W | S | E);
		testNodes[8][5] = create(5, 8, W | E);
		testNodes[8][6] = create(6, 8, W | E);
		testNodes[8][7] = create(7, 8, W | S);
		testNodes[8][8] = null;
		testNodes[8][9] = create(9, 8, N | S);
		testNodes[8][10] = null;
		testNodes[8][11] = create(11, 8, S);
		
		//Row 9
		testNodes[9][0] = create(0, 9, N | S);
		testNodes[9][1] = null;
		testNodes[9][2] = null;
		testNodes[9][3] = null;
		testNodes[9][4] = create(4, 9, N | S);
		testNodes[9][5] = null;
		testNodes[9][6] = null;
		testNodes[9][7] = create(7, 9, N | S);
		testNodes[9][8] = null;
		testNodes[9][9] = create(9, 9, N | S);
		testNodes[9][10] = null;
		testNodes[9][11] = create(11, 9, N | S);
		
		//Row 10
		testNodes[10][0] = create(0, 10, N | E);
		testNodes[10][1] = create(1, 10, W | E);
		testNodes[10][2] = create(2, 10, W | E);
		testNodes[10][3] = create(3, 10, W | E);
		testNodes[10][4] = create(4, 10, W | E | N);
		testNodes[10][5] = create(5, 10, W);
		testNodes[10][6] = null;
		testNodes[10][7] = create(7, 10, N | E);
		testNodes[10][8] = create(8, 10, W | E);
		testNodes[10][9] = create(9, 10, W | N | E);
		testNodes[10][10] = create(10, 10, W | E);
		testNodes[10][11] = create(11, 10, W | N);
		
	}
}