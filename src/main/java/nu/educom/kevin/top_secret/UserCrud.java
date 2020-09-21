package nu.educom.kevin.top_secret;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UserCrud implements IUserCrud{
  private ICrud crud;

  UserCrud(ICrud crud) {
    this.crud = crud;
  }

  public ICrud getCrud() {
    return crud;
  }

  @Override
  public Agent getAgentByServiceNumber(short serviceNumber) throws SQLException {
    String sql = "SELECT * FROM agents "
               + "WHERE service_number = ?";

    List<Object> params = Collections.singletonList(serviceNumber);

    return crud.readSingleRow(sql, params, new AgentMapper());
  }

  @Override
  public long storeLoginAttempt(short serviceNumber, boolean success) throws SQLException {
    String sql = "INSERT INTO login_attempts (service_number, success) "
               + "VALUES (?, ?)";

    List<Object> params = new ArrayList<>(2);
    params.add(serviceNumber);
    params.add(success);

    return crud.createRow(sql, params);

  }

  @Override
  public LoginAttempt getLastSuccessfulLoginAttempt(short serviceNumber) throws SQLException {
    String sql = "SELECT * FROM login_attempts "
               + "WHERE service_number = ? AND success = 1 "
               + "ORDER BY login_attempt_date DESC "
               + "LIMIT 1";

    List<Object> params = Collections.singletonList(serviceNumber);

    return crud.readSingleRow(sql, params, new LoginAttemptMapper());
  }

  @Override
  public List<LoginAttempt> getLastLoginAttempts(short serviceNumber) throws SQLException {
    String sql = "SELECT * FROM login_attempts "
               + "WHERE service_number = ? ";

    LoginAttempt lastLogin = getLastSuccessfulLoginAttempt(serviceNumber);

    List<Object> params = new ArrayList<>(2);
    params.add(serviceNumber);

    // Add date condition if a successful login attempt exists
    if (lastLogin != null) {
      sql += "AND login_attempt_date > ? ";
      params.add(Timestamp.valueOf(lastLogin.getLoginAttemptDate()));
    }

    sql += "ORDER BY login_attempt_date DESC";

    return crud.readMultipleRows(sql,params, new LoginAttemptMapper());
  }
}
