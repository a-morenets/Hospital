package controller.commands;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.exception.AppException;
import model.entities.Staff;
import model.services.StaffService;
import org.apache.log4j.Logger;
import view.Attributes;
import view.Parameters;
import view.Paths;

public class LoginCommand extends CommandWrapper {

    private static final String TITLE_HOME = "title.home";

    private StaffService staffService = StaffService.getInstance();

    @Override
    public String doExecute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter(Parameters.LOGIN);
        String password = request.getParameter(Parameters.PASSWORD);
        if (email != null && password != null) {
            Optional<Staff> staff;
            staff = staffService.login(email, password);
            if (staff.isPresent()) {
                request.getSession().setAttribute(Attributes.STAFF, staff.get());
                response.sendRedirect(Paths.REST_SHOW_PATIENTS);
            } else {
                request.setAttribute(Attributes.PAGE_TITLE, TITLE_HOME);
                response.sendRedirect(Paths.REST_HOME);
            }
        }
        return Paths.REDIRECTED;
    }

}
