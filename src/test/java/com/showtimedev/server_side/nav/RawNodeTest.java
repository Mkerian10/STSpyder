package com.showtimedev.server_side.nav;

import com.showtimedev.server_side.nav.raw_nav.RawNode;
import org.junit.Test;

import java.io.*;

import static org.junit.Assert.*;

public class RawNodeTest{
	
	@Test
	public void serializeableTest() throws IOException, ClassNotFoundException{
		
		RawNode rn = new RawNode(3, 4, 1, RawNode.N);
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ObjectOutputStream outputStream = new ObjectOutputStream(baos);
		
		outputStream.writeObject(rn);
		
		byte[] bytes = baos.toByteArray();
		
		ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
		ObjectInputStream inputStream = new ObjectInputStream(bais);
		
		RawNode output = (RawNode)inputStream.readObject();
		
		assertEquals(output, rn);
		
		
	}
	
}