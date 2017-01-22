package org.mfaruga.MFSpringJPAMongo.repository;

import java.util.List;

import org.mfaruga.MFSpringJPAMongo.DAO.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User,  String> {

	public User findById(String id);
	public List<User> findByUserName(String userName);	
	
}
