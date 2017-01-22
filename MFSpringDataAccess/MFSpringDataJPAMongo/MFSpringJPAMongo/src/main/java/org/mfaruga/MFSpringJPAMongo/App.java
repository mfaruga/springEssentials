package org.mfaruga.MFSpringJPAMongo;

import java.util.Date;

import org.joda.time.DateTime;
import org.mfaruga.MFSpringJPAMongo.DAO.Task;
import org.mfaruga.MFSpringJPAMongo.DAO.User;
import org.mfaruga.MFSpringJPAMongo.repository.TaskRepository;
import org.mfaruga.MFSpringJPAMongo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.mongodb.core.MongoTemplate;

/**
 * Hello world!
 *
 */
public class App {

//	@Autowired
//	private UserRepository repository;
	
	public static void main(String[] args) {
		System.out.println("Hello World!");
		App app = new App();
		app.doIt();
	}

	public void doIt() {
		ApplicationContext context = new ClassPathXmlApplicationContext(new String[] { "META-INF/application-context.xml" });

		// TODO there is SpringDataWeb that allows to automatically link data to web actions - try this someday @EnableSpringDataWebSupport
		MongoTemplate template = (MongoTemplate)context.getBean("mongoTemplate");
		UserRepository userRepository = (UserRepository)context.getBean("userRepository");
		TaskRepository taskRepository = (TaskRepository)context.getBean("taskRepository");
		
		System.out.println("Template is: " + template);
		System.out.println("User repository is: " + userRepository);		
		System.out.println("Task repository is: " + taskRepository);
		
		userRepository.deleteAll();
		User user = userRepository.save(new User("faro", "michal", "abc", new Date(1981, 5, 16), null));
				
		taskRepository.deleteAll();
		Task task = new Task("SAMPLE", 1, "OPEN", user, new Date(), user, null, "no comments");
		taskRepository.save(task);

		for (User u : userRepository.findAll()) {
			System.out.println(u.getId() + " " + u.getName());
			String lastModifiedBy = u.getLastModifiedBy();
			DateTime lastModifiedDate = u.getLastModifiedDate();
			System.out.println("Last modified by: " + lastModifiedBy + " at: " + lastModifiedDate);
			
		}

		for (Task t : taskRepository.findAll()){
			System.out.println("Task: " + t.getId() + " " + t.getName() + " assigned to: " + task.getAsignee().getId() + " " + task.getAsignee().getName());			
		}

		for (Task t : taskRepository.findByAsigneeUserName("faro")) {
			System.out.println("Task: " + t.getId() + " " + t.getName() + " assigned to: " + task.getAsignee().getId() + " " + task.getAsignee().getName());
		}

		System.out.println("High priority tasks");
		for (Task t : taskRepository.findHighPriorityTasks()) {
			System.out.println("Task: " + t.getId() + " " + t.getName() + " assigned to: " + task.getAsignee().getId() + " " + task.getAsignee().getName());
		}

		System.out.println("By assignee login");
		for (Task t : taskRepository.findByDedicatedUserLogin("faro")) {
			System.out.println("Task: " + t.getId() + " " + t.getName() + " assigned to: " + task.getAsignee().getId() + " " + task.getAsignee().getName());			
		}		
		
		System.out.println("After data source!");
		((ConfigurableApplicationContext) context).close();
			
	}
}
