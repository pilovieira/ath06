package br.com.pilovieira.cartracker.client.view;

import android.content.Context;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.EditTextPreference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceCategory;
import android.preference.PreferenceScreen;
import android.support.annotation.NonNull;

import br.com.pilovieira.cartracker.client.R;
import br.com.pilovieira.cartracker.core.service.fence.VirtualFenceScreen;
import br.com.pilovieira.cartracker.core.service.live.LiveTrackerScreen;

@SuppressWarnings("deprecation")
public class OptionsActivity extends PreferenceActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		PreferenceScreen mainScreen = getPreferenceManager().createPreferenceScreen(this);

		PreferenceCategory numberCategory = new PreferenceCategory(this);
		numberCategory.setTitle(R.string.preference_number_category_title);
		mainScreen.addPreference(numberCategory);
		numberCategory.addPreference(createTrackerNumberPreference(this));

        PreferenceCategory serviceCategory = new PreferenceCategory(this);  
        serviceCategory.setTitle(R.string.preference_services_category_title);
        mainScreen.addPreference(serviceCategory);
        
        serviceCategory.addPreference(LiveTrackerScreen.create(this, getPreferenceManager()));
        serviceCategory.addPreference(VirtualFenceScreen.create(this, getPreferenceManager()));

		PreferenceCategory miscCategory = new PreferenceCategory(this);
		miscCategory.setTitle(R.string.preference_misc_category_title);
		mainScreen.addPreference(miscCategory);
        miscCategory.addPreference(createAbortBroadcastPreference(this));

		setPreferenceScreen(mainScreen);
	}

	@NonNull
	private static EditTextPreference createTrackerNumberPreference(Context context) {
		EditTextPreference pref = new EditTextPreference(context);
		pref.setKey(context.getString(R.string.PREF_TRACKER_NUMBER));
		pref.setTitle(R.string.preference_tracker_number_title);
		pref.setSummary(R.string.preference_tracker_number_summary);
		return pref;
	}

    @NonNull
    private static CheckBoxPreference createAbortBroadcastPreference(Context context) {
        CheckBoxPreference pref = new CheckBoxPreference(context);
        pref.setKey(context.getString(R.string.PREF_ABORT_BROADCAST));
        pref.setTitle(R.string.preference_abort_broadcast_title);
        pref.setDefaultValue(context.getResources().getBoolean(R.bool.PREF_ABORT_BROADCAST_DEFAULT));
        return pref;
    }

}
