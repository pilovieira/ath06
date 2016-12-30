package br.com.pilovieira.ath06.view;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.com.pilovieira.ath06.R;
import br.com.pilovieira.ath06.log.ServerLog;
import br.com.pilovieira.ath06.log.ServerLogManager;

public class UserManualFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_manual, container, false);
        TextView textUserManual = (TextView) view.findViewById(R.id.text_user_manual);

        textUserManual.setText(getString(R.string.user_manual_literal));

        return view;
    }

}
