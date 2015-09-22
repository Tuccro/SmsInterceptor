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

    /**
     * Open writable database
     */
    public void openDataBase() {
        database = dbHelper.getWritableDatabase();
    }

    /**
     * Close database
     */
    public void closeDataBase() {
        database.close();
    }

    /**
     * Add new entry to table "sms"
     *
     * @param sender  sender of SMS
     * @param message body of SMS
     */
    public void addSmsToDB(String sender, String message) {

        ContentValues cv = new ContentValues();
        cv.put(DBConstants.DB_SMS_DATE, System.currentTimeMillis());
        cv.put(DBConstants.DB_SMS_SENDER, sender);
        cv.put(DBConstants.DB_SMS_MESSAGE, message);

        try {
            database.insert(DBConstants.DB_TABLE_SMS, null, cv);
        } catch (SQLException e) {
            Log.e("DB Error:", e.getMessage());
        }
    }
}
