package com.showtimedev.server_side.server_management;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SpyderServer{

	public static void main(String[] args){
	
		FileManager manager;
		
		ServerSocket spyderSocket = openSocket();
		SocketHandler handler = new SocketHandler();
		
		while(true){
			try{
				System.out.println("Waiting..");
				Socket conn = spyderSocket.accept();
				System.out.println("New socket successfully received!");
				handler.submit(conn);
			}catch(IOException e){
				e.printStackTrace(System.err);
				System.err.println("Failed to take in Socket Connection.");
			}
		}
	}
	
	private static ServerSocket openSocket(){
		try{
			return new ServerSocket(9000);
		}catch(IOException e){
			e.printStackTrace();
			throw new RuntimeException("Failed to open ServerSocket!");
		}
	}


}
