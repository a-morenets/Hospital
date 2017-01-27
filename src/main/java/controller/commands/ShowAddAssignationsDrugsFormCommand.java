package controller.commands;

import model.entities.Drug;
import model.services.DrugService;
import org.apache.log4j.Logger;
import view.Attributes;
import view.Paths;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * ShowAddAssignationsDrugsFormCommand
 * Created by alexey.morenets@gmail.com on 23.01.2017.
 */
public class ShowAddAssignationsDrugsFormCommand implements Command {

    private static final Logger LOGGER = Logger.getLogger(ShowAddAssignationsDrugsFormCommand.class);

    private DrugService drugService = DrugService.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse httpServletResponse)
            throws ServletException, IOException {

        List<Drug> drugsList = drugService.getAllDrugs();
        request.setAttribute(Attributes.DRUGS_LIST, drugsList);

        request.setAttribute(Attributes.PAGE_TITLE, "form.assignations.drugs.add");
        return Paths.ADD_ASSIGNATIONS_DRUGS_JSP;
    }

}
