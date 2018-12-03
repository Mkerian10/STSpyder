package com.showtimedev.server_side.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SerializationUtils{
	
	public static<T> T returnSerialized(T obj){
		try(var baos = new ByteArrayOutputStream(); var outputStream = new ObjectOutputStream(baos)){
			outputStream.writeObject(obj);
			var bytes = baos.toByteArray();
			
			var bais = new ByteArrayInputStream(bytes);
			var inputStream = new ObjectInputStream(bais);
			
			return (T)inputStream.readObject();
			
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
}
