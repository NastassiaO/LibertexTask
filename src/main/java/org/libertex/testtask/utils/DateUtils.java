package org.libertex.testtask.utils;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;



public class DateUtils {
    protected static final Logger logger = LoggerFactory.getLogger(DateUtils.class);
    private static final String DATE_PATTERNS_PATH = "src/main/resources/dateFormats.json";

    public static List<String> getDatesFormats() {
        return JsonUtils.getList(DATE_PATTERNS_PATH, "formats");
    }


    /**
     * Method verify if user with such date of birth is over 18th years old.
     * @param dateInStr - date in any string format
     * @return boolean value if date is more or equal 18
     * @throws RuntimeException
     */
    public static boolean isUserOver18(String dateInStr) throws RuntimeException {
        long age = getAge(dateInStr);
        logger.info( "Age is " + age);
        return age >= 18;
    }

    public static List<String> generateToListOfDates(LocalDate localDate) {
        Date date = java.sql.Date.valueOf(localDate);
        List<String> results = new ArrayList<>();
        for (String p : DateUtils.getDatesFormats()) {
            results.add(DateUtils.generateDateInFormat(date, p));
        }
        return results;
    }

    public static long getAge(String dateOfBirth){
        Date dateIn = parseToDate(dateOfBirth);
        return DateUtils.getDifferenceInYears(dateIn, new Date());
    }


    /**
     * @param dateFormat   - format of date
     * @param dateInString - date in String format
     * @return Date or null if can't parse
     */
    private static Date tryToParceToDate(String dateFormat, String dateInString) {
        SimpleDateFormat formatter = new SimpleDateFormat(dateFormat, Locale.ENGLISH);
        formatter.setLenient(false);
        try {
            return formatter.parse(dateInString);
        } catch (ParseException e) {
            return null;
        }
    }
    /**
     * @param dateInString - date in string format
     * @return Date or null if date format wasn't identified
     */
    private static Date parseToDate(String dateInString) throws RuntimeException {
        Date returnDate = null;
        for (String p : getDatesFormats()) {
            returnDate = tryToParceToDate(p, dateInString);
            if (returnDate != null) {
                break;
            }
        }
        if (returnDate == null)
            throw new RuntimeException(dateInString + " is not a date.");
        return returnDate;
    }

    private static long getDifferenceInYears(Date startDate, Date endDate) {
        LocalDate startLocalDate = LocalDate.ofInstant(startDate.toInstant(), ZoneId.systemDefault());
        LocalDate endLocalDate = LocalDate.ofInstant(endDate.toInstant(), ZoneId.systemDefault());
        return Period.between(startLocalDate, endLocalDate).getYears();
    }

    private static String generateDateInFormat(Date date, String pattern) {
        SimpleDateFormat df = new SimpleDateFormat(pattern);
        return df.format(date);
    }
}
