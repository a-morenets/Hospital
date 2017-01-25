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
import view.GlobalConstants;

/**
 * Servlet implementation class MainController
 */
@WebServlet("/rest/*")
public class MainController extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(MainController.class);

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
        commands.put(POST + GlobalConstants.ADD_ASSIGNATIONS, new AddAssignationsCommand());
        commands.put(POST + GlobalConstants.ADD_DIAGNOSIS, new AddDiagnosisCommand());
        commands.put(POST + GlobalConstants.SHOW_PATIENT_INFO, new ShowPatientInfoCommand());
// TODO GET & POST дублируются
        commands.put(GET + GlobalConstants.SHOW_LOGIN_FORM, new ShowLoginFormCommand());
        commands.put(GET + GlobalConstants.ADD_PATIENT_FORM, new AddPatientFormCommand());
        commands.put(GET + GlobalConstants.SHOW_PATIENT_INFO, new ShowPatientInfoCommand());
        commands.put(GET + GlobalConstants.SHOW_PATIENTS, new ShowPatientsCommand());
        commands.put(GET + GlobalConstants.SET_DIAGNOSIS, new SetDiagnosisCommand());
        commands.put(GET + GlobalConstants.SHOW_ASSIGNATIONS, new ShowAssignationsCommand());
        commands.put(GET + GlobalConstants.ADD_ASSIGNATIONS_FORM, new AddAssignationsFormCommand());
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
        String path = request.getRequestURI().replaceAll(".*/rest", "");
        String key = method + ":" + path;
        LOGGER.debug(key);
        Command command = commands.getOrDefault(key, (req, resp) -> GlobalConstants.REDIRECT);
        String viewPage = command.execute(request, response);
        LOGGER.debug(viewPage);
        if (viewPage.equals(GlobalConstants.REDIRECT)) {
            response.sendRedirect(GlobalConstants.INDEX_JSP);
        } else {
            request.getRequestDispatcher(viewPage).forward(request, response);
        }
    }

}
