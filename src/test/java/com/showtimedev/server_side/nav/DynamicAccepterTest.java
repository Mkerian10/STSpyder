package com.showtimedev.server_side.nav;

import com.showtimedev.server_side.utils.SerializationUtils;
import com.showtimedev.shared.misc.PlayerConfig;
import org.junit.Test;

import static org.junit.Assert.*;

public class DynamicAccepterTest{
	
	@Test
	public void dynamicAccepterSerialization(){
		var successConfig = new PlayerConfig(9, 3);
		var failConfig = new PlayerConfig(1, 5);
		
		var accepter = (DynamicAccepter) config -> config.getAgilityLevel() > 5;
		
		var second = SerializationUtils.returnSerialized(accepter);
		
		assertTrue(accepter.accept(successConfig));
		assertFalse(accepter.accept(failConfig));
		assertTrue(second.accept(successConfig));
		assertFalse(second.accept(failConfig));
		
	}
	
}