package br.com.pilovieira.cartracker.client.live;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import br.com.pilovieira.cartracker.client.R;
import br.com.pilovieira.cartracker.client.utils.Alerts;
import br.com.pilovieira.cartracker.client.view.EnableButtonBuilder;
import br.com.pilovieira.cartracker.core.service.live.LiveTrackerPrefs;
import br.com.pilovieira.cartracker.core.tag.service.LiveTrackerPosition;


public class LiveTrackerFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_live_tracker, container, false);

        resolveBtnEnable(view);
        resolvePositionsCount(view);
        resolveBtnTraceMap(view);
        resolveBtnViewLog(view);
        resolveBtnClearLog(view);

        return view;
    }

    private void resolveBtnEnable(View view) {
        final Context context = view.getContext();
        final LiveTrackerPrefs preferences = new LiveTrackerPrefs(context);

        Button button = (Button) view.findViewById(R.id.fragment_live_btn_enable);

        EnableButtonBuilder.EnableButtonListener listener = new EnableButtonBuilder.EnableButtonListener() {
            @Override
            public void onEnable() {
                preferences.enableService();
                alert(context, R.string.fragment_live_tracker_is_now_enabled);
            }

            @Override
            public void onDisable() {
                preferences.disableService();
                alert(context, R.string.fragment_live_tracker_is_now_disabled);
            }
        };

        new EnableButtonBuilder(button, listener, preferences.isEnabled()).build();
    }

    private void resolvePositionsCount(View view) {
        List<LiveTrackerPosition> positions = new LiveTrackerPositionCache(view.getContext()).getPositions();

        if (!positions.isEmpty()) {
            TextView textPositions = (TextView) view.findViewById(R.id.fragment_live_text_positions);
            textPositions.setText(getString(R.string.fragment_live_text_positions_count, positions.size(), positions.get(positions.size() - 1).toString()));
        }
    }

    private void resolveBtnTraceMap(View view) {
        Button button = (Button) view.findViewById(R.id.fragment_live_btn_trace_map);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.getContext().startActivity(new Intent(view.getContext(), LiveMapsActivity.class));
            }
        });
    }

    private void resolveBtnViewLog(View view) {
        Button button;
        button = (Button) view.findViewById(R.id.fragment_live_btn_view_log);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<LiveTrackerPosition> positions = new LiveTrackerPositionCache(view.getContext()).getPositions();
                new Alerts(getContext()).showAlert(getString(R.string.fragment_live_log_history), positions.toString().replace("[", "").replace("]", "").replace(", ", "\r\n\r\n"));
            }
        });
    }

    private void resolveBtnClearLog(View view) {
        Button button;
        button = (Button) view.findViewById(R.id.fragment_live_btn_clear_log);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                new LiveTrackerPositionCache(context).clearCache();
                alert(context, R.string.fragment_live_tracker_clear_log_message);
            }
        });
    }

    private void alert(Context context, int message) {
        new Alerts(context).showAlert(getString(R.string.activity_main_drawer_live_tracker), getString(message));
    }

}
