package edu.olya.tour.utils.context;

import javax.servlet.*;
import java.io.IOException;

/**
 * This filter is mapped to all urls of the application and is responsible to set
 * character encoding for {@code ServletRequest} object.
 */

public class RequestEncodingFilter implements Filter {
    private String requestContentEncoding;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.requestContentEncoding = filterConfig.getInitParameter("requestContentEncoding");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException
    {
        request.setCharacterEncoding(requestContentEncoding);
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}
