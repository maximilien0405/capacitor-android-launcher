# @maximilien0405/capacitor-android-launcher

A Capacitor plugin that lets your Android app request to become the system launcher (home screen app), remove itself as the launcher, and check if it's currently the default launcher.

## Installation

```bash
npm install capacitor-android-launcher
npx cap sync
```

And then import it like that : 

```ts
import { AndroidLauncher } from '@maximilien0405/capacitor-android-launcher';
```

## Methods

``requestLauncherRole(): Promise<{ granted: boolean }>``
Prompts the user to set your app as the default launcher.

```ts
const { granted } = await AndroidLauncher.requestLauncherRole();
```
<br>
``removeLauncherRole(): Promise<void>``
Opens system settings so the user can choose another default launcher.

```ts
await AndroidLauncher.removeLauncherRole();
```
<br>
``isLauncherDefault(): Promise<{ isDefault: boolean }>``
Checks if your app is currently the default launcher.

```ts
const { isDefault } = await AndroidLauncher.isLauncherDefault();
```