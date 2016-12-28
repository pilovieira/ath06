package br.com.pilovieira.ath06.comm;

import android.content.Context;
import android.telephony.SmsManager;

import br.com.pilovieira.ath06.R;
import br.com.pilovieira.ath06.persist.Prefs;

public class SMSEmitter {
	
	public static void emit(Context context, String message) {
        String trackerNumber = new Prefs(context).getTrackerNumber();

        if (trackerNumber.isEmpty())
            throw new RuntimeException(context.getString(R.string.msg_configure_tracker_number));

        SmsManager.getDefault().sendTextMessage(trackerNumber, null, message, null, null);
	}

}
