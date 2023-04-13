package com.storage.functions;

import android.content.Context;
import android.content.SharedPreferences;

public class Database {

    public static SharedPreferences read;
    public static SharedPreferences.Editor edit;

    public static void setup(Context context) {
        read = context.getSharedPreferences("Database", Context.MODE_PRIVATE);
        edit = read.edit();
    }

    public static class Edit {

        public static void putString(String key, String value) {
            edit.putString(key, value);
        }
    }

    public static enum Keys {
        token("auth-token")
        ;

        public final String val;

        Keys(String val) {
            this.val = val;
        }
    }
}
