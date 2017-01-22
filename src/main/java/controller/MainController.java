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
import view.GlobalConstants;

/**
 * Servlet implementation class MainController
 */
@WebServlet("/rest/*")
public class MainController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public static final String POST = "POST:";
    public static final String GET = "GET:";

    private Map<String, Command> commands = new HashMap<>();

    public MainController() {
        super();
    }

    public void init(ServletConfig config) throws ServletException {
        commands.put(POST + GlobalConstants.LOGIN, new LoginCommand());
        commands.put(POST + GlobalConstants.SHOW_PATIENTS, new ShowPatientsCommand());
        commands.put(POST + GlobalConstants.ADD_PATIENT, new AddPatientCommand());

        commands.put(GET + GlobalConstants.ADD_PATIENT_FORM, new AddPatientFormCommand());
        commands.put(GET + GlobalConstants.SHOW_PATIENT_INFO, new ShowPatientInfoCommand());
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
        Command command = commands.getOrDefault(key, (req, resp) -> GlobalConstants.INDEX_JSP);
        String viewPage = command.execute(request, response);
        request.getRequestDispatcher(viewPage).forward(request, response);
    }

}
