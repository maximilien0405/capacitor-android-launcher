package com.maximilien0405.androidlauncher;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Handler;
import android.app.Activity;
import android.os.Build;
import android.os.Looper;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowInsets;
import android.view.WindowInsetsController;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.RequiresApi;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.Bridge;
import android.provider.Settings;

public class AndroidLauncher {
    private AppCompatActivity activity;
    private Bridge bridge;
    private boolean immersiveModeEnabled = false;
    private ViewTreeObserver.OnWindowFocusChangeListener focusChangeListener;
    private View.OnSystemUiVisibilityChangeListener systemUiVisibilityChangeListener;

    public AndroidLauncher(AppCompatActivity activity, Bridge bridge) {
      this.activity = activity;
      this.bridge = bridge;
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

    private void setImmersiveMode(boolean enable) {
        immersiveModeEnabled = enable;
        View decorView = this.bridge.getActivity().getWindow().getDecorView();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            WindowInsetsController insetsController = decorView.getWindowInsetsController();
            if (insetsController != null) {
                if (enable) {
                    insetsController.hide(WindowInsets.Type.statusBars() | WindowInsets.Type.navigationBars());
                    insetsController.setSystemBarsBehavior(WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE);
                } else {
                    insetsController.show(WindowInsets.Type.statusBars() | WindowInsets.Type.navigationBars());
                    insetsController.setSystemBarsBehavior(WindowInsetsController.BEHAVIOR_SHOW_BARS_BY_TOUCH);
                }
            }
        } else {
            int flags = enable
                    ? View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    : View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(flags);
        }
    }

    // Starts immersive mode
    public void startImmersiveMode(PluginCall call) {
        runOnMainThread(() -> {
            try {
                setImmersiveMode(true);
                setupSystemUiVisibilityListener();
                call.resolve();
            } catch (Exception e) {
                call.reject("Error starting immersive mode", e);
            }
        });
    }

    // Stops immersive mode
    public void stopImmersiveMode(PluginCall call) {
        runOnMainThread(() -> {
            try {
                setImmersiveMode(false);
                removeSystemUiVisibilityListener();
                call.resolve();
            } catch (Exception e) {
                call.reject("Error stopping immersive mode", e);
            }
        });
    }

    // Helper to run things on the UI thread
    private void runOnMainThread(Runnable runnable) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            runnable.run();
        } else {
            new Handler(Looper.getMainLooper()).post(runnable);
        }
    }

    // Checks if the app is set as the default launcher
    public boolean isLauncherApp() {
        PackageManager packageManager = activity.getPackageManager();
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        
        // Get the default launcher by resolving the HOME intent
        ResolveInfo defaultLauncher = packageManager.resolveActivity(intent, PackageManager.MATCH_DEFAULT_ONLY);
        
        if (defaultLauncher == null) {
            return false;
        }
        
        // Check if the default launcher is our app
        String myPackageName = activity.getPackageName();
        return defaultLauncher.activityInfo.packageName.equals(myPackageName);
    }

    // Listen to system UI changes and hide UI if swiped
    private void setupSystemUiVisibilityListener() {
        Activity activity = this.bridge.getActivity();
        View decorView = activity.getWindow().getDecorView();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            focusChangeListener = hasFocus -> {
                if (immersiveModeEnabled && hasFocus) {
                    setImmersiveMode(true);
                }
            };
            decorView.getViewTreeObserver().addOnWindowFocusChangeListener(focusChangeListener);
        } else {
            systemUiVisibilityChangeListener = visibility -> {
                if (immersiveModeEnabled && (visibility & View.SYSTEM_UI_FLAG_FULLSCREEN) == 0) {
                    setImmersiveMode(true);
                }
            };
            decorView.setOnSystemUiVisibilityChangeListener(systemUiVisibilityChangeListener);
        }
    }

    // Remove the listener
    private void removeSystemUiVisibilityListener() {
        Activity activity = this.bridge.getActivity();
        View decorView = activity.getWindow().getDecorView();
    
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            if (focusChangeListener != null) {
                decorView.getViewTreeObserver().removeOnWindowFocusChangeListener(focusChangeListener);
                focusChangeListener = null;
            }
        } else {
            if (systemUiVisibilityChangeListener != null) {
                decorView.setOnSystemUiVisibilityChangeListener(null);
                systemUiVisibilityChangeListener = null;
            }
        }
    }
}