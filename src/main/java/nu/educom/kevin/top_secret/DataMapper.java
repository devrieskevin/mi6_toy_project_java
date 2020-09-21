package nu.educom.kevin.top_secret;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface DataMapper<T> {
  /**
   * Maps the columns of a row in a ResultSet to the properties of an object of type T
   *
   * @param row A ResultSet with its pointer at a specific row
   *
   * @return Object of type T
   *
   * @throws SQLException when the SQL query fails somehow
   * */
  T mapRow(ResultSet row) throws SQLException;
}
