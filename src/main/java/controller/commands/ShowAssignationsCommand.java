package controller.commands;

import model.entities.AssignationDrug;
import model.entities.AssignationProcedure;
import model.entities.AssignationSurgery;
import model.services.AssignationDrugService;
import model.services.AssignationProcedureService;
import model.services.AssignationSurgeryService;
import org.apache.log4j.Logger;
import view.GlobalConstants;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by alexey.morenets@gmail.com on 23.01.2017.
 */
public class ShowAssignationsCommand implements Command {

    private static Logger LOGGER = Logger.getLogger(ShowAssignationsCommand.class);

    /* Parameters & Attributes */
    public static final String PARAM_DIAGNOSIS_HISTORY_ID = "diagnosisHistoryId";
    public static final String ATTR_ASSIGNATION_DRUGS_LIST = "assignationDrugsList";
    public static final String ATTR_ASSIGNATION_PROCEDURES_LIST = "assignationProceduresList";
    public static final String ATTR_ASSIGNATION_SURGERIES_LIST = "assignationSurgeriesList";

    private AssignationDrugService assignationDrugService = AssignationDrugService.getInstance();
    private AssignationProcedureService assignationProcedureService = AssignationProcedureService.getInstance();
    private AssignationSurgeryService assignationSurgeryService = AssignationSurgeryService.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse httpServletResponse)
            throws ServletException, IOException {

        int diagnosisHistoryId = Integer.parseInt(request.getParameter(PARAM_DIAGNOSIS_HISTORY_ID));

        List<AssignationDrug> assignationDrugsList =
                assignationDrugService.getAssignationDrugsByDiagnosisHistoryIdList(diagnosisHistoryId);
        List<AssignationProcedure> assignationProceduresList =
                assignationProcedureService.getAssignationProceduresByDiagnosisHistoryIdList(diagnosisHistoryId);
        List<AssignationSurgery> assignationSurgeriesList =
                assignationSurgeryService.getAssignationSurgeriesByDiagnosisHistoryIdList(diagnosisHistoryId);

        request.setAttribute(ATTR_ASSIGNATION_DRUGS_LIST, assignationDrugsList);
        request.setAttribute(ATTR_ASSIGNATION_PROCEDURES_LIST, assignationProceduresList);
        request.setAttribute(ATTR_ASSIGNATION_SURGERIES_LIST, assignationSurgeriesList);

        return GlobalConstants.ASSIGNATIONS_JSP;
    }
}
