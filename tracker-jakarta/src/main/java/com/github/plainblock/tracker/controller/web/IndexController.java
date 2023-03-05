package com.github.plainblock.tracker.controller.web;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.ws.rs.core.MediaType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.plainblock.tracker.controller.constant.WebEndpoint;
import com.github.plainblock.tracker.util.LogUtil;

@WebServlet(WebEndpoint.TOP)
public class IndexController extends HttpServlet {

    private static final Logger LOGGER = LoggerFactory.getLogger(IndexController.class);

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        LogUtil.loggingRequest(LOGGER, request);
        try {
            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/index.jsp");
            response.setContentType(MediaType.TEXT_HTML);
            rd.forward(request, response);
        } catch (ServletException | IOException e) {
            LOGGER.error(e.getMessage());
        }
    }

}
