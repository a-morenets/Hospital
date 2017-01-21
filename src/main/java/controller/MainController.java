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

import controller.commands.Command;
import controller.commands.ShowPatientInfoCommand;
import controller.commands.ShowPatientsCommand;
import controller.commands.LoginCommand;

/**
 * Servlet implementation class MainController
 */
@WebServlet("/rest/*")
public class MainController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private Map<String, Command> commands = new HashMap<>();

    public MainController() {
        super();
    }

    public void init(ServletConfig config) throws ServletException {
        commands.put("POST:/login", new LoginCommand());
        commands.put("POST:/show_patients", new ShowPatientsCommand());

        commands.put("GET:/show_patient", new ShowPatientInfoCommand());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String method = request.getMethod().toUpperCase();
        String path = request.getRequestURI();
        path = path.replaceAll(".*/rest", "");
        String key = method + ":" + path;

        System.out.println(key);

        Command command = commands.getOrDefault(key, (req, resp) -> "/index.jsp");
        String viewPage = command.execute(request, response);
        request.getRequestDispatcher(viewPage).forward(request, response);
    }

}
