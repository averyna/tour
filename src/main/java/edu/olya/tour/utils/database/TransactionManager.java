package edu.olya.tour.utils.database;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;

/**
* The {@code TransactionManager} class wraps an object into a run-time generated class,
* implementing one or more interfaces, that automatically converts every method call to
 * one of those interfaces into a call to the invoke method on a provided implementation
 * of invocation handler.
 *
 * The invoke method of the invocation handler provides additional behaviour besides the
 * traditional method call. It obtains the connection to database from {@code ConnectionManager}
 * class and puts this connection into {@code ConnectionHolder} thread local variable before
 * method invocation and closes obtained connection after method has been invoked.
*/
public class TransactionManager {
    /**
     * Method creates an instance of a proxy class based on the interfaces provided with
     * {@code service} object passed to this method and returns generated proxy transferred
     * to the {@code <T>} type defined at run-time
     * @param service an object that provides interfaces for further implementation with proxy
     * @param <T> a run-time type of the object, passed as a parameter
     * @return an instance of {@code Proxy} class for the specified interfaces
     *       transferred to the type {@code <T>}
     */
    public static <T> T wrap(T service) {
        Class[] interfaces = service.getClass().getInterfaces();

        return (T) Proxy.newProxyInstance(
                TransactionManager.class.getClassLoader(),
                interfaces,
                new InvocationHandler() {

                    final T originalReference = service;

                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

                        Connection connection = null;
                        try {
                            connection = ConnectionManager.getConnection();
                            ConnectionHolder.setConnection(connection);
                            connection.setAutoCommit(false);

                            Object result = method.invoke(originalReference, args);
                            connection.commit();
                            return result;
                        } catch (Exception e) {
                            if (connection != null) {
                                connection.rollback();
                            }
                               throw e;
                        } finally {
                            if (connection != null) {
                                ConnectionHolder.setConnection(null);
                                ConnectionManager.closeConnection(connection);
                            }
                        }
                    }
                }
        );

    }
}
