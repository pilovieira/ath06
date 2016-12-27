package br.com.pilovieira.ath06.persist;

import android.database.sqlite.SQLiteDatabase;

public class ScriptsV1 {

    private SQLiteDatabase sqld;

    public ScriptsV1(SQLiteDatabase sqld) {
        this.sqld = sqld;
    }

    public void upgrade() {
        sqld.execSQL(createTableLiveTrackerPosition());
        sqld.execSQL(createTableServerLog());
    }

    private String createTableLiveTrackerPosition() {
        return
                "create table live_tracker_position (" +
                "id integer primary key autoincrement, " +
                "date integer not null, " +
                "latitude real not null, " +
                "longitude real not null, " +
                "speed real not null);";
    }

    private String createTableServerLog() {
        return
                "create table server_log (" +
                "id integer primary key autoincrement, " +
                "date integer, " +
                "title text, " +
                "message text);";
    }

}
