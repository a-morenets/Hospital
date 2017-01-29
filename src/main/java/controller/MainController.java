package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.commands.*;

import org.apache.log4j.Logger;
import view.Paths;

/**
 * Servlet implementation class MainController
 * Created by alexey.morenets@gmail.com on 11.11.2016.
 */
@WebServlet("/rest/*")
public class MainController extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(MainController.class);

    private CommandsHolder commandsHolder;

    public MainController() {
        super();
        commandsHolder = new CommandsHolder();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String commandKey = getCommandKey(request);
        LOGGER.debug(commandKey);
        Command command = commandsHolder.getCommand(commandKey);
        String viewPage = "";
        viewPage = command.execute(request, response);
        if (!viewPage.equals(Paths.REDIRECTED)) {
            LOGGER.debug("FORWARD to " + viewPage);
            request.getRequestDispatcher(viewPage).forward(request, response);
        }
    }

    private String getCommandKey(HttpServletRequest request) {
        String method = request.getMethod().toUpperCase();
        String path = request.getRequestURI().replaceAll(".*/rest", "");
        return method + ":" + path;
    }

}
