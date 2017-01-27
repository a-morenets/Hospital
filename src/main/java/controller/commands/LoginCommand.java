package controller.commands;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.entities.Staff;
import model.services.StaffService;
import view.Attributes;
import view.Parameters;
import view.Paths;

public class LoginCommand implements Command {

	private StaffService staffService = StaffService.getInstance();

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse httpServletResponse)
			throws ServletException, IOException {

		String pageToGo = Paths.REDIRECT;

		String email = request.getParameter(Parameters.LOGIN);
		String password = request.getParameter(Parameters.PASSWORD);
		if (email != null && password != null) {
			Optional<Staff> staff;
			staff = staffService.login(email, password);

			if (staff.isPresent()) {
				request.getSession().setAttribute(Attributes.STAFF, staff.get());
				pageToGo = Paths.REST_SHOW_PATIENTS;
				request.setAttribute(Attributes.PAGE_TITLE, "title.patients");
			} else {
                request.setAttribute(Attributes.PAGE_TITLE, "title.error");
            }
		}

		return pageToGo;
	}

}
