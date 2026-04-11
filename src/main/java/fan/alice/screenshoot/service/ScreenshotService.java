package fan.alice.screenshoot.service;

import fan.alice.screenshoot.util.AppLogger;
import fan.alice.screenshoot.util.ImageSelection;
import fan.alice.screenshoot.util.SoundPlayer;

import java.awt.image.BufferedImage;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.io.File;
import java.text.SimpleDateFormat;
import javax.imageio.ImageIO;

public class ScreenshotService {

    private static final String BASE_DIR = "screenshots";

    public static String latestFile;

    public static void init() {
        new File(BASE_DIR).mkdirs();
    }

    public static void capture() {
        try {
            Robot robot = new Robot();
            Rectangle screen = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());

            BufferedImage image = robot.createScreenCapture(screen);
            copyToClipboard(image);

            long time = System.currentTimeMillis ();
            String timePath = new SimpleDateFormat("yyyy/MM").format(time);
            String fileName = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(time) + ".png";

            File dir = new File(BASE_DIR + "/" + timePath);
            dir.mkdirs();

            File output = new File(dir, fileName);
            ImageIO.write(image, "png", output);

            SoundPlayer.play();

            latestFile = output.getAbsolutePath();

            AppLogger.log("Screenshot saved: " + latestFile);
        } catch (Exception e) {
            AppLogger.log("Screenshot failed: " + e.getMessage());
        }
    }

    private static void copyToClipboard(BufferedImage image) {
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new ImageSelection (image), null);
    }
}