package br.com.pilovieira.ath06.persist;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import br.com.pilovieira.ath06.R;

public class Prefs {

	private Context context;

	public Prefs(Context context) {
		this.context = context;
	}
	
	public String getTrackerNumber() {
		return getSharedPreferences().getString(context.getString(R.string.PREF_TRACKER_NUMBER), null);
	}

	private SharedPreferences getSharedPreferences() {
		return PreferenceManager.getDefaultSharedPreferences(this.context);
	}
}
