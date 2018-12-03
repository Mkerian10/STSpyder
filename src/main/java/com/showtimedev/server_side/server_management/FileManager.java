package com.showtimedev.server_side.server_management;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class FileManager{
	
	private static final String NODE_FILE_NAME = "PLACEHOLDER";
	
	public InputStream nodeInputStream(){
		File f = new File(NODE_FILE_NAME);
		try{
			return new FileInputStream(f);
		}catch(FileNotFoundException e){
			e.printStackTrace();
			throw new RuntimeException("File not found exception! Missing Node File. Name: " + NODE_FILE_NAME);
		}
	}
	
}
