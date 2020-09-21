package nu.educom.kevin.top_secret;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

public class TestUserCrud implements IUserCrud {

  private Agent agent;
  private LoginAttempt loginAttempt;
  private List<LoginAttempt> loginAttemptList;

  public void setAgent(Agent agent) {
    this.agent = agent;
  }

  public void setLoginAttempt(LoginAttempt loginAttempt) {
    this.loginAttempt = loginAttempt;
  }

  public void setLoginAttemptList(List<LoginAttempt> loginAttemptList) {
    this.loginAttemptList = loginAttemptList;
  }

  @Override
  public Agent getAgentByServiceNumber(short serviceNumber) throws SQLException {
    return agent;
  }

  @Override
  public long storeLoginAttempt(short serviceNumber, boolean success) throws SQLException {
    LoginAttempt loginAttempt = new LoginAttempt();

    loginAttempt.setLoginAttemptDate(LocalDateTime.now());
    loginAttempt.setServiceNumber(serviceNumber);
    loginAttempt.setSuccess(success);

    loginAttemptList.add(0, loginAttempt);

    return 0;
  }

  @Override
  public LoginAttempt getLastSuccessfulLoginAttempt(short serviceNumber) throws SQLException {
    return loginAttempt;
  }

  @Override
  public List<LoginAttempt> getLastLoginAttempts(short serviceNumber) throws SQLException {
    return loginAttemptList;
  }
}
