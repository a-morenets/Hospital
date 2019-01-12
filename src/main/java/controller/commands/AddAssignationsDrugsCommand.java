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
 * AddAssignationsDrugsCommand
 * Created by alexey.morenets@gmail.com on 23.01.2017.
 */
public class AddAssignationsDrugsCommand extends CommandWrapper {

    private static final String TITLE_ASSIGNATIONS_DRUGS_ADD = "title.assignations.drugs.add";
    private static final String NUM_UNITS = "drugNumUnits";
    private static final String NUM_TIMES = "drugNumTimes";
    private static final String NUM_DAYS = "drugNumDays";

    private AssignationsDrugsService assignationsDrugsService = AssignationsDrugsService.getInstance();

    @Override
    public String doExecute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int diagnosisHistoryId = Integer.parseInt(request.getParameter(Parameters.DIAGNOSIS_HISTORY_ID));

        List<AssignationsDrugs> assignationsDrugsList = getDrugsAssignationsFromRequest(request, diagnosisHistoryId);
        assignationsDrugsService.createAssignationDrug(assignationsDrugsList);

        request.setAttribute(Attributes.PAGE_TITLE, TITLE_ASSIGNATIONS_DRUGS_ADD);
        String contextPath = request.getContextPath();
        response.sendRedirect(contextPath + Paths.REST_SHOW_ASSIGNATIONS + Parameters._DIAGNOSIS_HISTORY_ID + diagnosisHistoryId);
        return Paths.REDIRECTED;
    }

    private List<AssignationsDrugs> getDrugsAssignationsFromRequest(HttpServletRequest request, int diagnosisHistoryId) {
        List<AssignationsDrugs> assignationsDrugsList = new ArrayList<>();
        Enumeration<String> params = request.getParameterNames();
//todo refactor - https://www.java4s.com/java-servlet-tutorials/example-on-getparametervalues-method-of-servlet-request/
        while (params.hasMoreElements()) {
            String paramName = params.nextElement();

            if (paramName.startsWith(NUM_UNITS)) {
                int drugId = 0;
                try {
                    drugId = Integer.parseInt(paramName.replaceAll(".*\\_", ""));
                } catch (NumberFormatException e) {
                }

                int numUnits = 0;
                try {
                    numUnits = Integer.parseInt(request.getParameter(paramName));
                } catch (NumberFormatException e) {
                }

                if (numUnits > 0) {
                    int numTimes = 0;
                    int numDays = 0;
                    try {
                        numTimes = Integer.parseInt(request.getParameter(NUM_TIMES + "_" + drugId));
                        numDays = Integer.parseInt(request.getParameter(NUM_DAYS + "_" + drugId));
                    } catch (NumberFormatException e) {
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
