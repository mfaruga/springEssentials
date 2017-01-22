package org.mfaruga.MFSpringJPAMongo.repository;

import java.util.List;

import org.mfaruga.MFSpringJPAMongo.DAO.Task;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface TaskRepository extends MongoRepository<Task, String>{

	// this is automatically generated based on method name - it traverse through assignee and in assignee go through userName ... 
	public List<Task> findByAsigneeUserName(String userName);
	
	// this is mongo-db JSON based query
	@Query("{'priority' : {$lt : 2}}")
	List<Task> findHighPriorityTasks();
	
	@Query("{asignee.name : ?0}")
	List<Task> findByDedicatedUserLogin(String login);
	
}
