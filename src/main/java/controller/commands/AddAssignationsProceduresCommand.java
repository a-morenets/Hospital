package controller.commands;

import model.entities.AssignationsProcedures;
import model.entities.Patient;
import model.entities.Procedure;
import model.services.AssignationsProceduresService;
import org.apache.log4j.Logger;
import view.Attributes;
import view.Parameters;
import view.Paths;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * AddAssignationsProceduresCommand
 * Created by alexey.morenets@gmail.com on 26.01.2017.
 */
public class AddAssignationsProceduresCommand extends CommandWrapper {

    private static final String TITLE_ASSIGNATIONS_PROCEDURES_ADD = "title.assignations.procedures.add";
    private static final String NUM_DAYS = "procedureNumDays";

    private AssignationsProceduresService assignationsProceduresService = AssignationsProceduresService.getInstance();

    @Override
    public String doExecute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int diagnosisHistoryId = Integer.parseInt(request.getParameter(Parameters.DIAGNOSIS_HISTORY_ID));

        List<AssignationsProcedures> assignationsProceduresList = getProceduresAssignationsFromRequest(request, diagnosisHistoryId);
        assignationsProceduresService.createAssignationsProcedures(assignationsProceduresList);

        request.setAttribute(Attributes.PAGE_TITLE, TITLE_ASSIGNATIONS_PROCEDURES_ADD);
        String contextPath = request.getContextPath();
        response.sendRedirect(contextPath + Paths.REST_SHOW_ASSIGNATIONS + Parameters._DIAGNOSIS_HISTORY_ID + diagnosisHistoryId);
        return Paths.REDIRECTED;
    }

    private List<AssignationsProcedures> getProceduresAssignationsFromRequest(HttpServletRequest request, int diagnosisHistoryId) {
        List<AssignationsProcedures> assignationsProceduresList = new ArrayList<>();
        Enumeration<String> params = request.getParameterNames();

        while (params.hasMoreElements()) {
            String paramName = params.nextElement();

            if (paramName.startsWith(NUM_DAYS)) {
                int procedureId = 0;
                try {
                    procedureId = Integer.parseInt(paramName.replaceAll(".*\\_", ""));
                } catch (NumberFormatException e) {
                }

                int numDays = 0;
                try {
                    numDays = Integer.parseInt(request.getParameter(paramName));
                } catch (NumberFormatException e) {
                }

                if (numDays > 0) {
                    Procedure procedure = new Procedure();
                    procedure.setId(procedureId);
                    AssignationsProcedures assignationsProcedures = new AssignationsProcedures.Builder()
                            .setDiagnosisHistoryId(diagnosisHistoryId)
                            .setProcedure(procedure)
                            .setNumDays(numDays)
                            .build();
                    assignationsProceduresList.add(assignationsProcedures);
                }
            }
        }
        return assignationsProceduresList;
    }

}
