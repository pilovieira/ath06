package br.com.pilovieira.ath06.comm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

public abstract class SMSReceiver extends BroadcastReceiver implements Receiver {

	private SMSPrefs preferences;

	@Override
	public void onReceive(Context context, Intent intent) {
		try {
			this.preferences = new SMSPrefs(context);
			
			Bundle extras = intent.getExtras();
			if (extras == null)
				return;
			
			SmsMessage sms = SmsMessage.createFromPdu((byte[]) ((Object[]) extras.get("pdus"))[0]);
			
			onReceive(context, sms.getOriginatingAddress(), sms.getMessageBody());
			
			if (isAbortBroadcast())
				abortBroadcast();
		} catch (Exception e) {
			Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG);
		}
	}

	private boolean isAbortBroadcast() {
		return preferences.isAbortBroadcast();
	}
}
