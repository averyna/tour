package edu.olya.tour.utils.database;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;

public class TransactionManager {

    public static <T> T wrap(T service) {
        Class[] interfaces = service.getClass().getInterfaces();
        /* Returns an instance of a proxy class for the specified interfaces  that dispatches (отправляет, посылает)
        method invocations to the specified invocation handler.*/
        return (T) Proxy.newProxyInstance( //данный метод комбинирует получение прокси-класса с помощью Proxy.getProxyClass
                // и создание экземпляра данного класса через рефлексию
                TransactionManager.class.getClassLoader(),
                interfaces,
                new InvocationHandler() {

                    final T originalReference = service;

                    /*Здесь proxy - экземпляр прокси-класса, который может использоваться при обработке вызова того или иного метода.
                    Второй параметр - method является экземпляром класса java.lang.reflect.Method.Значение данного параметра - один
                    из методов, определенных в каком-либо из переданных при создании прокси-класса интерфейсов.
                    Третий параметр - массив значений аргументов метода.                     */

                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

                        Connection connection = null;
                        try {
                            connection = ConnectionManager.getConnection();
                            ConnectionHolder.setConnection(connection);
                            connection.setAutoCommit(false);

                            Object result = method.invoke(originalReference, args);
                            connection.commit(); //This method should be used only when auto-commit mode has been disabled.
                            return result;
                        } catch (Exception e) {
                            if (connection != null) {
                                connection.rollback();//This method should be used only when auto-commit mode has been disabled.
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
