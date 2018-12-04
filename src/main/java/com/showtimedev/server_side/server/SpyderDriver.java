package com.showtimedev.server_side.server;

import com.showtimedev.server_side.nav.discrete_nav.DiscreteNodeWarehouse;
import com.showtimedev.server_side.nav.raw_nav.RawNodeWarehouse;
import lombok.Cleanup;

import java.io.IOException;
import java.io.InputStream;

public final class SpyderDriver{
	
	public static void main(String[] args){
		
		var manager = new FileManager(FileManager.Environment.TEST);
		initNodes(manager);
		
		SpyderServer spyderServer = new SpyderServer();
		spyderServer.setup();
		System.out.println("Server setup");
	}
	
	private static void initNodes(FileManager manager){
		try{
			@Cleanup InputStream rawNodes = manager.getResource(FileManager.Source.RAW_NODES);
			RawNodeWarehouse.getInstance().loadInto(rawNodes);
			@Cleanup InputStream dNodes = manager.getResource(FileManager.Source.DISCRETE_NODES);
			@Cleanup InputStream dEdges = manager.getResource(FileManager.Source.DISCRETE_EDGES);
			DiscreteNodeWarehouse.getInstance().loadInto(dNodes, dEdges);
			
		}catch(IOException e){
			e.printStackTrace();
			throw new RuntimeException("Failed to initialize warehouse");
		}
	}
	
}
