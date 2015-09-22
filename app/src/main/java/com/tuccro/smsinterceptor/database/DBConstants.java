package com.tuccro.smsinterceptor.database;

/**
 * Created by Valentin on 9/22/2015.
 */
public abstract class DBConstants {

    public static final String DB_NAME = "sms.db";
    public static final int DB_VERSION = 1;

    public static final String CREATE_DB = "CREATE TABLE `sms` (\n" +
            "\t`_id`\tINTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,\n" +
            "\t`date`\tINTEGER NOT NULL,\n" +
            "\t`sender`\tTEXT,\n" +
            "\t`message`\tTEXT\n" +
            ");";
}
