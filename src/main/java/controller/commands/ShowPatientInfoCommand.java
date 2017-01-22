package controller.commands;

import model.entities.Patient;
import model.services.PatientService;
import view.GlobalConstants;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by alexey.morenets@gmail.com on 22.01.2017.
 */
public class ShowPatientInfoCommand implements Command {

    /* Parameters & attributes */
    public static final String PARAM_ID = "id";
    public static final String ATTR_PATIENT = "patient";

    private PatientService patientService = PatientService.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse httpServletResponse)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter(PARAM_ID));
        Patient patient = patientService.getPatientById(id);
        request.getSession().setAttribute(ATTR_PATIENT, patient);

        return GlobalConstants.PATIENT_INFO_JSP;
    }
}
