package controller.commands;

import model.entities.Drug;
import model.entities.Procedure;
import model.entities.Surgery;
import model.services.DrugService;
import model.services.ProcedureService;
import model.services.SurgeryService;
import view.GlobalConstants;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by alexey.morenets@gmail.com on 23.01.2017.
 */
public class AddAssignationsFormCommand implements Command {

    /* Parameters & Attributes */
    public static final String DRUGS_LIST = "drugsList";
    private static final String PROCEDURES_LIST = "proceduresList";
    private static final String SURGERIES_LIST = "surgeriesList";

    private DrugService drugService = DrugService.getInstance();
    private ProcedureService procedureService = ProcedureService.getInstance();
    private SurgeryService surgeryService = SurgeryService.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse httpServletResponse)
            throws ServletException, IOException {

        List<Drug> drugsList = drugService.getAllDrugs();
        request.setAttribute(DRUGS_LIST, drugsList);

        List<Procedure> proceduresList = procedureService.getAllProcedures();
        request.setAttribute(PROCEDURES_LIST, proceduresList);

        List<Surgery> surgeriesList = surgeryService.getAllSurgeries();
        request.setAttribute(SURGERIES_LIST, surgeriesList);

        return GlobalConstants.ADD_ASSIGNATIONS_JSP;
    }
}
