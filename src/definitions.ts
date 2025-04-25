export interface AndroidLauncherPlugin {
  echo(options: { value: string }): Promise<{ value: string }>;
}
