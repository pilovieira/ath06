package br.com.pilovieira.cartracker.client.requisition;

import android.content.Intent;

import br.com.pilovieira.cartracker.client.R;
import br.com.pilovieira.ath06.log.ServerLogManager;
import br.com.pilovieira.cartracker.client.utils.NotificationBuilder;
import br.com.pilovieira.ath06.view.MainActivity;
import br.com.pilovieira.cartracker.core.tag.misc.Status;
import br.com.pilovieira.ath06.comm.Message;
import br.com.pilovieira.sptagcomm.requisition.Requisition;

public class StatusResolver extends Requisition<Status> {

	@Override
	public Message process() {
		new ServerLogManager(context).log(null, request.getMessage());

		NotificationBuilder builder = new NotificationBuilder(context);
		builder.setId(R.id.nav_get_status);
		builder.setTitle(context.getString(R.string.requisition_resolver_status_title));

		Intent intent = new Intent(context, MainActivity.class);
		intent.putExtra(MainActivity.KEY_FRAGMENT, R.id.nav_info);
		builder.setResult(intent);

		builder.build();
		return null;
	}
}
