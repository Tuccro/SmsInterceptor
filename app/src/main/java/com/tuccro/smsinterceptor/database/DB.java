package com.tuccro.smsinterceptor.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by Valentin on 9/22/2015.
 */
public class DB {

    DBHelper dbHelper;
    SQLiteDatabase database;

    public DB(Context context) {

        dbHelper = new DBHelper(context);
    }

    public void openDataBase() {
        database = dbHelper.getWritableDatabase();
    }

    public void closeDataBase() {
        database.close();
    }

    public void addSmsToDB(String sender, String message) {

        ContentValues cv = new ContentValues();
        cv.put("date", System.currentTimeMillis());
        cv.put("sender", sender);
        cv.put("message", message);

        try {
            database.insert("sms", null, cv);
        } catch (SQLException e) {
            Log.e("DB Error:", e.getMessage());
        }
    }
}
