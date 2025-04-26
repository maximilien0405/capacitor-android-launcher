# Capacitor Android Launcher Plugin

This Capacitor plugin allows you to open the launcher settings to set your app as the default launcher on Android. 

It can also, if needed, start (and stop) and immersive mode that hides the all UI elements (status bar and navigation bar). This is usefull for an Kiosk App, or for special apps made for childrens or seniors.

## Support

ANDROID Only.

- v7.X.X supports Capacitor V7 (Android minSdkVersion 23+).

## Installation

```bash
npm install @maximilien0405/capacitor-android-launcher
npx cap sync
```

## Usage


#### Open Launcher Settings

This method opens the settings page where the user can set your app as the default launcher.

```ts
await AndroidLauncher.openLauncherSettings();
```

#### Start Immersive Mode

This method hides all system controls.

> ⚠️ The app must be set as the launcher for it to work.

```ts
await AndroidLauncher.startImmersiveMode();
```

#### Stop Immersive Mode

This method restores the system controls.

```ts
await AndroidLauncher.stopImmersiveMode();
```

#### Check if is the Launcher

This method checks if your app is currently set as the default launcher.

```ts
const isLauncher = await AndroidLauncher.isLauncherApp();
console.log(isLauncher);  // true or false
```