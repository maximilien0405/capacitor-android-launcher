export interface AndroidLauncherPlugin {
  /**
   * Open the launcher settings page
   */
  openLauncherSettings(): Promise<void>;

  /**
   * Start the immersive mode
   * This method hides all system UI elements, creating a full-screen experience
   */
  startImmersiveMode(): Promise<void>;

  /**
   * Stop the immersive mode
   */
  stopImmersiveMode(): Promise<void>;

  /**
   * Check if the app is the default launcher or not
   */
  isLauncherApp(): Promise<boolean>;
}