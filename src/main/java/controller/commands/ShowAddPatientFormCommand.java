package controller.commands;

import view.Attributes;
import view.Paths;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * ShowAddPatientFormCommand
 * Created by alexey.morenets@gmail.com on 22.01.2017.
 */
public class ShowAddPatientFormCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse httpServletResponse)
            throws ServletException, IOException {

        request.setAttribute(Attributes.PAGE_TITLE, "title.patient.add");
        return Paths.ADD_PATIENT_FORM_JSP;
    }

}
