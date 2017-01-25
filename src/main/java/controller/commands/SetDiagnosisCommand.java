package controller.commands;

import model.entities.Diagnosis;
import model.services.DiagnosisService;
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
public class SetDiagnosisCommand implements Command {

    private static Logger LOGGER = Logger.getLogger(ShowAssignationsCommand.class);

    public static final String ATTR_DIAGNOSES_LIST = "diagnosesList";

    /* Parameters & Attributes */
    public static final String PARAM_DIAGNOSIS_HISTORY_ID = "diagnosisHistoryId";

    private DiagnosisService diagnosisService = DiagnosisService.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse httpServletResponse)
            throws ServletException, IOException {

        // TODO
//        int diagnosisHistoryId = Integer.parseInt(request.getParameter(PARAM_DIAGNOSIS_HISTORY_ID));

        List<Diagnosis> diagnosisList = diagnosisService.getAllDiagnoses();
        // TODO setAttribute()
        request.setAttribute(ATTR_DIAGNOSES_LIST, diagnosisList);

        return GlobalConstants.DIAGNOSES_JSP;
    }
}
