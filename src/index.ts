import { registerPlugin } from '@capacitor/core';
import { AndroidLauncherPlugin } from './definitions';

const AndroidLauncher = registerPlugin<AndroidLauncherPlugin>('AndroidLauncher', {
  web: () => import('./web').then(m => new m.AndroidLauncherWeb()),
});

export * from './definitions';
export { AndroidLauncher };