import { WebPlugin } from '@capacitor/core';

import type { AndroidLauncherPlugin } from './definitions';

export class AndroidLauncherWeb extends WebPlugin implements AndroidLauncherPlugin {
  async echo(options: { value: string }): Promise<{ value: string }> {
    console.log('ECHO', options);
    return options;
  }
}
