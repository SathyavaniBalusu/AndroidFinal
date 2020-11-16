package BusinessLogic;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import static Properties.Xpaths.*;

public class Login {
    public static void Loginpage(String User,String Pwd) throws MalformedURLException, InterruptedException {
        System.out.println("inside choose method");
        DesiredCapabilities capability = new DesiredCapabilities();
        capability.setCapability(MobileCapabilityType.DEVICE_NAME, "Sathya");
        capability.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        capability.setCapability(MobileCapabilityType.BROWSER_NAME, "Chrome");
        AndroidDriver driver = new AndroidDriver(new URL("http://0.0.0.0:4723/wd/hub"), capability);
        driver.get("https://magento.com/");
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.findElement(By.xpath(model)).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath(MyAccount)).click();
        Thread.sleep(3000);
        System.out.println("Running the login class");
        System.out.println(User+Pwd);
        driver.findElement(By.xpath(UserName)).sendKeys(User);
        driver.findElement(By.xpath(Password)).sendKeys(Pwd);
        driver.hideKeyboard();
        driver.findElement(By.xpath(SubmitButton)).click();
        Thread.sleep(4000);
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(document.body.scrollHeight, 0)");
        try {
            boolean invalid_message = driver.findElement(By.xpath(message)).isDisplayed();
            //if (invalid_message == true) {
                System.out.println("invalid credentials are entered");
            //} else {
              //  System.out.println("Logged in successfully");
            //}
        }
        catch(NoSuchElementException e){
            System.out.println(e);
        }
    }
}
