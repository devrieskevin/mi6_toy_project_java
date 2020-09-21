package nu.educom.kevin.top_secret;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

public class UserController implements ActionListener {
  private UserModel model;
  private IView view;

  UserController(IUserCrud userCrud) {
    model = new UserModel(userCrud);
  }

  public void run() {
    view = new LoginView(model);
    view.setActionListener(this);
    view.show();
  }

  private void login() {
    Map<String,String> textData = view.getTextData();

    String number = textData.getOrDefault("number","");
    String codePhrase = textData.getOrDefault("codePhrase","");

    model.validateLogin(number,codePhrase);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    String command = e.getActionCommand();
    switch (command) {
      case "login":
        login();
        view.show();
        break;
      case "main":
        view.close();
        model.resetMessage();
        view = new MainView(model);
        view.setActionListener(this);
        view.show();
        break;
      case "logout":
        view.close();
        model.resetMessage();
        view = new LoginView(model);
        view.setActionListener(this);
        view.show();
        break;
      case "exit":
        view.close();
        break;
      case "confirm":
        model.resetMessage();
        view.show();
        break;
    }
  }
}
