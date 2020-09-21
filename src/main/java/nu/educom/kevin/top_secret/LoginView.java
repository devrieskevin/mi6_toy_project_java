package nu.educom.kevin.top_secret;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class LoginView extends BasicView {
  private UserModel model;
  private Map<String,JTextField> inputMap;

  LoginView(UserModel model) {
    super();

    this.model = model;
    frame = new JFrame("Agent Login");
    rootPanel = null;

    inputMap = new HashMap<>();

    setWidth(350);
    setHeight(150);
  }

  @Override
  public Map<String,String> getTextData() {
    Map<String,String> dataMap = new HashMap<>();
    for (String key : inputMap.keySet()) {
      dataMap.put(key,inputMap.get(key).getText());
    }
    return dataMap;
  }

  @Override
  public void show() {
    createRootPanel();
    if (model.getMessage().equals("")) {
      showMainContent();
    }
    else if (model.isValid()) {
      showMessage("main");
    }
    else {
      showMessage("confirm");
    }
    showFrame();
  }

  private void showMainContent() {
    rootPanel.setLayout(new GridLayout(2,1));
    createInputFields();
    addButton("Login", "login");
  }

  private void showMessage(String command) {
    createRootPanel();
    rootPanel.setLayout(new GridLayout(2,1));
    addMessage(model.getMessage());
    addButton("Ok", command);
    showFrame();
  }

  private void createInputFields() {
    JPanel panel = new JPanel();
    panel.setLayout(new GridLayout(2,2));

    addTextField(panel,"number","Number: ");
    addTextField(panel,"codePhrase","Code Phrase: ");

    rootPanel.add(panel);
  }

  private void addTextField(JPanel panel, String name, String label) {
    JLabel fieldlabel = new JLabel(label);
    JTextField textField = new JTextField();

    textField.setColumns(20);

    // Store input field for data retrieval
    inputMap.put(name, textField);

    panel.add(fieldlabel);
    panel.add(textField);
  }

  private void addButton(String label, String command) {
    JPanel panel = new JPanel();

    JButton button = new JButton(label);
    button.addActionListener(actionListener);
    button.setActionCommand(command);

    panel.add(button);

    rootPanel.add(panel);
  }

  private void addMessage(String message){
    JPanel panel = new JPanel();
    JLabel messageLabel = new JLabel(message);

    panel.add(messageLabel);

    rootPanel.add(panel);
  }

}
