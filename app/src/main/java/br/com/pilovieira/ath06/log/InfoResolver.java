package br.com.pilovieira.ath06.log;

import android.content.Intent;

import br.com.pilovieira.cartracker.client.R;
import br.com.pilovieira.cartracker.client.utils.NotificationBuilder;
import br.com.pilovieira.ath06.view.MainActivity;
import br.com.pilovieira.cartracker.core.tag.config.InfoResponse;
import br.com.pilovieira.ath06.comm.Message;
import br.com.pilovieira.sptagcomm.requisition.Requisition;

public class InfoResolver extends Requisition<InfoResponse> {

	@Override
	public Message process() {
		new ServerLogManager(context).log(context.getString(R.string.fragment_info_info_title), request.getMessage());

		NotificationBuilder builder = new NotificationBuilder(context);
		builder.setId(R.id.nav_info);
		builder.setTitle(context.getString(R.string.requisition_resolver_info_title));

		Intent intent = new Intent(context, MainActivity.class);
		intent.putExtra(MainActivity.KEY_FRAGMENT, R.id.nav_info);
		builder.setResult(intent);

		builder.build();
		return null;
	}
}
