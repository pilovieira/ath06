package br.com.pilovieira.ath06.comm;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.telephony.SmsManager;
import android.widget.Toast;

import br.com.pilovieira.ath06.R;
import br.com.pilovieira.ath06.log.ServerLogManager;
import br.com.pilovieira.ath06.persist.Prefs;

public class SMSEmitter {

    private Context context;

    public SMSEmitter(Context context) {
        this.context = context;
    }

    public void emit(String commandName, String command) {
        try {
            emit(command);

            log(commandName, command);
        } catch (Exception ex) {
            Toast.makeText(context, ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

	private void emit(String message) {
        String trackerNumber = new Prefs(context).getTrackerNumber();

        if (trackerNumber.isEmpty())
            throw new RuntimeException(context.getString(R.string.msg_configure_tracker_number));

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setData(Uri.parse("smsto:" + trackerNumber));  // This ensures only SMS apps respond
        intent.putExtra("sms_body", message);
        context.startActivity(intent);
	}

    private void log(String commandName, String command) {
        String log = context.getString(R.string.message_command_confirmation, commandName, command);
        new ServerLogManager(context).log(context.getString(R.string.command), log);
    }

}
