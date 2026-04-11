package fan.alice.screenshoot.hotkey;

import fan.alice.screenshoot.service.ScreenshotService;
import fan.alice.screenshoot.util.AppLogger;

import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;

public class HotKeyListener implements NativeKeyListener {

    public static void start() {
        try {
            GlobalScreen.registerNativeHook();
            GlobalScreen.addNativeKeyListener(new HotKeyListener());
        } catch (Exception e) {
            AppLogger.log("Hotkey initialization failed: " + e.getMessage());
        }
    }

    @Override
    public void nativeKeyPressed(NativeKeyEvent e) {
        if (e.getKeyCode() == NativeKeyEvent.VC_F9) {
            ScreenshotService.capture();
        }
    }
}