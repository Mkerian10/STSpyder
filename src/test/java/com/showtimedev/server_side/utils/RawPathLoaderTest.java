package com.showtimedev.server_side.utils;

import com.showtimedev.server_side.nav.raw_nav.RawPathLoader;
import com.showtimedev.server_side.nav.raw_nav.RawNodeWarehouse;
import com.showtimedev.server_side.nav.RawNodeWarehouseTest;
import com.showtimedev.server_side.nav.raw_nav.RawNode;
import com.showtimedev.server_side.nav.algorithms.AStarAlgorithm;
import com.showtimedev.shared.misc.StopWatch;
import org.junit.Test;

import static org.junit.Assert.*;

public class RawPathLoaderTest{
	
	@Test
	public void testCache(){
		
		RawNodeWarehouse nw = RawNodeWarehouseTest.testWarehouse();
		
		RawNode src = nw.retrieve(3, 1, 0);
		assertNotNull(src);
		assertTrue(src.x == 3 && src.y == 1);
		RawNode dest = nw.retrieve(5, 8, 0);
		assertNotNull(dest);
		assertTrue(dest.y == 8 && dest.x == 5);
		
		StopWatch sw = new StopWatch();
		sw.start();
		for(int i = 0; i < 1000000; i++){
			AStarAlgorithm<RawNode> algorithm = AStarAlgorithm.<RawNode>builder().build();
			algorithm.findPath(src, dest);
		}
		sw.stop();
		System.out.println(sw.getTime());
		long time1 = sw.getTime();
		
		sw.reset();
		
		RawPathLoader rawPathLoader = new RawPathLoader();
		
		sw.start();
		
		for(int i = 0; i < 1000000; i++){
			rawPathLoader.load(src, dest);
		}
		
		sw.stop();
		System.out.println(sw.getTime());
		
		long time2 = sw.getTime();
		
		assertTrue(time1 / 10 > time2);
		
	}
	
}