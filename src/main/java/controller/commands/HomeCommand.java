package controller.commands;

import org.apache.log4j.Logger;
import view.Attributes;
import view.Paths;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * HomeCommand
 * Created by alexey.morenets@gmail.com on 27.01.2017.
 */
public class HomeCommand extends CommandWrapper {

    private static final String TITLE_HOME = "title.home";

    @Override
    public String doExecute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setAttribute(Attributes.PAGE_TITLE, TITLE_HOME);
        return Paths.HOME_JSP;
    }

}
