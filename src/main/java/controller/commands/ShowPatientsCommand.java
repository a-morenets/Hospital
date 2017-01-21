package controller.commands;

import model.entities.Patient;
import model.services.PatientService;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowPatientsCommand implements Command {

    private PatientService patientService = PatientService.getInstance();

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse httpServletResponse)
			throws ServletException, IOException {

        List<Patient> patients = patientService.getAllPatients();
        request.setAttribute("patientsList", patients);

		return "/WEB-INF/view/patients.jsp";
	}

}
