package com.letskodeit.utilities;

import com.google.common.collect.Ordering;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Util {
    private static final Logger log = LogManager.getLogger(Util.class.getName());

    /***
     * Sleep for specified number of milliseconds
     * @param msec
     * @param info
     */
    public static void sleep(long msec, String info) {
        if (info != null) {
            log.info("Waiting " + (msec * .001) + " seconds :: " + info);
        }
        try {
            Thread.sleep(msec);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /***
     * Sleep for specified number of milliseconds
     * @param msec
     */
    public static void sleep(long msec) {
        sleep(msec, null);
    }

    /***
     * Get a unique screenshot name
     * @param methodName
     * @return
     */
    public static String getScreenshotName(String methodName, String browserName) {
        String localDateTime = getCurrentDateTime();
        StringBuilder name = new StringBuilder().append(browserName)
                                                .append("_")
                                                .append(methodName)
                                                .append("_")
                                                .append(localDateTime)
                                                .append(".png");
        return name.toString();
    }

    /***
     * Get a unique report name
     * @return
     */
    public static String getReportName() {
        String localDateTime = getCurrentDateTime();
        StringBuilder name = new StringBuilder()
                                        .append("AutomationReport_")
                                        .append(localDateTime)
                                        .append(".html");
        return name.toString();
    }

    /***
     * Get Random number within specified range
     * @param min
     * @param max
     * @return a random number
     */
    public static int getRandomNumber(int min, int max) {
        int diff = max - min;
        int randomNum = (int)(min + Math.random() * diff);
        log.info("Random Number :: " + randomNum +
                " within range :: " + min + " and :: " + max);
        return randomNum;
    }

    /***
     * Get Random number within specified range
     * @param number
     * @return a random number
     */
    public static int getRandomNumber(int number) {
        return getRandomNumber(1, number);
    }

    /***
     * Get random unique string with specified length
     * @param length
     * @return a unique string
     */
    public static String getRandomString(int length) {
        StringBuilder sbuilder = new StringBuilder();
        String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        for (int i = 0; i<length; i++) {
            int index = (int) (Math.random() * chars.length());
            sbuilder.append(chars.charAt(index));
        }
        String randomString = sbuilder.toString();
        log.info("Random string with length :: "
                + length + " is :: " + randomString);
        return randomString;
    }

    /***
     * Get random unique string with 10 characters length
     * @return a unique string
     */
    public static String getRandomString() {
        return getRandomString(10);
    }

    /***
     * Get simple date as string in the specified format
     * @param format MMddyy MMddyyyy
     * @return date as string type
     */
    public static String getSimpleDateFormat(String format){
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        String formattedDate = formatter.format(date);
        log.info("Date with format :: " + format + " :: " + formattedDate);
        return formattedDate;
    }

    /***
     * Get simple date time as string
     * @return date time as string type
     */
    public static String getCurrentDateTime(){
        Calendar currentDate = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat(
                "MM/dd/yyyy HH:mm:ss");
        String date = formatter.format(currentDate.getTime()).replace("/", "_");
        date = date.replace(":", "_");
        log.info("Current Date and Time :: " + date);
        return date;
    }

    /**
     * Checks whether actual String contains expected string and prints both in log
     * @param actualText - actual Text picked up from application under Test
     * @param expText - expected Text to be checked with actual text
     * @return boolean result
     */
    public static boolean verifyTextContains(String actualText, String expText) {
        if (actualText.toLowerCase().contains(expText.toLowerCase())){
            log.info("Actual Text From Web Application UI   --> : "+ actualText);
            log.info("Expected Text From Web Application UI --> : "+ expText);
            log.info("### Verification Contains !!!");
            return true;
        }
        else{
            log.error("Actual Text From Web Application UI   --> : "+ actualText);
            log.error("Expected Text From Web Application UI --> : "+ expText);
            log.error("### Verification DOES NOT Contains !!!");
            return false;
        }

    }

    /**
     * Checks whether actual string matches with expected string and prints both in log
     * @param actualText - actual Text picked up from application under Test
     * @param expText - expected Text to be matched with actual text
     * @return
     */
    public static boolean verifyTextMatch(String actualText, String expText) {
        if (actualText.equals(expText)){
            log.info("Actual Text From Web Application UI   --> : "+ actualText);
            log.info("Expected Text From Web Application UI --> : "+ expText);
            log.info("### Verification MATCHED !!!");
            return true;
        }else{
            log.error("Actual Text From Web Application UI   --> : "+ actualText);
            log.error("Expected Text From Web Application UI --> : "+ expText);
            log.error("### Verification DOES NOT MATCH !!!");
            return false;
        }
    }

    /***
     * Verify List is not empty
     * @param actualList - actual list that needs to be verified
     * @return
     */
    public static Boolean verifyListNotEmpty(List actualList) {
        int listSize = actualList.size();
        log.info("Size of list :: " + listSize);
        if (listSize > 0) {
            log.info("List is not empty");
            return true;
        } else {
            log.error("List is empty");
            return false;
        }
    }

    /**
     * Verify actual list contains items of the expected list
     *
     * @param actList
     * @param expList
     * @return
     */
    public static Boolean verifyListContains(List<String> actList, List<String> expList) {
        int expListSize = expList.size();
        for (int i = 0; i < expListSize; i++) {
            if (!actList.contains(expList.get(i))) {
                return false;
            }
        }
        log.info("Actual List Contains Expected List !!!");
        return true;
    }

    /***
     * Verify actual list matches expected list
     * @param actList
     * @param expList
     * @return
     */
    public static Boolean verifyListMatch(List<String> actList, List<String> expList) {
        boolean found = false;
        int actListSize = actList.size();
        int expListSize = expList.size();
        if (actListSize != expListSize) {
            return false;
        }

        for (int i = 0; i < actListSize; i++) {
            found = false;
            for (int j = 0; j < expListSize; j++) {
                if (verifyTextMatch(actList.get(i), expList.get(j))) {
                    found = true;
                    break;
                }
            }
        }
        if (found) {
            log.info("Actual List Matches Expected List !!!");
            return true;
        }
        else {
            log.info("Actual List DOES NOT Match Expected List !!!");
            return false;
        }
    }

    /**
     * Verifies item is present in the list
     * @param actList
     * @param item
     * @return boolean result
     */
    public static Boolean verifyItemPresentInList(List<String> actList, String item){
        int actListSize = actList.size();
        for (int i = 0; i < actListSize; i++) {
            if (!actList.contains(item)) {
                log.error("Item is NOT present in List !!!");
                return false;
            }
        }
        log.info("Item is present in List !!!");
        return true;
    }

    /**
     * Verify if list is in ascending order
     * @param list
     * @return boolean result
     */
    public static boolean isListAscendingOrder(List<Long> list){
        boolean sorted = Ordering.natural().isOrdered(list);
        return sorted;
    }
}