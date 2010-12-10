package org.tomis.mvc.controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Project: tomis-mvc
 * @since 05.03.2010
 * @author Dan Persa
 */
public abstract class PageController extends PageFragmentController {

    private static Logger logger = LoggerFactory.getLogger(PageController.class);
    private String view;

    @Override
    public void init() throws ServletException {
        super.init();
        setView(getServletConfig().getInitParameter("view"));
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.trace("start");
        request.setAttribute("currentPage", request.getServletPath().substring(1));
        super.doGet(request, response);
        viewFreemarker(request, response);
        logger.trace("end");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.trace("start");
        logger.trace("setting currentPage attribute as: " + request.getServletPath().substring(1));
        request.setAttribute("currentPage", request.getServletPath().substring(1));
        super.doPost(request, response);
        logger.trace("end");
    }

    private void viewFreemarker(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (isRedirect(request)) {
            logger.info("Send redirect: " + getRedirectURL(request));
            response.sendRedirect(getRedirectURL(request));
            return;
        }
        logger.info("dispatch to view: " + getView());
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(getView());
        dispatcher.forward(request, response);
    }

    public String getView() {
        return view;
    }

    private void setView(String view) {
        this.view = view;
    }
}
