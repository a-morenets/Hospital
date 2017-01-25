package controller.commands;

import model.entities.Patient;
import view.GlobalConstants;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by alexey.morenets@gmail.com on 23.01.2017.
 */
public class AddDiagnosisCommand implements Command {

    public static final String PARAM_ID = "?id=";
    public static final String ATTR_PATIENT = "patient";
    public static final String PARAM_DIAGNOSIS_ID = "diagnosisId";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse httpServletResponse)
            throws ServletException, IOException {

        int diagnosisId = Integer.parseInt(request.getParameter(PARAM_DIAGNOSIS_ID));
        // TODO service - insert

        int patientId = ((Patient) request.getSession().getAttribute(ATTR_PATIENT)).getId();
        return GlobalConstants.REST_SHOW_PATIENT_INFO + PARAM_ID + patientId;
    }
}
