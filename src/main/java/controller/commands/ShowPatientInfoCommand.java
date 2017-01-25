package controller.commands;

import model.entities.DiagnosisHistory;
import model.entities.Patient;
import model.services.DiagnosisHistoryService;
import model.services.PatientService;
import org.apache.log4j.Logger;
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

    private static final Logger LOGGER = Logger.getLogger(ShowPatientInfoCommand.class);

    /* Parameters & attributes */
    public static final String PARAM_ID = "id";
    public static final String ATTR_PATIENT = "patient";
    public static final String ATTR_DIAGNOSIS_HISTORY_LIST = "diagnosisHistoryList";
    public static final String ATTR_IS_PATIENT_ON_CURE = "isPatientOnCure";

    private PatientService patientService = PatientService.getInstance();
    private DiagnosisHistoryService diagnosisHistoryService = DiagnosisHistoryService.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse httpServletResponse)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter(PARAM_ID));

        Patient patient = patientService.getPatientById(id);
        request.getSession().setAttribute(ATTR_PATIENT, patient);

        List<DiagnosisHistory> diagnosisHistoryList = diagnosisHistoryService.getDiagnosisHistoryByPatient(id);
        request.setAttribute(ATTR_DIAGNOSIS_HISTORY_LIST, diagnosisHistoryList);

        boolean isPatientOnCure = patientService.isPatientOnCure(id);
        request.setAttribute(ATTR_IS_PATIENT_ON_CURE, isPatientOnCure);

        return GlobalConstants.PATIENT_INFO_JSP;
    }
}
