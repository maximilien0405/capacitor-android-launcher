export interface AndroidLauncherPlugin {
  openLauncherSettings(): Promise<void>;
  startImmersiveMode(): Promise<void>;
  stopImmersiveMode(): Promise<void>;
  isLauncherApp(): Promise<boolean>;
}