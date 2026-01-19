package pageObjects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.openqa.selenium.By.xpath;

public class HomePage extends BaseClass{

    //this constructor is to initialise the page factory with the driver.
    public HomePage(WebDriver driver){
        super(driver);
    }


    //locating all the webElements in the "Home Tab"

    @FindBy(xpath = ("//div[contains(@class,'tab') and text()= 'Home']"))
    WebElement homeTab;

    @FindBy(xpath = "//input[@id='home-principal']")
    WebElement principalField;

    @FindBy(xpath = "//input[@id='home-rate']")
    WebElement rateField;

    @FindBy(xpath = "//input[@id='home-downpayment']")
    WebElement downPaymentField;

    @FindBy(xpath = "//input[@id='home-years']")
    WebElement yearsField;

    @FindBy(xpath = "//input[@id='home-months']")
    WebElement monthsField;

    @FindBy(xpath = "//button[@id='home-calculate-btn']")
    WebElement calculateButton;

    @FindBy(xpath = "//button[@id='home-reset-btn']")
    WebElement resetButton;

    @FindBy(xpath = "//div[@class='result-item']/span[@id='home-emi']")
    WebElement resultMonthlyEMI;

    @FindBy(xpath = "//div[@class='result-item']/span[@id='home-totalprincipal']")
    WebElement totalPrincipal;

    @FindBy(xpath = "//div[@class='result-item']/span[@id='home-totalinterest']")
    WebElement totalInterest;

    @FindBy(xpath = "//div[@class='result-item']/span[@id='home-totalamount']")
    WebElement totalAmount;

    @FindBy(xpath="//div[@class='chart-container']/h2")
    WebElement loanBreakDownText;

    @FindBy(xpath="//div[@class='pie-chart']")
    WebElement pieChart;

    @FindBy(xpath = "//span[@id='chartEmi']")
    WebElement chartEMI;

    @FindBy(xpath = "//span[@id='principal-percentage']")
    WebElement principalPercentage;

    @FindBy(xpath = "//span[@id='interest-percentage']")
    WebElement interestPercentage;

    @FindBy(xpath = "//input[@id='home-principal']/following-sibling::div[@class='error']")
    WebElement principalError;

    @FindBy(xpath = "//input[@id='home-rate']/following-sibling::div[@class='error']")
    WebElement loanRateError;

    @FindBy(xpath = "//input[@id='home-downpayment']/following-sibling::div[@class='error']")
    WebElement downPaymentError;

    @FindBy(xpath = "//input[@id='home-years']/following-sibling::div[@class='error']")
    WebElement yearsError;

    @FindBy(xpath = "//input[@id='home-months']/following-sibling::div[@class='error']")
    WebElement monthsError;

    @FindBy(xpath = "//div[@id='home-time-error-container']/div")
    WebElement homeTimeError;

    //helper methods

    public void enterText(WebElement element, String text, String field){
        element.sendKeys(text);
        log.info("entered the "+ text + " into the "+field + "home tab");
    }



    //this is the field related to the action methods


    public void clickHomeTab() {
        try{
            homeTab.click();
        }catch(RuntimeException e){
            throw new RuntimeException(e.getMessage());
        }
    }
    public HomePage setPrincipal(String text) {
        try{
            principalField.clear();
            enterText(principalField,text,"Principal Field");
            return this;
        }catch(RuntimeException e){
            log.info("This testcase failed because of the following error in the principal field of the home page -> {}",e.toString());
            throw new RuntimeException("unable to interact with the 'Prinicpal' field in the home tab");
        }
    }

    public HomePage setLoanRate(String text){
        try{
            rateField.clear();
            enterText(rateField,text,"Loan Rate");
            return this;
        }catch(RuntimeException e){
            log.info("This testcase failed because of the following error in the rate field of the home page -> {}",e.toString());
            throw new RuntimeException("unable to interact with the 'Loan Rate' field in the home tab");
        }
    }

    public HomePage setDownPayment(String text){
        try{
            downPaymentField.clear();
            enterText(downPaymentField,text,"Down Payment");
            return this;
        }catch(RuntimeException e){
            log.info("This testcase failed because of the following error in the down payment field of the home page -> {}",e.toString());
            throw new RuntimeException("unable to interact with the 'Down Payment' field in the home tab");
        }
    }

    public HomePage setYears(String text) {
        try{
            yearsField.clear();
            enterText(yearsField,text,"Years Field");
            return this;
        }catch(Exception e){
            log.info("This testcase failed because of the following error in the years field of the home page -> {}",e.toString());
            throw new RuntimeException("unable to interact with the 'years' field in the home tab");
        }
    }

    public HomePage setMonths(String text) {
        try{
            monthsField.clear();
            enterText(monthsField,text,"Months Field");
            return this;
        }catch(Exception e){
            log.info("This testcase failed because of the following error in the months field of the home page -> {}",e.toString());
            throw new RuntimeException("unable to interact with the 'months' field in the home tab");
        }
    }

    //this is the end of setter methods


    //function to verify the text in the calculate buttons

    public boolean isCalculateButtonsVisible() {
        if(calculateButton.isDisplayed() && calculateButton.getText().equals("Calculate EMI")) {
            return true;
        }else{
            return false;
        }
    }

    public boolean isResetButtonVisible() {
        if(resetButton.isDisplayed() && resetButton.getText().equals("Reset")) {
            return true;
        }else{
            return false;
        }
    }

    public HomePage clickCalculateButton(){
        try{
            calculateButton.click();
            return this;
        }catch(RuntimeException e){
            log.info("This testcase failed because of the following error in the calculate button of the home page -> {}",e.toString());
            throw new RuntimeException("unable to interact with the 'calculate EMI' Button in the home tab");
        }
    }

    public HomePage clickReset() {
        try{
            resetButton.click();
            return this;
        }catch(Exception e){
            log.info("This testcase failed because of the following error in the reset button of the home page -> {}",e.toString());
            throw new RuntimeException("unable to interact with the 'Reset' Button in the home tab");
        }
    }

    //all getter methods
    public String getPrincipal(){
        return principalField.getAttribute("value");
    }

    public String getLoanRate() {
        return rateField.getAttribute("value");
    }

    public String getDownPayment(){
        return downPaymentField.getAttribute("value");
    }

    public String getYears() {
        return yearsField.getAttribute("value");
    }

    public String getMonths() {
        return monthsField.getAttribute("value");
    }

    //getters for the errors

    public String getPrincipalError() {
        return principalError.getText();
    }

    public String getLoanRateError() {
        return loanRateError.getText();
    }

    public String getDownPaymentError() {
        return downPaymentError.getText();
    }

    public String getYearsError() {
        return yearsError.getText();
    }

    public String getMonthsError() {
        return monthsError.getText();
    }

    public String getTimeLimitError() {
        return homeTimeError.getText();
    }


}
