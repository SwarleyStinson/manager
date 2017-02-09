package login;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Application {

    public static void main(String[] args) throws Throwable {

        ApplicationContext context = new ClassPathXmlApplicationContext("WEB-INF/config/application-context.xml");

    }

}
