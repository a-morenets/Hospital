package controller.commands;

import view.Paths;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by alexey.morenets@gmail.com on 25.01.2017.
 */
public class ShowLoginFormCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        return Paths.LOGIN_JSP;
    }
}
