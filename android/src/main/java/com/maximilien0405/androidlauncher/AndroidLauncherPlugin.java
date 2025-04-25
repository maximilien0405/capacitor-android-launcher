package com.maximilien0405.androidlauncher;

import android.content.Intent;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.annotation.CapacitorPlugin;

@CapacitorPlugin(name = "AndroidLauncher")
public class AndroidLauncherPlugin extends Plugin {
    private AndroidLauncher implementation;

    @Override
    public void load() {
        implementation = new AndroidLauncher(getActivity());
    }

    @com.getcapacitor.PluginMethod
    public void requestLauncherRole(PluginCall call) {
        implementation.requestLauncherRole();
        call.resolve();
    }

    @com.getcapacitor.PluginMethod
    public void removeLauncherRole(PluginCall call) {
        implementation.removeLauncherRole();
        call.resolve();
    }

    @com.getcapacitor.PluginMethod
    public void isLauncherApp(PluginCall call) {
        boolean isLauncher = implementation.isLauncherApp();
        JSObject ret = new JSObject();
        ret.put("isLauncher", isLauncher);
        call.resolve(ret);
    }
}