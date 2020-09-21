package nu.educom.kevin.top_secret;

import java.sql.SQLException;
import java.util.List;

public interface IUserCrud {
  /**
   * Fetches an Agent associated with a given service number from the agents table
   * in the database
   *
   * @param serviceNumber An Agent service number
   *
   * @return The Agent associated with the given service number
   *
   * @throws SQLException when the SQL query fails somehow
   * */
  Agent getAgentByServiceNumber(short serviceNumber) throws SQLException;

  /**
   * Stores a login attempt in the login_attempts table in the database
   *
   * @param serviceNumber An Agent service number
   * @param success       A boolean indicating if a login attempt was successful
   *
   * @return The id number of the stored login_attempt entry
   *
   * @throws SQLException when the SQL query fails somehow
   * */
  long storeLoginAttempt(short serviceNumber, boolean success) throws SQLException;

  /**
   * Fetches the last successful login attempt associated with the given service number from
   * the login_attempts table in the database
   *
   * @param serviceNumber An Agent service number
   *
   * @return The last successful LoginAttempt associated with the given service number
   *
   * @throws SQLException when the SQL query fails somehow
   * */
  LoginAttempt getLastSuccessfulLoginAttempt(short serviceNumber) throws SQLException;

  /**
   * Fetches a list of LoginAttempt objects associated with all failed login attempts after the
   * last successful login attempt from the login_attempts table in the database
   *
   * @param serviceNumber An Agent service number
   *
   * @return A list of LoginAttempt objects associated with all failed login attempts after the
   *         last successful login attempt
   *
   * @throws SQLException when the SQL query fails somehow
   * */
  List<LoginAttempt> getLastLoginAttempts(short serviceNumber) throws SQLException;
}
