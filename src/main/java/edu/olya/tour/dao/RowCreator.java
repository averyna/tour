package edu.olya.tour.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface RowCreator<T> {
    public T buildRow(ResultSet rs) throws SQLException;
}
