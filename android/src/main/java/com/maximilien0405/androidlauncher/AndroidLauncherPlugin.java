package com.maximilien0405.androidlauncher;

import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;

@CapacitorPlugin(name = "AndroidLauncher")
public class AndroidLauncherPlugin extends Plugin {

    private AndroidLauncher androidLauncher;

    @Override
    public void load() {
        super.load();
        androidLauncher = new AndroidLauncher(getActivity()); 
    }

    // Opens the launcher settings
    @com.getcapacitor.PluginMethod
    public void openLauncherSettings(PluginCall call) {
        androidLauncher.openLauncherSettings(call);
    }

    // Starts immersive mode to hide all controls
    @com.getcapacitor.PluginMethod
    public void startImmersiveMode(PluginCall call) {
        androidLauncher.startImmersiveMode(call);
    }

    // Stops immersive mode and restores controls
    @com.getcapacitor.PluginMethod
    public void stopImmersiveMode(PluginCall call) {
        androidLauncher.stopImmersiveMode(call);
    }

    // Checks if the app is the launcher
    @com.getcapacitor.PluginMethod
    public void isLauncherApp(PluginCall call) {
        boolean isLauncher = androidLauncher.isLauncherApp();
        JSObject ret = new JSObject();
        ret.put("isLauncher", isLauncher);
        call.resolve(ret);
    }
}