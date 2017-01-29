package controller.commands;

import model.entities.Patient;
import model.services.PatientService;
import view.Attributes;
import view.Parameters;
import view.Paths;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * AddPatientCommand
 * Created by alexey.morenets@gmail.com on 22.01.2017.
 */
public class AddPatientCommand extends CommandWrapper {

    private static final String PATIENT_ADD = "patient.add";

    private PatientService patientService = PatientService.getInstance();

    @Override
    public String doExecute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String lastName = request.getParameter(Parameters.LASTNAME);
        String firstName = request.getParameter(Parameters.FIRSTNAME);
        String surName = request.getParameter(Parameters.SURNAME);

        Patient patient = new Patient.Builder()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setSurName(surName)
                .build();
        patientService.createPatient(patient);

        request.setAttribute(Attributes.PAGE_TITLE, PATIENT_ADD);
        response.sendRedirect(Paths.REST_SHOW_PATIENTS);
        return Paths.REDIRECTED;
    }

}
