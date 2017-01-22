package org.mfaruga.MFSpringFirst;

import org.mfaruga.MFSpringFirst.interfaces.GreetingService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		// should be done from command line arguments rather than from code
		System.setProperty("spring.profiles.active", "prod");
		ApplicationContext context = new ClassPathXmlApplicationContext(
				new String[] { "META-INF/application-context.xml" });

		// TODO not sure why this is not working - this is working in
		// application-context thought ...
		System.out.println("Description property: " + context.getEnvironment().getProperty("Description"));
		// Resource resource =
		// context.getResource("classpath:META-INF/datasource.properties");

		GreetingService greeter = (GreetingService) context.getBean("Greeter");
		greeter.greet(
				"I am your first Spring bean instance, configured purely with XML metadata. I am resolved by name.");

	}
}
