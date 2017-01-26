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
 * Created by alexey.morenets@gmail.com on 26.01.2017.
 */
public class AddAssignationsProceduresCommand implements Command {

    private static final Logger LOGGER = Logger.getLogger(AddAssignationsProceduresCommand.class);

    public static final String NUM_DAYS = "procedureNumDays";

    private AssignationsProceduresService assignationsProceduresService = AssignationsProceduresService.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse httpServletResponse)
            throws ServletException, IOException {

        int diagnosisHistoryId = (int) request.getSession().getAttribute(Attributes.DIAGNOSIS_HISTORY_ID);

        List<AssignationsProcedures> assignationsProceduresList = getProceduresAssignationsFromRequest(request, diagnosisHistoryId);
        LOGGER.debug(assignationsProceduresList);
        assignationsProceduresService.createAssignationsProcedures(assignationsProceduresList);

        int patientId = ((Patient) request.getSession().getAttribute(Attributes.PATIENT)).getId();

        return Paths.REST_SHOW_PATIENT_INFO + Parameters._ID + patientId;
    }

    private List<AssignationsProcedures> getProceduresAssignationsFromRequest(HttpServletRequest request, int diagnosisHistoryId) {
        List<AssignationsProcedures> assignationsProceduresList = new ArrayList<>();
        Enumeration<String> params = request.getParameterNames();

        while (params.hasMoreElements()) {
            String paramName = params.nextElement();
            String[] paramParts = paramName.split("_");
            String fieldName = paramParts[0];

            int procedureId = 0;
            try {
                procedureId = Integer.parseInt(paramParts[1]);
            } catch (NumberFormatException e) {
                LOGGER.warn(e);
            }
LOGGER.debug(fieldName);
            if (fieldName.equals(NUM_DAYS)) {
                int numDays = 0;
                try {
                    numDays = Integer.parseInt(request.getParameter(paramName));
                } catch (NumberFormatException e) {
                    LOGGER.warn(e);
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
