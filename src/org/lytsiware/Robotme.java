package org.lytsiware;

import java.awt.*;

public class Robotme {

    private static final int MAX_IDLE_TIME_MILLIS = 2 * 60 * 1000;


    public void run() throws AWTException {
        GraphicsEnvironment graphicEnv = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice screenDevice = graphicEnv.getDefaultScreenDevice();
        Robot robot = new Robot(screenDevice);
        Point oldMouseLocation = MouseInfo.getPointerInfo().getLocation();
        long mouseMovedOn = System.currentTimeMillis();

        while (true) {
            robot.delay(5 * 1000);
            Point currentMouseLocation = MouseInfo.getPointerInfo().getLocation();
            if (!oldMouseLocation.equals(currentMouseLocation)) {
                oldMouseLocation = currentMouseLocation;
                mouseMovedOn = System.currentTimeMillis();
            } else if (System.currentTimeMillis() - mouseMovedOn > MAX_IDLE_TIME_MILLIS) {
                int nextLocationX = (int) currentMouseLocation.getX() + (Math.random() * 10 > 0.5 ? 1 : -1);
                robot.waitForIdle();
                robot.mouseMove(nextLocationX, 100);
                oldMouseLocation = currentMouseLocation;
                mouseMovedOn = System.currentTimeMillis();
            }
        }
    }


    public static void main(String[] args) throws AWTException {
        new Robotme().run();
    }
}
