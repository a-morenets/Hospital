package controller.commands;

import model.entities.AssignationsDrugs;
import model.entities.Drug;
import model.entities.Patient;
import model.services.AssignationsDrugsService;
import org.apache.log4j.Logger;
import view.Attributes;
import view.Parameters;
import view.Paths;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * Created by alexey.morenets@gmail.com on 23.01.2017.
 */
public class AddAssignationsDrugsCommand implements Command {

    private static final Logger LOGGER = Logger.getLogger(AddAssignationsDrugsCommand.class);

    public static final String NUM_UNITS = "drugNumUnits";
    public static final String NUM_TIMES = "drugNumTimes";
    public static final String NUM_DAYS = "drugNumDays";

    private AssignationsDrugsService assignationsDrugsService = AssignationsDrugsService.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse httpServletResponse)
            throws ServletException, IOException {

        int diagnosisHistoryId = (int) request.getSession().getAttribute(Attributes.DIAGNOSIS_HISTORY_ID);

        List<AssignationsDrugs> assignationsDrugsList = getDrugsAssignationsFromRequest(request, diagnosisHistoryId);
        assignationsDrugsService.createAssignationDrug(assignationsDrugsList);

        int patientId = ((Patient) request.getSession().getAttribute(Attributes.PATIENT)).getId();

        return Paths.REST_SHOW_PATIENT_INFO + Parameters._ID + patientId;
    }

    private List<AssignationsDrugs> getDrugsAssignationsFromRequest(HttpServletRequest request, int diagnosisHistoryId) {
        List<AssignationsDrugs> assignationsDrugsList = new ArrayList<>();
        Enumeration<String> params = request.getParameterNames();

        while (params.hasMoreElements()) {
            String paramName = params.nextElement();
            String[] paramParts = paramName.split("_");
            String fieldName = paramParts[0];

            int drugId = 0;
            try {
                drugId = Integer.parseInt(paramParts[1]);
            } catch (NumberFormatException e) {
                LOGGER.warn(e);
            }

            if (fieldName.equals(NUM_UNITS)) {
                int numUnits = 0;
                try {
                    numUnits = Integer.parseInt(request.getParameter(paramName));
                } catch (NumberFormatException e) {
                    LOGGER.warn(e);
                }

                if (numUnits > 0) {
                    int numTimes = 0;
                    int numDays = 0;
                    try {
                        numTimes = Integer.parseInt(request.getParameter(NUM_TIMES + "_" + drugId));
                        numDays = Integer.parseInt(request.getParameter(NUM_DAYS + "_" + drugId));
                    } catch (NumberFormatException e) {
                        LOGGER.warn(e);
                    }

                    if (numTimes > 0 && numDays > 0) {
                        Drug drug = new Drug();
                        drug.setId(drugId);
                        AssignationsDrugs assignationsDrugs = new AssignationsDrugs.Builder()
                                .setDiagnosisHistoryId(diagnosisHistoryId)
                                .setDrug(drug)
                                .setNumUnits(numUnits)
                                .setNumTimes(numTimes)
                                .setNumDays(numDays)
                                .build();
                        assignationsDrugsList.add(assignationsDrugs);
                    }
                }
            }
        }
        return assignationsDrugsList;
    }

}
