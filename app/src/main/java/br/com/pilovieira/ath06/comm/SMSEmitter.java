package br.com.pilovieira.ath06.comm;

import android.content.Context;
import android.telephony.SmsManager;

import br.com.pilovieira.ath06.R;
import br.com.pilovieira.ath06.persist.Prefs;

public class SMSEmitter {
	
	private Prefs prefs;
    private Context context;

    public SMSEmitter(Context context) {
		this.prefs = new Prefs(context);
        this.context = context;
    }
	
	public void emit(String message) {
        String trackerNumber = this.prefs.getTrackerNumber();
        if (trackerNumber == null)
            throw new RuntimeException(context.getString(R.string.msg_configure_tracker_number));

        SmsManager.getDefault().sendTextMessage(trackerNumber, null, message, null, null);
	}

}
