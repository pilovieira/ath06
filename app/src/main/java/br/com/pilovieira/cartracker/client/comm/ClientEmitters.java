package br.com.pilovieira.cartracker.client.comm;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;

import br.com.pilovieira.ath06.comm.Message;
import br.com.pilovieira.ath06.persist.ClientPrefs;
import br.com.pilovieira.sptagcomm.p2p.Emitter;
import br.com.pilovieira.ath06.comm.SMSEmitter;

public class ClientEmitters {
	
	private ClientPrefs clientPreferences;

	public ClientEmitters(Context context) {
		clientPreferences = new ClientPrefs(context);
	}
	
	public void emit(Message message) {
		for (Emitter emitter : getEmitters())
			emitter.emit(message);
	}

	private List<Emitter> getEmitters() {
		List<Emitter> emitters = new ArrayList<Emitter>();
		
		Emitter trackerEmitter = getTrackerEmitter();
		if (trackerEmitter != null)
			emitters.add(trackerEmitter);
		
		return emitters;
	}

	public Emitter getTrackerEmitter() {
		if (clientPreferences.getTrackerNumber() == null)
			return null;
		
		return new SMSEmitter(clientPreferences.getTrackerNumber());
	}
}
