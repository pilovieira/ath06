package br.com.pilovieira.cartracker.core.config;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.LinkedHashSet;
import java.util.Set;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class StringSetStrategyV10CompatTest {

	private static final String BACATE_BANANA_BACAXI = "Bacate, Banana, Bacaxi";
	private static final String KEY = "key";

	@Mock private SharedPreferences sharedPreferences;
	@Mock private Editor editor;

	private StringSetStrategyV10Compat subject;

	
	@Before
	public void setup() {
		when(sharedPreferences.edit()).thenReturn(editor);

		subject = new StringSetStrategyV10Compat(sharedPreferences);
	}
	
	@Test
	public void putSet() {
		Set<String> set = new LinkedHashSet<String>();
		set.add("Bacate");
		set.add("Banana");
		set.add("Bacaxi");
		
		subject.putStringSet(KEY, set);
		
		verify(sharedPreferences).edit();
		verify(editor).putString(KEY, BACATE_BANANA_BACAXI);
		verify(editor).commit();
	}

	@Test
	public void getSet() {
		when(sharedPreferences.getString(KEY, null)).thenReturn(BACATE_BANANA_BACAXI);
		
		Set<String> stringSet = subject.getStringSet(KEY, null);
		
		assertEquals("Quantity of strings",  3, stringSet.size());
		
		verify(sharedPreferences).getString(KEY, null);
	}

}
