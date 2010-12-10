package org.tomis.mvc.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Project: tomis-mvc
 * @since 05.03.2010
 * @author Dan Persa
 */
public abstract class PageFragmentController extends HttpServlet {

    private static final Logger logger = LoggerFactory.getLogger(PageFragmentController.class);
    public static final String REDIRECT_URL = "redirect-url";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.trace("start");
        populatePageFragments(request, response);
        referenceData(request, response);
        logger.trace("end");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.trace("start");
        doGet(request, response);
        logger.trace("end");
    }

    protected abstract void referenceData(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

    protected void populatePageFragments(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (getPageFragmentControllers() == null || getPageFragmentControllers().isEmpty()) {
            return;
        }
        for (String pageFragmentController : getPageFragmentControllers()) {
            logger.info("including page fragment: " + pageFragmentController);
            // if we redirect, we don't include the rest of the controllers!
            if (isRedirect(request)) {
                break;
            }
            include(request, response, pageFragmentController);
        }
    }

    public void include(HttpServletRequest request, HttpServletResponse response, String servletName) throws ServletException, IOException {
        RequestDispatcher dispatcher = getServletContext().getNamedDispatcher(servletName);
        dispatcher.include(request, response);
    }

    public void redirect(HttpServletRequest request, String url) {
        request.setAttribute(REDIRECT_URL, url);
    }

    public boolean isRedirect(HttpServletRequest request) {
        if (request.getAttribute(REDIRECT_URL) != null) {
            return true;
        }
        return false;
    }

    public String getRedirectURL(HttpServletRequest request) {
        return (String) request.getAttribute(REDIRECT_URL);
    }

    public List<String> getPageFragmentControllers() {
        return null;
    }
}
