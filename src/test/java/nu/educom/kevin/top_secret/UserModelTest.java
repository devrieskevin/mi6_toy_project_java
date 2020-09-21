package nu.educom.kevin.top_secret;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class UserModelTest {

  private static Agent buildAgent(String number, String codePhrase, boolean active) {
    Agent agent = new Agent();

    agent.setServiceNumber(Short.parseShort(number));
    agent.setCodePhrase(codePhrase);
    agent.setActive(active);

    return agent;
  }

  private static LoginAttempt buildLoginAttempt(LocalDateTime dateTime) {
    LoginAttempt loginAttempt = new LoginAttempt();

    loginAttempt.setLoginAttemptDate(dateTime);

    return loginAttempt;
  }

  private static Stream<Arguments> agentLoginAttemptListAndValidProvider() {
    Supplier<Agent> activeValidAgent, activeInvalidAgent, inactiveAgent;
    LocalDateTime now = LocalDateTime.now();
    List<LoginAttempt> waiting, doneWaiting, waitingTwoMinutes;

    activeValidAgent = () -> buildAgent("007", "correct", true);
    activeInvalidAgent = () -> buildAgent("007", "incorrect", true);
    inactiveAgent = () -> buildAgent("777", "correct", false);

    waiting = List.of(buildLoginAttempt(now));
    doneWaiting = List.of(buildLoginAttempt(now.minusMinutes(1)));
    waitingTwoMinutes = List.of(buildLoginAttempt(now.minusMinutes(1)),
                                buildLoginAttempt(now.minusMinutes(2)));

    return Stream.of(
            Arguments.of(activeValidAgent.get(), new ArrayList<>(), true),
            Arguments.of(activeValidAgent.get(), new ArrayList<>(waiting), false),
            Arguments.of(activeValidAgent.get(), new ArrayList<>(doneWaiting), true),
            Arguments.of(activeValidAgent.get(), new ArrayList<>(waitingTwoMinutes), false),
            Arguments.of(activeInvalidAgent.get(), new ArrayList<>(), false),
            Arguments.of(inactiveAgent.get(), new ArrayList<>(), false),
            Arguments.of(inactiveAgent.get(), new ArrayList<>(doneWaiting), false)
    );
  }

  @ParameterizedTest
  @ValueSource(strings = {"0007","1234","957","0","-45",""}) // Invalid numbers
  void testInvalidNumbers(String number) {
    // Prepare
    IUserCrud userCrud = new TestUserCrud();
    UserModel model= new UserModel(userCrud);

    // Test
    model.validateServiceNumber(number);

    // Validate
    assertFalse(model.getMessage().isEmpty());

  }

  @ParameterizedTest
  @ValueSource(strings = {"1","01","001","7","956"}) // Valid numbers
  void testValidServiceNumbers(String number) {
    // Prepare
    IUserCrud userCrud = new TestUserCrud();
    UserModel model = new UserModel(userCrud);

    // Test
    model.validateServiceNumber(number);

    // Validate
    assertTrue(model.getMessage().isEmpty());

  }

  @ParameterizedTest
  @MethodSource("agentLoginAttemptListAndValidProvider")
  void testAgentValidation(Agent agent, List<LoginAttempt> loginAttemptList, boolean valid) {
    // Prepare
    TestUserCrud userCrud = new TestUserCrud();
    UserModel model = new UserModel(userCrud);

    userCrud.setAgent(agent);
    userCrud.setLoginAttemptList(loginAttemptList);

    // Test
    model.validateLogin("007","correct");

    // Validate
    assertEquals(valid, model.isValid());
  }

}
