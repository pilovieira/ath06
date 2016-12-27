package br.com.pilovieira.cartracker.client.view;

import android.view.View;
import android.widget.Button;

import br.com.pilovieira.cartracker.client.R;

public class EnableButtonBuilder {

    private final Button button;
    private final EnableButtonListener listener;
    private boolean enabled;

    public EnableButtonBuilder(Button button, EnableButtonListener listener, boolean enabled) {
        this.button = button;
        this.listener = listener;
        this.enabled = enabled;
    }

    public void build() {
        button.setText(enabled ? R.string.button_enabled_click_to_disable : R.string.button_disabled_click_to_enable);
        button.setBackgroundColor(button.getResources().getColor(enabled ? R.color.button_service_enabled : R.color.button_service_disabled));

        button.setOnClickListener( enabled ?
            new View.OnClickListener() { @Override public void onClick(View view) {
                listener.onDisable();
                refreshStatus();
            }} :
            new View.OnClickListener() { @Override public void onClick(View view) {
                listener.onEnable();
                refreshStatus();
            }}
        );
    }

    private void refreshStatus() {
        enabled = !enabled;
        build();
    }

    public interface EnableButtonListener {
        void onEnable();
        void onDisable();
    }
}
