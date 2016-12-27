package br.com.pilovieira.cartracker.client.requisition;

import java.util.Collections;

import android.content.Context;

import br.com.pilovieira.cartracker.client.live.LiveTrackerResolver;
import br.com.pilovieira.cartracker.client.lock.LockResolver;
import br.com.pilovieira.ath06.log.InfoResolver;
import br.com.pilovieira.cartracker.core.config.sync.ApplySyncPreference;
import br.com.pilovieira.cartracker.core.tag.Tag;
import br.com.pilovieira.cartracker.core.tag.TagMessage;
import br.com.pilovieira.cartracker.core.tag.config.InfoResponse;
import br.com.pilovieira.cartracker.core.tag.config.SyncPreference;
import br.com.pilovieira.cartracker.core.tag.func.Localization;
import br.com.pilovieira.cartracker.core.tag.func.Lock;
import br.com.pilovieira.cartracker.core.tag.func.Wallet;
import br.com.pilovieira.cartracker.core.tag.misc.Status;
import br.com.pilovieira.cartracker.core.tag.service.LiveTracker;
import br.com.pilovieira.cartracker.core.tag.service.VirtualFence;
import br.com.pilovieira.sptagcomm.p2p.Emitter;
import br.com.pilovieira.sptagcomm.requisition.RequisitionProcessor;
import br.com.pilovieira.sptagcomm.requisition.RequisitionStore;


public class ResponseManager {
	
	private Context context;
	private RequisitionProcessor processor;
	
	static {
		RequisitionStore.register(Localization.class, GlobalLocationResolver.class);
		RequisitionStore.register(Wallet.class, WalletResolver.class);
		RequisitionStore.register(Lock.class, LockResolver.class);
		RequisitionStore.register(SyncPreference.class, ApplySyncPreference.class);
		RequisitionStore.register(InfoResponse.class, InfoResolver.class);
		RequisitionStore.register(Status.class, StatusResolver.class);
		RequisitionStore.register(LiveTracker.class, LiveTrackerResolver.class);
		RequisitionStore.register(VirtualFence.class, VirtualFenceResolver.class);
	}

	public ResponseManager(Context context) {
		this.context = context;
		processor = new RequisitionProcessor(Collections.<Emitter> emptyList());
	}
	
	public void processResponse(String tagMessage) {
		TagMessage message = Tag.newInstance(tagMessage);
		processor.process(context, message);
	}
}
