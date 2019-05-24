package edu.udg.mx.sacb.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public abstract class DAOFactory<T> {

    private static final String DATASOURCE_CONTEXT = "jdbc/sacb";
    private final String SQL_ALL;
    private final String SQL_INSERT;
    private final String SQL_UPDATE;
    private final String SQL_DELETE;

    public DAOFactory(String sql_all, String sql_insert, String sql_update, String sql_delete) {
        this.SQL_ALL = sql_all;
        this.SQL_INSERT = sql_insert;
        this.SQL_UPDATE = sql_update;
        this.SQL_DELETE = sql_delete;
    }

    public int insert(T object) throws DAOException {
        try {
            return executeUpdate(SQL_INSERT, convertObjToParam(object));
        } catch (SQLException | NamingException e) {
            e.printStackTrace();
            throw new DAOException(e);
        }
    }

    public int update(T object) throws DAOException {
        try {
            return executeUpdate(SQL_UPDATE, convertObjToParam(object));
        } catch (SQLException | NamingException e) {
            e.printStackTrace();
            throw new DAOException(e);
        }
    }

    public int delete(T object) throws DAOException {
        try {
            return executeUpdate(SQL_DELETE, convertObjToParam(object));
        } catch (SQLException | NamingException e) {
            e.printStackTrace();
            throw new DAOException(e);
        }
    }

    public T find(String sql, Object... values) throws DAOException {
        T object = null;
        try {
            object = executeQuery(sql, values);
        } catch (SQLException | NamingException e) {
            e.printStackTrace();
            throw new DAOException(e);
        }
        return object;
    }

    public List<T> findAll() throws DAOException {
        return list(SQL_ALL);
    }

    public List<T> list(String sql, Object... values) throws DAOException {
        List<T> list = null;
        try {
            list = executeQueryList(sql, values);
        } catch (SQLException | NamingException e) {
            e.printStackTrace();
            throw new DAOException(e);
        }
        return list;
    }

    public Object singleResult(String sql, Object... values) throws DAOException {
        Object object = null;
        try {
            object = executeQuerySingleResult(sql, values);
        } catch (NamingException | SQLException e) {
            throw new DAOException(e);
        }
        return object;
    }

    public int executeUpdate(String sql, Map<String, Object> params) throws SQLException, NamingException {
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = getConnection();
            statement = connection.prepareCall(sql);
            setParams(statement, params, sql);
            return statement.executeUpdate();
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }

            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    private T executeQuery(String sql, Object... values) throws SQLException, NamingException {
        List<T> list = executeQueryList(sql, values);
        return list.size() > 0 ? list.get(0) : null;
    }

    private List<T> executeQueryList(String sql, Object... values) throws SQLException, NamingException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<T> list = null;
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            setValues(statement, values);
            resultSet = statement.executeQuery();

            list = new ArrayList<>();
            while (resultSet.next()) {
                T object = convertDbToOjb(resultSet);
                list.add(object);
            }
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }

            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return list;
    }

    private Object executeQuerySingleResult(String sql, Object... values) throws SQLException, NamingException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Object object = null;
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            setValues(statement, values);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                object = resultSet.getObject(1);
            }

        } finally {
            if (resultSet != null) {
                resultSet.close();
            }

            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return object;
    }

    private Connection getConnection() throws NamingException, SQLException {
        InitialContext initialContext = new InitialContext();
        DataSource datasource = (DataSource) initialContext.lookup(DATASOURCE_CONTEXT);
        return datasource.getConnection();
    }

    private void setValues(PreparedStatement statement, Object... values) throws SQLException {
        for (int i = 0; i < values.length; i++) {
            statement.setObject(i + 1, values[i]);
        }
    }

    private void setParams(CallableStatement statement, Map<String, Object> values, String sql) throws SQLException {
        String[] split = sql.replaceAll(",", " ").split(" ");
        List<String> nameParams = new ArrayList<>();

        for (String string : split) {
            if (string.startsWith(":")) {
                nameParams.add(
                        string.
                        replaceAll(",", "").
                        replaceAll(":", "").
                        replaceAll("\\)", "").
                        replaceAll(";", ""));
            }
        }
        for (String string : nameParams) {
            Object object = values.get(string);
            //System.out.println("Key:" + string + "->Value:" + object.toString());
            statement.setObject(string, object);
        }
    }

    public java.sql.Timestamp convertDate(java.util.Date dateUtil) {
        return new java.sql.Timestamp(dateUtil.getTime());
    }

    abstract Map<String, Object> convertObjToParam(T object);

    abstract T convertDbToOjb(ResultSet resultSet) throws SQLException;
}
