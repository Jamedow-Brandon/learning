package com.jamedow.laodoufang.utils;

import com.alibaba.fastjson.JSONObject;

/**
 * Description
 * <p>
 * Created by 365 on 2017/2/17 0017.
 */
public class IdentifyingUtils {

    /**
     * send identifying code
     *
     * @param fromPage        from which page
     * @param phone           phone number
     * @param identifyingCode the identifying code
     * @return
     */
    public static JSONObject sendIdentifyingCode(String fromPage, String phone, String identifyingCode) {
        JSONObject result = new JSONObject();

        String key = fromPage + "." + phone;//the identifyingCode's redis key

        return result;
    }

    /**
     * check identifying code
     *
     * @param fromPage                    from which page
     * @param phone                       phone number
     * @param waitForCheckIdentifyingCode the identifying code wait for check
     * @return
     */
    public static JSONObject checkIdentifyingCode(String fromPage, String phone, String waitForCheckIdentifyingCode) {
        JSONObject result = new JSONObject();

        String key = fromPage + "." + phone;//the identifyingCode's redis key

        String identifyingCode = "";//the identifyingCode in redis
        if (waitForCheckIdentifyingCode.equals(identifyingCode)) {
            //success
        } else {
            //fail
        }

        return result;
    }
}
