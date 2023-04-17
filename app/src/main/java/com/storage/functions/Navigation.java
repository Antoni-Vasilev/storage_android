package com.storage.functions;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.storage.activity.ForgotPasswordActivity;
import com.storage.activity.HomeActivity;
import com.storage.activity.LoginActivity;
import com.storage.activity.RegisterActivity;

public class Navigation {

    public static int NONE = 0;
    public static int THIS = 1;
    public static int ALL  = 2;

    public static void toLogin(Context context, int finishOption) {
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
        if (finishOption == 1) ((Activity) context).finish();
        else if (finishOption == 2) ((Activity) context).finishAffinity();
    }

    public static void toRegister(Context context, int finishOption) {
        Intent intent = new Intent(context, RegisterActivity.class);
        context.startActivity(intent);
        if (finishOption == 1) ((Activity) context).finish();
        else if (finishOption == 2) ((Activity) context).finishAffinity();
    }

    public static void toHome(Context context, int finishOption) {
        Intent intent = new Intent(context, HomeActivity.class);
        context.startActivity(intent);
        if (finishOption == 1) ((Activity) context).finish();
        else if (finishOption == 2) ((Activity) context).finishAffinity();
    }

    public static void toForgotPassword(Context context, int finishOption) {
        Intent intent = new Intent(context, ForgotPasswordActivity.class);
        context.startActivity(intent);
        if (finishOption == 1) ((Activity) context).finish();
        else if (finishOption == 2) ((Activity) context).finishAffinity();
    }
}
