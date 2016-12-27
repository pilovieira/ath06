package br.com.pilovieira.cartracker.client.requisition;

import android.content.Intent;
import android.net.Uri;

import br.com.pilovieira.cartracker.client.R;
import br.com.pilovieira.ath06.log.ServerLogManager;
import br.com.pilovieira.cartracker.client.utils.NotificationBuilder;
import br.com.pilovieira.cartracker.core.tag.func.Localization;
import br.com.pilovieira.ath06.comm.Message;
import br.com.pilovieira.sptagcomm.requisition.Requisition;

import static android.content.Intent.ACTION_VIEW;

public class GlobalLocationResolver extends Requisition<Localization> {

	private static String uri = "geo:%s,%s?q=%s";
	
	@Override
	public Message process() {
		NotificationBuilder builder = new NotificationBuilder(context);
		builder.setId(R.id.nav_get_location);
		builder.setTitle(context.getString(R.string.requisition_resolver_location_title));
		builder.setText(context.getString(R.string.requisition_resolver_location_text));

        String uriCompleta = String.format(uri, request.getLatitude(), request.getLongitude(), getQuery());
        new ServerLogManager(context).log(context.getString(R.string.fragment_info_location_title), uriCompleta);

		Intent intent = new Intent(ACTION_VIEW,	Uri.parse(uriCompleta));
		builder.setResult(intent);

		builder.build();
		return null;
	}

	private String getQuery() {
		String query = "%s,%s Car";
		return String.format(query, request.getLatitude(), request.getLongitude());
	}
}
