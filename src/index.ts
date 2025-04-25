import { registerPlugin } from '@capacitor/core';

export interface AndroidLauncherPlugin {
  requestLauncherRole(): Promise<void>;
  removeLauncherRole(): Promise<void>;
  isLauncherApp(): Promise<{ isLauncher: boolean }>;
}

const AndroidLauncher = registerPlugin<AndroidLauncherPlugin>('AndroidLauncher');

export * from './definitions';
export { AndroidLauncher };