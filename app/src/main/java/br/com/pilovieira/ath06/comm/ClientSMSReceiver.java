package br.com.pilovieira.ath06.comm;

import android.content.Context;

import br.com.pilovieira.ath06.persist.ClientPrefs;
import br.com.pilovieira.cartracker.client.requisition.ResponseManager;

public class ClientSMSReceiver extends SMSReceiver {

	private ClientPrefs preferences;

	@Override
	public void onReceive(Context context, String sender, String message) {
		this.preferences = new ClientPrefs(context);
		
		if (!isTrackerMessage(sender))
			return;
		
		new ResponseManager(context).processResponse(message);
	}

	private boolean isTrackerMessage(String sender) {
		String primeNumber = preferences.getTrackerNumber();
		return sender.contains(primeNumber);
	}
}
