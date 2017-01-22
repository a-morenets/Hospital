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
public class AddPatientCommand implements Command {

    /* Parameters & attributes */
    public static final String PARAM_FIRSTNAME = "firstname";
    public static final String PARAM_LASTNAME = "lastname";
    public static final String PARAM_SURNAME = "surname";

    private PatientService patientService = PatientService.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        String firstName = request.getParameter(PARAM_FIRSTNAME);
        String lastName = request.getParameter(PARAM_LASTNAME);
        String surName = request.getParameter(PARAM_SURNAME);

        Patient patient = new Patient.Builder()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setSurName(surName)
                .build();
        patientService.createPatient(patient);

        return GlobalConstants.REST_SHOW_PATIENTS;
    }
}
