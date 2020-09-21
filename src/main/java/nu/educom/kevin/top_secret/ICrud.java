package nu.educom.kevin.top_secret;

import java.sql.SQLException;
import java.util.List;

public interface ICrud {
  /**
   * Creates a single row in the database
   *
   * @param sql    The sql insert query
   * @param params A list of parameters to be sequentially added to the prepared statement
   *
   * @return       The index value associated with the table where the row is inserted
   *
   * @throws SQLException if the database query or communication is not successful
   * */
  long createRow(String sql, List<Object> params) throws SQLException;

  /**
   * Reads a single row from a database query and returns an object with the row attributes mapped
   * to the properties of the returned object
   *
   * @param sql    The sql select query
   * @param params A list of parameters to be sequentially added to the prepared statement
   * @param mapper A DataMapper object which builds the data objects using JDBC ResultSets
   *
   * @return An object with the row attributes mapped to its properties
   *
   * @throws SQLException if the database query or communication is not successful
   * */
  <R> R readSingleRow(String sql, List<Object> params, DataMapper<R> mapper) throws SQLException;

  /**
   * Reads multiple rows from a database query and returns a list of objects with the row attributes mapped
   * to the properties of the returned objects
   *
   * @param sql    The sql select query
   * @param params A list of parameters to be sequentially added to the prepared statement
   * @param mapper A DataMapper object which builds the data objects using JDBC ResultSets
   *
   * @return A list of objects with the row attributes mapped to their properties
   *
   * @throws SQLException if the database query or communication is not successful
   * */
  <R> List<R> readMultipleRows(String sql, List<Object> params, DataMapper<R> mapper) throws SQLException;

  /**
   * Updates rows in a database table
   *
   * @param sql    The sql update query
   * @param params A list of parameters to be sequentially added to the prepared statement
   *
   * @return       The number of rows affected by the update query
   *
   * @throws SQLException if the database query or communication is not successful
   * */
  int updateRows(String sql, List<Object> params) throws SQLException ;

  /**
   * Deletes rows in a database table
   *
   * @param sql    The sql delete query
   * @param params A list of parameters to be sequentially added to the prepared statement
   *
   * @return       The number of rows deleted by the delete query
   *
   * @throws SQLException if the database query or communication is not successful
   * */
  int deleteRows(String sql, List<Object> params) throws SQLException ;
}
