package com.jamedow.laodoufang.utils;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpSession;

/**
 * Description
 * <p>
 * Created by 365 on 2017/2/17 0017.
 */
public class SessionUtils {

    /**
     * add session by key and value
     *
     * @param key     session key (can not be Blank)
     * @param value   session value
     * @param session httpSession
     */
    public static void addSession(String key, String value, HttpSession session) {
        if (session == null) {
            return;
        }
        if (StringUtils.isBlank(key)) {
            return;
        }
        session.setAttribute(key, value);
    }

    /**
     * logout session
     *
     * @param session httpSession
     */
    public static void logoutSession(HttpSession session) {
        if (session == null) {
            return;
        }
        session.invalidate();
    }
}
