package br.com.pilovieira.ath06.view;


import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.com.pilovieira.ath06.R;
import br.com.pilovieira.ath06.business.H06Commands;
import br.com.pilovieira.ath06.business.ListenerProvider;

public class OperationsFragment extends Fragment {

    private H06Commands commands = new H06Commands();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_operations, container, false);

        ListenerProvider.emitCommandListener(view, R.id.btn_get_location, commands.getLocation());
        ListenerProvider.emitCommandListener(view, R.id.btn_lock_vehicle, commands.lockVehicle());
        ListenerProvider.emitCommandListener(view, R.id.btn_unlock_vehicle, commands.unlockVehicle());
        ListenerProvider.emitCommandListener(view, R.id.btn_fw_version, commands.firmwareVersion());
        ListenerProvider.emitCommandListener(view, R.id.btn_tracker_info, commands.trackerInfo());
        ListenerProvider.emitCommandListener(view, R.id.btn_gps_restart, commands.gpsRestart());
        ListenerProvider.emitCommandListener(view, R.id.btn_tracker_restart, commands.trackerRestart());

        return view;
    }

}
