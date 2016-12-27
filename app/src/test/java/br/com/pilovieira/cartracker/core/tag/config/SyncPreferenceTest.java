package br.com.pilovieira.cartracker.core.tag.config;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import br.com.pilovieira.cartracker.core.tag.Tag;

public class SyncPreferenceTest {

	private static final String KEY = "key";
	private static final String VALUE = "value";

	@Test
	public void toTagMessage() {
		SyncPreference message = new SyncPreference();
		message.setPrefKey(KEY);
		message.setPrefValue(VALUE);
		
		assertEquals("SYNC_PREFERENCE#key#value", message.getValue());
	}

	@Test
	public void fromTagMessage() {
		SyncPreference message = (SyncPreference) Tag.newInstance("SYNC_PREFERENCE#key#value");
		
		assertEquals(KEY, message.getPrefKey());
		assertEquals(VALUE, message.getPrefValue());
	}

}
