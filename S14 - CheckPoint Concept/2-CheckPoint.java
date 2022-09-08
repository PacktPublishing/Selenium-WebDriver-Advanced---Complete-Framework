package com.letskodeit.base;

import org.testng.Assert;

import java.util.ArrayList;
import java.util.HashMap;

public class CheckPoint {
    public static HashMap<String, String> resultMap = new HashMap<String, String>();
    private static String PASS = "PASS";
    private static String FAIL = "FAIL";

    /***
     * Clears the results hash map
     */
    public static void clearHashMap() {
        System.out.print("Clearing Results Hash Map");
        resultMap.clear();
    }

    /***
     * Set status of the Result Map
     * @param mapKey
     * @param status
     */
    private static void setStatus(String mapKey, String status) {
        resultMap.put(mapKey, status);
        System.out.println(mapKey + " :-> " + resultMap.get(mapKey));
    }

    public static void mark(String testName, boolean result, String resultMessage) {
        testName = testName.toLowerCase();
        String mapKey = testName + "." + resultMessage;
        try {
            if (result) {
                setStatus(mapKey, PASS);
            } else {
                setStatus(mapKey, FAIL);
            }
        } catch (Exception e) {
            System.out.println("Exception Occurred...");
            setStatus(mapKey, FAIL);
            e.printStackTrace();
        }
    }

    public static void markFinal(String testName, boolean result, String resultMessage) {
        testName = testName.toLowerCase();
        String mapKey = testName + "." + resultMessage;
        try {
            if (result) {
                setStatus(mapKey, PASS);
            } else {
                setStatus(mapKey, FAIL);
            }
        } catch (Exception e) {
            System.out.println("Exception Occurred...");
            setStatus(mapKey, FAIL);
            e.printStackTrace();
        }

        ArrayList<String> resultList = new ArrayList<String>();

        for (String key: resultMap.keySet()) {
            resultList.add(resultMap.get(key));
        }

        for (int i = 0; i < resultList.size(); i++) {
            if (resultList.contains(FAIL)) {
                System.out.println("Test Method Failed");
                Assert.assertTrue(false);
            } else {
                System.out.println("Test Method Successful");
                Assert.assertTrue(true);
            }
        }
    }
}











