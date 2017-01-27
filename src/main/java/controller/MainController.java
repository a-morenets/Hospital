package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletConfig;
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

    private Map<String, Command> commands;

    public MainController() {
        super();
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        commands = CommandsHolder.initCommands();
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
        String method = request.getMethod().toUpperCase();
        String path = request.getRequestURI().replaceAll(".*/rest", "");
        String key = method + ":" + path;
        LOGGER.debug(key);
        Command command = commands.getOrDefault(key, (req, resp) -> Paths.REDIRECT);
        String viewPage = command.execute(request, response);
        if (viewPage.equals(Paths.REDIRECT)) {
            LOGGER.debug("REDIRECT to " + Paths.REST_HOME);
            response.sendRedirect(Paths.REST_HOME);
        } else {
            LOGGER.debug("FORWARD to " + viewPage);
            request.getRequestDispatcher(viewPage).forward(request, response);
        }
    }

}
