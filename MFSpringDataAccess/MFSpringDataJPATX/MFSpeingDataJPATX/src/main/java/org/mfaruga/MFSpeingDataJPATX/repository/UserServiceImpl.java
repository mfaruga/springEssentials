package org.mfaruga.MFSpeingDataJPATX.repository;

import java.util.Date;

import org.mfaruga.MFSpeingDataJPATX.DAO.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;	

public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	public void printAllUsers() {
		System.out.println("Print all users");
		for (User foundUser : userRepository.findAll()) {
			System.out.println("User: " + foundUser.getName());
		}
	}

		//  Depending on definition of transactions for doFewOperations - this could be triggered in single or multiple transactions
		// this is easy way to manage transactions on multiple operations
		@Transactional(propagation=Propagation.NEVER)
	public void doFewOperations() {
		User user1 = new User("faro", "Michal Faruga", "secret", new Date(1981, 5, 16));
		User user2 = new User("faro", "Michal Faruga", "secret", new Date(1981, 5, 16));

		userRepository.save(user1);
		userRepository.save(user2);
	}

}
