export interface AndroidLauncherPlugin {
  requestLauncherRole(): Promise<void>;
  removeLauncherRole(): Promise<void>;
  isLauncherApp(): Promise<{ isLauncher: boolean }>;
}