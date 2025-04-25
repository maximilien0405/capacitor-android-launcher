package com.maximilien0405.androidlauncher;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Build;
import android.provider.Settings;

public class AndroidLauncher {
    private final Activity activity;

    public AndroidLauncher(Activity activity) {
        this.activity = activity;
    }

    public void requestLauncherRole() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intent);
    }

    public void removeLauncherRole() {
        Intent intent = new Intent(Settings.ACTION_HOME_SETTINGS);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intent);
    }

    public boolean isLauncherApp() {
        final Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        final ResolveInfo res = activity.getPackageManager().resolveActivity(intent, PackageManager.MATCH_DEFAULT_ONLY);
        if (res == null || res.activityInfo == null) return false;
        return activity.getPackageName().equals(res.activityInfo.packageName);
    }
}