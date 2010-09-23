package org.tomis.mvc.controller.helper;

import javax.servlet.ServletRequest;
import org.apache.log4j.Logger;

/**
 * Project: tomis-mvc
 * @since 10.02.2010
 * @author Dan Persa
 */
public class RequestHelper {

    private static Logger logger = Logger.getLogger(RequestHelper.class);

    public static int getIntParameter(ServletRequest request, String paramName) {
        int i = 0;
        try {
            i = Integer.parseInt(request.getParameter(paramName));
        } catch (Exception e) {
            logger.error("Could not get the parameter: " + paramName);
            logger.error("Exception: " + e);
        }
        return i;
    }

    public static long getLongParameter(ServletRequest request, String paramName) {
        long l = 0;
        try {
            l = Long.parseLong(request.getParameter(paramName));
        } catch (Exception e) {
            logger.error("Could not get the parameter: " + paramName);
            logger.error("Exception: " + e);
        }
        return l;
    }
}
