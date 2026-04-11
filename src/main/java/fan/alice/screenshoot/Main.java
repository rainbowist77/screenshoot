package fan.alice.screenshoot;

import fan.alice.screenshoot.app.TrayManager;
import fan.alice.screenshoot.hotkey.HotKeyListener;
import fan.alice.screenshoot.service.ScreenshotService;
import fan.alice.screenshoot.util.AppLogger;

public class Main {

    public static void main(String[] args) {

        AppLogger.init();

        ScreenshotService.init();

        TrayManager.init();
        HotKeyListener.start();

        AppLogger.log("ScreenShoot is running in background mode");

    }
}