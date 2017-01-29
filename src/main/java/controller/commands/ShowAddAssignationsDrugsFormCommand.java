package controller.commands;

import model.entities.Drug;
import model.services.DrugService;
import org.apache.log4j.Logger;
import view.Attributes;
import view.Parameters;
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
public class ShowAddAssignationsDrugsFormCommand extends CommandWrapper {

    private static final String TITLE_ASSIGNATIONS_DRUGS_ADD_FORM = "title.assignations.drugs.add.form";

    private DrugService drugService = DrugService.getInstance();

    @Override
    public String doExecute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int diagnosisHistoryId = Integer.parseInt(request.getParameter(Parameters.DIAGNOSIS_HISTORY_ID));

        List<Drug> drugsList = drugService.getAllDrugs();
        request.setAttribute(Attributes.DRUGS_LIST, drugsList);

        request.setAttribute(Attributes.DIAGNOSIS_HISTORY_ID, diagnosisHistoryId);
        request.setAttribute(Attributes.PAGE_TITLE, TITLE_ASSIGNATIONS_DRUGS_ADD_FORM);
        return Paths.ADD_ASSIGNATIONS_DRUGS_JSP;
    }

}
