package nu.educom.kevin.top_secret;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Map;

public class LoginAttempt {
  private long id;
  private short serviceNumber;
  private LocalDateTime loginAttemptDate;
  private boolean success;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public short getServiceNumber() {
    return serviceNumber;
  }

  public void setServiceNumber(short serviceNumber) {
    this.serviceNumber = serviceNumber;
  }

  public LocalDateTime getLoginAttemptDate() {
    return loginAttemptDate;
  }

  public void setLoginAttemptDate(LocalDateTime loginAttemptDate) {
    this.loginAttemptDate = loginAttemptDate;
  }

  public boolean isSuccess() {
    return success;
  }

  public void setSuccess(boolean success) {
    this.success = success;
  }

  public static LoginAttempt fromMap(Map<String, Object> row) {
    LoginAttempt loginAttempt = new LoginAttempt();
    Timestamp loginAttemptDate = (Timestamp) row.getOrDefault("login_attempt_date",null);

    loginAttempt.setId((long) row.getOrDefault("login_attempt_id",null));
    loginAttempt.setServiceNumber((short)(int) row.getOrDefault("service_number",null));
    loginAttempt.setSuccess((boolean) row.getOrDefault("success",null));
    loginAttempt.setLoginAttemptDate(loginAttemptDate.toLocalDateTime());

    return loginAttempt;
  }


  @Override
  public String toString() {
    return "LoginAttempt{" +
            "id=" + id +
            ", serviceNumber=" + serviceNumber +
            ", loginAttemptDate=" + loginAttemptDate +
            ", success=" + success +
            '}';
  }
}
