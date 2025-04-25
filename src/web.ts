import { WebPlugin } from '@capacitor/core';
import type { AndroidLauncherPlugin } from './definitions';

export class AndroidLauncherWeb extends WebPlugin implements AndroidLauncherPlugin {
  async requestLauncherRole(): Promise<void> {
    throw this.unavailable('requestLauncherRole is not available on web.');
  }

  async removeLauncherRole(): Promise<void> {
    throw this.unavailable('removeLauncherRole is not available on web.');
  }

  async isLauncherApp(): Promise<{ isLauncher: boolean }> {
    return { isLauncher: false };
  }
}