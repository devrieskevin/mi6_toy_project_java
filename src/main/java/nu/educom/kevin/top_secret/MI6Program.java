package nu.educom.kevin.top_secret;

public class MI6Program {

  public static void main(String[] args) {
    ICrud crud = new Crud();
    IUserCrud userCrud = new UserCrud(crud);
    UserController controller = new UserController(userCrud);
    controller.run();
  }

}
