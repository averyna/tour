package edu.olya.tour.utils.context;

import edu.olya.tour.model.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * The filter compares servlet path with secured paths and determines
 * whether the user is authorised or not.
 * When the user is not authorised they are redirected to the log in page.
 * Otherwise user moves to the requested url.
 */
public class AuthorizationFilter implements Filter {
    private static final String LAYOUT_PAGE = "/static/jsp/layout.jsp";
    private Set<String> securedPaths = new HashSet<>();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String securedPathsParam = filterConfig.getInitParameter("securedPaths");
        String[] paths = securedPathsParam.split(",");
        for(String path : paths) {
            securedPaths.add(path.trim());
        }
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException
    {
        HttpServletRequest hsr = (HttpServletRequest)request;
        HttpSession session = hsr.getSession();

        String contextPath = hsr.getServletPath();

        if(securedPaths.contains(contextPath)) {
            User user = (User) session.getAttribute(User.class.getName());
            if (user == null) {
                request.setAttribute("page", "authorization.jsp");
                request.getRequestDispatcher(LAYOUT_PAGE).forward(request, response);
                return;
            }
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}
