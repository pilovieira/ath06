package br.com.pilovieira.cartracker.client.live;

import android.content.Context;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import br.com.pilovieira.ath06.persist.DaoManager;
import br.com.pilovieira.cartracker.core.tag.service.LiveTrackerPosition;

public class LiveTrackerPositionCache {

    private Context context;

    public LiveTrackerPositionCache(Context context){
        this.context = context;
    }

	public void addPosition(LiveTrackerPosition position) {
        DaoManager.persist(context, position);
	}

	public List<LiveTrackerPosition> getPositions() {
        List<LiveTrackerPosition> all = DaoManager.getAll(context, LiveTrackerPosition.class);
        Collections.sort(all);
        return all;
    }
	
	public void clearCache() {
        try {
            DaoManager.truncate(context, LiveTrackerPosition.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}

}
