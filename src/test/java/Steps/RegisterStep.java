package Steps;

import Base.BaseStep;
import ScreenshotUtil.ScreenshotUtil;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;

import java.util.UUID;

public class RegisterStep extends BaseStep {
    String uuid = UUID.randomUUID().toString();

    @Before
    public void setUp() {
        System.out.println("\n----> Test başlamadan önce yapılacak işlemler <----\n");
        isScenarioComplated=false;

    }

    @After
    public void tearDown() {
        System.out.println("\n---->Test tamamlandıktan sonra yapılacak işlemler <----\n");
        if (isScenarioComplated==false){
            ScreenshotUtil.takeScreenshot(driver, "hata_durumu");
        }
        DriverQuit();
    }

    @Given("^Open \"([^\"]*)\" page$")
    public void openNewPage(String url) {
        geturl(url);
    }

    @Then("^Check, is URL True \"([^\"]*)\"$")
    public void checkUrl(String expectedUrl) {
        expectedUrl = addHttps(expectedUrl);
        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals("URL eşleşmedi! \nBeklenen: " + expectedUrl + ", \nBulunan: " + currentUrl, expectedUrl, currentUrl);
        System.out.println("URL'ler eşleşti \nBeklenen URL : "+ expectedUrl + "\n Güncel URl : "+currentUrl );
    }


    @When("^Click \"([^\"]*)\" Object by \"([^\"]*)\"$")
    public void clickObject(String locator, Pather locatorType) {
        findElementClick(locator,locatorType);

    }

    @When("^Check \"([^\"]*)\" Object by \"([^\"]*)\"$")
    public void checkObject(String locator, Pather locatorType) {
        findElement(locator,locatorType,TimeOut.MIDDLE);
    }

    @When("^Scroll to \"([^\"]*)\" Object by \"([^\"]*)\"$")
    public void scrollToObject(String locator, Pather locatorType) {
        scrollToElementUsingJS(locator,locatorType);
    }

    @When("^Slow scroll to \"([^\"]*)\" Object by \"([^\"]*)\"$")
    public void slowScrollToObject(String locator, Pather locatorType) {
        scrollToElementUsingJS(locator,locatorType);
    }

    @When("^Wait \"([^\"]*)\" seconds over \"([^\"]*)\" Object by \"([^\"]*)\"$")
    public void hoverOverElement(int timeoutInSeconds,String locator, Pather locatorType) {
        hoverOverElementWithWait(locator,locatorType,timeoutInSeconds);
    }

    @When("^Wait for \"([^\"]*)\" seconds$")
    public void waitForSecondsBdd(int time)
    {
        waitForSeconds(time);
    }

    @When("^Check \"([^\"]*)\" elemets by \"([^\"]*)\" for \"([^\"]*)\" text$")
            public  void areElementsIncludeTheTextBdd(String locator, Pather locatorType,String text)
    {
        areElementsIncludeTheText(locator,locatorType,text);
    }

    @Given("^Switch the page$")
    public void switchThePageBdd()
    {switchThePage();}

    @Given("^Finish the scenario$")
    public void setScenarioComplatedBdd()
    {setScenarioComplated();}

    @And("^driver close$")
    public void driverClose() {
        DriverQuit();
    }

}
