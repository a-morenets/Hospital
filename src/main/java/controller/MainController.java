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

    @Override
    public void init(ServletConfig config) throws ServletException {
        commands.put(GET + Paths.HOME, new HomeCommand());
        commands.put(GET + Paths.SHOW_LOGIN_FORM, new ShowLoginFormCommand());
        commands.put(GET + Paths.SHOW_ADD_PATIENT_FORM, new ShowAddPatientFormCommand());
        commands.put(GET + Paths.SHOW_PATIENT_INFO, new ShowPatientInfoCommand());
        commands.put(GET + Paths.SHOW_PATIENTS, new ShowPatientsCommand());
        commands.put(GET + Paths.SHOW_ASSIGNATIONS, new ShowAssignationsDrugsCommand());
        commands.put(GET + Paths.SET_DIAGNOSIS, new SetDiagnosisCommand());
        commands.put(GET + Paths.SHOW_ADD_ASSIGNATIONS_DRUGS_FORM, new ShowAddAssignationsDrugsFormCommand());
        commands.put(GET + Paths.SHOW_ADD_ASSIGNATIONS_PROCEDURES_FORM, new ShowAddAssignationsProceduresFormCommand());
        commands.put(GET + Paths.SHOW_ADD_ASSIGNATIONS_SURGERIES_FORM, new ShowAddAssignationsSurgeriesFormCommand());

        commands.put(POST + Paths.LOGIN, new LoginCommand());
        commands.put(POST + Paths.SHOW_PATIENTS, new ShowPatientsCommand());
        commands.put(POST + Paths.ADD_PATIENT, new AddPatientCommand());
        commands.put(POST + Paths.SHOW_PATIENT_INFO, new ShowPatientInfoCommand());
        commands.put(POST + Paths.ADD_DIAGNOSIS, new AddDiagnosisCommand());
        commands.put(POST + Paths.ADD_ASSIGNATIONS_DRUGS, new AddAssignationsDrugsCommand());
        commands.put(POST + Paths.ADD_ASSIGNATIONS_PROCEDURES, new AddAssignationsProceduresCommand());
        commands.put(POST + Paths.ADD_ASSIGNATIONS_SURGERIES, new AddAssignationsSurgeriesCommand());
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
        Command command = commands.getOrDefault(key, (req, resp) -> Paths.REDIRECT);
        String viewPage = command.execute(request, response);
        if (viewPage.equals(Paths.REDIRECT)) {
            LOGGER.debug("REDIRECT");
            response.sendRedirect(Paths.HOME_JSP);
        } else {
            LOGGER.debug("FORWARD to page " + viewPage);
            request.getRequestDispatcher(viewPage).forward(request, response);
        }
    }

}
