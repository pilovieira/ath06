package br.com.pilovieira.ath06.view;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.com.pilovieira.ath06.R;
import br.com.pilovieira.ath06.log.ServerLog;
import br.com.pilovieira.ath06.log.ServerLogManager;

public class ConfigurationsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_info, container, false);

        ListView logList = (ListView) view.findViewById(R.id.fragment_info_message_list);

        ArrayAdapter<String> changeList = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, 0, getLogs());
        logList.setAdapter(changeList);

        Button btnClear = (Button) view.findViewById(R.id.fragment_server_log_clear);
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new ServerLogManager(view.getContext()).clearLogs();
            }
        });

        return view;
    }

    private List<String> getLogs() {
        List<String> logsString = new ArrayList<>();

        List<ServerLog> logs = new ServerLogManager(getContext()).getLogs();
        Collections.sort(logs);
        Collections.reverse(logs);

        for (ServerLog log : logs) {
            StringBuilder b = new StringBuilder();
            if (log.getDate() != null)
                b.append(new SimpleDateFormat("dd/MM/yy hh:mm:ss").format(log.getDate())).append("\n");
            if (log.getTitle() != null && !log.getTitle().isEmpty())
                b.append(log.getTitle()).append("\n");
            b.append(log.getMessage()).append("\n");

            logsString.add(b.toString());
        }

        return logsString;
    }

}
