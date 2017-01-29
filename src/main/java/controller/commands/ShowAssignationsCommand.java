package controller.commands;

import model.entities.AssignationsDrugs;
import model.entities.AssignationsProcedures;
import model.entities.AssignationsSurgeries;
import model.services.AssignationsDrugsService;
import model.services.AssignationsProceduresService;
import model.services.AssignationsSurgeriesService;
import org.apache.log4j.Logger;
import view.Attributes;
import view.Paths;
import view.Parameters;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * ShowAssignationsCommand
 * Created by alexey.morenets@gmail.com on 23.01.2017.
 */
public class ShowAssignationsCommand implements Command {

    public static final String TITLE_ASSIGNATIONS_SHOW = "title.assignations.show";
    private static Logger LOGGER = Logger.getLogger(ShowAssignationsCommand.class);

    private static final String ATTR_ASSIGNATION_DRUGS_LIST = "assignationDrugsList";
    private static final String ATTR_ASSIGNATION_PROCEDURES_LIST = "assignationProceduresList";
    private static final String ATTR_ASSIGNATION_SURGERIES_LIST = "assignationSurgeriesList";

    private AssignationsDrugsService assignationsDrugsService = AssignationsDrugsService.getInstance();
    private AssignationsProceduresService assignationsProceduresService = AssignationsProceduresService.getInstance();
    private AssignationsSurgeriesService assignationsSurgeriesService = AssignationsSurgeriesService.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int diagnosisHistoryId = Integer.parseInt(request.getParameter(Parameters.DIAGNOSIS_HISTORY_ID));

        List<AssignationsDrugs> assignationDrugsList =
                assignationsDrugsService.getAssignationDrugsByDiagnosisHistoryIdList(diagnosisHistoryId);
        List<AssignationsProcedures> assignationProceduresList =
                assignationsProceduresService.getAssignationProceduresByDiagnosisHistoryIdList(diagnosisHistoryId);
        List<AssignationsSurgeries> assignationSurgeriesList =
                assignationsSurgeriesService.getAssignationSurgeriesByDiagnosisHistoryIdList(diagnosisHistoryId);

        request.setAttribute(Attributes.DIAGNOSIS_HISTORY_ID, diagnosisHistoryId);
        request.setAttribute(ATTR_ASSIGNATION_DRUGS_LIST, assignationDrugsList);
        request.setAttribute(ATTR_ASSIGNATION_PROCEDURES_LIST, assignationProceduresList);
        request.setAttribute(ATTR_ASSIGNATION_SURGERIES_LIST, assignationSurgeriesList);

        request.setAttribute(Attributes.PAGE_TITLE, TITLE_ASSIGNATIONS_SHOW);
        return Paths.ASSIGNATIONS_JSP;
    }

}
