package br.com.pilovieira.cartracker.client.view;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import br.com.pilovieira.cartracker.client.R;
import br.com.pilovieira.cartracker.client.utils.Alerts;
import br.com.pilovieira.cartracker.core.service.fence.VirtualFencePrefs;


public class VirtualFenceFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_virtual_fence, container, false);

        resolveBtnEnable(view);
        resolveBtnConfigureRadius(view);

        return view;
    }

    private void resolveBtnEnable(View view) {
        final Context context = view.getContext();
        final VirtualFencePrefs preferences = new VirtualFencePrefs(context);

        Button button = (Button) view.findViewById(R.id.fragment_virtual_fence_btn_enable);

        EnableButtonBuilder.EnableButtonListener listener = new EnableButtonBuilder.EnableButtonListener() {
            @Override
            public void onEnable() {
                preferences.enableService();
                alert(context, R.string.fragment_virtual_fence_is_now_enabled);
            }

            @Override
            public void onDisable() {
                preferences.disableService();
                alert(context, R.string.fragment_virtual_fence_is_now_disabled);
            }
        };

        new EnableButtonBuilder(button, listener, preferences.isEnabled()).build();
    }

    private void resolveBtnConfigureRadius(View view) {
        Button button = (Button) view.findViewById(R.id.fragment_virtual_fence_distance_radius);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO open preference de configurar Radius;
                new Alerts(view.getContext()).showAlert(getString(R.string.activity_main_drawer_live_tracker), "Unavaliable for now!");
            }
        });
    }

    private void alert(Context context, int message) {
        new Alerts(context).showAlert(getString(R.string.activity_main_drawer_live_tracker), getString(message));
    }

}
