package nu.educom.kevin.top_secret;

import java.util.List;
import java.util.function.Consumer;

public abstract class ValidationModel {
  protected String message;

  ValidationModel() {
    message = "";
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public void resetMessage() {
    setMessage("");
  }

  protected void validateInput(List<Consumer<String>> validators, String input) {
    for (Consumer<String> validator : validators) {
      if (!message.isEmpty()) {
        break;
      }

      validator.accept(input);
    }
  }

  // GENERAL VALIDATORS

  protected void validateNotEmpty(String input) {
    if (input.isEmpty()) {
      message = "No input given...";
    }
  }

  protected void validateMaxLengthOfThree(String input) {
    if (input.length() > 3) {
      message = "Invalid number format";
    }
  }

}
