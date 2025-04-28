# Capacitor Android Launcher Plugin

The Capacitor Android Launcher Plugin allows you to set your app as the default launcher on Android devices. 

It also provides immersive mode functionality, which hides all system UI elements (status bar and navigation bar). This can be useful for kiosk apps or even apps designed for children or seniors.

## Installation

To install the plugin, run the following commands:

```bash
npm install @maximilien0405/capacitor-android-launcher
npx cap sync
```

And import it just like this:
```ts
import { AndroidLauncher } from '@maximilien0405/capacitor-android-launcher';
```

## Usage

### Open Launcher Settings

This method opens the settings page where the user can set your app as the default launcher.

```ts
await AndroidLauncher.openLauncherSettings();
```

### Start Immersive Mode

This method hides all system UI elements, creating a full-screen experience.

```ts
await AndroidLauncher.startImmersiveMode();
```

### Stop Immersive Mode

This method restores the system UI elements.

```ts
await AndroidLauncher.stopImmersiveMode();
```

### Check if App is the Default Launcher

This method checks if your app is currently set as the default launcher.

```ts
const isLauncher = await AndroidLauncher.isLauncherApp();
console.log(isLauncher); // true or false
```

## Notes

- When set as the default launcher, users can still exit the app. To prevent this, consider using Android's "pinning" feature in combination with this plugin.

## Support

For issues or feature requests, please open an issue on the [GitHub repository](https://github.com/maximilien0405/capacitor-android-launcher).

## License

This project is licensed under the MIT License. See the LICENSE file for details.