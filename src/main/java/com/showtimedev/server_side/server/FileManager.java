package com.showtimedev.server_side.server;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class FileManager{
	
	public static final String ENV_PROD = "/prod";
	
	public static final String ENV_TEST = "/test";
	
	private static final String BASE_PATH = "data";
	
	private static final String PATH_RAW_NODES = "/raw_nodes";
	
	private static final String PATH_DISCRETE_NODES = "/discrete_nodes";
	
	private static final String PATH_DISCRETE_EDGES = "/discrete_edges";
	
	public FileManager(Environment env){
		this.filePath = env.path();
	}
	
	private String filePath;
	
	public InputStream getResource(Source src) throws FileNotFoundException{
		File f = new File(src.path(filePath));
		return new FileInputStream(f);
	}
	
	enum Environment{
		PROD(ENV_PROD),
		TEST(ENV_TEST);
		
		Environment(String ext){
			this.ext = ext;
		}
		
		private final String ext;
		
		private String path(){
			return BASE_PATH + ext;
		}
	}
	
	enum Source{
		RAW_NODES(PATH_RAW_NODES),
		DISCRETE_NODES(PATH_DISCRETE_NODES),
		DISCRETE_EDGES(PATH_DISCRETE_EDGES);
		
		Source(String ext){
			this.ext = ext;
		}
		
		private final String ext;
		
		private String path(String base){
			return base + ext;
		}
	}
	
}
