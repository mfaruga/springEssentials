package org.mfaruga.MFSpeingDataJPATX;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.mfaruga.MFSpeingDataJPATX.DAO.User;
import org.mfaruga.MFSpeingDataJPATX.repository.UserRepository;
import org.mfaruga.MFSpeingDataJPATX.repository.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		System.out.println("Hello World!");
		ApplicationContext context = new ClassPathXmlApplicationContext(
				new String[] { "META-INF/application-context.xml" });
		final UserRepository userRepository = (UserRepository) context.getBean("userRepository");

		System.out.println("User repository is: " + userRepository);

		userRepository.deleteAll();

		// PlatformTransactionManager transManager =
		// (PlatformTransactionManager) context.getBean("transactionManager");
		// TransactionTemplate transTemplate = new
		// TransactionTemplate(transManager);
		//
		// try {
		// final User user1 = new User("faro", "Michal Faruga", "secret", new
		// Date(1981, 5, 16));
		// final User user2 = new User("faro", "Michal Faruga", "secret", new
		// Date(1981, 5, 16));
		// final List<User> users = new ArrayList<User>();
		// users.add(user1);
		// users.add(user2);
		//
		// transTemplate.execute(new TransactionCallbackWithoutResult() {
		//
		// @Override
		// protected void doInTransactionWithoutResult(TransactionStatus status)
		// {
		// userRepository.save(user1);
		// userRepository.save(user2);
		// }
		// });
		//
		// } catch (Exception e) {
		// e.printStackTrace();
		// }

		for (User foundUser : userRepository.findAll()) {
			System.out.println("User: " + foundUser.getName());
		}

		UserService service = (UserService) context.getBean("userService");
		try {
			service.doFewOperations();
		} catch (Exception e) {
			System.out.println("Exception");
		}
		service.printAllUsers();

		System.out.println("After data source!");
		((ConfigurableApplicationContext) context).close();

	}

}
