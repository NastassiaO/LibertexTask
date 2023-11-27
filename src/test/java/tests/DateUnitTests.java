package tests;

import org.libertex.testtask.utils.DateUtils;
import org.libertex.testtask.utils.MathUtils;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class DateUnitTests {
    @DataProvider(name = "User has 18th")
    public Object[] userHas18thTodayData() {
        LocalDate now = LocalDate.now();
        LocalDate date = now.minusYears(18);
        LocalDate date1 = now.minusYears(18 + MathUtils.generateRandomNumber(1, 100));
        List<String> resultDates = new ArrayList<>();
        resultDates.addAll(DateUtils.generateToListOfDates(date));
        resultDates.addAll(DateUtils.generateToListOfDates(date1));
        return resultDates.toArray();
    }

    @DataProvider(name = "User doesn't have 18th")
    public Object[] userNotHave18th() {
        LocalDate now = LocalDate.now();
        LocalDate date = now.minusYears(18).plusDays(1);
        LocalDate date2 = now.minusYears(18).
                minusYears(MathUtils.generateRandomNumber(1, 17));
        List<String> resultDates = new ArrayList<>();
        //18 tomorrow
        resultDates.addAll(DateUtils.generateToListOfDates(date));
        //burn today
        resultDates.addAll(DateUtils.generateToListOfDates(now));
        //18 earlier
        resultDates.addAll(DateUtils.generateToListOfDates(date2));
        return resultDates.toArray();
    }

    @DataProvider(name = "Birth Date is in future")
    public Object[] birthDateInFuture() {
        LocalDate now = LocalDate.now();
        LocalDate date = now.plusDays(MathUtils.generateRandomNumber(1, 1000));
        return DateUtils.generateToListOfDates(date).toArray();
    }

    @DataProvider(name = "Not date format")
    public Object[] wrongFormat() {
        return new Object[]{"wrongFromat", "22.23.10999", "13-13-15"};
    }

    @Test(dataProvider = "User has 18th")
    public void verifyFunctionIfUserHas18th(String dateIn){
        Reporter.log("Birth date is " +  dateIn);
        Assert.assertTrue(DateUtils.isUserOver18(dateIn), "User should has 18th. His birthday is: " + dateIn);
    }

    @Test(dataProvider = "Birth Date is in future")
    public void verifyFunctionIfBirthDateInFuture(String dateIn){
        Reporter.log("Birth date is " +  dateIn);
        Assert.assertFalse(DateUtils.isUserOver18(dateIn), "User shouldn't has 18th. His birthday is: " + dateIn);
    }

    @Test(dataProvider = "Not date format", expectedExceptions = { RuntimeException.class})
    public void verifyFunctionIfWrongFormatWasEntered(String dateIn){
        Reporter.log("Birth date is " +  dateIn);
        DateUtils.isUserOver18(dateIn);
    }

}
