package edu.olya.tour.utils.context;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


/**
 * This class represents a listener that sets the values of thread local variables
 * of {@code WebContextHolder} class.
 * It passes current servlet or session contexts as setter's parameter when the
 * request is initialized and passes null when request is destroyed.
 */
public class WebContextListener implements ServletRequestListener {
    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        WebContextHolder.setApplicationContext(sre.getServletContext());

        HttpSession session = ((HttpServletRequest)sre.getServletRequest()).getSession();
        WebContextHolder.setSessionContext(session);
    }

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        WebContextHolder.setApplicationContext(null);
        WebContextHolder.setSessionContext(null);
    }
}

