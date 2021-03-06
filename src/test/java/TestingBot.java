import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class TestingBot {

        public static final String KEY = "f58ee926c31abf4e0ce37902c7c96e31";
        public static final String SECRET = "fd69f7db06f9d9087ef26b034b74fa49";
        public static final String HUB_URL = "http://" + KEY + ":" + SECRET + "@hub.testingbot.com/wd/hub";
        private WebDriver driver;

        @Parameters({"browser"})
        @BeforeClass
        public void setup(String browser) throws MalformedURLException {
            DesiredCapabilities cap = new DesiredCapabilities();
            cap.setBrowserName(browser);
            cap.setCapability("name", "Testing Bot Test 1");
           cap.setPlatform(Platform.MAC);
           cap.setCapability("platform", "Win10");

            URL url = new URL(HUB_URL);
            driver = new RemoteWebDriver(url, cap);

        }
    @Test(priority = 1)
    @Parameters({"username", "password", "name", "shortName", "multiplier"})
    public void setUp(String username, String password, String myName, String myshortName, String myValue) {
        // login the website
        driver.get("https://test-basqar.mersys.io");
        driver.manage().window().maximize();
        driver.findElement(By.cssSelector("[formcontrolname=\"username\"]")).sendKeys(username);
        driver.findElement(By.cssSelector("[formcontrolname=\"password\"]")).sendKeys(password);
        driver.findElement(By.cssSelector("button[aria-label=\"LOGIN\"]")).click();
        // wait = new WebDriverWait(driver, 7);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        // clicking on human resource
        WebElement humanResource = driver.findElement(By.xpath("//span[contains(text(),'Human Resources')]"));
        humanResource.click();

        // clicking on set up
        WebElement setUp = driver.findElement(By.xpath("(//span[contains(text(),'Setup')])[4]"));
        setUp.click();

        // clicking on notation keys
        WebElement notationKey = driver.findElement(By.xpath("//span[@class='nav-link-title ng-star-inserted'][contains(text(),'Notation Keys')]"));
        notationKey.click();

        // click on plus icon

        WebElement plusIcon = driver.findElement(By.cssSelector("svg[data-icon='plus']"));
        plusIcon.click();

        // fill out the name

        WebElement name = driver.findElement(By.xpath("//ms-dialog-content//input[@formcontrolname='name']"));
        name.sendKeys(myName);

        // fill out the shortname

        WebElement shortName = driver.findElement(By.xpath("//ms-dialog-content//input[@formcontrolname='shortName']"));
        shortName.sendKeys(myshortName);

        // fill out the multiplier

        WebElement mName = driver.findElement(By.xpath("//ms-dialog-content//input[@formcontrolname='multiplier']"));
        mName.sendKeys(myValue);

        // click on apply button

        WebElement applyButton = driver.findElement(By.xpath("//ms-button[@caption='GENERAL.BUTTON.APPLY']"));

        applyButton.click();


    }

    @Parameters("name")
    @Test(priority = 2)

    public void deleteElement(String myName) throws InterruptedException {

        String expectedname = myName;

        // click on delete button
        WebElement deleteButton = driver.findElement(By.xpath("//td[contains(text(), '" + expectedname + "')]//..//ms-delete-button"));
        deleteButton.click();
        // click on yes button
        driver.findElement(By.xpath(" //span[contains(text(),'Yes')]")).click();

    }


    @AfterClass
    public void quit() {
        driver.quit();
    }
}