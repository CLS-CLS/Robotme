package org.lytsiware;

import java.awt.*;
import java.time.LocalTime;

public class Robotme {

    private static final int MAX_IDLE_TIME_MILLIS = 2 * 60 * 1000;


    public void run(LocalTime endTime) throws AWTException {
        GraphicsDevice screenDevice = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        Robot robot = new Robot(screenDevice);

        Point oldMouseLocation = MouseInfo.getPointerInfo().getLocation();
        long mouseMovedOn = System.currentTimeMillis();

        while (before(endTime)) {
            robot.delay(5 * 1000);
            Point currentMouseLocation = MouseInfo.getPointerInfo().getLocation();
            // mouse moved, update the new time and position
            if (!oldMouseLocation.equals(currentMouseLocation)) {
                oldMouseLocation = currentMouseLocation;
                mouseMovedOn = System.currentTimeMillis();
            }
            // mouse not moved, if mouse has not moved for than MAX_IDLE_TIME, make it move manually
            else if (System.currentTimeMillis() - mouseMovedOn > MAX_IDLE_TIME_MILLIS) {
                int nextLocationX = (int) currentMouseLocation.getX() + (Math.random() * 10 > 0.5 ? 1 : -1);
                robot.waitForIdle();
                robot.mouseMove(nextLocationX, currentMouseLocation.y);
                oldMouseLocation = currentMouseLocation;
                mouseMovedOn = System.currentTimeMillis();
            }
        }
    }

    private boolean before(LocalTime endTime) {
        if (endTime == null) {
            return true;
        }
        return LocalTime.now().isBefore(endTime);
    }


    public static void main(String[] args) throws AWTException {
        LocalTime time = null;
        if (args != null && args.length >=1) {
            time = LocalTime.parse(args[0]);
        }
        new Robotme().run(time);
    }
}
