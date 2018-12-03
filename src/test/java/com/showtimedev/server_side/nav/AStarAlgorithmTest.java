package com.showtimedev.server_side.nav;

import com.showtimedev.server_side.nav.algorithms.AStarAlgorithm;
import com.showtimedev.server_side.nav.dist.Heuristics;
import com.showtimedev.server_side.nav.raw_nav.RawNode;
import com.showtimedev.server_side.nav.raw_nav.RawNodeWarehouse;
import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;

import static org.junit.Assert.*;

public class AStarAlgorithmTest{
	
	@Test
	public void findPath(){
		
		RawNodeWarehouse warehouse = RawNodeWarehouseTest.testWarehouse();
		
		testSimpleCorrectness(warehouse);
		testModerateCorrectness(warehouse);
		testComplexCorrectness(warehouse);
		
		
		
	}
	
	private void testSimpleCorrectness(RawNodeWarehouse warehouse){
		
		AStarAlgorithm algorithm = AStarAlgorithm.builder().build();
		
		RawNode start = warehouse.retrieve(0, 0, 0);
		assertNotNull(start);
		RawNode dest = warehouse.retrieve(0, 2, 0);
		assertNotNull(dest);
		
		LinkedList<RawNode> knownPath = new LinkedList<>();
		knownPath.addFirst(start);
		knownPath.addLast(warehouse.retrieve(0, 1, 0));
		knownPath.addLast(dest);
		
		LinkedList<RawNode> foundPath = (LinkedList<RawNode>)algorithm.findPath(start, dest);

		assertEquals(foundPath, knownPath);
		
	}
	
	private void testModerateCorrectness(RawNodeWarehouse warehouse){
		
		AStarAlgorithm algorithm = AStarAlgorithm.builder().build();
		
		RawNode start = warehouse.retrieve(0, 0, 0);
		assertNotNull(start);
		RawNode dest = warehouse.retrieve(3 ,3, 0);
		assertNotNull(dest);
		
		LinkedList<RawNode> knownPath = new LinkedList<>();
		knownPath.addFirst(start);
		knownPath.addLast(warehouse.retrieve(0, 1, 0));
		knownPath.addLast(warehouse.retrieve(0, 2, 0));
		knownPath.addLast(warehouse.retrieve(1, 2, 0));
		knownPath.addLast(warehouse.retrieve(2, 2, 0));
		knownPath.addLast(warehouse.retrieve(3, 2, 0));
		knownPath.addLast(warehouse.retrieve(3, 3, 0));
		
		LinkedList<RawNode> foundPath = (LinkedList<RawNode>)algorithm.findPath(start, dest);
		
		assertEquals(foundPath, knownPath);
		
	}
	
	private void testComplexCorrectness(RawNodeWarehouse warehouse){
		
		AStarAlgorithm algorithm = AStarAlgorithm.builder().heuristic(Heuristics.diagonal).build();
		
		RawNode start = warehouse.retrieve(1, 10, 0);
		assertNotNull(start);
		RawNode dest = warehouse.retrieve(11, 2, 0);
		assertNotNull(dest);
		
		LinkedList<RawNode> knownPath = new LinkedList<>();
		knownPath.addFirst(start);
		knownPath.addLast(warehouse.retrieve(0, 10, 0));
		knownPath.addLast(warehouse.retrieve(0, 9, 0));
		knownPath.addLast(warehouse.retrieve(0, 8, 0));
		knownPath.addLast(warehouse.retrieve(0, 7, 0));
		knownPath.addLast(warehouse.retrieve(0, 6, 0));
		knownPath.addLast(warehouse.retrieve(1, 6, 0));
		knownPath.addLast(warehouse.retrieve(1, 5, 0));
		knownPath.addLast(warehouse.retrieve(2, 5, 0));
		knownPath.addLast(warehouse.retrieve(2, 4, 0));
		knownPath.addLast(warehouse.retrieve(3, 4, 0));
		knownPath.addLast(warehouse.retrieve(4, 4, 0));
		knownPath.addLast(warehouse.retrieve(5, 4, 0));
		knownPath.addLast(warehouse.retrieve(6, 4, 0));
		knownPath.addLast(warehouse.retrieve(7, 4, 0));
		knownPath.addLast(warehouse.retrieve(8, 4, 0));
		knownPath.addLast(warehouse.retrieve(8, 5, 0));
		knownPath.addLast(warehouse.retrieve(9, 5, 0));
		knownPath.addLast(warehouse.retrieve(9, 4, 0));
		knownPath.addLast(warehouse.retrieve(9, 3, 0));
		knownPath.addLast(warehouse.retrieve(9, 2, 0));
		knownPath.addLast(warehouse.retrieve(9, 1, 0));
		knownPath.addLast(warehouse.retrieve(9, 0, 0));
		knownPath.addLast(warehouse.retrieve(10, 0, 0));
		knownPath.addLast(warehouse.retrieve(11, 0, 0));
		knownPath.addLast(warehouse.retrieve(11, 1, 0));
		knownPath.addLast(warehouse.retrieve(11, 2, 0));
		
		LinkedList<RawNode> foundPath = (LinkedList<RawNode>)algorithm.findPath(start, dest);
		
		System.out.println(Arrays.toString(foundPath.toArray(new RawNode[foundPath.size()])));
		
		assertEquals(knownPath, foundPath);
	}
}