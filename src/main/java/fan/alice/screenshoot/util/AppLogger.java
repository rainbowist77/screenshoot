package fan.alice.screenshoot.util;

import java.io.FileWriter;
import java.text.SimpleDateFormat;

public class AppLogger {

    private static final String LOG_FILE = "app.log";

    public static void init() {
        log("=== ScreenShoot Started ===");
    }

    public static void log(String msg) {
        String time = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss.SSS").format (System.currentTimeMillis ());

        String line = "[" + time + "] " + msg;

        //System.out.println(line);

        try (FileWriter fw = new FileWriter(LOG_FILE, true)) {
            fw.write(line + "\n");
        } catch (Exception ignored) {}
    }
}