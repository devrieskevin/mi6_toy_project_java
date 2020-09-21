package nu.educom.kevin.top_secret;

import java.sql.*;
import java.util.*;

public class Crud implements ICrud{
  private Connection conn;
  private PreparedStatement stmt;
  private ResultSet rows;

  Crud(){
    conn = null;
    stmt = null;
    rows = null;
  }

  private void connect() {
    try {
      // db parameters
      String url       = "jdbc:mysql://127.0.0.1:3306/mi6";
      String user      = "mi6_admin";
      String password  = "wTOcJj1eiCP2xAX2";

      // create a connection to the database
      conn = DriverManager.getConnection(url, user, password);

    }
    catch(SQLException e) {
      System.out.println(e.getMessage());
    }
  }

  private void close() {
    if (rows != null) {
      try { rows.close(); } catch (Exception e) {/* Ignored */}
      rows = null;
    }
    if (stmt != null) {
      try { stmt.close(); } catch (Exception e) {/* Ignored */}
      stmt = null;
    }
    if (conn != null) {
      try { conn.close(); } catch (Exception e) { /* Ignore */ }
      conn = null;
    }
  }

  /**
   * @throws SQLException when parameter binding fails
   */
  private void bind(PreparedStatement stmt, List<Object> params) throws SQLException {
    for (int i = 0; i < params.size(); i++) {
      stmt.setObject(i + 1, params.get(i));
    }
  }

  @Override
  public long createRow(String sql, List<Object> params) throws SQLException {
    try {
      connect();
      stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
      bind(stmt, params);
      stmt.executeUpdate();

      rows = stmt.getGeneratedKeys();

      return rows.next() ? rows.getLong(1) : 0;
    }
    finally {
      close();
    }
  }

  @Override
  public <R> R readSingleRow(String sql, List<Object> params, DataMapper<R> mapper) throws SQLException {
    R result;

    try {
      connect();
      stmt = conn.prepareStatement(sql);
      bind(stmt, params);

      rows = stmt.executeQuery();

      if (rows.next()) {
        result = mapper.mapRow(rows);
      } else {
        result = null;
      }

      return result;
    }
    finally {
      close();
    }
  }

  @Override
  public <R> List<R> readMultipleRows(String sql, List<Object> params, DataMapper<R> mapper) throws SQLException {
    List<R> results;

    try {
      connect();
      stmt = conn.prepareStatement(sql);
      bind(stmt, params);

      rows = stmt.executeQuery();

      results = new ArrayList<>();
      while (rows.next()) {
        results.add(mapper.mapRow(rows));
      }

      return results;
    }
    finally {
      close();
    }
  }

  @Override
  public int updateRows(String sql, List<Object> params) throws SQLException {
    try {
      connect();
      stmt = conn.prepareStatement(sql);
      bind(stmt, params);
      return stmt.executeUpdate();
    }
    finally {
      close();
    }
  }

  @Override
  public int deleteRows(String sql, List<Object> params) throws SQLException {
    try {
      connect();
      stmt = conn.prepareStatement(sql);
      bind(stmt, params);
      return stmt.executeUpdate();
    }
    finally {
      close();
    }
  }

}
