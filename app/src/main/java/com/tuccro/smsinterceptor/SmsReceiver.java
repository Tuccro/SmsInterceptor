package com.tuccro.smsinterceptor;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;

import com.tuccro.smsinterceptor.database.DB;

/**
 * Created by Valentin on 9/22/2015.
 */
public class SmsReceiver extends BroadcastReceiver {

    public static final String ACTION = "android.provider.Telephony.SMS_RECEIVED";
    public static final String EMPTY_STRING = "";

    public void onReceive(Context context, Intent intent) {

        if (intent.getAction().equals(ACTION)) {

            Bundle bundle = intent.getExtras();

            if (bundle != null) {
                Object[] pdus = (Object[]) bundle.get("pdus");
                SmsMessage[] messages = new SmsMessage[pdus.length];

                String sender = EMPTY_STRING;
                StringBuilder sbMessage = new StringBuilder(EMPTY_STRING);

                for (int i = 0; i < pdus.length; i++) {
                    messages[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                }

                for (SmsMessage message : messages) {
                    String strMessageFrom = message.getDisplayOriginatingAddress();
                    String strMessageBody = message.getDisplayMessageBody();

                    if (strMessageFrom != null) sender = strMessageFrom;
                    if (strMessageBody != null) sbMessage.append(strMessageBody);
                }

                Log.e(sender, sbMessage.toString());

                DB db = new DB(context);
                db.openDataBase();

                db.addSmsToDB(sender, sbMessage.toString());
                db.closeDataBase();
            }
        }
    }
}