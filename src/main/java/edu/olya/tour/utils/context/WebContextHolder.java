package edu.olya.tour.utils.context;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

public class WebContextHolder {
    private static ThreadLocal<HttpSession> SESSION_CONTEXT = new ThreadLocal<HttpSession>();

    public static HttpSession getSessionContext() {
        return SESSION_CONTEXT.get();
    }

    public static void setSessionContext(HttpSession session) {
        SESSION_CONTEXT.set(session);
    }



    private static ThreadLocal<ServletContext> APPLICATION_CONTEXT = new ThreadLocal<ServletContext>();

    public static ServletContext getApplicationContext() {
        return APPLICATION_CONTEXT.get();
    }

    public static void setApplicationContext(ServletContext servletContext) {
        APPLICATION_CONTEXT.set(servletContext);
    }
}
