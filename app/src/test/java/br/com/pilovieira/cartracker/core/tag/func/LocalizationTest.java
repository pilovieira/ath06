package br.com.pilovieira.cartracker.core.tag.func;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import br.com.pilovieira.cartracker.core.tag.Tag;

public class LocalizationTest {

	@Test
	public void toTagMessage() {
		Localization message = new Localization();
		
		assertEquals("LOCALIZATION#0.0#0.0", message.getValue());
	}

	@Test
	public void fromTagMessage() {
		Localization message = (Localization) Tag.newInstance("LOCALIZATION#52.65244#25.63225");
		
		
		assertEquals(52.65244, message.getLatitude(), 0);
		assertEquals(25.63225, message.getLongitude(), 0);
	}

}
