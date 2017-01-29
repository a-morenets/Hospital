package controller.commands;

import model.entities.Surgery;
import model.services.SurgeryService;
import view.Attributes;
import view.Parameters;
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
public class ShowAddAssignationsSurgeriesFormCommand extends CommandWrapper {

    private static final String TITLE_ASSIGNATIONS_SURGERIES_ADD_FORM = "title.assignations.surgeries.add.form";

    private SurgeryService surgeryService = SurgeryService.getInstance();

    @Override
    public String doExecute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int diagnosisHistoryId = Integer.parseInt(request.getParameter(Parameters.DIAGNOSIS_HISTORY_ID));

        List<Surgery> surgeriesList = surgeryService.getAllSurgeries();
        request.setAttribute(Attributes.SURGERIES_LIST, surgeriesList);

        request.setAttribute(Attributes.DIAGNOSIS_HISTORY_ID, diagnosisHistoryId);
        request.setAttribute(Attributes.PAGE_TITLE, TITLE_ASSIGNATIONS_SURGERIES_ADD_FORM);
        return Paths.ADD_ASSIGNATIONS_SURGERIES_JSP;
    }

}
