package com.maximilien0405.androidlauncher;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Handler;
import android.os.Build;
import android.os.Looper;
import android.view.View;
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

    public AndroidLauncher(AppCompatActivity activity, Bridge bridge) {
      this.activity = activity;
      this.bridge = bridge;
      setupSystemUiVisibilityListener();
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
        try {
            if (Looper.myLooper() == Looper.getMainLooper()) {
                enableImmersiveMode();
                call.resolve();
            } else {
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        enableImmersiveMode();
                        call.resolve();
                    }
                });
            }
        } catch (Exception e) {
            call.reject("Error starting immersive mode", e);
        }
    }

    private void enableImmersiveMode() {
      try {
        immersiveModeEnabled = true;
        View decorView = this.bridge.getActivity().getWindow().getDecorView();
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.R) {
          WindowInsetsController insetsController = decorView.getWindowInsetsController();
          if (insetsController != null) {
              insetsController.hide(android.view.WindowInsets.Type.statusBars()
                                  | android.view.WindowInsets.Type.navigationBars());

              insetsController.setSystemBarsBehavior(
                  WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE);
          }
        } else {
            int flags = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
            decorView.setSystemUiVisibility(flags);
        }
      } catch (Exception e) {
          e.printStackTrace();
      }
    }

    public void stopImmersiveMode(PluginCall call) {
      try {
          if (Looper.myLooper() == Looper.getMainLooper()) {
              clearImmersiveMode();
              call.resolve();
          } else {
              new Handler(Looper.getMainLooper()).post(new Runnable() {
                  @Override
                  public void run() {
                      clearImmersiveMode();
                      call.resolve();
                  }
              });
          }
      } catch (Exception e) {
          call.reject("Error stopping immersive mode", e);
      }
    }

    private void clearImmersiveMode() {
        try {
          immersiveModeEnabled = false;
          View decorView = this.bridge.getActivity().getWindow().getDecorView();
          if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
              WindowInsetsController insetsController = decorView.getWindowInsetsController();
              if (insetsController != null) {
                  insetsController.hide(WindowInsetsController.BEHAVIOR_SHOW_BARS_BY_SWIPE);
                  insetsController.setSystemBarsBehavior(WindowInsetsController.BEHAVIOR_SHOW_BARS_BY_TOUCH);
              }
          } else {
              int uiOptions = View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY |
                              View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
                              View.SYSTEM_UI_FLAG_FULLSCREEN;
              decorView.setSystemUiVisibility(uiOptions);
          }
        } catch (Exception e) {
            e.printStackTrace();
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

    // Listen to system UI changes and hide UI if swiped
    private void setupSystemUiVisibilityListener() {
      if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.R) {
        // For Android 11+, use a window focus change listener instead
        activity.getWindow().getDecorView().setOnApplyWindowInsetsListener(
            (view, windowInsets) -> {
                if (immersiveModeEnabled &&
                    windowInsets.isVisible(android.view.WindowInsets.Type.statusBars() | android.view.WindowInsets.Type.navigationBars())) {
                    if (immersiveModeEnabled) {
                      enableImmersiveMode();
                    }
                }
                return view.onApplyWindowInsets(windowInsets);
            });
      } else {
        // For older Android versions
        activity.getWindow().getDecorView().setOnSystemUiVisibilityChangeListener(
            visibility -> {
                if (immersiveModeEnabled && (visibility & View.SYSTEM_UI_FLAG_FULLSCREEN) == 0) {
                  if (immersiveModeEnabled) {
                      enableImmersiveMode();
                  }
                }
            }
        );
      }
    }
}