package controller.commands;

import model.entities.AssignationsSurgeries;
import model.entities.Patient;
import model.entities.Surgery;
import model.services.AssignationsSurgeriesService;
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
 * AddAssignationsSurgeriesCommand
 * Created by alexey.morenets@gmail.com on 26.01.2017.
 */
public class AddAssignationsSurgeriesCommand implements Command {

    private static final Logger LOGGER = Logger.getLogger(AddAssignationsSurgeriesCommand.class);

    private static final String SURGERY_CHK = "surgeryChk";

    private AssignationsSurgeriesService assignationsSurgeriesService = AssignationsSurgeriesService.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse httpServletResponse)
            throws ServletException, IOException {

        int diagnosisHistoryId = (int) request.getSession().getAttribute(Attributes.DIAGNOSIS_HISTORY_ID);

        List<AssignationsSurgeries> assignationsProceduresList = getSurgeriesAssignationsFromRequest(request, diagnosisHistoryId);
        assignationsSurgeriesService.createAssignationsSurgeries(assignationsProceduresList);

        int patientId = ((Patient) request.getSession().getAttribute(Attributes.PATIENT)).getId();

        request.setAttribute(Attributes.PAGE_TITLE, "title.assignations.surgeries");
        return Paths.REST_SHOW_PATIENT_INFO + Parameters._ID + patientId;
    }

    private List<AssignationsSurgeries> getSurgeriesAssignationsFromRequest(HttpServletRequest request, int diagnosisHistoryId) {
        List<AssignationsSurgeries> assignationsProceduresList = new ArrayList<>();
        Enumeration<String> params = request.getParameterNames();

        while (params.hasMoreElements()) {
            String paramName = params.nextElement();

            if (paramName.startsWith(SURGERY_CHK)) {
                String value = request.getParameter(paramName);

                int surgeryId;
                try {
                    surgeryId = Integer.parseInt(value);
                } catch (NumberFormatException e) {
                    LOGGER.error(e);
                    throw new RuntimeException(e);
                }
                LOGGER.debug(surgeryId);

                Surgery surgery = new Surgery();
                surgery.setId(surgeryId);
                AssignationsSurgeries assignationsSurgeries = new AssignationsSurgeries.Builder()
                        .setDiagnosisHistoryId(diagnosisHistoryId)
                        .setSurgery(surgery)
                        .build();
                assignationsProceduresList.add(assignationsSurgeries);
            }
        }
        return assignationsProceduresList;
    }

}
