package controller.commands;

import model.entities.Drug;
import model.entities.Procedure;
import model.entities.Surgery;
import model.services.DrugService;
import model.services.ProcedureService;
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

        return Paths.ADD_ASSIGNATIONS_DRUGS_JSP;
    }

}
