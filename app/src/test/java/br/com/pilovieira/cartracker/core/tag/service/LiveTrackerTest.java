package br.com.pilovieira.cartracker.core.tag.service;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import junit.framework.Assert;

import org.junit.Test;

import br.com.pilovieira.cartracker.core.tag.Tag;

public class LiveTrackerTest {

	private static final float SPEED = 33;
	private static final double LONGITUDE = 25.56624;
	private static final double LATITUDE = 52.51154;

	@Test
	public void toTagMessage() throws ParseException {
		LiveTracker message = new LiveTracker();
		
		LiveTrackerPosition position = new LiveTrackerPosition(dateValue(), LATITUDE, LONGITUDE, SPEED);
		message.setPosition(position);
		
		assertEquals("LIVE_TRACKER#06062016000000#52.51154#25.56624#33.0", message.getValue());
	}

	@Test
	public void fromTagMessage() throws ParseException {
		LiveTracker message = (LiveTracker) Tag.newInstance("LIVE_TRACKER#06062016000000#52.51154#25.56624#33.0");
		
		LiveTrackerPosition position = message.getPosition();
		
		Assert.assertNotNull(position);
		assertEquals(dateValue(), position.getDate());
		assertEquals(LATITUDE, position.getLatitude(), 0);
		assertEquals(LONGITUDE, position.getLongitude(), 0);
		assertEquals(SPEED, position.getSpeed(), 0);
	}
	
	private Date dateValue() throws ParseException {
		return new SimpleDateFormat("dd/MM/yyyy").parse("06/06/2016");
	}
	
	

}
