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
public class HomeCommand implements Command {

    private static final Logger LOGGER = Logger.getLogger(HomeCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse httpServletResponse)
            throws ServletException, IOException {

        request.setAttribute(Attributes.PAGE_TITLE, "title.home");

        return Paths.HOME_JSP;
    }

}
