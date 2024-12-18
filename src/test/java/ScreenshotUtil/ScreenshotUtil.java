package ScreenshotUtil;

import org.apache.commons.io.FileUtils;
        import org.openqa.selenium.OutputType;
        import org.openqa.selenium.TakesScreenshot;
        import org.openqa.selenium.WebDriver;

        import java.io.File;
        import java.io.IOException;
        import java.text.SimpleDateFormat;
        import java.util.Date;

public class ScreenshotUtil {

    public static void takeScreenshot(WebDriver driver, String fileName) {
        // Ekran görüntüsü alma
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        // Dosya yolu belirleme
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String filePath = "screenshots/" + fileName + "_" + timeStamp + ".png";

        try {
            // Ekran görüntüsünü kaydetme
            FileUtils.copyFile(srcFile, new File(filePath));
            System.out.println("Ekran görüntüsü alındı: " + filePath);
        } catch (IOException e) {
            System.out.println("Ekran görüntüsü alınamadı: " + e.getMessage());
        }
    }
}
