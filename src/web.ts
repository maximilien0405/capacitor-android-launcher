import { WebPlugin } from '@capacitor/core';
import { AndroidLauncherPlugin } from './definitions';

export class AndroidLauncherWeb extends WebPlugin implements AndroidLauncherPlugin {
  async openLauncherSettings(): Promise<void> {
    throw new Error('openLauncherSettings is not supported on the web.');
  }

  async startImmersiveMode(): Promise<void> {
    throw new Error('startImmersiveMode is not supported on the web.');
  }

  async stopImmersiveMode(): Promise<void> {
    throw new Error('stopImmersiveMode is not supported on the web.');
  }

  async isLauncherApp(): Promise<boolean> {
    throw new Error('stopImmersiveMode is not supported on the web.');
  }
}