package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import hiber.service.UserServiceImp;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainApp {
   public static void main(String[] args) {
      AnnotationConfigApplicationContext context =
              new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      User alex = new User("Alexandr", "Lomachenko", "loma@mail.ru");
      User vasil = new User("Vasil", "Usik", "usik@mail.ru");
      User gajey = new User("Justin", "Gajey", "gajey@mail.ru");

      Car volga = new Car("Volga", 1);
      Car bmw = new Car("BMW", 4);
      Car lada = new Car("Lada", 6);

      alex.setCar(volga);
      vasil.setCar(bmw);
      gajey.setCar(lada);

      userService.add(alex);
      userService.add(vasil);
      userService.add(gajey);

      System.out.println(userService.getUserByCar("Volga", 1).getFirstName());
      userService.listUsers();




      context.close();
   }
}
