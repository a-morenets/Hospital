package controller.commands;

import model.entities.Procedure;
import model.services.ProcedureService;
import org.apache.log4j.Logger;
import view.Attributes;
import view.Paths;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * ShowAddAssignationsProceduresFormCommand
 * Created by alexey.morenets@gmail.com on 26.01.2017.
 */
public class ShowAddAssignationsProceduresFormCommand implements Command {

    private static final Logger LOGGER = Logger.getLogger(ShowAddAssignationsProceduresFormCommand.class);

    private ProcedureService procedureService = ProcedureService.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse httpServletResponse)
            throws ServletException, IOException {

        List<Procedure> proceduresList = procedureService.getAllProcedures();
        request.setAttribute(Attributes.PROCEDURES_LIST, proceduresList);

        request.setAttribute(Attributes.PAGE_TITLE, "form.assignations.procedures.add");
        return Paths.ADD_ASSIGNATIONS_PROCEDURES_JSP;
    }

}
