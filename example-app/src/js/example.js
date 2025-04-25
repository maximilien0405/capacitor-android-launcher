import { AndroidLauncher } from '@maximilien0405/capacitor-android-launcher';

window.openLauncherSettings = async () => {
  try {
    await AndroidLauncher.openLauncherSettings();
    alert('Launcher settings opened successfully!');
  } catch (error) {
    alert('Failed to open launcher settings: ' + error);
  }
};

window.startImmersiveMode = async () => {
  try {
    await AndroidLauncher.startImmersiveMode();
    alert('Immersive mode started!');
  } catch (error) {
    alert('Failed to start immersive mode: ' + error);
  }
};

window.stopImmersiveMode = async () => {
  try {
    await AndroidLauncher.stopImmersiveMode();
    alert('Immersive mode stopped!');
  } catch (error) {
    alert('Failed to stop immersive mode: ' + error);
  }
};

window.isLauncherApp = async () => {
  try {
    const isLauncher = await AndroidLauncher.isLauncherApp();
    document.getElementById('status').textContent = `Is the app the launcher? ${isLauncher}`;
  } catch (error) {
    alert('Failed to check launcher status: ' + error);
  }
};