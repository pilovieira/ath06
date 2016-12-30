package br.com.pilovieira.ath06.log;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

import br.com.pilovieira.ath06.R;

public class InfoFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_info, container, false);

        mountLogsList(view);
        mountBtnClear(view);

        return view;
    }

    private void mountLogsList(View view) {
        ListView logList = (ListView) view.findViewById(R.id.fragment_info_message_list);
        List<ServerLog> logs = new ServerLogManager(getContext()).getLogs();
        logList.setAdapter(new LogListAdapter(getContext(), logs));
    }

    private void mountBtnClear(View view) {
        Button btnClear = (Button) view.findViewById(R.id.fragment_server_log_clear);
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new ServerLogManager(view.getContext()).clearLogs();
            }
        });
    }

}
