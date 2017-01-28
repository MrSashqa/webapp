package project.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import project.controller.command.ActionCommand;
import project.controller.command.CommandFactory;
import project.controller.wrapper.RequestWrapper;

@WebServlet("/controller")
public class AppController extends HttpServlet {

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
        RequestWrapper requestWrapper = new RequestWrapper(request, response);
        ActionCommand command = CommandFactory.defineCommand(requestWrapper);
        String path = command.execute(requestWrapper);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(path);
        requestDispatcher.forward(request, response);
    }

}
