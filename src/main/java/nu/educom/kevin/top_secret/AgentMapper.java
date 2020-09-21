package nu.educom.kevin.top_secret;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class AgentMapper implements DataMapper<Agent>{
  @Override
  public Agent mapRow(ResultSet row) throws SQLException {
    Agent agent = new Agent();
    Timestamp licenceExpirationDate = row.getTimestamp("license_expiration_date");

    agent.setId(row.getLong("agent_id"));
    agent.setServiceNumber(row.getShort("service_number"));
    agent.setCodePhrase(row.getString("code_phrase"));
    agent.setActive(row.getBoolean("active"));
    agent.setLicenceToKill(row.getBoolean("license_to_kill"));

    if (licenceExpirationDate != null)
      agent.setLicenceExpirationDate(licenceExpirationDate.toLocalDateTime());

    return agent;
  }
}
