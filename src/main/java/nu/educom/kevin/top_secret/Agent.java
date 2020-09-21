package nu.educom.kevin.top_secret;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Map;

public class Agent {
  private long id;
  private short serviceNumber;
  private String codePhrase;
  private boolean active;
  private boolean licenceToKill;
  private LocalDateTime licenceExpirationDate;

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

  public String getCodePhrase() {
    return codePhrase;
  }

  public void setCodePhrase(String codePhrase) {
    this.codePhrase = codePhrase;
  }

  public boolean isActive() {
    return active;
  }

  public void setActive(boolean active) {
    this.active = active;
  }

  public boolean hasLicenceToKill() {
    return licenceToKill;
  }

  public void setLicenceToKill(boolean licenceToKill) {
    this.licenceToKill = licenceToKill;
  }

  public LocalDateTime getLicenceExpirationDate() {
    return licenceExpirationDate;
  }

  public void setLicenceExpirationDate(LocalDateTime licenceExpirationDate) {
    this.licenceExpirationDate = licenceExpirationDate;
  }

  public static Agent fromMap(Map<String, Object> row) {
    Agent agent = new Agent();
    Timestamp licenceExpirationDate = (Timestamp) row.getOrDefault("license_expiration_date",null);

    agent.setId((long) row.getOrDefault("agent_id", null));
    agent.setServiceNumber((short)(int) row.getOrDefault("service_number", null));
    agent.setCodePhrase((String) row.getOrDefault("code_phrase",null));
    agent.setActive((boolean) row.getOrDefault("active",null));
    agent.setLicenceToKill((boolean) row.getOrDefault("license_to_kill",null));
    agent.setLicenceExpirationDate(licenceExpirationDate.toLocalDateTime());

    return agent;
  }


  @Override
  public String toString() {
    return "Agent{" +
            "id=" + id +
            ", serviceNumber=" + serviceNumber +
            ", codePhrase='" + codePhrase + '\'' +
            ", active=" + active +
            ", licenceToKill=" + licenceToKill +
            ", licenceExpirationDate=" + licenceExpirationDate +
            '}';
  }
}
