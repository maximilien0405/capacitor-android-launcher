# Capacitor Android Launcher Plugin

This Capacitor plugin allows you to open the launcher settings to set your app as the default launcher on Android. 

It can also, if needed, start (and stop) and immersive mode that hides the all UI elements (status bar and navigation bar). This is usefull for an Kiosk App, or for special apps made for childrens or seniors.

> When set as the default launcher, your user will still be able to exit it, but when it happens it will re-open the app.
If you really want to prevent your user from leaving, i suggest "pinning" the app, and then setting it as the default launcher. 

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