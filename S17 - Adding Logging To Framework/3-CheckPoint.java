package com.letskodeit.base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import java.util.ArrayList;
import java.util.HashMap;

public class CheckPoint {

    private static final Logger log = LogManager.getLogger(CheckPoint.class.getName());
    public static HashMap<String, String> resultMap = new HashMap<String, String>();
    private static String PASS = "PASS";
    private static String FAIL = "FAIL";

    /***
     * Clears the results hash map
     */
    public static void clearHashMap() {
        log.info("Clearing Results Hash Map");
        resultMap.clear();
    }

    /***
     * Set status of the Result Map
     * @param mapKey
     * @param status
     */
    private static void setStatus(String mapKey, String status) {
        resultMap.put(mapKey, status);
        log.info(mapKey + " :-> " + resultMap.get(mapKey));
    }

    /**
     * Keeps the verification point status with testName, Result and Verification Point Message in hash map
     *
     * @param testName      - The test case name
     * @param result        - Verification Result from test method
     * @param resultMessage - Message tagged with verification
     */
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
            log.error("Exception Occurred...");
            setStatus(mapKey, FAIL);
            e.printStackTrace();
        }
    }

    /**
     * Keeps the verification point status with testName, Result and Verification Point Message in hash map
     * It asserts all the verifications in a test method, if any verification
     * in a test method is failed then the test case is failed
     *
     * @param testName      - The test case name
     * @param result        - Verification Result from test method
     * @param resultMessage - Message tagged with verification
     */
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
            log.error("Exception Occurred...");
            setStatus(mapKey, FAIL);
            e.printStackTrace();
        }

        ArrayList<String> resultList = new ArrayList<String>();

        for (String key: resultMap.keySet()) {
            resultList.add(resultMap.get(key));
        }

        for (int i = 0; i < resultList.size(); i++) {
            if (resultList.contains(FAIL)) {
                log.info("Test Method Failed");
                Assert.assertTrue(false);
            } else {
                log.info("Test Method Successful");
                Assert.assertTrue(true);
            }
        }
    }
}