package controller.commands;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.entities.Person;
import model.services.PersonService;

public class LoginCommand implements Command {
	public static final String PARAM_LOGIN = "login";
	public static final String PARAM_PASSWORD = "password";

	private PersonService personService = PersonService.getInstance();

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse httpServletResponse)
			throws ServletException, IOException {

		String pageToGo = "/index.jsp";
		String email = request.getParameter(PARAM_LOGIN);
		String password = request.getParameter(PARAM_PASSWORD);
		if (email != null && password != null) {
			Optional<Person> person;
			person = personService.login(email, password);

			if (person.isPresent()) {
				request.getSession().setAttribute("user", person.get());
				pageToGo = "/patients";
			}
		}

		return pageToGo;
	}

}
