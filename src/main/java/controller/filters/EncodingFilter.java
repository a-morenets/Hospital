package controller.filters;

import controller.HospitalConfig;
import i18n.SupportedLocale;
import view.Attributes;
import view.Parameters;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Locale;

public class EncodingFilter implements Filter {

    private String encoding;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        encoding = filterConfig.getInitParameter("encoding");
        if (encoding == null) encoding = "UTF-8";
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        HttpServletRequest req = ((HttpServletRequest) servletRequest);
        HttpSession session = req.getSession();

        /* i18n */
        if (req.getSession().getAttribute(Attributes.BUNDLE_FILE) == null) {
            req.getSession().setAttribute(Attributes.BUNDLE_FILE, HospitalConfig.MESSAGES);
        }

        changeUserLocaleByRequestParameter(req);

        /* Encoding */
        String codeRequest = servletRequest.getCharacterEncoding();
        if (!encoding.equalsIgnoreCase(codeRequest)) {
            servletRequest.setCharacterEncoding(encoding);
            servletResponse.setCharacterEncoding(encoding);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    private void changeUserLocaleByRequestParameter(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (request.getParameter(Parameters.USER_LOCALE) != null) {
            Locale locale = SupportedLocale.getDefault();
            for (SupportedLocale loc : SupportedLocale.values()) {
                if (loc.getParam().equals(request.getParameter(Parameters.USER_LOCALE))) {
                    locale = loc.getLocale();
                    break;
                }
            }
            session.setAttribute(Attributes.USER_LOCALE, locale);
        }
    }

    @Override
    public void destroy() {
        encoding = null;
    }
}
