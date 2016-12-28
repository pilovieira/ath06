package br.com.pilovieira.ath06.business;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import br.com.pilovieira.ath06.R;
import br.com.pilovieira.ath06.comm.SMSEmitter;
import br.com.pilovieira.ath06.log.ServerLogManager;

public class ListenerProvider {

    private View view;

    public ListenerProvider(View view){
        this.view = view;
    }

    public void simpleCommandListener(int id, final String command) {
        final Button button = (Button) view.findViewById(id);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                try {
                    SMSEmitter.emit(context, command);

                    log(context, button);
                } catch (Exception ex) {
                    Toast.makeText(context, ex.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void log(Context context, Button button) {
        String log = context.getString(R.string.message_command_confirmation, button.getText());
        new ServerLogManager(context).log(context.getString(R.string.log_name_command), log);
        Toast.makeText(context, log, Toast.LENGTH_SHORT).show();
    }
}
