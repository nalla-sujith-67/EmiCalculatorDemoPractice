package utils;

import org.testng.annotations.DataProvider;

import java.io.IOException;

public class DataProviderUtils {

    @DataProvider(name = "individualFieldsData")
    public String[][] individualFieldsData() throws IOException {
        String filepath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\testData.xlsx";
        return ExcelUtils.getSheetDataAsString(filepath,"IndividualFields",false);
    }
}
