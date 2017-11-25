package edu.olya.tour.utils.database;

import edu.olya.tour.dao.RowCreator;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

abstract public class AbstractDAO {

    /**
     * Executes the <code>SELECT</code> SQL statement
     * @param sql query string
     * @param rowCreator the row creator to create an object of specified class
     * @return a list of objects created with rowCreator from the data produced by the query
     */
    public  <T> List<T>     executeQuery(String sql, RowCreator<T> rowCreator){
        List<T> values = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = ConnectionHolder.getConnection()
                    .prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                values.add(rowCreator.buildRow(rs));
            }
        }catch (SQLException e){
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return values;
    }

    /**
     * Executes the <code>SELECT</code> SQL statement
     * @param sql query string
     * @param rowCreator the row creator to create an instance of specified class
     * @param params List of parameters for sql string
     * @return a list of objects created with rowCreator from the data produced by the query
     */
    public <T> List<T> executeQuery (String sql,
                                         RowCreator<T> rowCreator,
                                         List<Object> params){
        List<T> result = new ArrayList<>();

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = ConnectionHolder.getConnection().
                    prepareStatement(sql);

            for (int i = 0; i < params.size(); i++) {
                Object param = params.get(i);
                ps.setObject(i + 1, param);
            }

            rs = ps.executeQuery();

            while (rs.next()) {
                result.add(
                        rowCreator.buildRow(rs)
                );
            }
        }catch (SQLException e){
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    /**
     * Executes the SQL statement such as <code>INSERT</code>, <code>UPDATE</code> or
     * <code>DELETE</code>; or an SQL statement that returns nothing
     * @param sql query string
     * @param params List of parameters for sql string
     * @return either (1) the row count for SQL Data Manipulation Language (DML) statements
     *         or (2) 0 for SQL statements that return nothing (DDL)
     */
    public int executeUpdate(String sql, List<Object> params){
        int qty = 0;
        PreparedStatement ps = null;
        try {
            ps = ConnectionHolder
                    .getConnection()
                    .prepareStatement(sql);
            for (int i = 0; i < params.size(); i++) {
                Object param = params.get(i);
                ps.setObject(i + 1, param);
            }
            qty = ps.executeUpdate();
            return qty;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
