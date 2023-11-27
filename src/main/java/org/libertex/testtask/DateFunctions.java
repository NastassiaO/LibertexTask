package org.libertex.testtask;

import org.libertex.testtask.utils.DateUtils;
import org.libertex.testtask.utils.StreamUtils;


public class DateFunctions {
    public static final String CANCEL = "Cancel";

    public static void main(String[] args) {
        String dateIn = StreamUtils.getInput("Enter Date of Birth in English: ");
        Boolean ifHave18 = null;
        do {
            try {
                ifHave18 = DateUtils.isUserOver18(dateIn);
            } catch (RuntimeException ex) {
                dateIn = StreamUtils.getInput("Date format was wrong, please enter Birth date in following formats:" + DateUtils.getDatesFormats() +
                        "/ Or enter " + CANCEL);
            }
        } while (dateIn.equals(CANCEL) && ifHave18 == null);
        System.out.println("User is over 18: " + ifHave18);
    }

}
