package controller.commands;

import model.entities.Surgery;
import model.services.SurgeryService;
import org.apache.log4j.Logger;
import view.Attributes;
import view.Paths;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * ShowAddAssignationsSurgeriesFormCommand
 * Created by alexey.morenets@gmail.com on 26.01.2017.
 */
public class ShowAddAssignationsSurgeriesFormCommand implements Command {

    private static final Logger LOGGER = Logger.getLogger(ShowAddAssignationsSurgeriesFormCommand.class);

    private SurgeryService surgeryService = SurgeryService.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse httpServletResponse)
            throws ServletException, IOException {

        List<Surgery> surgeriesList = surgeryService.getAllSurgeries();
        request.setAttribute(Attributes.SURGERIES_LIST, surgeriesList);

        request.setAttribute(Attributes.PAGE_TITLE, "form.assignations.surgeries.add");
        return Paths.ADD_ASSIGNATIONS_SURGERIES_JSP;
    }

}
