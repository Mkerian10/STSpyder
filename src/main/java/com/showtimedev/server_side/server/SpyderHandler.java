package com.showtimedev.server_side.server;

import com.showtimedev.server_side.nav.raw_nav.RawPathLoader;
import com.showtimedev.server_side.nav.web.WebResultLoader;
import com.showtimedev.server_side.utils.ServerUtils;
import com.showtimedev.shared.misc.WebResult;
import com.showtimedev.shared.misc.utils.SerializationUtils;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public final class SpyderHandler implements HttpHandler{
	
	public SpyderHandler(){
		this.executor = Executors.newCachedThreadPool();
		loader = new WebResultLoader(new RawPathLoader());
	}
	
	private final ExecutorService executor;
	
	private final WebResultLoader loader;
	
	@Override
	public void handle(HttpExchange exchange) throws IOException{
		System.out.println("New request");
		var putParemeters = ServerUtils.decodeRequest(exchange.getRequestBody());
		
		System.out.println(putParemeters);
		
		var pathfinder = new Pathfinder(loader, putParemeters);
		WebResult result = pathfinder.findPath();
		
		byte[] bytes = SerializationUtils.serialize(result);
		exchange.sendResponseHeaders(200, bytes.length);
		exchange.getResponseBody().write(bytes);
	}
}
