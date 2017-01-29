package controller.commands;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * interface Command
 * Created by alexey.morenets@gmail.com on 23.01.2017.
 */
public interface Command {
    String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
