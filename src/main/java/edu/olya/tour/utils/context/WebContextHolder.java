package edu.olya.tour.utils.context;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

/**
 * todo нужна документация
 */
public class WebContextHolder {
    private static ThreadLocal<HttpSession> SESSION_CONTEXT = new ThreadLocal<HttpSession>();

    /**
     *
     * @return
     */
    public static HttpSession getSessionContext() {
        return SESSION_CONTEXT.get();
    }

    /**
     *
     * @param session
     */
    public static void setSessionContext(HttpSession session) {
        SESSION_CONTEXT.set(session);
    }


    private static ThreadLocal<ServletContext> APPLICATION_CONTEXT = new ThreadLocal<ServletContext>();

    /**
     *
     * @return
     */
    public static ServletContext getApplicationContext() {
        return APPLICATION_CONTEXT.get();
    }

    /**
     *
     * @param servletContext
     */
    public static void setApplicationContext(ServletContext servletContext) {
        APPLICATION_CONTEXT.set(servletContext);
    }
}
