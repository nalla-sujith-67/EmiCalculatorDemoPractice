package pageObjects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.devtools.v135.page.Page;
import org.openqa.selenium.support.PageFactory;

public class BaseClass {
    public static WebDriver driver;
    public Logger log;
    public BaseClass(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
        log = LogManager.getLogger(this.getClass());
    }

}
