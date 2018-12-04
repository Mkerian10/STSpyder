package com.showtimedev.server_side.server;

import com.showtimedev.server_side.nav.web.WebResultLoader;
import com.showtimedev.shared.misc.PlayerConfig;
import com.showtimedev.shared.misc.WebResult;
import com.showtimedev.shared.misc.WebResultBuilder;
import com.showtimedev.shared.misc.WebTile;
import com.showtimedev.shared.misc.utils.SerializationUtils;

import javax.annotation.Nullable;
import java.io.IOException;
import java.util.Map;

public final class Pathfinder{
	
	public final static String PARAM_CONFIG = "config";
	
	public final static String PARAM_SRC = "source";
	
	public final static String PARAM_DEST = "dest";
	
	public Pathfinder(WebResultLoader loader, Map<String, byte[]> parameters){
		this.loader = loader;
		this.parameters = parameters;
	}
	
	private final WebResultLoader loader;
	
	
	private final Map<String, byte[]> parameters;
	
	public WebResult findPath(){
		if(!validateParemeters()){
			return pathNotFound();
		}
		
		return getResult();
	}
	
	private WebResult getResult(){
		WebResult res = loadResult();
		return res == null ? pathNotFound(): res;
	}
	
	@Nullable
	private WebResult loadResult(){
		PlayerConfig config;
		WebTile src, dest;
		try{
			config = parseConfig(parameters.get(PARAM_CONFIG));
			src = source(parameters.get(PARAM_SRC));
			dest = source(parameters.get(PARAM_DEST));
		}catch(IOException | ClassNotFoundException e){
			e.printStackTrace();
			return null;
		}
		
		return loader.load(config, src, dest);
	}
	
	private PlayerConfig parseConfig(byte[] bytes) throws IOException, ClassNotFoundException, ClassCastException{
		return (PlayerConfig) SerializationUtils.deserialize(bytes);
	}
	
	private WebTile source(byte[] bytes) throws IOException, ClassNotFoundException, ClassCastException{
		return (WebTile) SerializationUtils.deserialize(bytes);
	}
	
	private WebTile dest(byte[] bytes) throws IOException, ClassNotFoundException, ClassCastException{
		return (WebTile) SerializationUtils.deserialize(bytes);
	}
	
	private WebResult pathNotFound(){
		return WebResultBuilder.builder().setPathFound(false).build();
	}
	
	private boolean validateParemeters(){
		return parameters.containsKey(PARAM_CONFIG)
				&& parameters.containsKey(PARAM_DEST)
				&& parameters.containsKey(PARAM_SRC);
	}
	
	
}
