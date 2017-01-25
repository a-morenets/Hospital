package controller.commands;

import view.GlobalConstants;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by alexey.morenets@gmail.com on 23.01.2017.
 */
public class AddAssignationsCommand implements Command {

    public static final String PARAM_ID = "?id=";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse httpServletResponse)
            throws ServletException, IOException {

        return GlobalConstants.REST_SHOW_PATIENT_INFO + PARAM_ID + 1; // TODO
    }
}
