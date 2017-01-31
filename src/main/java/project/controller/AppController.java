package project.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import project.controller.command.Action;
import project.controller.command.Action.ActionType;
import project.controller.command.ActionCommand;
import project.controller.command.CommandFactory;
import project.controller.wrapper.RequestWrapper;


@WebServlet("/")
public class AppController extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(AppController.class);
    private static final long serialVersionUID = 1L;

    public AppController() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        process(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        process(request, response);
    }

    private void process(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        LOGGER.info("Controller REQUEST URI:" + request.getRequestURI());
        ActionCommand cmd = CommandFactory.defineCommand(request.getRequestURI());
        Action action = cmd.execute(new RequestWrapper(request));
        if (action.getActionType().equals(ActionType.REDIRECT)) {
            response.sendRedirect(action.getUrl());
        } else {
            request.getRequestDispatcher(action.getUrl()).forward(request, response);
        }
    }
}


