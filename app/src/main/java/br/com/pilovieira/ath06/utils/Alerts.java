package br.com.pilovieira.ath06.utils;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;

import br.com.pilovieira.ath06.R;

public class Alerts {
	
	private Context context;

	public Alerts(Context context) {
		this.context = context;
	}

	public void showErrorAlert(String message) {
		showAlert(context.getString(R.string.alert_error), message);
	}
	
	public void showAlert(String title, String message) {
		Builder alert = new AlertDialog.Builder(context);
		alert.setTitle(title);
		alert.setMessage(message);
		alert.setPositiveButton("OK", null);
		alert.show();
	}
	
}
