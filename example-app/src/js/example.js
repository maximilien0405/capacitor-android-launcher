import { AndroidLauncher } from '@maximilien0405/capacitor-android-launcher';

window.testEcho = () => {
    const inputValue = document.getElementById("echoInput").value;
    AndroidLauncher.echo({ value: inputValue })
}
