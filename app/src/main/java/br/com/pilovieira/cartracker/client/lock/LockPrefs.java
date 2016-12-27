package br.com.pilovieira.cartracker.client.lock;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import br.com.pilovieira.cartracker.client.R;

public class LockPrefs {

	private Context context;

	public LockPrefs(Context context) {
		this.context = context;
	}

	public SharedPreferences getSharedPrefs() {
		return PreferenceManager.getDefaultSharedPreferences(context);
	}

    public void setLocked(String message) {
        if (context.getString(R.string.lock_service_locked_message).equals(message))
            setLocked(true);
        if (context.getString(R.string.lock_service_unlocked_message).equals(message))
            setLocked(false);
    }

    private void setLocked(boolean locked) {
        SharedPreferences.Editor editor = getSharedPrefs().edit();
        editor.putBoolean(context.getString(R.string.PREF_LOCK_IS_LOCKED), locked);
        editor.apply();
        editor.commit();
    }

    public boolean isLocked() {
        return getSharedPrefs().getBoolean(context.getString(R.string.PREF_LOCK_IS_LOCKED), context.getResources().getBoolean(R.bool.PREF_LOCK_IS_LOCKED_DEFAULT));
    }
}
