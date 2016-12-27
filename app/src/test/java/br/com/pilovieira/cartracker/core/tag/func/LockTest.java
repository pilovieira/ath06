package br.com.pilovieira.cartracker.core.tag.func;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import br.com.pilovieira.cartracker.core.tag.Tag;

public class LockTest {

	@Test
	public void toTagMessage() {
		Lock message = new Lock();
		message.setCommand(LockCommand.Lock);
		
		assertEquals("LOCK#Lock#", message.getValue());
	}

	@Test
	public void fromTagMessage() {
		Lock message = (Lock) Tag.newInstance("LOCK#Unlock#Yep");
		
		assertEquals(LockCommand.Unlock, message.getCommand());
		assertEquals("Yep", message.getMessage());
	}

}
