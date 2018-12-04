package com.showtimedev.server_side.utils;

import com.showtimedev.shared.misc.utils.StreamUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class ServerUtils{
	
	public static Map<String, byte[]> decodeRequest(InputStream stream) throws IOException{
		var map = new HashMap<String, byte[]>();
		
		String str = StreamUtils.readInputStream(stream);
		String[] sections = str.split("&");
		
	}
}
