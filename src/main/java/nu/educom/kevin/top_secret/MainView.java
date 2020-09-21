package nu.educom.kevin.top_secret;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Map;

public class MainView extends BasicView {
  private UserModel model;

  MainView(UserModel model) {
    super();

    this.model = model;
    frame = new JFrame("Home");
    rootPanel = null;

    setWidth(300);
    setHeight(200);
  }

  @Override
  public Map<String,String> getTextData() {
    return null;
  }

  @Override
  public void show() {
    createMenu();
    createRootPanel();
    showMainContent();
    showFrame();
  }
  
  private void createMenu() {
    JMenuBar menuBar = new JMenuBar();

    JMenu file = new JMenu("File");

    createMenuItem(file,"Logout","logout");
    createMenuItem(file,"Exit","exit");

    menuBar.add(file);

    frame.setJMenuBar(menuBar);
  }

  private void createMenuItem(JMenu menu, String itemName, String command) {
    JMenuItem item = new JMenuItem(itemName);

    item.addActionListener(actionListener);
    item.setActionCommand(command);

    menu.add(item);
  }

  private void showMainContent() {
    rootPanel.setLayout(new BoxLayout(rootPanel, BoxLayout.PAGE_AXIS));
    showLicenceToKill();
    showLastLoginAttempts();
  }

  private void showLicenceToKill() {
    JLabel label;
    Agent agent = model.getAgent();

    String labelText = "Licence to kill: ";

    if(agent.hasLicenceToKill()) {
      labelText += "EXPIRES ON " + agent.getLicenceExpirationDate()
                                        .format(DateTimeFormatter.ofPattern("YYYY-MM-dd HH:mm:ss"));
    }
    else {
      labelText += "NO LICENCE CURRENTLY ACTIVE";
    }

    label = new JLabel(labelText);
    label.setAlignmentX(Component.CENTER_ALIGNMENT);

    rootPanel.add(label);
  }

  private void showLastLoginAttempts() {
    JLabel label = new JLabel("Previous login attempts:");

    List<String> dateTimes = new ArrayList<>();
    for (LoginAttempt loginAttempt: model.getLastLoginAttempts()) {
      dateTimes.add(loginAttempt.getLoginAttemptDate().format(DateTimeFormatter.ofPattern("YYYY-MM-dd HH:mm:ss")));
    }

    JList<Object> loginAttemptList = new JList<>(dateTimes.toArray());
    loginAttemptList.setVisibleRowCount(-1);

    label.setAlignmentX(Component.CENTER_ALIGNMENT);
    loginAttemptList.setAlignmentX(Component.CENTER_ALIGNMENT);

    JScrollPane listScroller = new JScrollPane();
    listScroller.setViewportView(loginAttemptList);

    rootPanel.add(label);
    rootPanel.add(listScroller);
  }

}
