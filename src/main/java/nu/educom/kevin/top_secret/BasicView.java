package nu.educom.kevin.top_secret;

import javax.swing.*;
import java.awt.event.ActionListener;

public abstract class BasicView implements IView {
  protected JFrame frame;
  protected JPanel rootPanel;
  protected ActionListener actionListener;

  private int width;
  private int height;

  BasicView() {
    width = 350;
    height = 150;
  }

  public void setWidth(int width) {
    this.width = width;
  }

  public void setHeight(int height) {
    this.height = height;
  }

  @Override
  public void setActionListener(ActionListener actionListener) {
    this.actionListener = actionListener;
  }

  @Override
  public void close() {
    frame.dispose();
  }

  protected void createRootPanel() {
    if (rootPanel != null) {
      frame.remove(rootPanel);
    }
    rootPanel = new JPanel();
  }

  protected void showFrame() {
    frame.add(rootPanel);
    frame.pack();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(width, height);
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
  }
}
