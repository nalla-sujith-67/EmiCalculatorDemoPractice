package testBase;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.time.Duration;

public class BaseClass {
    public WebDriver driver;
    public Logger log;
    @BeforeClass
    public void driverSetup(){
        driver = new ChromeDriver();
        driver.get("https://hyperstrom.github.io/EMI_Calculation/");

        //this is to maximise the window
        driver.manage().window().maximize();

        //this is to implement the waits for all the webelements for about 10 sec.
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
        log = LogManager.getLogger(this.getClass());
        System.out.println(driver);
    }


    @AfterClass
    public void tearDown(){
        driver.quit();
    }

}
