package controller.commands;

import model.entities.Patient;
import model.services.PatientService;
import view.Attributes;
import view.Parameters;
import view.Paths;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ShowPatientsCommand
 * Created by alexey.morenets@gmail.com on 25.01.2017.
 */
public class ShowPatientsCommand implements Command {

    private PatientService patientService = PatientService.getInstance();

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse httpServletResponse)
			throws ServletException, IOException {

        List<Patient> patients = patientService.getAllPatients();
        request.setAttribute(Parameters.ATTR_PATIENTS_LIST, patients);

        request.setAttribute(Attributes.PAGE_TITLE, "title.patients.show");
		return Paths.PATIENTS_JSP;
	}

}
