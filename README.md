# Capacitor Android Launcher Plugin

This Capacitor plugin allows you to open the launcher settings to set your app as the default launcher on Android. 

It can also, if needed, start (and stop) and immersive mode that hides all UI elements and prevents your user from leaving the app. This is usefull for an Kiosk App, or for children or seniors.

## Installation

```bash
npm install @maximilien0405/capacitor-android-launcher
npx cap sync
```

## Usage


#### Request Launcher Role

This method opens the settings page where the user can set your app as the default launcher.

```ts
await AndroidLauncher.openLauncherSettings();
```

#### Start Immersive Mode

This method hides all system controls and prevents the user from leaving the app.

> ⚠️ The app must be set as the launcher for it to work.

```ts
await AndroidLauncher.startImmersiveMode();
```

#### Stop Immersive Mode

This method restores the system controls and allows the user to exit the app.

```ts
await AndroidLauncher.stopImmersiveMode();
```

#### Check if the App is the Launcher

This method checks if your app is currently set as the default launcher.

```ts
const isLauncher = await AndroidLauncher.isLauncherApp();
console.log(isLauncher);  // true or false
```

## API

```ts
interface AndroidLauncherPlugin {
  openLauncherSettings(): Promise<void>;
  startImmersiveMode(): Promise<void>;
  stopImmersiveMode(): Promise<void>;
  isLauncherApp(): Promise<boolean>;
}
```