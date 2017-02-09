package login;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Application {

    public static void main(String[] args) throws Throwable {

        ApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");

    }

}
