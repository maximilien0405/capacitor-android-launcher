package com.maximilien0405.androidlauncher;

import android.view.View;
import android.content.Intent;
import android.provider.Settings;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.annotation.CapacitorPlugin;
import com.getcapacitor.PluginMethod;

public class AndroidLauncher {

    private AppCompatActivity activity;

    public AndroidLauncher(AppCompatActivity activity) {
        this.activity = activity;
    }

    // Opens the settings to allow the user to set the app as launcher
    public void openLauncherSettings(PluginCall call) {
        try {
            Intent intent = new Intent(Settings.ACTION_HOME_SETTINGS);
            activity.startActivity(intent);
            call.resolve();
        } catch (Exception e) {
            call.reject("Error opening launcher settings", e);
        }
    }

    // Starts immersive mode and prevents the user from leaving the app (if it's the launcher)
    public void startImmersiveMode(PluginCall call) {
        if (!isLauncherApp()) {
            call.reject("App is not set as the launcher.");
            return;
        }

        try {
            // Hide the status bar and navigation bar
            activity.getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE |
                View.SYSTEM_UI_FLAG_FULLSCREEN |
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
            call.resolve();
        } catch (Exception e) {
            call.reject("Error starting immersive mode", e);
        }
    }

    // Stops immersive mode and allows the user to interact with the controls again
    public void stopImmersiveMode(PluginCall call) {
        try {
            // Reset system UI visibility
            activity.getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_VISIBLE);
            call.resolve();
        } catch (Exception e) {
            call.reject("Error stopping immersive mode", e);
        }
    }

    // Checks if the app is set as the default launcher
    public boolean isLauncherApp() {
        PackageManager packageManager = activity.getPackageManager();
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        ResolveInfo resolveInfo = packageManager.resolveActivity(intent, PackageManager.MATCH_DEFAULT_ONLY);
        return resolveInfo.activityInfo.packageName.equals(activity.getPackageName());
    }
}