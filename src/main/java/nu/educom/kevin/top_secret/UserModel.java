package nu.educom.kevin.top_secret;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.function.Consumer;

public class UserModel extends ValidationModel {
  private boolean valid;
  private short serviceNumber;

  private IUserCrud userCrud;

  private Agent agent;
  private List<LoginAttempt> lastLoginAttempts;

  UserModel(IUserCrud userCrud) {
    super();

    valid = false;
    message = "";

    this.userCrud = userCrud;
  }

  public short getServiceNumber() {
    return serviceNumber;
  }

  public boolean isValid() {
    return valid;
  }

  public Agent getAgent() {
    return agent;
  }

  public List<LoginAttempt> getLastLoginAttempts() {
    return lastLoginAttempts;
  }

  public void validateLogin(String number, String codePhrase) {
    resetMessage();

    validateServiceNumber(number);
    validateAgent(codePhrase);

    valid = message.isEmpty();
    if (valid) {
      message = "Welcome, agent " + String.format("%03d", agent.getServiceNumber());
    }
  }

  public void validateServiceNumber(String number) {
    List<Consumer<String>> validators = Arrays.asList(this::validateNotEmpty,
                                                      this::validateMaxLengthOfThree,
                                                      this::validateNumberValue);

    validateInput(validators, number);
  }

  public void validateAgent(String codePhrase) {
    List<Consumer<String>> validators = Arrays.asList(this::validateAgentExists,
                                                      this::validateNotWaiting,
                                                      this::validateAuthentication);

    validateInput(validators,codePhrase);

  }

  // HELPER FUNCTIONS
  private boolean authenticateAgent(String codePhrase) {
    return agent.isActive() && codePhrase != null && codePhrase.equals(agent.getCodePhrase());
  }

  // VALIDATOR FUNCTIONS
  protected void validateNumberValue(String input) {
    short number;

    try {
      number = Short.parseShort(input);
      if (number < 1 || number > 956) {
        message = "Invalid number value";
      }
      else {
        serviceNumber = number;
      }
    }
    catch (NumberFormatException e) {
      message = "Invalid number format";
    }
  }

  protected void validateAgentExists(String input) {
    try {
      agent = userCrud.getAgentByServiceNumber(serviceNumber);
      if (agent == null)
        message = "ACCESS DENIED";
    }
    catch (SQLException e) {
      System.out.println(e.getMessage());
      message = "A technical error has occurred, please try again later";
    }
  }

  protected void validateNotWaiting(String input) {
    String waitString;
    long waitMinutes;
    LocalDateTime waitTime, curTime;

    try {
      lastLoginAttempts = userCrud.getLastLoginAttempts(serviceNumber);

      if (lastLoginAttempts.size() > 0) {
        waitMinutes = 1 << (lastLoginAttempts.size() - 1);
        waitTime = lastLoginAttempts.get(0).getLoginAttemptDate().plusMinutes(waitMinutes);
      }
      else {
        waitTime = LocalDateTime.MIN;
      }

      curTime = LocalDateTime.now();
      if (curTime.isBefore(waitTime)) {
        waitString = waitTime.toLocalTime().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        message = "ACCESS DENIED Please try again after " + waitString;
      }
    }
    catch (SQLException e) {
      System.out.println(e.getMessage());
      message = "A technical error has occurred, please try again later";
    }
  }

  protected void validateAuthentication(String codePhrase) {
    String waitString;
    long waitMinutes;
    LocalDateTime waitTime;

    try {
      if (authenticateAgent(codePhrase)) {
        agent.setCodePhrase("");
        userCrud.storeLoginAttempt(serviceNumber, true);
      }
      else {
        userCrud.storeLoginAttempt(serviceNumber, false);
        lastLoginAttempts = userCrud.getLastLoginAttempts(serviceNumber);

        waitMinutes = 1 << (lastLoginAttempts.size() - 1);

        waitTime = lastLoginAttempts.get(0).getLoginAttemptDate().plusMinutes(waitMinutes);
        waitString = waitTime.toLocalTime().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        message = "ACCESS DENIED Please try again after " + waitString;
      }
    }
    catch (SQLException e) {
      System.out.println(e.getMessage());
      message = "A technical error has occurred, please try again later";
    }
  }
}
