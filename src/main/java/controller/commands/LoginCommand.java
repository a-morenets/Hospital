package controller.commands;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.entities.Staff;
import model.services.StaffService;

public class LoginCommand implements Command {
	public static final String PARAM_LOGIN = "login";
	public static final String PARAM_PASSWORD = "password";

	private StaffService staffService = StaffService.getInstance();

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse httpServletResponse)
			throws ServletException, IOException {

		String pageToGo = "/index.jsp";

		String email = request.getParameter(PARAM_LOGIN);
		String password = request.getParameter(PARAM_PASSWORD);
		if (email != null && password != null) {
			Optional<Staff> staff;
			staff = staffService.login(email, password);

			if (staff.isPresent()) {
				request.getSession().setAttribute("staff", staff.get());
				pageToGo = "/rest/show_patients";
			}
		}

		return pageToGo;
	}

}
