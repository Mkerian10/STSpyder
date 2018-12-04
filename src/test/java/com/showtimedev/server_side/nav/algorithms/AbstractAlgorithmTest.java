package com.showtimedev.server_side.nav.algorithms;

import com.showtimedev.server_side.nav.raw_nav.RawNodeWarehouse;
import com.showtimedev.server_side.nav.RawNodeWarehouseTest;
import com.showtimedev.server_side.nav.raw_nav.RawNode;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class AbstractAlgorithmTest{
	
	@Test
	public void testPathfindingAlgorithms(){
		
		RawNodeWarehouse nw = RawNodeWarehouseTest.testWarehouse();
		
		RawNode src = nw.retrieve(3, 1, 0);
		assertNotNull(src);
		assertTrue(src.x == 3 && src.y == 1);
		RawNode dest = nw.retrieve(5, 8, 0);
		assertNotNull(dest);
		assertTrue(dest.y == 8 && dest.x == 5);
		
		BFSAlgorithm<RawNode> bfs = BFSAlgorithm.<RawNode>builder().build();
		DijkstrasAlgorithm<RawNode> dijkstras = DijkstrasAlgorithm.<RawNode>builder().build();
		AStarAlgorithm<RawNode> astar = AStarAlgorithm.<RawNode>builder().build();
		
		test(src, dest, bfs);
		test(src, dest, dijkstras);
		test(src, dest, astar);
		
	}
	
	private void test(RawNode src, RawNode dest, AbstractAlgorithm<RawNode> algorithm){
		
		List<RawNode> path = algorithm.findPath(new ArrayList<>(), src, dest);
		
		assertNotNull(path);
		assertTrue(path.contains(src));
		assertTrue(path.contains(dest));
		assertEquals(src, path.get(0));
		assertEquals(dest, path.get(path.indexOf(dest)));
	}
	
}