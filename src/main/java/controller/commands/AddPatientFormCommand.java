package controller.commands;

import view.GlobalConstants;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by alexey.morenets@gmail.com on 22.01.2017.
 */
public class AddPatientFormCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse httpServletResponse)
            throws ServletException, IOException {

        return GlobalConstants.ADD_PATIENT_FORM_JSP;
    }
}
