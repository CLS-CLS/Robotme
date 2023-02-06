package org.lytsiware;

import java.awt.*;

public class Robotme {

    public void run() throws AWTException {
        GraphicsEnvironment graphicEnv = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice screenDevice = graphicEnv.getDefaultScreenDevice();
        Robot robot = new Robot(screenDevice);
        while (true) {
            Point currentMouseLocation = MouseInfo.getPointerInfo().getLocation();
            int nextLocationX = currentMouseLocation.getX() > 100 ? 90 : 500;
            robot.delay(30 * 1000);
            robot.waitForIdle();
            robot.mouseMove(nextLocationX, 100);
        }
    }



    public static void main(String[] args) throws AWTException {
        new Robotme().run();
    }
}
