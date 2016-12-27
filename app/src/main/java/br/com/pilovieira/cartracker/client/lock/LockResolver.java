package br.com.pilovieira.cartracker.client.lock;

import android.content.Intent;

import br.com.pilovieira.cartracker.client.R;
import br.com.pilovieira.ath06.log.ServerLogManager;
import br.com.pilovieira.cartracker.client.utils.NotificationBuilder;
import br.com.pilovieira.ath06.view.MainActivity;
import br.com.pilovieira.cartracker.core.tag.func.Lock;
import br.com.pilovieira.ath06.comm.Message;
import br.com.pilovieira.sptagcomm.requisition.Requisition;

public class LockResolver extends Requisition<Lock> {

	@Override
	public Message process() {
		new LockPrefs(context).setLocked(request.getMessage());
		new ServerLogManager(context).log(context.getString(R.string.fragment_info_lock_title), request.getMessage());

		NotificationBuilder builder = new NotificationBuilder(context);
		builder.setId(R.id.nav_lock);
		builder.setTitle(context.getString(R.string.requisition_resolver_lock_title));

		Intent intent = new Intent(context, MainActivity.class);
		intent.putExtra(MainActivity.KEY_FRAGMENT, R.id.nav_lock);
		builder.setResult(intent);

		builder.build();
		return null;
	}
}
