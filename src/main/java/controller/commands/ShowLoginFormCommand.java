package controller.commands;

import view.Attributes;
import view.Paths;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * ShowLoginFormCommand
 * Created by alexey.morenets@gmail.com on 25.01.2017.
 */
public class ShowLoginFormCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse httpServletResponse)
            throws ServletException, IOException {

        request.setAttribute(Attributes.PAGE_TITLE, "title.login");
        return Paths.LOGIN_JSP;
    }

}
