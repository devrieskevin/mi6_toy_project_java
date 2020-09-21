package nu.educom.kevin.top_secret;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class LoginAttemptMapper implements DataMapper<LoginAttempt> {
  @Override
  public LoginAttempt mapRow(ResultSet row) throws SQLException {
    LoginAttempt loginAttempt = new LoginAttempt();
    Timestamp loginAttemptDate = row.getTimestamp("login_attempt_date");

    loginAttempt.setId(row.getLong("login_attempt_id"));
    loginAttempt.setServiceNumber(row.getShort("service_number"));
    loginAttempt.setSuccess(row.getBoolean("success"));
    loginAttempt.setLoginAttemptDate(loginAttemptDate.toLocalDateTime());

    return loginAttempt;
  }
}
