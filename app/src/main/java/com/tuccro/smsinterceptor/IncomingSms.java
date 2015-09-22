package com.tuccro.smsinterceptor;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;

/**
 * Created by Valentin on 9/22/2015.
 */
public class IncomingSms extends BroadcastReceiver {

    public static final String ACTION = "android.provider.Telephony.SMS_RECEIVED";

    public void onReceive(Context context, Intent intent) {

        if (intent.getAction().equals(ACTION)) {

            Bundle bundle = intent.getExtras();

            if (bundle != null) {

                Object[] pdus = (Object[]) bundle.get("pdus");
                SmsMessage[] messages = new SmsMessage[pdus.length];

                for (int i = 0; i < pdus.length; i++) {
                    messages[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                }
                for (SmsMessage message : messages) {

                    String strMessageFrom = message.getDisplayOriginatingAddress();
                    String strMessageBody = message.getDisplayMessageBody();

                    Intent intentMainActivity = new Intent(context, MainActivity.class);
                    intentMainActivity.putExtra(MainActivity.SENDER, strMessageFrom);
                    intentMainActivity.putExtra(MainActivity.MESSAGE, strMessageBody);
                    intentMainActivity.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                    context.startActivity(intentMainActivity);
                }
            }
        }
    }
}