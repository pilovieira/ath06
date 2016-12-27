package br.com.pilovieira.ath06.persist;

import android.content.Context;

import br.com.pilovieira.ath06.R;
import br.com.pilovieira.cartracker.core.config.CorePrefs;

public class ClientPrefs extends CorePrefs {
	
	public ClientPrefs(Context context) {
		super(context);
	}
	
	public String getTrackerNumber() {
		return getSharedPreferences().getString(context.getString(R.string.PREF_TRACKER_NUMBER), null);
	}
}
