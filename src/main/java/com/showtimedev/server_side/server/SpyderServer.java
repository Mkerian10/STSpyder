package com.showtimedev.server_side.server;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

public final class SpyderServer{
	
	void setup(){
		try{
			HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
			server.createContext("/", new SpyderHandler());
			server.setExecutor(Executors.newCachedThreadPool());
			server.start();
			
		}catch(IOException e){
			e.printStackTrace();
			System.exit(1);
		}
	}
}
