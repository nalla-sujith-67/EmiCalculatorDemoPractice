package testCases;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import testBase.BaseClass;
import utils.DataProviderUtils;

public class HomePageTest extends BaseClass {
    HomePage home;
    @BeforeMethod
    public void setup(){
        home = new HomePage(driver);

    }

    public boolean validate_fields(String value, String givesError, String enteredValue, String expectedErrorMessage, String actualErrorMessage) {
        if(givesError.equalsIgnoreCase("yes")){
            log.info("{} is the expected value and the actual value is {}",expectedErrorMessage,actualErrorMessage);
            return expectedErrorMessage.equals(actualErrorMessage);
        }else{
            return value.equals(enteredValue);
        }
    }

    //clicking the home tab
    @Test(priority = 1)
    public void click_home_tab(){
        try{
            home.clickHomeTab();
        }catch(Exception e){
            Assert.fail("the home tab click is failed as "+e.getMessage());
        }
    }

    //first validate all the individual input fields
    @Test(priority = 1, groups = {"individual-test"},dataProviderClass = DataProviderUtils.class,dataProvider = "individualFieldsData")
    public void individual_field_validation_home_tab(String field, String value, String givesError, String enteredValue, String errorMessage){
        if(field.equalsIgnoreCase("principal")){
            home.setPrincipal(value);
            String actualError = "";
            if(givesError.equalsIgnoreCase("yes")){
                actualError = home.getPrincipalError();
            }
            String enteredPrincipal = home.getPrincipal();
            if(value.matches("[a-zA-Z]+")){
                value = "";
            }
            Assert.assertTrue(validate_fields(value,givesError,enteredPrincipal,errorMessage,actualError));
        }else if(field.equalsIgnoreCase("Loan Rate")){
            home.setLoanRate(value);
            String actualError = "";
            if(givesError.equalsIgnoreCase("yes")){
                actualError = home.getLoanRateError();
            }
            String enteredLoanRate = home.getLoanRate();
            Assert.assertTrue(validate_fields(value,givesError,enteredLoanRate,errorMessage,actualError));
        }else if(field.equalsIgnoreCase("DownPayment")) {
            home.setDownPayment(value);
            String actualError = "";
            if(givesError.equalsIgnoreCase("yes")){
                actualError = home.getDownPaymentError();
            }
            String enteredDownPayment = home.getDownPayment();
            Assert.assertTrue(validate_fields(value,givesError,enteredDownPayment,errorMessage,actualError));
        }else if(field.equalsIgnoreCase("years")){
            home.setYears(value);
            String actualError = "";
            String enteredYears = home.getYears();

            if(givesError.equalsIgnoreCase("yes") && Integer.parseInt(enteredYears) <= 30){
                actualError = home.getYearsError();
            }

            if(givesError.equalsIgnoreCase("yes") && Integer.parseInt(enteredYears) > 30){
                actualError = home.getTimeLimitError();
            }
            Assert.assertTrue(validate_fields(value,givesError,enteredYears,errorMessage,actualError));
        }else if(field.equalsIgnoreCase("months")){
            home.setMonths(value);
            String actualError = "";
            if(givesError.equalsIgnoreCase("yes")){
                actualError = home.getMonthsError();
            }

            String enteredMonths = home.getMonths();
            Assert.assertTrue(validate_fields(value,givesError,enteredMonths,errorMessage,actualError));
        }
    }

    //then validate the existence and the text presence of the buttons

//    @Test(priority = 3)
//    public void test_the_action_buttons(){
//
//    }

    //then validate the working of the buttons

    //then validate the presence and the working of the result section

    //
}
