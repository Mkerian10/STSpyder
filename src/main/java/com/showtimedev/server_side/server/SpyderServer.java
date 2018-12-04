package com.showtimedev.server_side.server;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class SpyderServer{
	
	void setup(){
		try{
			HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
			server.createContext("/");
			
			
			
			
			
		}catch(IOException e){
			e.printStackTrace();
			System.exit(1);
		}
	}
}
