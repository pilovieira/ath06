package br.com.pilovieira.cartracker.client.live;

import android.content.Intent;

import br.com.pilovieira.cartracker.client.R;
import br.com.pilovieira.cartracker.client.utils.NotificationBuilder;
import br.com.pilovieira.ath06.view.MainActivity;
import br.com.pilovieira.cartracker.core.tag.service.LiveTracker;
import br.com.pilovieira.cartracker.core.tag.service.LiveTrackerPosition;
import br.com.pilovieira.ath06.comm.Message;
import br.com.pilovieira.sptagcomm.requisition.Requisition;

public class LiveTrackerResolver extends Requisition<LiveTracker> {

	@Override
	protected Message process() {
		LiveTrackerPosition position = request.getPosition();
		new LiveTrackerPositionCache(context).addPosition(position);

		NotificationBuilder builder = new NotificationBuilder(context);
		builder.setId(R.id.nav_live);
		builder.setTitle(context.getString(R.string.requisition_resolver_livetracker_title));
		builder.setText(context.getString(R.string.requisition_resolver_livetracker_text));

		Intent intent = new Intent(context, MainActivity.class);
		intent.putExtra(MainActivity.KEY_FRAGMENT, R.id.nav_live);
		builder.setResult(intent);

		builder.build();
		return null;
	}

}
