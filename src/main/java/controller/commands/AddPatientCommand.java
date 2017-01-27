package controller.commands;

import model.entities.Patient;
import model.services.PatientService;
import view.Attributes;
import view.Paths;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * AddPatientCommand
 * Created by alexey.morenets@gmail.com on 22.01.2017.
 */
public class AddPatientCommand implements Command {

    /* Parameters & attributes */
    private static final String PARAM_LASTNAME = "lastname";
    private static final String PARAM_FIRSTNAME = "firstname";
    private static final String PARAM_SURNAME = "surname";

    private PatientService patientService = PatientService.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        String lastName = request.getParameter(PARAM_LASTNAME);
        String firstName = request.getParameter(PARAM_FIRSTNAME);
        String surName = request.getParameter(PARAM_SURNAME);

        Patient patient = new Patient.Builder()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setSurName(surName)
                .build();
        patientService.createPatient(patient);

        request.setAttribute(Attributes.PAGE_TITLE, "patient.add");
        return Paths.REST_SHOW_PATIENTS;
    }

}
