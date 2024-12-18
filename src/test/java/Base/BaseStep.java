package Base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.Set;
import java.util.List;


public class BaseStep {
    public WebDriver driver;
    public boolean isScenarioComplated;
    public BaseStep()
    {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        /*
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Sevo\\Downloads\\chromedriver_win32\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
         */

    }
    public enum Pather {
        id,
        className,
        name,
        xPath,
        cssSelector,
        linkText
    }

    public enum TimeOut {
        LOW(5),
        MIDDLE(10),
        HIGH(15),
        CUSTOM_MAX(60);
        private final int value;

        public int getValue() {
            return value;
        }

        // enum constructor - cannot be public or protected
        private TimeOut(int value) {
            this.value = value;
        }

    }
    public void geturl()
    {
    driver.get("https://www.hepsiburada.com");

    }

    public void geturl(String url)
    {
        url = addHttps(url);
        driver.get(url);
        System.out.println(url+" Sayfası açıldı");
    }

    public String addHttps(String url)
    {
        if (url.contains("https://")||url.contains("http://")) {
            return url;
        } else {
            return "https://"+url;
        }
    }

    public void waitElement(WebElement element, TimeOut timeOut) {

        try {
            WebDriverWait wait = new WebDriverWait(driver, timeOut.value);
            wait.until(ExpectedConditions.invisibilityOf(element));
        } catch (Exception ex) {

        }

    }
    public void findElementClick(String path, Pather type) {
        System.out.println(path+" Elementine tıklanıyor");
        try {
            WebDriverWait wait = new WebDriverWait(driver, TimeOut.MIDDLE.value);
            switch (type) {
                case className:
                    wait.until(ExpectedConditions.elementToBeClickable(By.className(path))).click();
                    break;
                case id:
                    wait.until(ExpectedConditions.elementToBeClickable(By.id(path))).click();
                    break;
                case name:
                    wait.until(ExpectedConditions.elementToBeClickable(By.name(path))).click();
                    break;
                case xPath:
                    wait.until(ExpectedConditions.elementToBeClickable(By.xpath(path))).click();
                    break;
                case cssSelector:
                    wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(path))).click();
                    break;
                case linkText:
                    wait.until(ExpectedConditions.elementToBeClickable(By.linkText(path))).click();
                    break;
                default:
                    new NotFoundException();
            }

        } catch (Exception ex) {
            throw new AssertionError("Element Click method error" + ex.getMessage());
        }
    }
    public WebElement findElement(String path, Pather type,TimeOut timeOut) {
        System.out.println(path+" Elementi bulunuyor");
        try {
            WebDriverWait wait = new WebDriverWait(driver, TimeOut.HIGH.value);
            WebElement element = null;
            switch (type) {
                case className:
                    element = wait.until(ExpectedConditions.presenceOfElementLocated(By.className(path)));
                    break;
                case id:
                    element = wait.until(ExpectedConditions.presenceOfElementLocated(By.id(path)));
                    break;
                case name:
                    element = wait.until(ExpectedConditions.presenceOfElementLocated(By.name(path)));
                    break;
                case xPath:
                    element = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(path)));
                    break;
                case cssSelector:
                    element = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(path)));
                    break;
                case linkText:
                    element = wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText(path)));
                    break;
                default:
                    new NotFoundException();
            }
            return element;
        }
        catch (Exception ex)
        {
            throw new AssertionError("find element method error" + ex.getMessage());
        }
    }

    public WebElement findElement(String path, Pather type, String description) {
        System.out.println(path+" Elementi bulunuyor");
        try {
            WebDriverWait wait = new WebDriverWait(driver, TimeOut.MIDDLE.value);
            WebElement element = null;
            switch (type) {
                case className:
                    element = wait.until(ExpectedConditions.presenceOfElementLocated(By.className(path)));
                    break;
                case id:
                    element = wait.until(ExpectedConditions.presenceOfElementLocated(By.id(path)));
                    break;
                case name:
                    element = wait.until(ExpectedConditions.presenceOfElementLocated(By.name(path)));
                    break;
                case xPath:
                    element = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(path)));
                    break;
                case cssSelector:
                    element = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(path)));
                    break;
                case linkText:
                    element = wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText(path)));
                    break;
                default:
                    new NotFoundException();
            }
            return element;
        } catch (Exception ex) {
            throw new AssertionError("find element method error" + ex.getMessage());
        }
    }


    public void PageScrolldown() {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("window.scrollBy(0,300)", "");

    }

    public void PageScrollup() {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("window.scrollBy(0,-300)", "");
    }


    public void DriverQuit() {
        System.out.println("Browser kapatıldı");
        if (driver != null) {
            driver.quit();
            driver = null;
        }
        }

    public void scrollToElementUsingJS(String path, Pather type) {
        try
        {
            Thread.sleep(1000);
            WebElement element = findElement(path,type,TimeOut.LOW);
            Thread.sleep(1000);
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView(true);", element);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    public void scrollToElementSlowlyUsingActions(String path, Pather type) {
        WebElement element = findElement(path, type, TimeOut.LOW);
        Actions actions = new Actions(driver);
        // Element görünür hale gelene kadar küçük adımlarla kaydır
        while (!element.isDisplayed()) {
            actions.sendKeys(Keys.PAGE_DOWN).perform();
            try {
                Thread.sleep(100); // Bekleme süresi (milisaniye)
            } catch (InterruptedException e) {
                System.out.println("Kaydırma işlemi kesildi: " + e.getMessage());
            }
        }
        System.out.println("Element bulundu ve görünür hale geldi.");
    }


    public void hoverOverElementWithWait(String path, Pather type, int timeoutInSeconds) {
        timeoutInSeconds=timeoutInSeconds*1000;
        try {
            WebElement element = findElement(path,type,TimeOut.LOW);
            // Explicit Wait ile elementi bekle
            WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
            wait.until(ExpectedConditions.visibilityOf(element));

            // Actions ile elementin üzerine gel
            Actions actions = new Actions(driver);
            actions.moveToElement(element).perform();

            System.out.println("Elementin üzerinde başarıyla beklendi.");
        } catch (Exception e) {
            System.out.println("Hata oluştu: " + e.getMessage());
        }
    }

    public void waitForSeconds(int time)
    {
     System.out.println(time+" saniye bekle");
     time=time*1000;
     try {
         Thread.sleep(time);

     } catch (InterruptedException e) {
         System.out.println("Bekleme işlemi sırasında hata oluştu");
         throw new RuntimeException(e);
     }

    }

    public void areElementsIncludeTheText(String path, Pather type, String text)
    {
        System.out.println(path+" Elementler içinde '"+text+"' metni aranıyor");
        By locator = null;
        try {
            WebDriverWait wait = new WebDriverWait(driver, TimeOut.MIDDLE.value);
            switch (type) {
                case className:
                    locator = By.className(path);
                    break;
                case id:
                    locator = By.id(path);
                    break;
                case name:
                    locator = By.name(path);
                    break;
                case xPath:
                    locator = By.xpath(path);
                    break;
                case cssSelector:
                    locator = By.cssSelector(path);
                    break;
                case linkText:
                    locator = By.linkText(path);
                    break;
                default:
                    new NotFoundException();
            }
        } catch (Exception ex) {
            throw new AssertionError("find element method error" + ex.getMessage());
        }
        List<WebElement> webElements = driver.findElements(locator);
        System.out.println("bulunanan element : "+webElements.size());
        int i = 0;
        for(WebElement element : webElements)
        {
            i++;
            if(text.equals(element.getText()))
            {
                System.out.println(i+". Element '"+text+"' metnini içeriyor");
            }
            else {
                throw new AssertionError(i+". Element '"+text+"' metnini içermiyor '"+element.getText()+"' yazıyor" );
            }
        }
    }

    public void switchThePage()
    {
        System.out.println("İşlem yapılacak sayfa değiştiriliyor");
        try {
            // Mevcut pencereyi kaydet
            String originalWindow = driver.getWindowHandle();

            // Tüm pencereleri al
            Set<String> allWindows = driver.getWindowHandles();

            // Yeni sekmeye geçiş yap
            for (String window : allWindows) {
                if (!window.equals(originalWindow)) {
                    driver.switchTo().window(window);
                    break;
                }
            }

        } catch (Exception e) {
            System.out.println("Hata oluştu: " + e.getMessage());
        }
    }

    public void setScenarioComplated()
    {
        System.out.println("Senaryo tamamlandı");
        isScenarioComplated=true;
    }




    }

