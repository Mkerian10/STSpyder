package com.showtimedev.server_side.server;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SpyderHandler implements HttpHandler{
	
	public SpyderHandler(){
		this.executor = Executors.newCachedThreadPool();
	}
	
	private final ExecutorService executor;
	
	@Override
	public void handle(HttpExchange exchange) throws IOException{
	
	}
}
