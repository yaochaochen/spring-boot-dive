package com.security.dive.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import java.io.BufferedReader;
import java.io.IOException;

public class RequestUtil {


    private static final Logger log = LoggerFactory.getLogger(RequestUtil.class);

    private RequestUtil() {
    }

    /**
     * 获取 request body
     * @param request req
     * @return body str
     */
    public static String obtainBody(ServletRequest request) {


        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();
        try {
            br = request.getReader();
            String str;
            while ((str = br.readLine()) != null) {
                sb.append(str);
            }
            br.close();
        } catch (IOException e) {
            log.error(" requestBody read error");
        } finally {
            if (null != br) {
                try {
                    br.close();
                } catch (IOException e) {
                    log.error(" close io error");
                }
            }
        }
        return sb.toString();

    }
}
