package controller.commands;

import model.entities.*;
import model.services.DiagnosisHistoryService;
import org.apache.log4j.Logger;
import view.Attributes;
import view.Parameters;
import view.Paths;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

/**
 * AddDiagnosisCommand
 * Created by alexey.morenets@gmail.com on 23.01.2017.
 */
public class AddDiagnosisCommand implements Command {

    private static Logger LOGGER = Logger.getLogger(AddDiagnosisCommand.class);

    private DiagnosisHistoryService diagnosisHistoryService = DiagnosisHistoryService.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse httpServletResponse)
            throws ServletException, IOException {

        DiagnosisType diagnosisType = (DiagnosisType)(request.getSession().getAttribute(Parameters.DIAGNOSIS_TYPE));

        Patient patient = (Patient) request.getSession().getAttribute(Attributes.PATIENT);
        int patientId = patient.getId();
        Staff staff = (Staff) request.getSession().getAttribute(Attributes.STAFF);

        int diagnosisId = Integer.parseInt(request.getParameter(Parameters.DIAGNOSIS_ID));
        Diagnosis diagnosis = new Diagnosis();
        diagnosis.setId(diagnosisId);

        DiagnosisHistory diagnosisHistory = new DiagnosisHistory.Builder()
                .setDate(new Timestamp(new Date().getTime()))
                .setPatient(patient)
                .setStaff(staff)
                .setDiagnosis(diagnosis)
                .setDiagnosisType(diagnosisType)
                .build();
        diagnosisHistoryService.createDiagnosisHistory(diagnosisHistory);

        request.setAttribute(Attributes.PAGE_TITLE, "title.diagnosis.add");
        return Paths.REST_SHOW_PATIENT_INFO + Parameters._ID + patientId;
    }

}
