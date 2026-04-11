package fan.alice.screenshoot.app;

import fan.alice.screenshoot.service.ScreenshotService;
import fan.alice.screenshoot.util.AppLogger;

import java.awt.Desktop;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.io.File;
import javax.imageio.ImageIO;

public class TrayManager {

    public static void init() {
        if (!SystemTray.isSupported()) {
            AppLogger.log("System tray is not supported");
            return;
        }

        try {
            SystemTray tray = SystemTray.getSystemTray();

            //Image image = Toolkit.getDefaultToolkit().createImage(TrayManager.class.getResource("/icon.png"));
            Image image = ImageIO.read(TrayManager.class.getResource("/icon.png"));

            PopupMenu menu = new PopupMenu();

            MenuItem openFolderItem = new MenuItem("Open screenshot folder");
            openFolderItem.addActionListener(e -> {
                openDir("screenshots");
            });

            MenuItem openLogItem = new MenuItem("Open log file");
            openLogItem.addActionListener(e -> {
                openFile("app.log");
            });

            MenuItem exitItem = new MenuItem("Exit");
            exitItem.addActionListener(e -> {
                AppLogger.log("ScreenShoot exited");
                System.exit(0);
            });

            menu.add(openFolderItem);
            menu.add(openLogItem);
            menu.addSeparator();
            menu.add(exitItem);

            TrayIcon trayIcon = new TrayIcon(image, "ScreenShoot", menu);
            trayIcon.setImageAutoSize(true);

            trayIcon.addActionListener(e -> openFile (ScreenshotService.latestFile));

            tray.add(trayIcon);

            AppLogger.log("System tray initialized successfully");

        } catch (Exception e) {
            AppLogger.log("System tray initialization failed: " + e.getMessage());
        }
    }

    private static void openDir(String path) {
        try {
            Desktop.getDesktop().open(new File(path));
        } catch (Exception e) {
            AppLogger.log("Failed to open directory: " + e.getMessage());
        }
    }

    private static void openFile(String file) {
        try {
            Desktop.getDesktop().open(new File(file));
        } catch (Exception e) {
            AppLogger.log("Failed to open file: " + e.getMessage());
        }
    }
}