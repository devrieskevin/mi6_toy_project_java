package nu.educom.kevin.top_secret;

import java.awt.event.ActionListener;
import java.util.Map;

public interface IView {
  /**
   * Retrieves the data from all textfields in the view as a Map
   *
   * @return A map with all text data associated with a specific keyword
   * */
  Map<String,String> getTextData();

  /**
   * Creates and shows the view to the user
   * */
  void show();

  /**
   * Sets a listener object for the view which can process event actions
   *
   * @param listener An action listener object which processes events triggered by the user,
   *                 generally a listening controller object.
   * */
  void setActionListener(ActionListener listener);

  /**
   * Closes the view object and frees up its associated resources
   * */
  void close();
}
