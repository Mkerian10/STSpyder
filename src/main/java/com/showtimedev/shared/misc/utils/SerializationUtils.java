package com.showtimedev.shared.misc.utils;

import lombok.Cleanup;

import javax.annotation.WillClose;
import java.io.*;

public class SerializationUtils{
	
	public static byte[] serialize(Serializable obj) throws IOException{
		@Cleanup ByteArrayOutputStream baos = new ByteArrayOutputStream();
		@Cleanup ObjectOutputStream outputStream = new ObjectOutputStream(baos);
		
		outputStream.writeObject(obj);
		
		return baos.toByteArray();
	}
	
	public static Object deserialize(byte[] bytes) throws IOException, ClassNotFoundException{
		@Cleanup ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
		return deserialize(bais);
	}
	
	public static Object deserialize(@WillClose InputStream stream) throws IOException, ClassNotFoundException{
		@Cleanup ObjectInputStream inputStream = new ObjectInputStream(stream);
		Object obj = inputStream.readObject();
		stream.close();
		return obj;
	}
}
