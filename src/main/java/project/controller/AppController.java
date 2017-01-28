package project.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import project.controller.command.ActionCommand;
import project.controller.command.CommandFactory;
import project.controller.wrapper.RequestWrapper;

@WebServlet("/controller")
public class AppController extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(AppController.class);
    private static final long serialVersionUID = 1L;

    public AppController() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        LOGGER.debug("GET REQUEST");
        process(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        LOGGER.debug("POST REQUEST");
        process(request, response);
    }

    private void process(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestWrapper requestWrapper = new RequestWrapper(request, response);
        ActionCommand command = CommandFactory.defineCommand(requestWrapper);
        LOGGER.debug("COMMAND CLASS:" + command.getClass());
        String path = command.execute(requestWrapper);
        LOGGER.debug("PATH AFTER EXECUTING COMMAND :" + path);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(path);
        requestDispatcher.forward(request, response);
    }

}
