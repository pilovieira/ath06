package br.com.pilovieira.cartracker.client.lock;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import br.com.pilovieira.cartracker.client.R;
import br.com.pilovieira.cartracker.client.comm.ClientEmitters;
import br.com.pilovieira.cartracker.client.utils.Alerts;
import br.com.pilovieira.cartracker.client.view.EnableButtonBuilder;
import br.com.pilovieira.cartracker.core.tag.func.Lock;
import br.com.pilovieira.cartracker.core.tag.func.LockCommand;


public class LockFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lock, container, false);

        refreshLockButton(view);

        Button button = (Button) view.findViewById(R.id.lock_fragment_btn_lock);
        button.setOnClickListener(lockListener(LockCommand.Lock));

        button = (Button) view.findViewById(R.id.lock_fragment_btn_unlock);
        button.setOnClickListener(lockListener(LockCommand.Unlock));

        return view;
    }

    private void refreshLockButton(View view) {
        boolean isLocked = new LockPrefs(getContext()).isLocked();
        Button button = (Button) view.findViewById(R.id.fragment_lock_btn_last_status);

        button.setText(isLocked ? R.string.fragment_lock_is_locked : R.string.fragment_lock_is_unlocked);
        button.setBackgroundColor(button.getResources().getColor(isLocked ? R.color.button_module_locked : R.color.button_module_unlocked));
    }

    private View.OnClickListener lockListener(LockCommand command) {
        final Lock lockRequest = new Lock();
        lockRequest.setCommand(command);

        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Alerts alerts = new Alerts(view.getContext());

                try {
                    new ClientEmitters(view.getContext()).emit(lockRequest);
                    alerts.showAlert(getString(R.string.lock_fragment_lock_module), getString(R.string.fragment_lock_command_has_sent, lockRequest.getCommand().name()));
                } catch (Exception e) {
                    alerts.showErrorAlert(e.getMessage());
                }
            }
        };
    }

}
