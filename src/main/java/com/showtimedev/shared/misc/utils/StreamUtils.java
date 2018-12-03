package com.showtimedev.shared.misc.utils;

import java.io.*;
import java.nio.ByteBuffer;

public class StreamUtils{
	
	public static String readInputStream(InputStream stream) throws IOException{
		return new String(stream.readAllBytes());
	}
	
	public static void sendOutputStream(OutputStream steam, String data) throws IOException{
		steam.write(data.getBytes());
	}
}
