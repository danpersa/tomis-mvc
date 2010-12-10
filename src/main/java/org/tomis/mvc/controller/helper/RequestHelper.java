package org.tomis.mvc.controller.helper;

import javax.servlet.ServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Project: tomis-mvc
 * @since 10.02.2010
 * @author Dan Persa
 */
public class RequestHelper {

    private static Logger logger = LoggerFactory.getLogger(RequestHelper.class);

    public static int getIntParameter(ServletRequest request, String paramName) {
        int i = 0;
        try {
            i = Integer.parseInt(request.getParameter(paramName));
        } catch (Exception e) {
//            logger.error("Could not get the parameter: " + paramName);
//            logger.error("Exception: " + e);
        }
        return i;
    }

    public static long getLongParameter(ServletRequest request, String paramName) {
        long l = 0;
        try {
            l = Long.parseLong(request.getParameter(paramName));
        } catch (Exception e) {
//            logger.error("Could not get the parameter: " + paramName);
//            logger.error("Exception: " + e);
        }
        return l;
    }

    public static long getLongAttribute(ServletRequest request, String attributeName) {
        Object o = request.getAttribute(attributeName);
        Long l = null;
        try {
            l = (Long) o;
        } catch (Exception e) {
//            logger.error("Could not get the long attribute: " + attributeName);
//            logger.error("The attribute exists, but is not a Long: " + attributeName);
//            logger.error("Exception: " + e);
        }
        if (l != null) {
            return l;
        }
        return 0;
    }
}
