package controller.commands;

import model.entities.DiagnosisHistory;
import model.entities.Patient;
import model.services.DiagnosisHistoryService;
import model.services.PatientService;
import org.apache.log4j.Logger;
import view.Attributes;
import view.Parameters;
import view.Paths;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * ShowPatientInfoCommand
 * Created by alexey.morenets@gmail.com on 22.01.2017.
 */
public class ShowPatientInfoCommand implements Command {

    private static final Logger LOGGER = Logger.getLogger(ShowPatientInfoCommand.class);

    private PatientService patientService = PatientService.getInstance();
    private DiagnosisHistoryService diagnosisHistoryService = DiagnosisHistoryService.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse httpServletResponse)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter(Parameters.ID));//todo if not null + validation \\d+

        Patient patient = patientService.getPatientById(id);
        request.getSession().setAttribute(Attributes.PATIENT, patient);//todo setsAttr patient_ID into request

        List<DiagnosisHistory> diagnosisHistoryList = diagnosisHistoryService.getDiagnosisHistoryByPatient(id);
        request.getSession().setAttribute(Attributes.DIAGNOSIS_HISTORY_LIST, diagnosisHistoryList);

        boolean isPatientOnCure = patientService.isPatientOnCure(id);
        request.setAttribute(Attributes.ATTR_IS_PATIENT_ON_CURE, isPatientOnCure);

        request.setAttribute(Attributes.PAGE_TITLE, "title.patient.info");
        return Paths.PATIENT_INFO_JSP;
    }

}
