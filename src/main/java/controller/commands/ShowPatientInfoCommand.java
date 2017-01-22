package controller.commands;

import model.entities.DiagnosisHistory;
import model.entities.Patient;
import model.services.DiagnosisHistoryService;
import model.services.PatientService;
import view.GlobalConstants;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by alexey.morenets@gmail.com on 22.01.2017.
 */
public class ShowPatientInfoCommand implements Command {

    /* Parameters & attributes */
    public static final String PARAM_ID = "id";
    public static final String ATTR_PATIENT = "patient";
    public static final String ATTR_DIAGNOSIS_HISTORY_LIST = "diagnosisHistoryList";

    private PatientService patientService = PatientService.getInstance();
    private DiagnosisHistoryService diagnosisHistoryService = DiagnosisHistoryService.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse httpServletResponse)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter(PARAM_ID));

        Patient patient = patientService.getPatientById(id);
        request.getSession().setAttribute(ATTR_PATIENT, patient);

        List<DiagnosisHistory> diagnosisHistoryList = diagnosisHistoryService.getDiagnosisHistoryByPatient(id);
        request.getSession().setAttribute(ATTR_DIAGNOSIS_HISTORY_LIST, diagnosisHistoryList);

        return GlobalConstants.PATIENT_INFO_JSP;
    }
}
