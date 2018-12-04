package com.showtimedev.server_side.utils;

import com.showtimedev.shared.misc.utils.StreamUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public final class ServerUtils{
	
	public static Map<String, byte[]> decodeRequest(InputStream stream) throws IOException{
		var map = new HashMap<String, byte[]>();
		
		var str = StreamUtils.readInputStream(stream);
		var sections = str.split("&");
		
		for(var s: sections){
			var subsections = s.split("=");
			if(subsections.length != 2) continue;
			
			map.put(subsections[0], subsections[1].getBytes());
		}
		return map;
	}
}
