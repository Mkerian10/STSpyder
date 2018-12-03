package com.showtimedev.server_side.server_management;

import com.showtimedev.shared.misc.utils.StreamUtils;

import java.io.*;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SocketHandler{
	
	public SocketHandler(){
		this.executor = Executors.newCachedThreadPool();
	}
	
	private final ExecutorService executor;
	
	void submit(Socket socket){
		Runnable r = () -> {
			try{
				BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				
				
				
				
			}catch(IOException e){
				e.printStackTrace();
			}
		};
		
		executor.submit(r);
	}
}
