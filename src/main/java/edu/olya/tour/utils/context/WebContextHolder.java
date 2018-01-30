package edu.olya.tour.utils.context;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

/**
 * This class contains thread-local variables to hold application context and
 * session context and the access to that variables via setters and getters.
 */
public class WebContextHolder {
    private static ThreadLocal<HttpSession> SESSION_CONTEXT = new ThreadLocal<HttpSession>();

    /**
     * @return session object stored in {@code SESSION_CONTEXT}
     */
    public static HttpSession getSessionContext() {
        return SESSION_CONTEXT.get();
    }

    /**
     * Method sets specified session object into {@code SESSION_CONTEXT} thread local variable.
     * @param session a session object to be set
     */
    public static void setSessionContext(HttpSession session) {
        SESSION_CONTEXT.set(session);
    }


    private static ThreadLocal<ServletContext> APPLICATION_CONTEXT = new ThreadLocal<ServletContext>();

    /**
     * @return object stored in {@code APPLICATION_CONTEXT}
     */
    public static ServletContext getApplicationContext() {
        return APPLICATION_CONTEXT.get();
    }

    /**
     * Method sets specified {@code servletContext} object into {@code APPLICATION_CONTEXT} thread local variable.
     * @param servletContext an object to be set
     */
    public static void setApplicationContext(ServletContext servletContext) {
        APPLICATION_CONTEXT.set(servletContext);
    }
}
