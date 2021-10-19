package com.panda.spring;

import com.panda.spring.service.ClientService;
import com.panda.spring.service.PersonService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Administrator
 */
@ImportResource(value = "classpath:services.xml")
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);

        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("services.xml");
        PersonService personService = context.getBean("personService", PersonService.class);
        System.out.println(personService.getPerson().toString());
//      Shutting Down the Spring IoC Container Gracefully in Non-Web Applications   add a shutdown hook for the above context...
        context.registerShutdownHook();

//        最灵活的变种与读卡器 - 代表（例如，XML 文件）相结合
/*        GenericApplicationContext genericAp = new GenericApplicationContext();
        new XmlBeanDefinitionReader(genericAp).loadBeanDefinitions("services.xml");
        genericAp.refresh();
        PersonService personService = genericAp.getBean("personService",PersonService.class);
        System.out.println(personService.getPerson().toString());*/

//        ApplicationContext context = new ClassPathXmlApplicationContext("services.xml");
//        ClientService clientService = context.getBean("clientService", ClientService.class);
//        System.out.println(clientService);


//        System.out.println(exampleBean.toString());

    }


}
