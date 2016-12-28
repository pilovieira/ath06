package br.com.pilovieira.ath06.view;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import br.com.pilovieira.ath06.R;
import br.com.pilovieira.ath06.persist.Prefs;

public class ConfigurationsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_configurations, container, false);

        resolveTrackerNumberEditText(view);

        return view;
    }

    private void resolveTrackerNumberEditText(View view) {
        final Prefs prefs = new Prefs(view.getContext());
        final EditText textView = (EditText) view.findViewById(R.id.text_tracker_number);
        textView.setText(prefs.getTrackerNumber());

        textView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                prefs.setTrackerNumber(((EditText)view).getText().toString());
                return false;
            }
        });
    }

}
