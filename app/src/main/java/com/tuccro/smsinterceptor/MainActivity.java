package com.tuccro.smsinterceptor;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by don on 9/22/15.
 */
public class MainActivity extends Activity {

    public static final String SENDER = "sender";
    public static final String MESSAGE = "message";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();

        setContentView(R.layout.activity_main);

        TextView tvSender = (TextView) findViewById(R.id.tv_sender);
        TextView tvMessage = (TextView) findViewById(R.id.tv_message);

        try {
            String sender = intent.getStringExtra(SENDER);
            String message = intent.getStringExtra(MESSAGE);

            tvSender.setText(sender);
            tvMessage.setText(message);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
