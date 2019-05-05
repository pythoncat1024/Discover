package com.pycat.schedule.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class ScheduleDbHelper extends SQLiteOpenHelper {
    public static final int DB_VERSION = 1;
    public ScheduleDbHelper(@Nullable Context context) {
        super(context, "play_todo_list", null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
