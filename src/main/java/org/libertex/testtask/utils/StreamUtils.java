package org.libertex.testtask.utils;


import java.util.Scanner;

public class StreamUtils {
    /**
     * @param streamMsg
     * @return String with entered text
     */
    public static String getInput(String streamMsg){
        Scanner sc= new Scanner(System.in);
        System.out.print(streamMsg);
        return sc.nextLine();
    }


}
