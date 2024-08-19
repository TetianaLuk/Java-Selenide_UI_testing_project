package utils;

import io.qameta.allure.Allure;
import java.io.*;
import static com.codeborne.selenide.Screenshots.takeScreenShotAsFile;

public class ScreenshotMethods {

    public static void attachScreenshotToAllureReport(String screenshotName){
        File screenshotFile = takeScreenShotAsFile();
        byte[] screenshotBytes = null;
        try {
            assert screenshotFile != null;
            try (FileInputStream fis = new FileInputStream(screenshotFile)) {
                screenshotBytes = fis.readAllBytes();
            }
        } catch (IOException e) {
            System.err.println(e);
        }

        if (screenshotBytes != null) {
            Allure.addAttachment(screenshotName, new ByteArrayInputStream(screenshotBytes));
        } else {
            throw new IllegalStateException("Screenshot could not be captured.");
        }
    }
}
